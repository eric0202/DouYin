package com.byteteam.douyin.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ListItemMyFansHeaderBinding;
import com.byteteam.douyin.databinding.ListItemRankHeaderBinding;

/**
 * @introduction： 粉丝页面Header适配器
 * @author： 林锦焜
 * @time： 2022/8/15 22:00
 */
public class MyFansHeaderAdapter extends RecyclerView.Adapter<MyFansHeaderAdapter.RankHeaderHolder> {

    private int fansCount;

    public MyFansHeaderAdapter() {
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
        notifyItemChanged(0);
    }

    @NonNull
    @Override
    public RankHeaderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_fans_header, parent, false);
        return new RankHeaderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankHeaderHolder holder, int position) {
        holder.setFansCount(fansCount);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class RankHeaderHolder extends RecyclerView.ViewHolder {

        ListItemMyFansHeaderBinding binding;

        public RankHeaderHolder(@NonNull View itemView) {
            super(itemView);
            binding = ListItemMyFansHeaderBinding.bind(itemView);
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
