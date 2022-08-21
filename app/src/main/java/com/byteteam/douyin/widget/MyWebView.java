/*
 * Copyright (c) 2021.
 * author:微一软件-林锦焜
 */

package com.byteteam.douyin.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.byteteam.douyin.widget.client.MyWebChromeClient;
import com.byteteam.douyin.widget.client.MyWebViewClient;


public class MyWebView extends WebView {

    public static MyWebChromeClient myWebChromeClient;

    public MyWebView(Context context) {
        super(context);
        ini();
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ini();
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void ini() {
        WebSettings mSettings = getSettings();
        //缓存处理
        mSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//默认缓存策略
        mSettings.setDatabaseEnabled(true);//数据库缓存
        mSettings.setDomStorageEnabled(true);
        mSettings.setAppCacheEnabled(true);

        mSettings.setJavaScriptEnabled(true);// 支持js

        mSettings.setSupportZoom(true); // 支持缩放
        mSettings.setDefaultTextEncodingName("utf-8");// 避免中文乱码
        mSettings.setUseWideViewPort(true); //自适应屏幕
        mSettings.setLoadWithOverviewMode(true);//适应屏幕
        mSettings.setBuiltInZoomControls(true);
        mSettings.setDisplayZoomControls(false);//缩放按钮
        mSettings.setAllowContentAccess(true);
        mSettings.setBlockNetworkImage(false);
        setDrawingCacheBackgroundColor(0);

        setLayerType(View.LAYER_TYPE_HARDWARE, null);//开启硬件加速
        myWebChromeClient = new MyWebChromeClient();
        setWebChromeClient(myWebChromeClient);
        setWebViewClient(new MyWebViewClient(this));
    }


    public boolean canGoBack() {
        return super.canGoBack();
    }

    public void loadUrl(String url) {
        super.loadUrl(url);
    }

    public void reload() {
        super.reload();
    }

    public WebSettings getSettings() {
        return super.getSettings();
    }


}


