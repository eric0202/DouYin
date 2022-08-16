package com.byteteam.douyin.logic.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.byteteam.douyin.logic.database.model.RankItem;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * @introduction： 榜单数据 Dao类
 * @author： 林锦焜
 * @time： 2022/8/10 13:37
 */
@Dao
public interface RankItemDao {

    @Query("select * from rankitem where type == :type and version == :version")
    Maybe<List<RankItem>> queryRank(int type, int version);

    @Insert
    Completable insert(List<RankItem> rankItems);

    @Query("delete from rankitem where type == :type and version == :version")
    Completable delete(int type,int version);

}
