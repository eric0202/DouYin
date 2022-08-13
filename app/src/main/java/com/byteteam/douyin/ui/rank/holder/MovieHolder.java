package com.byteteam.douyin.ui.rank.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.byteteam.douyin.databinding.ListItemMovieBinding;
import com.byteteam.douyin.logic.database.model.RankItem;

/**
 * @introduction：
 * @author： 林锦焜
 * @time： 2022/8/13 14:37
 */
public class MovieHolder extends BaseRankHolder {

    ListItemMovieBinding binding;

    public MovieHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void bind(RankItem rankItem) {
        binding.setMovie(rankItem);
    }
}
