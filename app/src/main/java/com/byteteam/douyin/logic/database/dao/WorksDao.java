package com.byteteam.douyin.logic.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.byteteam.douyin.logic.database.model.RankList;
import com.byteteam.douyin.logic.database.model.Works;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * @introduction： 视频作品 Dao类
 * @author： 林锦焜
 * @time： 2022/8/10 13:37
 */
@Dao
public interface WorksDao {

    @Query("select * from works")
    Maybe<List<Works>> queryWorksList();

    @Insert
    Completable insert(List<Works> works);

    @Query("delete from works")
    Completable delete();

}
