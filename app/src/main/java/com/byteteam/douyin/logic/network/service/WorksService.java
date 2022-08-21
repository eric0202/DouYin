package com.byteteam.douyin.logic.network.service;

import com.byteteam.douyin.logic.network.model.WorksResponse;
import com.byteteam.douyin.logic.network.response.DouYinResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface WorksService {

    @GET("/video/list/")
    Observable<DouYinResponse<WorksResponse>> queryMyWorks(@Header("access-token")String accessToken,
                                                           @Query("open_id") String openId,
                                                           @Query("cursor") long cursor,
                                                           @Query("count") int count);

    @GET("/video/list/")
    Observable<DouYinResponse<WorksResponse>> queryTest(@Header("access-token")String accessToken,
                                 @Query("open_id") String openId,
                                 @Query("cursor") long cursor,
                                 @Query("count") int count);

}
