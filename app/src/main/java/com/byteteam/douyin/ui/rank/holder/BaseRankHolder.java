package com.byteteam.douyin.ui.rank.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.logic.database.model.RankItem;

/**
 * @introduction：
 * @author： 林锦焜
 * @time： 2022/8/13 14:36
 */
public abstract class BaseRankHolder extends RecyclerView.ViewHolder {

    public BaseRankHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(RankItem rankItem);

}
