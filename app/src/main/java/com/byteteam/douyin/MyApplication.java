package com.byteteam.douyin;

import android.app.Application;

import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.DouYinOpenConfig;

/**
 * @introduction：
 * @author： 林锦焜
 * @time： 2022/8/4 21:32
 */
public class MyApplication extends Application {

    public static final String clientkey = "awe756uumsrb0pqt";

    public static final String clientSecret = "7674ef60b7318a62bcd119fe1af2bd52";

    @Override
    public void onCreate() {
        super.onCreate();
        DouYinOpenApiFactory.init(new DouYinOpenConfig(clientkey));
    }
}
