package com.byteteam.douyin.ui;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.signature.ObjectKey;
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
                    .placeholder(new ColorDrawable(imageView.getContext().getResources().getColor(R.color.textO_on_white_body))) // 加载成功之前占位图
                    .error(R.drawable.ic_img_load_fail) // 加载错误之后的错误图
                    .skipMemoryCache(false) // 是否跳过内存缓存
                    .transition(DrawableTransitionOptions.withCrossFade()) // 图片过渡效果
                    .centerCrop() // 指定图片的缩放类型为centerCrop
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存所有版本的图像
                    .into(imageView);
        }
    }

    @BindingAdapter({"srcCompat"})
    public static void loadSrc(AppCompatImageView imageView, int src) {
        Glide.with(imageView.getContext()).load(src).into(imageView);
    }

}
