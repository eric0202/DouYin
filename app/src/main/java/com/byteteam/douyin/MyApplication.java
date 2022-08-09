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

    public static final String clientkey = "awooog0as21zbmrl";

    public static final String clientSecret = "74fdcb426b3034f6219fdb3d89c04982";

    @Override
    public void onCreate() {
        super.onCreate();
        DouYinOpenApiFactory.init(new DouYinOpenConfig(clientkey));
    }
}
