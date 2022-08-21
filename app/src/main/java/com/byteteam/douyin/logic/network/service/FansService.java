package com.byteteam.douyin.logic.network.service;

import com.byteteam.douyin.logic.database.model.FansItem;
import com.byteteam.douyin.logic.network.model.FansData;
import com.byteteam.douyin.logic.network.response.DouYinResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * @introduction： 关注列表接口
 * @author： 陈光磊
 * @time： 2022/8/20 20:42
 */

public interface FansService {
    @GET("/following/list/")
    Observable<DouYinResponse<FansData>> queryFans(@Header("access-token")String accessToken,
                                                   @Query("open_id") String openId,
                                                   @Query("cursor") long cursor,
                                                   @Query("count") int count);
}
