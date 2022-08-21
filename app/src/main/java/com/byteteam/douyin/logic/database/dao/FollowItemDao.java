package com.byteteam.douyin.logic.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.byteteam.douyin.logic.database.model.FollowItem;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

@Dao
public interface FollowItemDao {
    @Query("select * from FollowItem")
    Maybe<List<FollowItem>> queryFansList();

    @Insert
    Completable insert(List<FollowItem> followItem);

    @Query("delete from FollowItem")
    Completable delete();
}
