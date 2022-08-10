package com.byteteam.douyin.ui;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.byteteam.douyin.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @introduction： 图片dataBinding适配器
 * @author： 林锦焜
 * @time： 2022/8/7 18:10
 */
public class ImageBindingAdapter {

    private static final ExecutorService executors = Executors.newFixedThreadPool(3);

    @BindingAdapter({"srcCompat"})
    public static void loadSrc(AppCompatImageView imageView, String str) {
        if (str.startsWith("http")) {
            Glide.with(imageView.getContext())
                    .load(str)
                    .error(R.mipmap.ic_film)
                    .into(imageView);
        }
    }

    @BindingAdapter({"srcCompat"})
    public static void loadSrc(AppCompatImageView imageView, int src) {
        Glide.with(imageView.getContext()).load(src).into(imageView);
    }

}
