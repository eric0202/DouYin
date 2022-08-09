package com.byteteam.douyin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi;
import com.byteteam.douyin.douyinapi.ApiUtil;
import com.byteteam.douyin.douyinapi.DouYinEntryActivity;
import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.logic.dataSource.ClientTokenDataSource;
import com.byteteam.douyin.logic.database.model.ClientToken;
import com.byteteam.douyin.logic.factory.RepositoryFactory;
import com.byteteam.douyin.logic.network.exception.ErrorConsumer;
import com.byteteam.douyin.logic.network.exception.NetException;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    private AccessTokenDataSource accessTokenDataSource;

    private ClientTokenDataSource clientTokenDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accessTokenDataSource = RepositoryFactory.providerAccessTokenRepository(this);
        clientTokenDataSource = RepositoryFactory.providerClientTokenRepository(this);
        /*findViewById(R.id.getAccessToken).setOnClickListener(v -> {
            Disposable disposable = accessTokenDataSource.getAccessToken(System.currentTimeMillis())
                    .doOnComplete(() -> ApiUtil.sendAuth(MainActivity.this))
                    .subscribe(accessToken -> {
                        System.out.println(accessToken);
                    });
        });
        findViewById(R.id.getClientToken).setOnClickListener(v -> {
            Disposable disposable = clientTokenDataSource.getClientToken(System.currentTimeMillis())
                    .subscribe(accessToken -> {
                        System.out.println("subscribe:" + accessToken);
                    }, new ErrorConsumer() {
                        @Override
                        protected void error(NetException e) {
                            Toast.makeText(MainActivity.this,e.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    });
        });*/
    }


}