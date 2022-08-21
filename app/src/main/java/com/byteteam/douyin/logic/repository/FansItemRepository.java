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
    ClientTokenDataSource clientTokenDataSource;

    FansItemDao fansItemDao;

    public FansItemRepository(ClientTokenDataSource clientTokenDataSource, FansItemDao fansItemDao) {
        this.clientTokenDataSource = clientTokenDataSource;
        this.fansItemDao = fansItemDao;
    }

    @Override
    public Maybe<List<FansItem>> queryFans(int type) {
        // 首先请求获取clientToken，如果数据库的未过期会从数据库中拿，数据库过期会联网申请
        return clientTokenDataSource.getClientToken()
                .flatMap((Function<ClientToken, MaybeSource<List<FansItem>>>) clientToken -> {
                    // 成功获取ClientToken
                    Retrofit retrofit = NetWorkFactory.provideRetrofit();
                    FansService fansService = retrofit.create(FansService.class);
                    // 根据是否需要版本号选择请求方式
                    Observable<DouYinResponse<FansData<FansItem>>> observable
                            = fansService.getFans(clientToken.getAccessToken(), type); // 获取最新
                    return observable
                            .compose(ResponseTransformer.obtain())
                            .map(fansItemFansData -> {
                                // 如果map被调用，则说明联网请求成功，将结果异步缓存到数据库
                                // 保存数据到数据库，并清空之前的缓存
                                Disposable disposable = fansItemDao.delete(type)
                                        .subscribeOn(Schedulers.io())
                                        .subscribe(new Action() {
                                            @Override
                                            public void run() {
                                                fansItemDao.insert(fansItemFansData.getList())
                                                        .subscribeOn(Schedulers.io())
                                                        .subscribe();
                                            }
                                        });
                                return fansItemFansData.getList();
                            })
                            .singleElement();
                })
                .onErrorResumeNext((Function<Throwable, MaybeSource<List<FansItem>>>) throwable -> {
                    // 如果请求出现异常(联网失败等情况)
                    return fansItemDao.queryFans(type)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .defaultIfEmpty(new ArrayList<>())
                            // 当数据库无数据时会发送这条默认数据
                            .flatMap((Function<List<FansItem>, MaybeSource<List<FansItem>>>) fansItems -> {
                                // 说明本地数据库无数据
                                if (fansItems.size() == 0) {
                                    // 抛出上面的错误
                                    return Maybe.error(throwable);
                                }
                                // 说明是数据库中的数据
                                return Maybe.just(fansItems);
                            });
                });
    }
}
