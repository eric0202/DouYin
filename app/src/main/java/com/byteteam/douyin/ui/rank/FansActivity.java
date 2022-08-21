package com.byteteam.douyin.ui.rank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ActivityFansBinding;
import com.byteteam.douyin.ui.rank.adapter.ViewPagerAdapter;
import com.byteteam.douyin.ui.rank.fragment.FansFragment;
import com.byteteam.douyin.ui.rank.fragment.RankFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @introduction： 关注列表主页面
 * @author： 陈光磊
 * @time： 2022/8/17 21:10
 */

public class FansActivity extends AppCompatActivity {
    ActivityFansBinding binding;

    private ViewPagerAdapter adapter;

    private String[] titles = new String[]{"关注","粉丝"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFansBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fansViewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.fansTablayout.selectTab(binding.fansTablayout.getTabAt(position));
            }
        });
        binding.fansTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            public void onTabSelected(TabLayout.Tab tab) {
                binding.fansViewpager2.setCurrentItem(tab.getPosition());
            }


            public void onTabUnselected(TabLayout.Tab tab) {

            }


            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (adapter == null) {
            binding.fansTablayout.addTab(binding.fansTablayout.newTab().setText("关注"));
            binding.fansTablayout.addTab(binding.fansTablayout.newTab().setText("粉丝"));
            List<Fragment> list = new ArrayList<>();
//            list.add(FansFragment.newInstance());
//            list.add(FansFragment.newInstance());
            adapter = new ViewPagerAdapter(list,getSupportFragmentManager(),getLifecycle());
            binding.fansViewpager2.setAdapter(adapter);
        }
    }
}