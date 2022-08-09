package com.byteteam.douyin.douyinapi;

import android.app.Activity;
import android.content.Context;

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
    private static final String mScope = "user_info,trial.whitelist,renew_refresh_token,discovery.ent";

    // 调用授权
    public static boolean sendAuth(Activity activity) {
        DouYinOpenApi douYinOpenApi = DouYinOpenApiFactory.create(activity);
        Authorization.Request request = new Authorization.Request();
        request.scope = mScope;                                 // 用户授权时必选权限
        return douYinOpenApi.authorize(request);                // 优先使用抖音app进行授权，如果抖音app因版本或者其他原因无法授权，则使用wap页授权

    }

}
