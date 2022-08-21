package com.byteteam.douyin.logic.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;

/**
 * @introduction： 粉丝item数据实体类
 * @author： 陈光磊
 * @time： 2022/8/20 13:37
 */

@Entity
public class FansItem {
    @PrimaryKey(autoGenerate = true)
    private int mid;

    // 关注者姓名
    private String name;

    // 关注者作品数
    private int artNum;

    // 1代表关注者，2代表粉丝
    private int type;

    // 头像缩略图链接
    private String poster;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getArtNum() {
        return artNum;
    }

    public void setArtNum(int artNum) {
        this.artNum = artNum;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "FansItem{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", artNum=" + artNum +
                ", type=" + type +
                ", poster='" + poster + '\'' +
                '}';
    }
}
