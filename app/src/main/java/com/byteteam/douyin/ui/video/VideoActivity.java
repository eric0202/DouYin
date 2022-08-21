package com.byteteam.douyin.ui.video;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.byteteam.douyin.logic.database.model.Works;
import com.byteteam.douyin.ui.BaseActivity;
import com.byteteam.douyin.databinding.ActivityVideoBinding;

/**
 * @introduction： 视频详情Activity类
 * @author： 林锦焜
 * @time： 2022/8/10 20:25
 */
public class VideoActivity extends BaseActivity {

    ActivityVideoBinding binding;

    private Works works;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        works = (Works) getIntent().getSerializableExtra("works");
        System.out.println("works:" + works);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        binding.setVideo(works);
        setContentView(binding.getRoot());
        binding.back.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.webView.loadUrl(works.getShareUrl());
    }

}
