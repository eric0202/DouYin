package com.byteteam.douyin.logic.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.byteteam.douyin.logic.database.model.AccessToken;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * @introduction： AccessToken Dao类
 * @author： 林锦焜
 * @time： 2022/8/10 13:37
 */
@Dao
public interface AccessTokenDao {

    @Query("select * from accesstoken where expiresIn - :curTime > 1000 * 60 limit 1")
    Maybe<AccessToken> getAccessToken(long curTime);

    @Insert
    Completable insert(AccessToken accessToken);

}
