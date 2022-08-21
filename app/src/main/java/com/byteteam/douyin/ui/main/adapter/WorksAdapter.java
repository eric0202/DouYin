package com.byteteam.douyin.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ListItemRankVersionBinding;
import com.byteteam.douyin.databinding.ListItemWorksBinding;
import com.byteteam.douyin.logic.database.model.RankList;
import com.byteteam.douyin.logic.database.model.Works;

import java.util.List;

/**
 * @introduction： 个人作品适配器
 * @author： 林锦焜
 * @time： 2022/8/19 15:36
 */
public class WorksAdapter extends RecyclerView.Adapter<WorksAdapter.WorksHolder> {

    private final List<Works> dates;

    private final OnItemClickListener onItemClickListener;

    public WorksAdapter(List<Works> dates, OnItemClickListener onItemClickListener) {
        this.dates = dates;
        this.onItemClickListener = onItemClickListener;
    }

    public void addDate(List<Works> works) {
        dates.addAll(works);
    }

    @NonNull
    @Override
    public WorksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_works, parent, false);
        return new WorksHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorksHolder holder, int position) {
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

    public class WorksHolder extends RecyclerView.ViewHolder {

        ListItemWorksBinding binding;

        Works works;

        public WorksHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            if (onItemClickListener != null) {
                itemView.setOnClickListener(v -> onItemClickListener.onClick(works));
            }
        }

        public void bind(Works works) {
            this.works = works;
            binding.setItem(works);
        }

    }

    // 点击事件接口
    public interface OnItemClickListener {
        void onClick(Works works);
    }

}
