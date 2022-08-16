package com.byteteam.douyin.logic.network.service;

import com.byteteam.douyin.logic.database.model.RankItem;
import com.byteteam.douyin.logic.network.model.RankData;
import com.byteteam.douyin.logic.network.model.RankVersion;
import com.byteteam.douyin.logic.network.response.DouYinResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * @introduction： 榜单网络接口
 * @author： 林锦焜
 * @time： 2022/8/8 20:42
 */
public interface RankService {

    @GET("/discovery/ent/rank/item/")
    Observable<DouYinResponse<RankData<RankItem>>> getRank(@Header("access-token")String clientToken
            , @Query("type") int type);

    @GET("/discovery/ent/rank/item/")
    Observable<DouYinResponse<RankData<RankItem>>> getRank(@Header("access-token")String clientToken
            , @Query("type") int type, @Query("version") int version);

    // 获取历史榜单
    @GET("/discovery/ent/rank/version/")
    Observable<DouYinResponse<RankVersion>> getRankVersion(@Header("access-token")String clientToken
            , @Query("cursor") long cursor, @Query("count") long count, @Query("type") int type);

}
