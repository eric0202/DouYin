package com.byteteam.douyin.logic.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.byteteam.douyin.logic.database.model.AccessToken;

import io.reactivex.Completable;
import io.reactivex.Maybe;

@Dao
public interface AccessTokenDao {

    @Query("select * from accesstoken where expiresIn - :curTime > 1000 * 60 limit 1")
    Maybe<AccessToken> getAccessToken(long curTime);

    @Insert
    Completable insert(AccessToken accessToken);

}
