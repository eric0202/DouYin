package com.byteteam.douyin.logic.database.model;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @introduction： 粉丝实体类
 * @author： 林锦焜
 * @time： 2022/8/21 16:58
 */
@Entity
public class MyFans {

    @PrimaryKey(autoGenerate = true)
    private int id;

    // 性别: `0` - 未知， `1` - 男性， `2` - 女性
    private Integer gender;

    // 用户昵称
    private String nickname;

    // 用户头像
    private String avatar;

    // 用户所在城市
    private String city;

    // 用户所在省份
    private String province;

    // 用户所在国家
    private String country;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @NonNull
    @Override
    public String toString() {
        return "Fans{" +
                "id=" + id +
                ", gender=" + gender +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
