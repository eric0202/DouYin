package com.byteteam.douyin.logic.network.service;

import com.byteteam.douyin.logic.network.model.AccessTokenData;
import com.byteteam.douyin.logic.network.model.ClientTokenData;
import com.byteteam.douyin.logic.network.response.DouYinResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @introduction： 凭证Token网络接口
 * @author： 林锦焜
 * @time： 2022/8/8 20:42
 */
public interface TokenService {

    @FormUrlEncoded
    @POST("/oauth/access_token/")
    Observable<DouYinResponse<AccessTokenData>> getAccessToken(@Field("client_secret") String clientSecret
            , @Field("code") String code, @Field("grant_type") String grantType
            , @Field("client_key") String clientKey);

    @FormUrlEncoded
    @POST("/oauth/client_token/")
    Observable<DouYinResponse<ClientTokenData>> getClientToken(@Field("client_key") String clientKey,
                                                               @Field("client_secret") String clientSecret,
                                                               @Field("grant_type") String grantType);

}
