package com.byteteam.douyin.logic.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * @introduction： 榜单item数据实体类（电影、电视剧、综艺）
 * @author： 林锦焜
 * @time： 2022/8/10 13:37
 */
@Entity
public class RankItem {

    @PrimaryKey(autoGenerate = true)
    private int mid;

    // 导演
    private String[] directors;

    // 上映地区
    private String[] areas;

    // 演员
    private String[] actors;


    // 讨论热度
    @SerializedName("discussion_hot")
    @ColumnInfo(name = "discussion_hot")
    private long discussionHot;

    // 主题热度
    private long hot;

    // 搜索热度
    @SerializedName("search_hot")
    @ColumnInfo(name = "search_hot")
    private long searchHot;

    // 影响力热度
    @SerializedName("topic_hot")
    @ColumnInfo(name = "topic_hot")
    private long topicHot;

    // 片名
    private String name;

    // 海报缩略图链接
    private String poster;

    // 上映时间
    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    private String releaseDate;

    // 类型标签
    private String[] tags;

    // 英文片名
    @SerializedName("name_en")
    @ColumnInfo(name = "name_en")
    private String nameEn;

    // 猫眼id：只有电影榜返回，可能为空
    @SerializedName("maoyan_id")
    @ColumnInfo(name = "maoyan_id")
    private String maoyanId;

    // 类型：1=电影 2=电视剧 3=综艺
    private int type;


    // 版本号：0代表本周榜单
    private int version;


    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String[] getDirectors() {
        return directors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public long getDiscussionHot() {
        return discussionHot;
    }

    public void setDiscussionHot(long discussionHot) {
        this.discussionHot = discussionHot;
    }

    public long getHot() {
        return hot;
    }

    public void setHot(long hot) {
        this.hot = hot;
    }

    public long getSearchHot() {
        return searchHot;
    }

    public void setSearchHot(long searchHot) {
        this.searchHot = searchHot;
    }

    public long getTopicHot() {
        return topicHot;
    }

    public void setTopicHot(long topicHot) {
        this.topicHot = topicHot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getMaoyanId() {
        return maoyanId;
    }

    public void setMaoyanId(String maoyanId) {
        this.maoyanId = maoyanId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @NonNull
    @Override
    public String toString() {
        return "RankItem{" +
                "mid=" + mid +
                ", directors=" + Arrays.toString(directors) +
                ", areas=" + Arrays.toString(areas) +
                ", actors=" + Arrays.toString(actors) +
                ", discussionHot=" + discussionHot +
                ", hot=" + hot +
                ", searchHot=" + searchHot +
                ", topicHot=" + topicHot +
                ", name='" + name + '\'' +
                ", poster='" + poster + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", nameEn='" + nameEn + '\'' +
                ", maoyanId='" + maoyanId + '\'' +
                ", type=" + type +
                ", version=" + version +
                '}';
    }
}
