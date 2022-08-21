/*
 * Copyright (c) 2021.
 * author:微一软件-林锦焜
 */

package com.byteteam.douyin.widget.client;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Handler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.byteteam.douyin.widget.MyWebView;


public class MyWebViewClient extends WebViewClient {
    private final MyWebView myWebView;

    public MyWebViewClient(MyWebView myWebView) {
        this.myWebView = myWebView;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        if (url.startsWith("http://") || url.startsWith("https://")) {
            webView.loadUrl(url);
        }
        return false;
    }

    public void onPageFinished(WebView view, String url) {
        myWebView.measure(0, 0);
        new Handler().postDelayed(() -> view.loadUrl(getHideNoImportantElement()), 500);
    }

    // 隐藏抖音播放时无用的元素
    private String getHideNoImportantElement() {
        String jsPrefix = "javascript:(function() {";
        String js = "document.getElementsByClassName('footer')[0].style.display = 'none';"; // 隐藏底部信息
        js += "document.getElementsByClassName('login-header')[0].style.display = 'none';"; // 隐藏顶部logo区
        js += "document.getElementsByClassName('right')[0].style.display = 'none';"; // 隐藏右边视频数据区
        js += "document.getElementsByClassName('end-model')[0].style.display = 'none';"; // 隐藏播放完提示跳转抖音的区域
        js += "document.getElementsByClassName('video-player')[0].click();"; // 自动播放
        String jsSuffix = "})()";//end-model
        return jsPrefix + js + jsSuffix;
    }

    @SuppressLint("WebViewClientOnReceivedSslError")
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {//处理https请
        handler.proceed();    //表示等待证书响应
        handler.cancel();      //表示挂起连接，为默认方式
        //handler.handleMessage(null);    //可做其他处理
    }


}
