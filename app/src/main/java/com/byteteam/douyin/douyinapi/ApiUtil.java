package com.byteteam.douyin.douyinapi;

import android.app.Activity;

import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi;

/**
 * @introduction： 抖音Api工具类
 * @author： 林锦焜
 * @time： 2022/8/9 21:27
 */
public class ApiUtil {

    // 权限集
    private static final String mScope = "user_info,trial.whitelist,renew_refresh_token,discovery.ent,fans.list,following.list,video.list,video.data,fans.check";

    // 调用授权
    public static void sendAuth(Activity activity) {
        DouYinOpenApi douYinOpenApi = DouYinOpenApiFactory.create(activity);
        Authorization.Request request = new Authorization.Request();
        request.scope = mScope;                                 // 用户授权时必选权限
        douYinOpenApi.authorize(request);
    }

}
