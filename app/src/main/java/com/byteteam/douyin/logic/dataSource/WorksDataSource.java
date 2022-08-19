package com.byteteam.douyin.logic.dataSource;

import com.byteteam.douyin.logic.network.model.WorksResponse;

import io.reactivex.Maybe;

/**
 * @introduction： Works接口类
 * @author： 林锦焜
 * @time： 2022/8/8 20:23
 */
public interface WorksDataSource {

    // 根据cursor查询历史榜单列表
    Maybe<WorksResponse> queryMyWorks(String accessToken, String openId, long cursor);

}
