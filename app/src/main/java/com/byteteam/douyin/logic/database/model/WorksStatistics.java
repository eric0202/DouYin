package com.byteteam.douyin.logic.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @introduction： 视频作品统计数据实体类
 * @author： 林锦焜
 * @time： 2022/8/18 23:13
 */
public class WorksStatistics implements Serializable {

    // 点赞数
    @ColumnInfo(name = "digg_count")
    @SerializedName("digg_count")
    private int diggCount;

    // 下载数
    @ColumnInfo(name = "download_count")
    @SerializedName("download_count")
    private int downloadCount;

    // 播放数，只有作者本人可见。公开视频设为私密后，播放数也会返回0
    @ColumnInfo(name = "play_count")
    @SerializedName("play_count")
    private int playCount;

    // 分享数
    @ColumnInfo(name = "share_count")
    @SerializedName("share_count")
    private int shareCount;

    // 转发数
    @ColumnInfo(name = "forward_count")
    @SerializedName("forward_count")
    private int forwardCount;

    // 评论数
    @ColumnInfo(name = "comment_count")
    @SerializedName("comment_count")
    private int commentCount;

    public int getDiggCount() {
        return diggCount;
    }

    public void setDiggCount(int diggCount) {
        this.diggCount = diggCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(int forwardCount) {
        this.forwardCount = forwardCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @NonNull
    @Override
    public String toString() {
        return "WorksStatistics{" +
                "diggCount=" + diggCount +
                ", downloadCount=" + downloadCount +
                ", playCount=" + playCount +
                ", shareCount=" + shareCount +
                ", forwardCount=" + forwardCount +
                ", commentCount=" + commentCount +
                '}';
    }
}
