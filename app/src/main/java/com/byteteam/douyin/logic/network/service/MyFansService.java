package com.byteteam.douyin.logic.network.service;

import com.byteteam.douyin.logic.network.model.MyFansData;
import com.byteteam.douyin.logic.network.response.DouYinResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MyFansService {

    @GET("/fans/list/")
    Observable<DouYinResponse<MyFansData>> queryMyFan(@Header("access-token")String accessToken,
                                                      @Query("open_id") String openId,
                                                      @Query("cursor") long cursor,
                                                      @Query("count") int count);

}
