package com.byteteam.douyin.ui.rank.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.FragmentMovieBinding;
import com.byteteam.douyin.logic.network.exception.ErrorConsumer;
import com.byteteam.douyin.logic.network.exception.NetException;
import com.byteteam.douyin.ui.ViewModelFactory;
import com.byteteam.douyin.ui.rank.adapter.RankAdapter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @introduction： 电影榜单Fragment
 * @author： 林锦焜
 * @time： 2022/8/10 20:25
 */
public class RankFragment extends Fragment {


    public static RankFragment newInstance(int type,int version) {
        return new RankFragment(type, version);
    }

    // 榜单类型
    private final int type;
    // 榜单版本 0 最新
    private final int version;

    public RankFragment(int type,int version) {
        this.type = type;
        this.version = version;
    }


    private RankViewModel vm;

    private FragmentMovieBinding binding;

    private CompositeDisposable compositeDisposable;

    private RankAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this, ViewModelFactory.provide(requireContext())).get(RankViewModel.class);
        compositeDisposable = new CompositeDisposable();
        binding = FragmentMovieBinding.inflate(getLayoutInflater(), container, false);
        binding.swipeRefresh.setOnRefreshListener(this::loadList);
        // 设置列表相关属性
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setItemViewCacheSize(15);
        binding.swipeRefresh.setColorSchemeColors(requireContext().getResources().getColor(R.color.purple_500));
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.swipeRefresh.setRefreshing(true);
        loadList();

    }

    private void loadList() {
        // 请求获取榜单列表
        compositeDisposable.add(vm.getRankList(type,version)
                .subscribe(rankItems -> {
                    if (rankItems.size() > 0) {
                        if (adapter == null) {
                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                        }
                        adapter = new RankAdapter(rankItems,type);
                        adapter.setHasStableIds(true);
                        binding.recyclerView.setAdapter(adapter);
                    }
                    binding.swipeRefresh.setRefreshing(false);
                    binding.msgText.setVisibility(View.GONE);
                }, new ErrorConsumer() {
                    @Override
                    protected void error(NetException e) {
                        Toast.makeText(requireContext(), e.getMsg(), Toast.LENGTH_SHORT).show();
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