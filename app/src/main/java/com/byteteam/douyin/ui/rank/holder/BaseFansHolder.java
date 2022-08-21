package com.byteteam.douyin.ui.rank.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.logic.database.model.FansItem;

/**
 * @introduction：
 * @author： 陈光磊
 * @time： 2022/8/13 14:36
 */

public abstract class BaseFansHolder extends RecyclerView.ViewHolder{
    public BaseFansHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(FansItem fansItem);

}
