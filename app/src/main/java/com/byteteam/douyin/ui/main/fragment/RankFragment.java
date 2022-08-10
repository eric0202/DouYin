package com.byteteam.douyin.ui.main.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.byteteam.douyin.databinding.FragmentRankBinding;
import com.byteteam.douyin.ui.rank.RankActivity;

/**
 * @introduction： 榜单页面Fragment
 * @author： 林锦焜
 * @time： 2022/8/7 18:10
 */
public class RankFragment extends Fragment {

    private FragmentRankBinding binding;

    public static RankFragment newInstance() {
        return new RankFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRankBinding.inflate(getLayoutInflater(),container,false);
        // 设置点击跳转电影榜单页面
        binding.filmCardView.setOnClickListener((v) ->{
            Intent intent = new Intent(requireContext(), RankActivity.class);
            requireContext().startActivity(intent);
        });
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}