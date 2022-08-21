package com.byteteam.douyin.logic.dataSource;

import com.byteteam.douyin.logic.network.model.MyFansData;
import com.byteteam.douyin.logic.network.model.WorksResponse;

import io.reactivex.Maybe;

/**
 * @introduction： MyFans接口类
 * @author： 林锦焜
 * @time： 2022/8/8 20:23
 */
public interface MyFansDataSource {

    // 根据cursor查询粉丝列表
    Maybe<MyFansData> queryMyFans(String accessToken, String openId, long cursor);

}
