package com.byteteam.douyin.logic.network.model;

import androidx.annotation.NonNull;

import com.byteteam.douyin.logic.network.response.DouYinBaseData;

import java.util.List;

/**
 * @introduction： 榜单列表数据
 * @author： 林锦焜
 * @time： 2022/8/10 14:31
 */
public class RankData<T> extends DouYinBaseData {

    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public String toString() {
        return "RankData{" +
                "list=" + list +
                ", errorCode=" + errorCode +
                ", description='" + description + '\'' +
                '}';
    }
}
