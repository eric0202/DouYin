package com.byteteam.douyin.logic.network.model;

import androidx.annotation.NonNull;

import com.byteteam.douyin.logic.database.model.RankList;
import com.byteteam.douyin.logic.database.model.Works;
import com.byteteam.douyin.logic.network.response.DouYinBaseData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @introduction： 我的作品数据
 * @author： 林锦焜
 * @time： 2022/8/14 14:08
 */
public class WorksResponse extends DouYinBaseData {

    // 版本列表
    private List<Works> list;

    // 用于下一页请求的cursor
    private Long cursor;

    // 是否还有下一页
    @SerializedName("has_more")
    private boolean hasMore;


    public List<Works> getList() {
        return list;
    }

    public void setList(List<Works> list) {
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

    @NonNull
    @Override
    public String toString() {
        return "WorksResponse{" +
                "list=" + list +
                ", cursor=" + cursor +
                ", hasMore=" + hasMore +
                '}';
    }
}
