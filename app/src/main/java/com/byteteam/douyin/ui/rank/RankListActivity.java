package com.byteteam.douyin.ui.rank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ActivityRankListBinding;
import com.byteteam.douyin.logic.database.model.RankList;
import com.byteteam.douyin.logic.network.exception.ErrorConsumer;
import com.byteteam.douyin.logic.network.exception.NetException;
import com.byteteam.douyin.ui.BaseActivity;
import com.byteteam.douyin.ui.ViewModelFactory;
import com.byteteam.douyin.ui.rank.adapter.ExtendAdapter;
import com.byteteam.douyin.ui.rank.adapter.RankListAdapter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @introduction： 历史榜单Activity类
 * @author： 林锦焜
 * @time： 2022/8/14 15:30
 */
public class RankListActivity extends BaseActivity {

    private ActivityRankListBinding binding;

    private RankListViewModel vm;

    private CompositeDisposable compositeDisposable;

    private RankListAdapter rankListAdapter;

    private ExtendAdapter adapter;

    private int rankType = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取榜单类型，默认为1
        if (getIntent() != null) {
            rankType = getIntent().getIntExtra("type",1);
        }
        binding = ActivityRankListBinding.inflate(getLayoutInflater());
        vm = new ViewModelProvider(this, ViewModelFactory.provide(this)).get(RankListViewModel.class);
        setContentView(binding.getRoot());
        // 往顶部标题栏增加返回按钮
        addBackButton(binding.toolbar);
        binding.swipeRefresh.setOnRefreshListener(this::loadList);
        // 设置列表相关属性
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setHasFixedSize(true);
        binding.swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.purple_500));
        binding.recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (recyclerView.getLayoutManager() != null) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    //屏幕中最后一个可见子项的position
                    int lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                    //当前屏幕所看到的子项个数
                    int visibleItemCount = layoutManager.getChildCount();
                    //当前RecyclerView的所有子项个数
                    int totalItemCount = layoutManager.getItemCount();
                    //RecyclerView的滑动状态
                    int state = recyclerView.getScrollState();
                    if (!scrollInLast && visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == RecyclerView.SCROLL_STATE_IDLE) {
                        scrollInLast = true;
                        if (hasMore) {
                            loadList();
                            if (adapter != null) {
                                adapter.changeFooter(1);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"没有更多了",Toast.LENGTH_SHORT).show();
                            adapter.changeFooter(0);
                        }
                    }
                }
            }
        });
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.swipeRefresh.setRefreshing(true);
        loadList();
    }

    private long cursor;

    private boolean hasMore;

    private boolean scrollInLast;

    private void loadList() {
        // 请求获取榜单列表
        compositeDisposable.add(vm.getRankList(rankType,cursor)
                .subscribe(rankVersion -> {
                    List<RankList> rankItems = rankVersion.getList();
                    if (rankItems.size() > 0) {
                        if (adapter == null) {
                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        }
                        if (cursor == 0) {// adapter
                            rankListAdapter = new RankListAdapter(rankItems, rankList -> {
                                Intent intent = new Intent();
                                intent.putExtra("version",rankList.getVersion());
                                setResult(0,intent);
                                finish();
                            });
                            rankListAdapter.setHasStableIds(true);
                            adapter = new ExtendAdapter(rankListAdapter, true);
                            binding.recyclerView.setAdapter(adapter.getAdapter());
                        } else {
                            int p = rankListAdapter.getItemCount();
                            rankListAdapter.addDate(rankItems);
                            rankListAdapter.notifyItemRangeInserted(p,rankItems.size());
                        }
                    }
                    binding.swipeRefresh.setRefreshing(false);
                    binding.msgText.setVisibility(View.GONE);
                    hasMore = rankVersion.isHasMore();
                    if (hasMore || cursor == 0) {
                        scrollInLast = false;
                    }
                    cursor = rankVersion.getCursor();
                }, new ErrorConsumer() {
                    @Override
                    protected void error(NetException e) {
                        Toast.makeText(getApplicationContext(), e.getMsg(), Toast.LENGTH_SHORT).show();
                        binding.swipeRefresh.setRefreshing(false);
                        binding.msgText.setVisibility(View.VISIBLE);
                        binding.msgText.setText(e.getMsg() + "，请下拉刷新");
                    }
                }));
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

}
