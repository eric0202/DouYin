package com.byteteam.douyin.logic.network.model;

import androidx.annotation.NonNull;

import com.byteteam.douyin.logic.database.model.FansItem;

import com.byteteam.douyin.logic.network.response.DouYinBaseData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @introduction： 关注列表
 * @author： 陈光磊
 * @time： 2022/8/10 14:31
 */


public class FansData extends DouYinBaseData {

    // 关注者列表
    private List<FansItem> list;

    // 用于下一页请求的cursor
    private Long cursor;

    // 是否还有下一页
    @SerializedName("has_more")
    private boolean hasMore;

    // 关注者总数
    private Integer total;

    public List<FansItem> getList() {
        return list;
    }

    public void setList(List<FansItem> list) {
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
        return "FansData{" +
                "list=" + list +
                ", cursor=" + cursor +
                ", hasMore=" + hasMore +
                ", total=" + total +
                '}';
    }
}
