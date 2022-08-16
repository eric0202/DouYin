package com.byteteam.douyin.ui.rank;

import androidx.lifecycle.ViewModel;

import com.byteteam.douyin.logic.dataSource.RankListDataSource;
import com.byteteam.douyin.logic.database.model.RankList;
import com.byteteam.douyin.logic.network.model.RankVersion;

import java.util.List;

import io.reactivex.Maybe;

/**
 * @introduction：
 * @author： 林锦焜
 * @time： 2022/8/14 15:42
 */
public class RankListViewModel extends ViewModel {

    private final RankListDataSource rankListDataSource;

    public RankListViewModel(RankListDataSource rankListDataSource) {
        this.rankListDataSource = rankListDataSource;
    }

    public Maybe<RankVersion> getRankList(int type, long cursor) {
        return rankListDataSource.queryRankList(type, cursor);
    }

}
