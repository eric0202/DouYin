package com.byteteam.douyin.ui.rank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.logic.database.model.FansItem;
import com.byteteam.douyin.logic.database.model.RankItem;
import com.byteteam.douyin.ui.rank.holder.BaseFansHolder;
import com.byteteam.douyin.ui.rank.holder.FansHolder;
import com.byteteam.douyin.ui.rank.holder.VarietyHolder;

import java.util.List;

/**
 * @introduction： 粉丝数据适配器
 * @author： 陈光磊
 * @time： 2022/8/20 21:37
 */


public class FansAdapter extends RecyclerView.Adapter<BaseFansHolder>{


    private List<FansItem> dates;

    // 榜单类型：1 关注者 2 粉丝
    private final int type;

    public FansAdapter(List<FansItem> dates, int type) {
        this.dates = dates;
        this.type = type;
    }

    @NonNull
    @Override
    public BaseFansHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
            给不同类型的榜单指定不同的xml布局
         */
        if (viewType == 1) { // 关注者
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_fans, parent, false);
            return new FansHolder(itemView);
        }
        // 粉丝
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_fans, parent, false);
        return new FansHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull BaseFansHolder holder, int position) {
        holder.bind(dates.get(position));
    }

    public void setDates(List<FansItem> dates) {
        this.dates = dates;
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
