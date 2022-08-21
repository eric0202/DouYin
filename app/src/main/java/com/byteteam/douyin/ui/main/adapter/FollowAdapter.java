package com.byteteam.douyin.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ListItemFollowBinding;
import com.byteteam.douyin.logic.database.model.FollowItem;

import java.util.List;

/**
 * @introduction： 粉丝数据适配器
 * @author： 陈光磊
 * @time： 2022/8/20 21:37
 */


public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.FansHolder>{


    private final List<FollowItem> dates;

    public FollowAdapter(List<FollowItem> dates) {
        this.dates = dates;
    }

    public void addDate(List<FollowItem> works) {
        dates.addAll(works);
    }

    @NonNull
    @Override
    public FollowAdapter.FansHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_follow, parent, false);
        return new FansHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowAdapter.FansHolder holder, int position) {
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

    public static class FansHolder extends RecyclerView.ViewHolder {

        ListItemFollowBinding binding;

        public FansHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(FollowItem followItem) {
            binding.setItem(followItem);
        }

    }


}
