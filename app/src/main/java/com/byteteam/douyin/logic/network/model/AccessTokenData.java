package com.byteteam.douyin.logic.network.model;

import androidx.annotation.NonNull;

import com.byteteam.douyin.logic.network.response.DouYinBaseData;
import com.google.gson.annotations.SerializedName;

/**
 * @introduction： AccessToken请求回调data实体类
 * @author： 林锦焜
 * @time： 2022/8/6 13:33
 */
public class AccessTokenData extends DouYinBaseData {

    // 接口调用凭证
    @SerializedName("access_token")
    private String accessToken;

    // access_token接口调用凭证超时时间，单位（秒)
    @SerializedName("expires_in")
    private Long expiresIn;

    // 授权用户唯一标识
    @SerializedName("open_id")
    private String openId;

    // 用户授权的作用域(Scope)，使用逗号（,）分隔，开放平台几乎几乎每个接口都需要特定的Scope。
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @NonNull
    @Override
    public String toString() {
        return "AccessTokenData{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", openId='" + openId + '\'' +
                ", scope='" + scope + '\'' +
                ", errorCode=" + errorCode +
                ", description='" + description + '\'' +
                '}';
    }
}
