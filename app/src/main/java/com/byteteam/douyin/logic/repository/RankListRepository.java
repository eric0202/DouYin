package com.byteteam.douyin.logic.repository;


import com.byteteam.douyin.logic.dataSource.ClientTokenDataSource;
import com.byteteam.douyin.logic.dataSource.RankListDataSource;
import com.byteteam.douyin.logic.database.dao.RankListDao;
import com.byteteam.douyin.logic.database.model.ClientToken;
import com.byteteam.douyin.logic.database.model.RankList;
import com.byteteam.douyin.logic.factory.NetWorkFactory;
import com.byteteam.douyin.logic.network.model.RankVersion;
import com.byteteam.douyin.logic.network.response.DouYinResponse;
import com.byteteam.douyin.logic.network.response.ResponseTransformer;
import com.byteteam.douyin.logic.network.service.RankService;

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
 * @introduction： 历史榜单仓库类
 * @author： 林锦焜
 * @time： 2022/8/10 20:43
 */
public class RankListRepository implements RankListDataSource {

    ClientTokenDataSource clientTokenDataSource;

    RankListDao rankListDao;

    public RankListRepository(ClientTokenDataSource clientTokenDataSource, RankListDao rankListDao) {
        this.clientTokenDataSource = clientTokenDataSource;
        this.rankListDao = rankListDao;
    }

    @Override
    public Maybe<RankVersion> queryRankList(int type, long cursor) {
        // 首先请求获取clientToken，如果数据库的未过期会从数据库中拿，数据库过期会联网申请
        return clientTokenDataSource.getClientToken()
                .flatMap((Function<ClientToken, MaybeSource<RankVersion>>) clientToken -> {
                    // 成功获取ClientToken
                    Retrofit retrofit = NetWorkFactory.provideRetrofit();
                    RankService rankService = retrofit.create(RankService.class);
                    // 根据是否需要版本号选择请求方式
                    Observable<DouYinResponse<RankVersion>> observable
                            = rankService.getRankVersion(clientToken.getAccessToken(), cursor, 12, type);
                    return observable
                            .compose(ResponseTransformer.obtain())
                            .map(rankVersion -> {
                                if (cursor == 0) {
                                    // 如果map被调用，则说明联网请求成功，将结果异步缓存到数据库
                                    // 保存数据到数据库，并清空之前的缓存
                                    Disposable disposable = rankListDao.delete(type)
                                            .subscribeOn(Schedulers.io())
                                            .subscribe(new Action() {
                                                @Override
                                                public void run() {
                                                    rankListDao.insert(rankVersion.getList())
                                                            .subscribeOn(Schedulers.io())
                                                            .subscribe();
                                                }
                                            });
                                }
                                return rankVersion;
                            })
                            .singleElement();
                })
                .onErrorResumeNext((Function<Throwable, MaybeSource<RankVersion>>) throwable -> {
                    // 如果请求出现异常(联网失败等情况)
                    return rankListDao.queryRankList(type)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .defaultIfEmpty(new ArrayList<>())
                            // 当数据库无数据时会发送这条默认数据
                            .flatMap((Function<List<RankList>, MaybeSource<RankVersion>>) rankLists -> {
                                // 说明本地数据库无数据
                                if (rankLists.size() == 0 || cursor > 1) {
                                    // 抛出上面的错误
                                    return Maybe.error(throwable);
                                }
                                RankVersion rankVersion = new RankVersion();
                                rankVersion.setList(rankLists);
                                rankVersion.setCursor(0L);
                                rankVersion.setHasMore(false);
                                // 说明是数据库中的数据
                                return Maybe.just(rankVersion);
                            });
                });
    }
}
