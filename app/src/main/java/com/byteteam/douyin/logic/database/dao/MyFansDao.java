package com.byteteam.douyin.logic.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.byteteam.douyin.logic.database.model.MyFans;
import com.byteteam.douyin.logic.database.model.Works;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * @introduction： 我的粉丝 Dao类
 * @author： 林锦焜
 * @time： 2022/8/10 13:37
 */
@Dao
public interface MyFansDao {

    @Query("select * from myfans")
    Maybe<List<MyFans>> queryFansList();

    @Insert
    Completable insert(List<MyFans> myFans);

    @Query("delete from myfans")
    Completable delete();

}
