package com.byteteam.douyin.ui.rank.fragment;

import androidx.lifecycle.ViewModel;

import com.byteteam.douyin.logic.dataSource.RankItemDataSource;
import com.byteteam.douyin.logic.database.model.RankItem;
import com.byteteam.douyin.logic.repository.RankItemRepository;

import java.util.List;

import io.reactivex.Maybe;

/**
 * @introduction： 电影榜单ViewModel
 * @author： 林锦焜
 * @time： 2022/8/7 18:10
 */
public class RankViewModel extends ViewModel {

    private final RankItemDataSource rankItemDataSource;

    public RankViewModel(RankItemDataSource rankItemDataSource) {
        this.rankItemDataSource = rankItemDataSource;
    }

    // 获取榜单数据列表Maybe对象
    public Maybe<List<RankItem>> getRankList(int type,int version) {
        return rankItemDataSource.queryMovie(type,version);
    }

}