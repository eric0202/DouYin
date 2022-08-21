package com.byteteam.douyin.ui.main.viewmodel;

import androidx.lifecycle.ViewModel;

import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.logic.dataSource.FansItemDataSource;
import com.byteteam.douyin.logic.database.model.AccessToken;
import com.byteteam.douyin.logic.network.model.FollowData;

import io.reactivex.Maybe;

/**
 * @introduction： 粉丝列表ViewModel
 * @author： 陈光磊
 * @time： 2022/8/21
 */

public class FollowViewModel extends ViewModel {
    private final AccessTokenDataSource accessTokenDataSource;

    private final FansItemDataSource fansItemDataSource;

    public FollowViewModel(AccessTokenDataSource accessTokenDataSource, FansItemDataSource fansItemDataSource) {
        this.accessTokenDataSource = accessTokenDataSource;
        this.fansItemDataSource = fansItemDataSource;
    }

    public Maybe<AccessToken> getAccessToken() {
        return accessTokenDataSource.getAccessToken();
    }

    public Maybe<FollowData> queryFans(AccessToken accessToken, int cursor) {
        return fansItemDataSource.queryFans(accessToken.getAccessToken(), accessToken.getOpenId(), cursor);
    }
}
