package com.byteteam.douyin.ui.rank.fragment;

import androidx.lifecycle.ViewModel;

import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.logic.dataSource.FansItemDataSource;
import com.byteteam.douyin.logic.dataSource.MyFansDataSource;
import com.byteteam.douyin.logic.database.model.AccessToken;
import com.byteteam.douyin.logic.database.model.FansItem;
import com.byteteam.douyin.logic.network.model.FansData;
import com.byteteam.douyin.logic.network.model.MyFansData;

import java.util.List;

import io.reactivex.Maybe;

/**
 * @introduction： 粉丝列表ViewModel
 * @author： 陈光磊
 * @time： 2022/8/21
 */

public class FansViewModel extends ViewModel {
    private final AccessTokenDataSource accessTokenDataSource;

    private final FansItemDataSource fansItemDataSource;

    public FansViewModel(AccessTokenDataSource accessTokenDataSource, FansItemDataSource fansItemDataSource) {
        this.accessTokenDataSource = accessTokenDataSource;
        this.fansItemDataSource = fansItemDataSource;
    }

    public Maybe<AccessToken> getAccessToken() {
        return accessTokenDataSource.getAccessToken();
    }

    public Maybe<FansData> queryFans(AccessToken accessToken, int cursor) {
        return fansItemDataSource.queryFans(accessToken.getAccessToken(), accessToken.getOpenId(), cursor);
    }
}
