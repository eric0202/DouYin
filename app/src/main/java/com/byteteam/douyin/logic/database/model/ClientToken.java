package com.byteteam.douyin.logic.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @introduction： 调用的凭证client_access_token实体类
 * @author： 林锦焜
 * @time： 2022/8/8 20:01
 */
@Entity
public class ClientToken {

    @PrimaryKey(autoGenerate = true)
    private int id;

    // 接口调用凭证
    private String accessToken;

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

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @NonNull
    @Override
    public String toString() {
        return "ClientToken{" +
                "id=" + id +
                ", accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
