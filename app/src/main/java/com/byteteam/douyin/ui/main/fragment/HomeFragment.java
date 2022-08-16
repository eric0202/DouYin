package com.byteteam.douyin.ui.main.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byteteam.douyin.databinding.FragmentHomeBinding;
import com.byteteam.douyin.databinding.FragmentRankBinding;
import com.byteteam.douyin.ui.rank.RankActivity;

/**
 * @introduction： 榜单页面Fragment
 * @author： 林锦焜
 * @time： 2022/8/7 18:10
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(),container,false);
        // 设置点击跳转电影榜单页面
        binding.filmCardView.setOnClickListener((v) ->{
            Intent intent = new Intent(requireContext(), RankActivity.class);
            intent.putExtra("type",1);
            intent.putExtra("version",0);
            requireContext().startActivity(intent);
        });
        // 设置点击跳转电视剧榜单页面
        binding.tvCardView.setOnClickListener((v) ->{
            Intent intent = new Intent(requireContext(), RankActivity.class);
            intent.putExtra("type",2);
            intent.putExtra("version",0);
            requireContext().startActivity(intent);
        });

        // 设置点击跳转综艺榜单页面
        binding.varietyCardView.setOnClickListener((v) ->{
            Intent intent = new Intent(requireContext(), RankActivity.class);
            intent.putExtra("type",3);
            intent.putExtra("version",0);
            requireContext().startActivity(intent);
        });
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}