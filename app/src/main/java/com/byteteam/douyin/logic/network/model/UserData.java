package com.byteteam.douyin.logic.network.model;

import androidx.annotation.NonNull;

import com.byteteam.douyin.logic.network.response.DouYinBaseData;
import com.google.gson.annotations.SerializedName;

public class UserData extends DouYinBaseData {

    @SerializedName("open_id")
    private String open_id;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("country")
    private String country;

    @SerializedName("city")
    private String city;

    @SerializedName("gender")
    private String gender;

    @SerializedName("nickname")
    private String nickname;

    private String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @NonNull
    @Override
    public String toString(){
        return "UserData{" +
                "id='" + open_id + '/' +
                ", avatar=" +avatar + '/'+
                ", country=" + country+ '/' +
                ", city=" + city+ '/'+
                ", gender=" + gender+ '/'+
                ", nickname=" + nickname + '/'
                + '}';

    }

}
