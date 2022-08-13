package com.byteteam.douyin.ui.rank.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.byteteam.douyin.databinding.ListItemTvBinding;
import com.byteteam.douyin.databinding.ListItemVarietyBinding;
import com.byteteam.douyin.logic.database.model.RankItem;

/**
 * @introduction：
 * @author： 林锦焜
 * @time： 2022/8/13 14:37
 */
public class VarietyHolder extends BaseRankHolder {

    ListItemVarietyBinding binding;

    public VarietyHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void bind(RankItem rankItem) {
        binding.setMovie(rankItem);
    }
}
