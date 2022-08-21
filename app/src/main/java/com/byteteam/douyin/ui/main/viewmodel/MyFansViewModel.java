package com.byteteam.douyin.ui.main.viewmodel;

import androidx.lifecycle.ViewModel;

import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.logic.dataSource.MyFansDataSource;
import com.byteteam.douyin.logic.dataSource.WorksDataSource;
import com.byteteam.douyin.logic.database.model.AccessToken;
import com.byteteam.douyin.logic.network.model.MyFansData;
import com.byteteam.douyin.logic.network.model.WorksResponse;

import io.reactivex.Maybe;


/**
 * @introduction：
 * @author： 林锦焜
 * @time： 2022/8/19 17:29
 */
public class MyFansViewModel extends ViewModel {

    private final AccessTokenDataSource accessTokenDataSource;

    private final MyFansDataSource myFansDataSource;

    public MyFansViewModel(AccessTokenDataSource accessTokenDataSource, MyFansDataSource myFansDataSource) {
        this.accessTokenDataSource = accessTokenDataSource;
        this.myFansDataSource = myFansDataSource;
    }

    public Maybe<AccessToken> getAccessToken() {
        return accessTokenDataSource.getAccessToken();
    }

    public Maybe<MyFansData> queryMyFans(AccessToken accessToken, int cursor) {
        return myFansDataSource.queryMyFans(accessToken.getAccessToken(), accessToken.getOpenId(), cursor);
    }

}
