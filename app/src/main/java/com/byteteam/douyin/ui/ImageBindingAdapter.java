package com.byteteam.douyin.ui;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
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
            RequestBuilder<Drawable> drawableRequestBuilder = Glide.with(imageView.getContext())
                    .load(str)
                    .error(R.drawable.ic_img_load_fail) // 加载错误之后的错误图
                    .skipMemoryCache(false) // 是否跳过内存缓存
                    .transition(DrawableTransitionOptions.withCrossFade()) // 图片过渡效果
                    .diskCacheStrategy(DiskCacheStrategy.ALL);//缓存所有版本的图像;
            if (imageView.getContentDescription() != null && imageView.getContentDescription().equals("circle")) { // 设置圆图
                drawableRequestBuilder = drawableRequestBuilder.apply(RequestOptions.bitmapTransform(new CircleCrop()));
            } else {
                drawableRequestBuilder = drawableRequestBuilder
                        .placeholder(new ColorDrawable(imageView.getContext().getResources() // 加载成功之前占位图.centerCrop()
                                .getColor(R.color.textO_on_white_body))); // 指定图片的缩放类型为centerCrop
            }
            drawableRequestBuilder.into(imageView);
        } else if (str.length() == 0) {
            Glide.with(imageView.getContext())
                    .load(R.drawable.ic_img_load_fail)
                    .into(imageView);
        }
        System.out.println("context:" + imageView.getTag());
    }

    // .apply(RequestOptions.bitmapTransform(new CircleCrop()))

    @BindingAdapter({"srcCompat"})
    public static void loadSrc(AppCompatImageView imageView, int src) {
        System.out.println("loadSrc:" + src);
        Glide.with(imageView.getContext()).load(src).into(imageView);
    }

}
