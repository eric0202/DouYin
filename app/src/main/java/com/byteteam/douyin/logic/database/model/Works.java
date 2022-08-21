package com.byteteam.douyin.logic.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.byteteam.douyin.logic.network.response.DouYinBaseData;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @introduction： 视频作品实体类
 * @author： 林锦焜
 * @time： 2022/8/18 23:05
 */
@Entity
public class Works implements Serializable {

    // 视频id
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "item_id")
    @SerializedName("item_id")
    private String itemId = "";

    // 媒体类型。2:图集;4:视频
    @ColumnInfo(name = "media_type")
    @SerializedName("media_type")
    private int mediaType;

    // 	视频标题
    private String title;

    // 视频封面
    private String cover;

    // 是否置顶
    @ColumnInfo(name = "is_top")
    @SerializedName("is_top")
    private boolean isTop;

    // 视频创建时间戳
    @ColumnInfo(name = "create_time")
    @SerializedName("create_time")
    private Long createTime;

    // 表示是否审核结束。审核通过或者失败都会返回true，审核中返回false。
    @ColumnInfo(name = "is_reviewed")
    @SerializedName("is_reviewed")
    private boolean isReviewed;

    // 表示视频状态。1:已发布; 2:不适宜公开; 4:审核中
    @ColumnInfo(name = "video_status")
    @SerializedName("video_status")
    private int videoStatus;

    // 视频真实ID
    @ColumnInfo(name = "video_id")
    @SerializedName("video_id")
    private Long videoId;

    // 视频播放页面。视频播放页可能会失效，请在观看视频前调用/video/data/获取最新的播放页（视频失效指视频被删除或隐藏）。
    @ColumnInfo(name = "share_url")
    @SerializedName("share_url")
    private String shareUrl;

    // 统计数据
    @Embedded
    private WorksStatistics statistics;

    @NonNull
    public String getItemId() {
        return itemId;
    }

    public void setItemId(@NonNull String itemId) {
        this.itemId = itemId;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public boolean isReviewed() {
        return isReviewed;
    }

    public void setReviewed(boolean reviewed) {
        isReviewed = reviewed;
    }

    public int getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(int videoStatus) {
        this.videoStatus = videoStatus;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public WorksStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(WorksStatistics statistics) {
        this.statistics = statistics;
    }

    @NonNull
    @Override
    public String toString() {
        return "Works{" +
                "itemId='" + itemId + '\'' +
                ", mediaType=" + mediaType +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", isTop=" + isTop +
                ", createTime=" + createTime +
                ", isReviewed=" + isReviewed +
                ", videoStatus=" + videoStatus +
                ", videoId=" + videoId +
                ", shareUrl='" + shareUrl + '\'' +
                ", statistics=" + statistics +
                '}';
    }
}
