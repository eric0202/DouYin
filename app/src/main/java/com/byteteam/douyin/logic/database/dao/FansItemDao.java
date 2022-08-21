package com.byteteam.douyin.logic.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.byteteam.douyin.logic.database.model.FansItem;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface FansItemDao {
    @Query("select * from fansitem where type == :type")
    Maybe<List<FansItem>> queryFans(int type);

    @Insert
    Completable insert(List<FansItem> fansItem);

    @Query("delete from fansitem where type == :type")
    Completable delete(int type);
}
