package com.byteteam.douyin.logic.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.byteteam.douyin.logic.database.model.User;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * @introduction： UserDao
 * @author： 何文鹏
 * @time： 2022/8/16 21:53
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    Maybe<User> getUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(User user);

    @Delete()
    Completable deleteUser(User user);
}
