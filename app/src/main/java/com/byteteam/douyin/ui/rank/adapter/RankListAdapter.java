package com.byteteam.douyin.ui.rank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ListItemRankVersionBinding;
import com.byteteam.douyin.logic.database.model.RankList;

import java.util.List;

/**
 * @introduction： 历史榜单适配器
 * @author： 林锦焜
 * @time： 2022/8/14 15:36
 */
public class RankListAdapter extends RecyclerView.Adapter<RankListAdapter.RankVersionHolder> {

    private final List<RankList> dates;

    private final OnItemClickListener onItemClickListener;

    public RankListAdapter(List<RankList> dates,OnItemClickListener onItemClickListener) {
        this.dates = dates;
        this.onItemClickListener = onItemClickListener;
    }

    public void addDate(List<RankList> rankLists) {
        dates.addAll(rankLists);
    }

    @NonNull
    @Override
    public RankVersionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rank_version, parent, false);
        return new RankVersionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RankVersionHolder holder, int position) {
        holder.bind(dates.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public class RankVersionHolder extends RecyclerView.ViewHolder {

        ListItemRankVersionBinding binding;

        RankList rankList;

        public RankVersionHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            if (onItemClickListener != null) {
                itemView.setOnClickListener(v -> onItemClickListener.onClick(rankList));
            }
        }

        public void bind(RankList rankList) {
            this.rankList = rankList;
            binding.setVersion(rankList);
        }

    }

    // 点击事件接口
    public interface OnItemClickListener {
        void onClick(RankList rankList);
    }

}
