package com.byteteam.douyin.ui.rank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ListItemFansHeaderBinding;
import com.byteteam.douyin.databinding.ListItemRankHeaderBinding;


public class FansHeaderAdapter extends RecyclerView.Adapter<FansHeaderAdapter.RankHeaderHolder>{
    private int fansCount;

    public FansHeaderAdapter() {
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
        notifyItemChanged(0);
    }

    @NonNull
    @Override
    public FansHeaderAdapter.RankHeaderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_fans_header, parent, false);
        return new FansHeaderAdapter.RankHeaderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FansHeaderAdapter.RankHeaderHolder holder, int position) {
        holder.setFansCount(fansCount);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class RankHeaderHolder extends RecyclerView.ViewHolder {

        ListItemFansHeaderBinding binding;

        public RankHeaderHolder(@NonNull View itemView) {
            super(itemView);
            binding = ListItemFansHeaderBinding.bind(itemView);
        }

        public void setFansCount(int count) {
            if (count < 0) {
                binding.fansTextView.setText("网络请求失败");
            } else if (count == 0) {
                binding.fansTextView.setText("你还没有粉丝哦");
            } else {
                binding.fansTextView.setText("我的粉丝(" + count + "人)");
            }
        }

    }
}
