package com.byteteam.douyin.logic.dataSource;

import com.byteteam.douyin.logic.database.model.RankList;
import com.byteteam.douyin.logic.network.model.RankVersion;

import java.util.List;

import io.reactivex.Maybe;

/**
 * @introduction： RanList接口类
 * @author： 林锦焜
 * @time： 2022/8/8 20:23
 */
public interface RankListDataSource {

    // 根据类型与cursor查询历史榜单列表
    Maybe<RankVersion> queryRankList(int type, long cursor);

}
