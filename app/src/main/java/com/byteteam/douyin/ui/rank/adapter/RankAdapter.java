package com.byteteam.douyin.ui.rank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.logic.database.model.RankItem;
import com.byteteam.douyin.ui.rank.holder.BaseRankHolder;
import com.byteteam.douyin.ui.rank.holder.MovieHolder;
import com.byteteam.douyin.ui.rank.holder.TvHolder;
import com.byteteam.douyin.ui.rank.holder.VarietyHolder;

import java.util.List;

/**
 * @introduction： 榜单数据适配器
 * @author： 林锦焜
 * @time： 2022/8/10 21:37
 */
public class RankAdapter extends RecyclerView.Adapter<BaseRankHolder>{

    private List<RankItem> dates;

    // 榜单类型：1 电影 2 电视剧 3 综艺
    private final int type;

    public RankAdapter(List<RankItem> dates, int type) {
        this.dates = dates;
        this.type = type;
    }

    @NonNull
    @Override
    public BaseRankHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
            给不同类型的榜单指定不同的xml布局
         */
        if (viewType == 1) { // 电影榜单
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
            return new MovieHolder(itemView);
        } else if (viewType == 2) { // 电视剧榜单
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tv, parent, false);
            return new TvHolder(itemView);
        }
        // 综艺榜单
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_variety, parent, false);
        return new VarietyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRankHolder holder, int position) {
        // 将榜单数据绑定到榜单视图
        holder.bind(dates.get(position));
    }

    public void setDates(List<RankItem> dates) {
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
