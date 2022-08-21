package com.byteteam.douyin.logic.dataSource;

import com.byteteam.douyin.logic.database.model.FansItem;

import java.util.List;

import io.reactivex.Maybe;

/**
 * @introduction： FansData接口类
 * @author： 陈光磊
 * @time： 2022/8/20
 */

public interface FansItemDataSource {
    Maybe<List<FansItem>> queryFans(int type);
}
