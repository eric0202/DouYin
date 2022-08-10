package com.byteteam.douyin.logic.dataSource;

import com.byteteam.douyin.logic.database.model.ClientToken;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * @introduction： ClientToken接口类
 * @author： 林锦焜
 * @time： 2022/8/8 20:23
 */
public interface ClientTokenDataSource {

    // 从数据库获取ClientToken
    Maybe<ClientToken> getClientToken();

    // 添加ClientToken到数据库
    Completable insert(ClientToken clientToken);
}
