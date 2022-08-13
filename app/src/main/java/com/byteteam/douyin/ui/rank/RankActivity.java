package com.byteteam.douyin.ui.rank;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ActivityRankBinding;
import com.byteteam.douyin.ui.rank.fragment.RankFragment;

/**
 * @introduction： 榜单Activity类
 * @author： 林锦焜
 * @time： 2022/8/10 20:25
 */
public class RankActivity extends AppCompatActivity {

    ActivityRankBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRankBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadFragment();
    }

    // 加载页面
    private void loadFragment() {
        int type = getIntent().getIntExtra("type", 1);
        int version = getIntent().getIntExtra("version", 0);
        Fragment fragment = RankFragment.newInstance(type, version);
        getSupportFragmentManager()
                .beginTransaction()
                .add(binding.fragment.getId(), fragment)
                .commit();
    }

}
