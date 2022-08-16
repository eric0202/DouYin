package com.byteteam.douyin.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.byteteam.douyin.databinding.ActivityMainBinding;
import com.byteteam.douyin.ui.main.adapter.MainLayoutAdapter;
import com.byteteam.douyin.ui.main.fragment.HomeFragment;
import com.byteteam.douyin.ui.main.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * @introduction： 主界面Activity
 * @author： 林锦焜
 * @time： 2022/8/7 18:10
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MainLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter == null) {
            List<Fragment> list = new ArrayList<>();
            list.add(HomeFragment.newInstance());
            list.add(MineFragment.newInstance());
            adapter = new MainLayoutAdapter(list, getSupportFragmentManager(), getLifecycle());
            binding.viewPager2.setAdapter(adapter);
        }
    }
}