package com.byteteam.douyin.ui.rank.fragment;

import androidx.lifecycle.ViewModel;

import com.byteteam.douyin.logic.dataSource.FansItemDataSource;
import com.byteteam.douyin.logic.database.model.FansItem;

import java.util.List;

import io.reactivex.Maybe;

/**
 * @introduction： 粉丝列表ViewModel
 * @author： 陈光磊
 * @time： 2022/8/21
 */

public class FansViewModel extends ViewModel {
    private final FansItemDataSource fansItemDataSource;

    public FansViewModel(FansItemDataSource fansItemDataSource) {
        this.fansItemDataSource = fansItemDataSource;
    }

    // 获取榜单数据列表Maybe对象
    public Maybe<List<FansItem>> getFansList(int type) {
        return fansItemDataSource.queryFans(type);
    }
}
