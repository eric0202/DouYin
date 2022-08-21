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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.databinding.FragmentMyFansBinding;
import com.byteteam.douyin.databinding.FragmentWorksBinding;
import com.byteteam.douyin.douyinapi.ApiUtil;
import com.byteteam.douyin.logic.database.model.MyFans;
import com.byteteam.douyin.logic.database.model.Works;
import com.byteteam.douyin.logic.network.exception.ErrorConsumer;
import com.byteteam.douyin.logic.network.exception.NetException;
import com.byteteam.douyin.logic.network.model.MyFansData;
import com.byteteam.douyin.ui.ViewModelFactory;
import com.byteteam.douyin.ui.custom.adapter.ExtendAdapter;
import com.byteteam.douyin.ui.main.adapter.MyFansAdapter;
import com.byteteam.douyin.ui.main.adapter.MyFansHeaderAdapter;
import com.byteteam.douyin.ui.main.adapter.WorksAdapter;
import com.byteteam.douyin.ui.main.viewmodel.MyFansViewModel;
import com.byteteam.douyin.ui.main.viewmodel.WorksViewModel;
import com.byteteam.douyin.ui.video.VideoActivity;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @introduction：
 * @author： 林锦焜
 * @time： 2022/8/18 22:53
 */
public class MyFanFragment extends Fragment {

    public static MyFanFragment newInstance() {
        return new MyFanFragment();
    }

    private MyFansViewModel vm;

    FragmentMyFansBinding binding;

    private CompositeDisposable disposable;

    private MyFansHeaderAdapter myFansHeaderAdapter;

    private MyFansAdapter myFansAdapter;

    private ExtendAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMyFansBinding.inflate(getLayoutInflater(), container, false);
        vm = new ViewModelProvider(this, ViewModelFactory.provide(requireActivity())).get(MyFansViewModel.class);
        disposable = new CompositeDisposable();
        // 设置列表相关属性
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setHasFixedSize(true);
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
                    disposable.add(vm.queryMyFans(accessToken, cursor)
                            .subscribe(myFansData -> {
                                binding.loading.setVisibility(View.GONE);
                                List<MyFans> myFans = myFansData.getList();
                                if (myFans.size() > 0) {
                                    if (adapter == null) {
                                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                                    }
                                    if (cursor == 0) { // adapter
                                        myFansAdapter = new MyFansAdapter(myFans);
                                        myFansAdapter.setHasStableIds(true);
                                        myFansHeaderAdapter = new MyFansHeaderAdapter();
                                        adapter = new ExtendAdapter(myFansHeaderAdapter, myFansAdapter, true);
                                        binding.recyclerView.setAdapter(adapter.getAdapter());
                                    } else {
                                        int p = myFansAdapter.getItemCount();
                                        myFansAdapter.addDate(myFans);
                                        myFansAdapter.notifyItemRangeInserted(p,myFans.size());
                                    }
                                } else if (adapter == null) {
                                    binding.msgText.setVisibility(View.VISIBLE);
                                    binding.msgText.setText("没有更多了");
                                }
                                myFansHeaderAdapter.setFansCount(myFansData.getTotal());
                                binding.msgText.setVisibility(View.GONE);
                                hasMore = myFansData.isHasMore();
                                if (!hasMore || cursor == 0) {
                                    scrollInLast = false;
                                }
                                cursor = Math.toIntExact(hasMore ? myFansData.getCursor() : cursor);
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
