package com.byteteam.douyin.ui.rank.fragment;

import android.content.Intent;
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

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.FragmentFansBinding;
import com.byteteam.douyin.logic.database.model.FansItem;
import com.byteteam.douyin.logic.network.exception.ErrorConsumer;
import com.byteteam.douyin.logic.network.exception.NetException;
import com.byteteam.douyin.ui.ViewModelFactory;
import com.byteteam.douyin.ui.custom.adapter.ExtendAdapter;
import com.byteteam.douyin.ui.rank.FansActivity;
import com.byteteam.douyin.ui.rank.FansListActivity;
import com.byteteam.douyin.ui.rank.RankListActivity;
import com.byteteam.douyin.ui.rank.adapter.FansAdapter;
import com.byteteam.douyin.ui.rank.adapter.RankHeaderAdapter;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @introduction： fans的fragment
 * @author： 陈光磊
 * @time： 2022/8/19 21:10
 */
public class FansFragment extends Fragment {

    public static FansFragment newInstance(int type) {
        return new FansFragment(type);
    }

    // 榜单类型
    private final int type;

    private FansViewModel vm;

    private FragmentFansBinding binding;

    public FansFragment(int type) {
        this.type = type;
    }

    // Disposable管理器
    private CompositeDisposable compositeDisposable;

    // 列表适配器
    private ExtendAdapter extendAdapter;

    private FansAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // 实例化ViewModel
        vm = new ViewModelProvider(this, ViewModelFactory.provide(requireContext())).get(FansViewModel.class);
        compositeDisposable = new CompositeDisposable();
        binding = FragmentFansBinding.inflate(getLayoutInflater(), container, false);
        binding.fansSwipefresh.setOnRefreshListener(this::loadList);
        // 设置列表相关属性
        binding.fansRecylerview.setItemAnimator(new DefaultItemAnimator());
        binding.fansRecylerview.setHasFixedSize(true);
        binding.fansRecylerview.setItemViewCacheSize(15);
        binding.fansSwipefresh.setColorSchemeColors(requireContext().getResources().getColor(R.color.purple_500));
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.fansSwipefresh.setRefreshing(true);
        loadList();
    }

    private void loadList() {
        // 请求获取榜单列表
        compositeDisposable.add(vm.getFansList(type)
                .subscribe(fansItem -> {
                    if (fansItem.size() > 0) {
                        if (adapter == null) {
                            binding.fansRecylerview.setLayoutManager(new LinearLayoutManager(requireContext()));
                        }
                        adapter = new FansAdapter(fansItem,type);
                        adapter.setHasStableIds(true);
                        // 设置列表头部
//                        RankHeaderAdapter rankHeaderAdapter = new RankHeaderAdapter(() -> {
//                            Intent intent = new Intent(requireActivity(), RankListActivity.class);
//                            intent.putExtra("type",type);
//                            requireActivity().startActivityFromFragment(RankListActivity.this,intent,0);
//                        });
//                        extendAdapter = new ExtendAdapter(rankHeaderAdapter, adapter, true);
//                        binding.fansRecylerview.setAdapter(extendAdapter.getAdapter());
                    }
                    binding.fansRecylerview.setVisibility(View.VISIBLE);
                    binding.fansSwipefresh.setRefreshing(false);
                    binding.fansMsgText.setVisibility(View.GONE);
                }, new ErrorConsumer() {
                    @Override
                    protected void error(NetException e) {
                        if (adapter != null) {
                            int count = adapter.getItemCount();
                            adapter.setDates(new ArrayList<>());
                            adapter.notifyItemRangeRemoved(0,count);
                        }
                        binding.fansRecylerview.setVisibility(View.INVISIBLE);
                        Toast.makeText(requireContext(), e.getMsg(), Toast.LENGTH_SHORT).show();
                        binding.fansSwipefresh.setRefreshing(false);
                        binding.fansMsgText.setVisibility(View.VISIBLE);
                        binding.fansMsgText.setText(e.getMsg() + "，请下拉刷新");
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
            loadList();
        }
    }

}