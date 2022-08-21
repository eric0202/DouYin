package com.byteteam.douyin.ui.rank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ListItemFansBinding;
import com.byteteam.douyin.databinding.ListItemMyFansBinding;
import com.byteteam.douyin.logic.database.model.FansItem;
import com.byteteam.douyin.logic.database.model.MyFans;
import com.byteteam.douyin.logic.database.model.RankItem;
import com.byteteam.douyin.ui.main.adapter.MyFansAdapter;
import com.byteteam.douyin.ui.rank.holder.BaseFansHolder;
import com.byteteam.douyin.ui.rank.holder.FansHolder;
import com.byteteam.douyin.ui.rank.holder.VarietyHolder;

import java.util.List;

/**
 * @introduction： 粉丝数据适配器
 * @author： 陈光磊
 * @time： 2022/8/20 21:37
 */


public class FansAdapter extends RecyclerView.Adapter<FansAdapter.FansHolder>{


    private final List<FansItem> dates;

    public FansAdapter(List<FansItem> dates) {
        this.dates = dates;
    }

    public void addDate(List<FansItem> works) {
        dates.addAll(works);
    }

    @NonNull
    @Override
    public FansAdapter.FansHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_fans, parent, false);
        return new FansHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FansAdapter.FansHolder holder, int position) {
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

        ListItemFansBinding binding;

        public FansHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(FansItem fansItem) {
            binding.setItem(fansItem);
        }

    }


}
