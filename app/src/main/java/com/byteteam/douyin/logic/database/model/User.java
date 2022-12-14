package com.byteteam.douyin.logic.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.byteteam.douyin.logic.network.response.DouYinBaseData;

/**
 * @introduction： User实体类
 * @author： 何文鹏
 * @time： 2022/8/16 21:53
 */

@Entity
public class User extends DouYinBaseData {

    @NonNull
    @PrimaryKey
    private String open_id = "";

    @ColumnInfo
    private String avatar;

    @ColumnInfo
    private String city;

    @ColumnInfo
    private String country;

    @ColumnInfo
    private String nickname;

    @ColumnInfo
    private String gender;

    @ColumnInfo
    private String background;

    public String getBackground() {
        return background;
    }


    public User(User user){
        this.open_id = user.open_id;
        this.gender = user.gender;
        this.city = user.city;
        this.avatar = user.avatar;
        this.country = user.country;
        this.background = user.background;
        this.nickname = user.nickname;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    private String introduction;

    private int subs;
    private int likes;
    private int fans;

    public User() {

    }


    public int getSubs() {
        return subs;
    }

    public void setSubs(int subs) {
        this.subs = subs;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public User(String uid) {
        this.open_id = uid;
    }

    @NonNull
    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String uid) {
        this.open_id = uid;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        if(gender == "1"){
            return "男";
        }
        else if (gender == "2"){
            return "女";
        }
        else {
            return "未知性别";
        }

    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @NonNull
    @Override
    public String toString() {
        return "Logged in user: {" +
                "uid: " + open_id +
                ", \tnickname= " + nickname +
                ", \tavatar= " + avatar +
                ", \tcity= " + city +
                ", \tcountry= "+ country+
                ", \tgender= "+ gender+
                ", \tbackground= "+ background+
                ", \tintroduction= "+introduction+ "}";
    }

}
