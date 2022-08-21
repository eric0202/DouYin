package com.byteteam.douyin.logic.repository;


import com.byteteam.douyin.logic.dataSource.MyFansDataSource;
import com.byteteam.douyin.logic.dataSource.WorksDataSource;
import com.byteteam.douyin.logic.database.dao.MyFansDao;
import com.byteteam.douyin.logic.database.dao.WorksDao;
import com.byteteam.douyin.logic.database.model.MyFans;
import com.byteteam.douyin.logic.database.model.Works;
import com.byteteam.douyin.logic.factory.NetWorkFactory;
import com.byteteam.douyin.logic.network.model.MyFansData;
import com.byteteam.douyin.logic.network.model.WorksResponse;
import com.byteteam.douyin.logic.network.response.ResponseTransformer;
import com.byteteam.douyin.logic.network.service.MyFansService;
import com.byteteam.douyin.logic.network.service.WorksService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @introduction： 我的粉丝仓库类
 * @author： 林锦焜
 * @time： 2022/8/10 20:43
 */
public class MyFansRepository implements MyFansDataSource {

    MyFansDao myFansDao;

    public MyFansRepository(MyFansDao myFansDao) {
        this.myFansDao = myFansDao;
    }

    @Override
    public Maybe<MyFansData> queryMyFans(String accessToken, String openId, long cursor) {
        Retrofit retrofit = NetWorkFactory.provideRetrofit();
        MyFansService myFansService = retrofit.create(MyFansService.class);
        return myFansService.queryMyFan(accessToken, openId, cursor, 20)
                .compose(ResponseTransformer.obtain())
                .map(worksResponse -> {
                    if (worksResponse.getList() == null) {
                        worksResponse.setList(new ArrayList<>());
                    }
                    if (cursor == 0 && worksResponse.getList().size() > 0) {
                        // 如果map被调用，则说明联网请求成功，将结果异步缓存到数据库
                        // 保存数据到数据库，并清空之前的缓存
                        Disposable disposable = myFansDao.delete()
                                .subscribeOn(Schedulers.io())
                                .subscribe(() -> myFansDao.insert(worksResponse.getList())
                                        .subscribeOn(Schedulers.io())
                                        .subscribe());
                    }
                    return worksResponse;
                })
                .onErrorResumeNext((Function<Throwable, ObservableSource<MyFansData>>) throwable -> {
                    // 如果请求出现异常(联网失败等情况)
                    return myFansDao.queryFansList()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .defaultIfEmpty(new ArrayList<>())
                            // 当数据库无数据时会发送这条默认数据
                            .flatMap((Function<List<MyFans>, MaybeSource<MyFansData>>) myFans -> {
                                // 说明本地数据库无数据
                                if (myFans.size() == 0 || cursor > 1) {
                                    // 抛出上面的错误
                                    return Maybe.error(throwable);
                                }
                                MyFansData myFansData = new MyFansData();
                                myFansData.setCursor(0L);
                                myFansData.setHasMore(false);
                                myFansData.setList(myFans);
                                myFansData.setTotal(-1);
                                // 说明是数据库中的数据
                                return Maybe.just(myFansData);
                            })
                            .toObservable();
                })
                .singleElement();
    }

}
