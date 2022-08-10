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
public class MovieViewModel extends ViewModel {

    private final RankItemRepository rankItemRepository;

    public MovieViewModel(RankItemRepository rankItemRepository) {
        this.rankItemRepository = rankItemRepository;
    }

    public Maybe<List<RankItem>> getRankList(int version) {
        return rankItemRepository.queryMovie(1,version);
    }

}