package com.byteteam.douyin.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ListItemGridBinding;
import com.byteteam.douyin.logic.database.model.Works;

import java.util.List;

/**
 * @introduction：
 * @author： 林锦焜
 * @time： 2022/8/20 11:24
 */
public class BlankAdapter extends RecyclerView.Adapter<BlankAdapter.BlankHolder> {

    List<Works> worksList;

    public BlankAdapter(List<Works> worksList) {
        this.worksList = worksList;
    }

    @NonNull
    @Override
    public BlankHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_grid, parent, false);
        return new BlankHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlankHolder holder, int position) {
        holder.bind(worksList.get(position));
    }

    @Override
    public int getItemCount() {
        return worksList.size();
    }

    public static class BlankHolder extends RecyclerView.ViewHolder {

        ListItemGridBinding binding;

        public BlankHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(Works works) {
            binding.setItem(works);
        }

    }

}
