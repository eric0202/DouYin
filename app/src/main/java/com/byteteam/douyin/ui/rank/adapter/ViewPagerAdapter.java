package com.byteteam.douyin.ui.rank.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * @introduction： 主界面ViewPager适配器类
 * @author： 林锦焜
 * @time： 2022/8/9 22:23
 */
public class ViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> list;

    public ViewPagerAdapter(List<Fragment> list, @NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
