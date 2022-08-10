package com.byteteam.douyin.logic.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.byteteam.douyin.logic.database.model.ClientToken;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * @introduction： ClientToken Dao类
 * @author： 林锦焜
 * @time： 2022/8/10 13:37
 */
@Dao
public interface ClientTokenDao {

    @Query("select * from clienttoken where expiresIn - :curTime > 1000 * 60 limit 1")
    Maybe<ClientToken> getClientToken(long curTime);

    @Insert
    Completable insert(ClientToken clientToken);

}
