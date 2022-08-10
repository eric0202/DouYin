package com.byteteam.douyin.logic.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * @introduction： 榜单版本数据实体类（电影、电视剧、综艺）
 * @author： 林锦焜
 * @time： 2022/8/10 22:46
 */
@Entity
public class RankList {

    @PrimaryKey(autoGenerate = true)
    private int id;

    // 榜单生成时间
    @SerializedName("active_time")
    @ColumnInfo(name = "active_time")
    private String activeTime;

    // 榜单结束时间
    @SerializedName("end_time")
    @ColumnInfo(name = "end_time")
    private String endTime;

    // 榜单起始时间
    @SerializedName("start_time")
    @ColumnInfo(name = "start_time")
    private String startTime;

    // 类型：1=电影 2=电视剧 3=综艺
    private int type;

    // 榜单版本
    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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
        return "RankList{" +
                "id=" + id +
                ", activeTime='" + activeTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", startTime='" + startTime + '\'' +
                ", type=" + type +
                ", version=" + version +
                '}';
    }
}
