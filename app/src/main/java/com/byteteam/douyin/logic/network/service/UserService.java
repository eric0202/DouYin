package com.byteteam.douyin.logic.network.service;

import com.byteteam.douyin.logic.database.model.User;
import com.byteteam.douyin.logic.network.response.DouYinResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @introduction：
 * @author： 何文鹏
 * @time： 2022/8/19
 */

public interface UserService {
    @POST("/oauth/userinfo/")
    Observable<DouYinResponse<User>> getUser(@Header("access-token")String accessToken,
                                             @QueryMap Map<String,String> options);

}
