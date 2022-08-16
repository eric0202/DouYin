package com.byteteam.douyin.ui.rank.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import com.byteteam.douyin.databinding.FragmentRankBinding;
import com.byteteam.douyin.logic.network.exception.ErrorConsumer;
import com.byteteam.douyin.logic.network.exception.NetException;
import com.byteteam.douyin.ui.ViewModelFactory;
import com.byteteam.douyin.ui.custom.adapter.ExtendAdapter;
import com.byteteam.douyin.ui.rank.RankListActivity;
import com.byteteam.douyin.ui.rank.adapter.RankAdapter;
import com.byteteam.douyin.ui.rank.adapter.RankHeaderAdapter;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @introduction： 电影榜单Fragment
 * @author： 林锦焜
 * @time： 2022/8/10 20:25
 */
public class RankFragment extends Fragment {


    public static RankFragment newInstance(int type) {
        return new RankFragment(type);
    }

    // 榜单类型
    private final int type;
    // 榜单版本 0 最新
    private int version;

    public RankFragment(int type) {
        this.type = type;
    }


    private RankViewModel vm;

    private FragmentRankBinding binding;

    // Disposable管理器
    private CompositeDisposable compositeDisposable;

    // 列表适配器
    private ExtendAdapter extendAdapter;

    // 榜单数据适配器
    private RankAdapter adapter;

    private final String[] titles = {"热门电影榜单","热门电视剧榜单","热门综艺榜单"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // 实例化ViewModel
        vm = new ViewModelProvider(this, ViewModelFactory.provide(requireContext())).get(RankViewModel.class);
        compositeDisposable = new CompositeDisposable();
        binding = FragmentRankBinding.inflate(getLayoutInflater(), container, false);
        binding.swipeRefresh.setOnRefreshListener(this::loadList);
        // 设置列表相关属性
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setItemViewCacheSize(15);
        binding.swipeRefresh.setColorSchemeColors(requireContext().getResources().getColor(R.color.purple_500));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 更新标题栏标题
        requireActivity().setTitle(titles[type - 1]);
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
                        // 设置列表头部
                        RankHeaderAdapter rankHeaderAdapter = new RankHeaderAdapter(() -> {
                            Intent intent = new Intent(requireActivity(), RankListActivity.class);
                            intent.putExtra("type",type);
                            requireActivity().startActivityFromFragment(RankFragment.this,intent,0);
                        });
                        extendAdapter = new ExtendAdapter(rankHeaderAdapter, adapter, true);
                        binding.recyclerView.setAdapter(extendAdapter.getAdapter());
                    }
                    binding.recyclerView.setVisibility(View.VISIBLE);
                    binding.swipeRefresh.setRefreshing(false);
                    binding.msgText.setVisibility(View.GONE);
                }, new ErrorConsumer() {
                    @Override
                    protected void error(NetException e) {
                        if (adapter != null) {
                            int count = adapter.getItemCount();
                            adapter.setDates(new ArrayList<>());
                            adapter.notifyItemRangeRemoved(0,count);
                        }
                        binding.recyclerView.setVisibility(View.INVISIBLE);
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
        // 清理订阅
        compositeDisposable.clear();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 && data != null) {
            version = data.getIntExtra("version",0);
            loadList();
        }
    }
}