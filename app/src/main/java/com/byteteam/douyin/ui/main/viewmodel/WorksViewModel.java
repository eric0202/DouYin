package com.byteteam.douyin.ui.main.viewmodel;

import androidx.lifecycle.ViewModel;

import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.logic.dataSource.WorksDataSource;
import com.byteteam.douyin.logic.database.model.AccessToken;
import com.byteteam.douyin.logic.network.model.WorksResponse;

import io.reactivex.Maybe;


/**
 * @introduction：
 * @author： 林锦焜
 * @time： 2022/8/19 17:29
 */
public class WorksViewModel extends ViewModel {

    private final AccessTokenDataSource accessTokenDataSource;

    private final WorksDataSource worksDataSource;

    public WorksViewModel(AccessTokenDataSource accessTokenDataSource, WorksDataSource worksDataSource) {
        this.accessTokenDataSource = accessTokenDataSource;
        this.worksDataSource = worksDataSource;
    }

    public Maybe<AccessToken> getAccessToken() {
        return accessTokenDataSource.getAccessToken();
    }

    public Maybe<WorksResponse> queryMyWorks(AccessToken accessToken, int cursor) {
        return worksDataSource.queryMyWorks(accessToken.getAccessToken(), accessToken.getOpenId(), cursor);
    }

}
