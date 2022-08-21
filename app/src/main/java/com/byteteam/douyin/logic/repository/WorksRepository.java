package com.byteteam.douyin.logic.repository;


import com.byteteam.douyin.logic.dataSource.ClientTokenDataSource;
import com.byteteam.douyin.logic.dataSource.RankListDataSource;
import com.byteteam.douyin.logic.dataSource.WorksDataSource;
import com.byteteam.douyin.logic.database.dao.RankListDao;
import com.byteteam.douyin.logic.database.dao.WorksDao;
import com.byteteam.douyin.logic.database.model.ClientToken;
import com.byteteam.douyin.logic.database.model.RankList;
import com.byteteam.douyin.logic.database.model.Works;
import com.byteteam.douyin.logic.factory.NetWorkFactory;
import com.byteteam.douyin.logic.network.model.RankVersion;
import com.byteteam.douyin.logic.network.model.WorksResponse;
import com.byteteam.douyin.logic.network.response.DouYinResponse;
import com.byteteam.douyin.logic.network.response.ResponseTransformer;
import com.byteteam.douyin.logic.network.service.RankService;
import com.byteteam.douyin.logic.network.service.WorksService;

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
 * @introduction： 历史榜单仓库类
 * @author： 林锦焜
 * @time： 2022/8/10 20:43
 */
public class WorksRepository implements WorksDataSource {

    WorksDao worksDao;

    public WorksRepository(WorksDao worksDao) {
        this.worksDao = worksDao;
    }

    @Override
    public Maybe<WorksResponse> queryMyWorks(String accessToken, String openId, long cursor) {
        Retrofit retrofit = NetWorkFactory.provideRetrofit();
        WorksService worksService = retrofit.create(WorksService.class);
        return worksService.queryMyWorks(accessToken, openId, cursor, 20)
                .compose(ResponseTransformer.obtain())
                .map(worksResponse -> {
                    if (worksResponse.getList() == null) {
                        worksResponse.setList(new ArrayList<>());
                    }
                    if (cursor == 0 && worksResponse.getList().size() > 0) {
                        // 如果map被调用，则说明联网请求成功，将结果异步缓存到数据库
                        // 保存数据到数据库，并清空之前的缓存
                        Disposable disposable = worksDao.delete()
                                .subscribeOn(Schedulers.io())
                                .subscribe(() -> worksDao.insert(worksResponse.getList())
                                        .subscribeOn(Schedulers.io())
                                        .subscribe());
                    }
                    return worksResponse;
                })
                .onErrorResumeNext((Function<Throwable, ObservableSource<WorksResponse>>) throwable -> {
                    // 如果请求出现异常(联网失败等情况)
                    return worksDao.queryWorksList()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .defaultIfEmpty(new ArrayList<>())
                            // 当数据库无数据时会发送这条默认数据
                            .flatMap((Function<List<Works>, MaybeSource<WorksResponse>>) works -> {
                                // 说明本地数据库无数据
                                if (works.size() == 0 || cursor > 1) {
                                    // 抛出上面的错误
                                    return Maybe.error(throwable);
                                }
                                WorksResponse worksResponse = new WorksResponse();
                                worksResponse.setCursor(0L);
                                worksResponse.setHasMore(false);
                                worksResponse.setList(works);
                                // 说明是数据库中的数据
                                return Maybe.just(worksResponse);
                            })
                            .toObservable();
                })
                .singleElement();
    }

}
