package com.byteteam.douyin.logic.network.response;

import androidx.annotation.NonNull;

/**
 * @introduction： 抖音Api接口回调实体类
 * @author： 林锦焜
 * @time： 2022/8/6 13:33
 */
public class DouYinResponse<T extends DouYinBaseData> {

    // 回调核心数据
    private T data;

    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public String toString() {
        return "DouYinResponse{" +
                "data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
