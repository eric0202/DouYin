package com.byteteam.douyin.logic.repository;

import com.byteteam.douyin.MyApplication;
import com.byteteam.douyin.logic.database.dao.AccessTokenDao;
import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.logic.database.model.AccessToken;
import com.byteteam.douyin.logic.factory.NetWorkFactory;
import com.byteteam.douyin.logic.network.response.ResponseTransformer;
import com.byteteam.douyin.logic.network.service.TokenService;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @introduction： AccessToken仓库类
 * @author： 林锦焜
 * @time： 2022/8/7 18:08
 */
public class AccessTokenRepository implements AccessTokenDataSource {

    AccessTokenDao accessTokenDao;

    public AccessTokenRepository(AccessTokenDao accessTokenDao) {
        this.accessTokenDao = accessTokenDao;
    }

    /**
     * 向数据库查询AccessToken
     * @return Maybe<AccessToken>
     */
    @Override
    public Maybe<AccessToken> getAccessToken() {
        return accessTokenDao.getAccessToken(System.currentTimeMillis())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 向数据库插入一条AccessToken
     * @return Completable
     */
    @Override
    public Completable insert(AccessToken accessToken) {
        return accessTokenDao.insert(accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 联网申请AccessToken
     * @param authCode 授权码
     * @return Completable
     */
    @Override
    public Observable<AccessToken> requestAccessToken(String authCode) {
        Retrofit retrofit = NetWorkFactory.provideRetrofit();
        TokenService tokenService = retrofit.create(TokenService.class);
        return tokenService.getAccessToken(MyApplication.clientSecret, authCode
                        , "authorization_code", MyApplication.clientkey)
                .compose(ResponseTransformer.obtain())
                .map(data -> {
                    // 保存AccessToken
                    AccessToken accessToken = new AccessToken();
                    accessToken.setAccessToken(data.getAccessToken());
                    accessToken.setExpiresIn(System.currentTimeMillis() + data.getExpiresIn() * 1000);
                    accessToken.setOpenId(data.getOpenId());
                    accessToken.setScope(data.getScope());
                    // 添加到数据库
                    insert(accessToken).subscribe();
                    return accessToken;
                });
    }

}
