package com.byteteam.douyin.logic.network.service;

import com.byteteam.douyin.logic.database.model.User;
import com.byteteam.douyin.logic.network.model.UserData;
import com.byteteam.douyin.logic.network.response.DouYinResponse;

import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @POST("/oauth/userinfo/")
    Observable<DouYinResponse<UserData>> getUser(@Header("access-token")String accessToken,
                                                 @Query("open_id")String open_id);

}
