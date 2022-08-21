package com.byteteam.douyin.logic.network.model;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.byteteam.douyin.logic.database.model.MyFans;
import com.byteteam.douyin.logic.network.response.DouYinBaseData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @introduction： 个人粉丝回调
 * @author： 林锦焜
 * @time： 2022/8/21 17:05
 */
public class MyFansData extends DouYinBaseData {

    // 粉丝列表
    private List<MyFans> list;

    // 用于下一页请求的cursor
    private Long cursor;

    // 是否还有下一页
    @SerializedName("has_more")
    private boolean hasMore;

    // 粉丝总数
    private Integer total;


    public List<MyFans> getList() {
        return list;
    }

    public void setList(List<MyFans> list) {
        this.list = list;
    }

    public Long getCursor() {
        return cursor;
    }

    public void setCursor(Long cursor) {
        this.cursor = cursor;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @NonNull
    @Override
    public String toString() {
        return "MyFansData{" +
                "list=" + list +
                ", cursor=" + cursor +
                ", hasMore=" + hasMore +
                ", total=" + total +
                '}';
    }
}
