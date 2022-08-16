package com.byteteam.douyin.ui.rank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ListItemRankHeaderBinding;
import com.byteteam.douyin.logic.database.model.RankList;

/**
 * @introduction： 榜单列表Header适配器
 * @author： 林锦焜
 * @time： 2022/8/15 22:00
 */
public class RankHeaderAdapter extends RecyclerView.Adapter<RankHeaderAdapter.RankHeaderHolder> {


    private final OnItemClickListener onItemClickListener;

    public RankHeaderAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public RankHeaderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rank_header, parent, false);
        return new RankHeaderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankHeaderHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class RankHeaderHolder extends RecyclerView.ViewHolder {

        ListItemRankHeaderBinding binding;

        public RankHeaderHolder(@NonNull View itemView) {
            super(itemView);
            binding = ListItemRankHeaderBinding.bind(itemView);
            if (onItemClickListener != null) {
                binding.getRoot().setOnClickListener(v -> onItemClickListener.onClick());
            }
        }

    }

    // 点击事件接口
    public interface OnItemClickListener {
        void onClick();
    }
}
