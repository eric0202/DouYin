package com.byteteam.douyin.ui.rank.holder;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.byteteam.douyin.databinding.ListItemFansBinding;
import com.byteteam.douyin.logic.database.model.FansItem;

/**
 * @introduction：
 * @author： 陈光磊
 * @time： 2022/8/20 14:37
 */
public class FansHolder extends BaseFansHolder{

    ListItemFansBinding binding;

    public FansHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void bind(FansItem fansItem) {
        binding.setFans(fansItem);
    }
}
