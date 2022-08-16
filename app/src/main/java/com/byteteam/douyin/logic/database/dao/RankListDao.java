package com.byteteam.douyin.logic.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.byteteam.douyin.logic.database.model.RankItem;
import com.byteteam.douyin.logic.database.model.RankList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * @introduction： 历史榜单列表 Dao类
 * @author： 林锦焜
 * @time： 2022/8/10 13:37
 */
@Dao
public interface RankListDao {

    @Query("select * from ranklist where type == :type")
    Maybe<List<RankList>> queryRankList(int type);

    @Insert
    Completable insert(List<RankList> rankLists);

    @Query("delete from ranklist where type == :type")
    Completable delete(int type);

}
