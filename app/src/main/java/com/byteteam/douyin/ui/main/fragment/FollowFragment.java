package com.byteteam.douyin.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.databinding.FragmentFollowBinding;
import com.byteteam.douyin.logic.database.model.FollowItem;
import com.byteteam.douyin.logic.network.exception.ErrorConsumer;
import com.byteteam.douyin.logic.network.exception.NetException;
import com.byteteam.douyin.ui.ViewModelFactory;
import com.byteteam.douyin.ui.custom.adapter.ExtendAdapter;
import com.byteteam.douyin.ui.main.adapter.FollowAdapter;
import com.byteteam.douyin.ui.main.viewmodel.FollowViewModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @introduction： fans的fragment
 * @author： 陈光磊
 * @time： 2022/8/19 21:10
 */
public class FollowFragment extends Fragment {

    public static FollowFragment newInstance() {
        return new FollowFragment();
    }

    private FollowViewModel vm;

    FragmentFollowBinding binding;

    private CompositeDisposable disposable;

    private ExtendAdapter adapter;

    private FollowAdapter followAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFollowBinding.inflate(getLayoutInflater(), container, false);
        vm = new ViewModelProvider(this, ViewModelFactory.provide(requireActivity())).get(FollowViewModel.class);
        disposable = new CompositeDisposable();
        // 设置列表相关属性
        binding.recylerview.setItemAnimator(new DefaultItemAnimator());
        binding.recylerview.setHasFixedSize(true);
        binding.recylerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            Toast.makeText(requireContext(),"没有更多了",Toast.LENGTH_SHORT).show();
                            adapter.changeFooter(0);
                        }
                    }
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.loading.setVisibility(View.VISIBLE);
        loadList();
    }

    @Override
    public void onPause() {
        super.onPause();
        disposable.clear();
    }

    private int cursor;

    private boolean hasMore;

    private boolean scrollInLast;

    private void loadList() {
        // 请求获取个人作品列表
        disposable.add(vm.getAccessToken().doOnComplete(() -> { // 获取失败()
                    binding.loading.setVisibility(View.GONE);
                    binding.msgText.setVisibility(View.VISIBLE);
                    binding.msgText.setText("应用未授权");
                })
                .subscribe(accessToken -> {
                    disposable.add(vm.queryFans(accessToken, cursor)
                            .subscribe(FansData -> {
                                binding.loading.setVisibility(View.GONE);
                                List<FollowItem> fans = FansData.getList();
                                if (fans.size() > 0) {
                                    if (adapter == null) {
                                        binding.recylerview.setLayoutManager(new LinearLayoutManager(requireContext()));
                                    }
                                    if (cursor == 0) { // adapter
                                        followAdapter = new FollowAdapter(fans);
                                        followAdapter.setHasStableIds(true);
                                        adapter = new ExtendAdapter(followAdapter, true);
                                        binding.recylerview.setAdapter(adapter.getAdapter());
                                    } else {
                                        int p = followAdapter.getItemCount();
                                        followAdapter.addDate(fans);
                                        followAdapter.notifyItemRangeInserted(p,fans.size());
                                    }
                                } else if (adapter == null) {
                                    binding.msgText.setVisibility(View.VISIBLE);
                                    binding.msgText.setText("没有更多了");
                                }
                                binding.msgText.setVisibility(View.GONE);
                                hasMore = FansData.isHasMore();
                                if (!hasMore || cursor == 0) {
                                    scrollInLast = false;
                                }
                                cursor = Math.toIntExact(hasMore ? FansData.getCursor() : cursor);
                            }, new ErrorConsumer() {
                                @Override
                                protected void error(NetException e) {
                                    binding.loading.setVisibility(View.GONE);
                                    Toast.makeText(requireContext(),"获取个人作品失败：" + e.getMsg(), Toast.LENGTH_SHORT).show();
                                    binding.msgText.setVisibility(View.VISIBLE);
                                    binding.msgText.setText(e.getMsg());
                                }
                            }));
                }));
    }

}