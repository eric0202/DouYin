package com.byteteam.douyin.logic.repository;

import com.byteteam.douyin.logic.dataSource.ClientTokenDataSource;
import com.byteteam.douyin.logic.dataSource.FansItemDataSource;
import com.byteteam.douyin.logic.database.dao.FansItemDao;
import com.byteteam.douyin.logic.database.model.ClientToken;
import com.byteteam.douyin.logic.database.model.FansItem;
import com.byteteam.douyin.logic.factory.NetWorkFactory;
import com.byteteam.douyin.logic.network.model.FansData;
import com.byteteam.douyin.logic.network.response.DouYinResponse;
import com.byteteam.douyin.logic.network.response.ResponseTransformer;
import com.byteteam.douyin.logic.network.service.FansService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @introduction： 联网获取关注列表
 * @author： 陈光磊
 * @time： 2022/8/20
 */

public class FansItemRepository implements FansItemDataSource {

    FansItemDao fansItemDao;

    public FansItemRepository(FansItemDao fansItemDao) {
        this.fansItemDao = fansItemDao;
    }

    @Override
    public Maybe<FansData> queryFans(String accessToken, String openId, long cursor) {
        Retrofit retrofit = NetWorkFactory.provideRetrofit();
        FansService fansService = retrofit.create(FansService.class);
        return fansService.queryFans(accessToken, openId, cursor, 20)
                .compose(ResponseTransformer.obtain())
                .map(worksResponse -> {
                    if (worksResponse.getList() == null) {
                        worksResponse.setList(new ArrayList<>());
                    }
                    if (cursor == 0 && worksResponse.getList().size() > 0) {
                        // 如果map被调用，则说明联网请求成功，将结果异步缓存到数据库
                        // 保存数据到数据库，并清空之前的缓存
                        Disposable disposable = fansItemDao.delete()
                                .subscribeOn(Schedulers.io())
                                .subscribe(() -> fansItemDao.insert(worksResponse.getList())
                                        .subscribeOn(Schedulers.io())
                                        .subscribe());
                    }
                    return worksResponse;
                })
                .onErrorResumeNext((Function<Throwable, ObservableSource<FansData>>) throwable -> {
                    // 如果请求出现异常(联网失败等情况)
                    return fansItemDao.queryFansList()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .defaultIfEmpty(new ArrayList<>())
                            // 当数据库无数据时会发送这条默认数据
                            .flatMap((Function<List<FansItem>, MaybeSource<FansData>>) fans -> {
                                // 说明本地数据库无数据
                                if (fans.size() == 0 || cursor > 1) {
                                    // 抛出上面的错误
                                    return Maybe.error(throwable);
                                }
                                FansData fansData = new FansData();
                                fansData.setCursor(0L);
                                fansData.setHasMore(false);
                                fansData.setList(fans);
                                fansData.setTotal(-1);
                                // 说明是数据库中的数据
                                return Maybe.just(fansData);
                            })
                            .toObservable();
                })
                .singleElement();
    }

}
