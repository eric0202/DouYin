package com.byteteam.douyin.douyinapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bytedance.sdk.open.aweme.CommonConstants;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.aweme.common.handler.IApiEventHandler;
import com.bytedance.sdk.open.aweme.common.model.BaseReq;
import com.bytedance.sdk.open.aweme.common.model.BaseResp;
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi;
import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.logic.factory.RepositoryFactory;
import com.byteteam.douyin.logic.network.exception.ErrorConsumer;
import com.byteteam.douyin.logic.network.exception.NetException;

import io.reactivex.disposables.Disposable;

/**
 * 主要功能：接受授权返回结果的activity
 * <p>
 * <p>
 * 也可通过request.callerLocalEntry = "com.xxx.xxx...activity"; 定义自己的回调类
 */
public class DouYinEntryActivity extends Activity implements IApiEventHandler {

    DouYinOpenApi douYinOpenApi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        douYinOpenApi = DouYinOpenApiFactory.create(this);
        douYinOpenApi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        // 授权成功可以获得authCode
        if (resp.getType() == CommonConstants.ModeType.SEND_AUTH_RESPONSE) {
            Authorization.Response response = (Authorization.Response) resp;
            if (resp.isSuccess()) {
                Toast.makeText(this, "授权成功",Toast.LENGTH_LONG).show();
                getAccessToken(response.authCode);
            } else {
                Toast.makeText(this, "授权失败" + response.grantedPermissions,
                        Toast.LENGTH_LONG).show();
            }
            //finish();
        }
    }

    @Override
    public void onErrorIntent(@Nullable Intent intent) {
        // 错误数据
        Toast.makeText(this, "intent出错啦", Toast.LENGTH_LONG).show();
        finish();
    }

    private void getAccessToken(String authCode) {
        AccessTokenDataSource accessTokenDataSource = RepositoryFactory.providerAccessTokenRepository(this);
        Disposable disposable = accessTokenDataSource.requestAccessToken(authCode)
                .subscribe(accessToken -> {
                    Toast.makeText(DouYinEntryActivity.this, "成功获取AccessToken", Toast.LENGTH_SHORT).show();
                    System.out.println(accessToken);
                    finish();
                }, new ErrorConsumer() {
                    @Override
                    protected void error(NetException e) {
                        Toast.makeText(DouYinEntryActivity.this,e.getMsg(),Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
