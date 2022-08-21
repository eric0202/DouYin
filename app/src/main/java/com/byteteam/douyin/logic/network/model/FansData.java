package com.byteteam.douyin.logic.network.model;

import com.byteteam.douyin.logic.network.response.DouYinBaseData;

import java.util.List;

/**
 * @introduction： 关注列表
 * @author： 陈光磊
 * @time： 2022/8/10 14:31
 */


public class FansData<T> extends DouYinBaseData {
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FansData{" +
                "list=" + list +
                ", errorCode=" + errorCode +
                ", description='" + description + '\'' +
                '}';
    }
}
