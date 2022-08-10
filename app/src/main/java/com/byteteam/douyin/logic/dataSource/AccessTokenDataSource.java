package com.byteteam.douyin.logic.dataSource;

import com.byteteam.douyin.logic.database.model.AccessToken;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * @introduction： AccessToken接口类
 * @author： 林锦焜
 * @time： 2022/8/8 20:23
 */
public interface AccessTokenDataSource {

    // 从数据库获取AccessToken
    Maybe<AccessToken> getAccessToken();

    // 添加AccessToken到数据库
    Completable insert(AccessToken accessToken);

    // 联网申请AccessToken
    Observable<AccessToken> requestAccessToken(String authCode);
}
