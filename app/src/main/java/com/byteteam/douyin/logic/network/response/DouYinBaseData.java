package com.byteteam.douyin.logic.network.response;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

/**
 * @introduction： 抖音Api接口回调数据data项的基类
 * @author： 林锦焜
 * @time： 2022/8/8 20:42
 */
public abstract class DouYinBaseData {

    // 错误码
    @SerializedName("error_code")
    @Ignore
    protected Long errorCode;

    // 错误码描述
    @Ignore
    protected String description;

    public Long getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
