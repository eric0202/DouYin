package com.byteteam.douyin.logic.dataSource;

import com.byteteam.douyin.logic.network.model.FansData;
import com.byteteam.douyin.logic.network.model.WorksResponse;

import java.util.List;

import io.reactivex.Maybe;

/**
 * @introduction： FansData接口类
 * @author： 陈光磊
 * @time： 2022/8/20
 */

public interface FansItemDataSource {
    // 根据cursor查询粉丝列表
    Maybe<FansData> queryFans(String accessToken, String openId, long cursor);
}
