package com.byteteam.douyin.ui.custom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.ListItemFooterBinding;
import com.byteteam.douyin.databinding.ListItemRankHeaderBinding;

/**
 * @introduction： 页脚适配器
 * @author： 林锦焜
 * @time： 2022/8/15 22:00
 */
public class FooterAdapter extends RecyclerView.Adapter<FooterAdapter.FooterHolder> {

    // 状态：0 没有更多了  1 加载更多
    private int status;

    public FooterAdapter() {
    }

    public FooterAdapter(int status) {
        this.status = status;
    }

    public void changeStatus(int status) {
        this.status = status;
        notifyItemChanged(0);
    }

    @NonNull
    @Override
    public FooterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_footer, parent, false);
        return new FooterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FooterHolder holder, int position) {
        holder.changeStatus(status);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class FooterHolder extends RecyclerView.ViewHolder {

        ListItemFooterBinding binding;

        public FooterHolder(@NonNull View itemView) {
            super(itemView);
            binding = ListItemFooterBinding.bind(itemView);
        }

        public void changeStatus(int status) {
            if (status == 1) {
                binding.loading.setVisibility(View.VISIBLE);
                binding.messageText.setText("正在加载中...");
            } else {
                binding.loading.setVisibility(View.GONE);
                binding.messageText.setText("没有更多了");
            }
        }

    }

}
