package com.byteteam.douyin.ui.rank;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ActivityRankBinding;
import com.byteteam.douyin.ui.BaseActivity;
import com.byteteam.douyin.ui.main.adapter.MainLayoutAdapter;
import com.byteteam.douyin.ui.rank.adapter.ViewPagerAdapter;
import com.byteteam.douyin.ui.rank.fragment.RankFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @introduction： 榜单Activity类
 * @author： 林锦焜
 * @time： 2022/8/10 20:25
 */
public class RankActivity extends BaseActivity {

    ActivityRankBinding binding;


    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRankBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addBackButton(binding.toolbar);
        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter == null) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("电影"));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("电视剧"));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("综艺"));
            List<Fragment> list = new ArrayList<>();
            list.add(RankFragment.newInstance(1));
            list.add(RankFragment.newInstance(2));
            list.add(RankFragment.newInstance(3));
            adapter = new ViewPagerAdapter(list,getSupportFragmentManager(),getLifecycle());
            binding.viewPager2.setAdapter(adapter);
        }
    }

}
