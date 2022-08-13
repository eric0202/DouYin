package com.byteteam.douyin.ui.rank.fragment;

import androidx.lifecycle.ViewModel;

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

    private final RankItemRepository rankItemRepository;

    public RankViewModel(RankItemRepository rankItemRepository) {
        this.rankItemRepository = rankItemRepository;
    }

    public Maybe<List<RankItem>> getRankList(int type,int version) {
        return rankItemRepository.queryMovie(type,version);
    }

}