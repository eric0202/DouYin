package com.byteteam.douyin.logic.dataSource;

import com.byteteam.douyin.logic.database.model.RankItem;

import java.util.List;

import io.reactivex.Maybe;

/**
 * @introduction： RankItem接口类
 * @author： 林锦焜
 * @time： 2022/8/8 20:23
 */
public interface RankItemDataSource {

    // 根据类型与版本号查询榜单
    Maybe<List<RankItem>> queryMovie(int type, int version);

}
