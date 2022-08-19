package com.byteteam.douyin.logic.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.logic.dataSource.UserDataSource;
import com.byteteam.douyin.logic.database.dao.UserDao;
import com.byteteam.douyin.logic.database.model.AccessToken;
import com.byteteam.douyin.logic.database.model.User;
import com.byteteam.douyin.logic.factory.NetWorkFactory;
import com.byteteam.douyin.logic.network.response.DouYinResponse;
import com.byteteam.douyin.logic.network.response.ResponseTransformer;
import com.byteteam.douyin.logic.network.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.functions.Function;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @introduction： 联网获取user
 * @author： 何文鹏
 * @time： 2022/8/19
 */

public class UserRepository implements UserDataSource {

    UserDao userDao;
    AccessTokenDataSource accessTokenDataSource;

    public UserRepository(AccessTokenDataSource accessTokenDataSource, UserDao userDao){
        this.userDao = userDao;
        this.accessTokenDataSource = accessTokenDataSource;
    }

    @Override
    public Maybe<User> queryUser() {
        return accessTokenDataSource.getAccessToken()
                .flatMap((Function<AccessToken, MaybeSource<User>>) accessToken ->{
                    Retrofit retrofit = NetWorkFactory.provideRetrofit();
                    UserService userService = retrofit.create(UserService.class);

                    Map map = new HashMap();
                    map.put("access_token",accessToken.getAccessToken());
                    map.put("open_id",accessToken.getOpenId());
                    Observable<DouYinResponse<User>> observable = userService.getUser(accessToken.getAccessToken(),map);
                    return observable
                            .compose(ResponseTransformer.obtain())
                            .map(userData ->{
                                // 如果map被调用，则说明联网请求成功，将结果异步缓存到数据库
                                // 保存数据到数据库，并清空之前的缓存
                                Disposable disposable = userDao.deleteUser(userData)
                                        .subscribeOn(Schedulers.io())
                                        .subscribe(new Action() {
                                            @Override
                                            public void run() throws Exception {
                                                userDao.insertUser(userData)
                                                        .subscribeOn(Schedulers.io())
                                                        .subscribe();
                                            }
                                        });
                                return userData;
                            })
                            .singleElement();
                }).onErrorResumeNext((Function<Throwable,MaybeSource<User>>) throwable -> {
                    return userDao.getUser()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .defaultIfEmpty(new User())
                            .flatMap((Function<User, MaybeSource<User>>) user ->{
                                if(user == null){
                                    return Maybe.error(throwable);
                                }
                                return Maybe.just(user);
                    });
                });



    }
}
