package com.byteteam.douyin.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ActivityMainBinding;
import com.byteteam.douyin.ui.main.adapter.MainLayoutAdapter;
import com.byteteam.douyin.ui.main.fragment.HomeFragment;
import com.byteteam.douyin.ui.main.fragment.MineFragment;
import com.byteteam.douyin.ui.main.fragment.WorksFragment;
import com.google.android.material.navigation.NavigationBarView;

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
        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    binding.viewPager2.setCurrentItem(0);
                } else if (id == R.id.mine) {
                    binding.viewPager2.setCurrentItem(1);
                }
                return true;
            }
        });
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