package com.byteteam.douyin.logic.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @introduction： 接口调用凭证实体类
 * @author： 林锦焜
 * @time： 2022/8/7 18:00
 */
@Entity
public class AccessToken {

    @PrimaryKey(autoGenerate = true)
    private int id;

    // 接口调用凭证
    private String accessToken;

    // 用户授权的作用域(Scope)
    private String scope;

    // 授权用户唯一标识
    private String openId;

    // accessToken无效时间，单位（毫秒)
    private Long expiresIn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @NonNull
    @Override
    public String toString() {
        return "AccessToken{" +
                "id=" + id +
                ", accessToken='" + accessToken + '\'' +
                ", scope='" + scope + '\'' +
                ", openId='" + openId + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
