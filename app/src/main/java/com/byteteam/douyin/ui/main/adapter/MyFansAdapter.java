package com.byteteam.douyin.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ListItemMyFansBinding;
import com.byteteam.douyin.databinding.ListItemWorksBinding;
import com.byteteam.douyin.logic.database.model.MyFans;
import com.byteteam.douyin.logic.database.model.Works;

import java.util.List;

/**
 * @introduction： 个人粉丝适配器
 * @author： 林锦焜
 * @time： 2022/8/19 15:36
 */
public class MyFansAdapter extends RecyclerView.Adapter<MyFansAdapter.MyFansHolder> {

    private final List<MyFans> dates;

    public MyFansAdapter(List<MyFans> dates) {
        this.dates = dates;
    }

    public void addDate(List<MyFans> works) {
        dates.addAll(works);
    }

    @NonNull
    @Override
    public MyFansHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_fans, parent, false);
        return new MyFansHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFansHolder holder, int position) {
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

    public static class MyFansHolder extends RecyclerView.ViewHolder {

        ListItemMyFansBinding binding;

        public MyFansHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(MyFans myFans) {
            binding.setItem(myFans);
        }

    }

}
