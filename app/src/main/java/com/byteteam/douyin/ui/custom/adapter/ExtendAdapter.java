package com.byteteam.douyin.ui.custom.adapter;

import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @introduction： 简易的支持Header、Footer的适配器
 * @author： 林锦焜
 * @time： 2022/8/16 14:21
 */
public class ExtendAdapter {

    // 头部适配器
    private final RecyclerView.Adapter<? extends RecyclerView.ViewHolder> headerAdapter;

    // 核心适配器
    private final RecyclerView.Adapter<? extends RecyclerView.ViewHolder> rootAdapter;

    // 尾部适配器
    private final FooterAdapter footerAdapter;

    // 结果适配器
    private final ConcatAdapter adapter;

    public ExtendAdapter(RecyclerView.Adapter<? extends RecyclerView.ViewHolder> rootAdapter) {
        this(null, rootAdapter, false);
    }

    public ExtendAdapter(RecyclerView.Adapter<? extends RecyclerView.ViewHolder> rootAdapter, boolean needFooter) {
        this(null, rootAdapter, needFooter);
    }

    public ExtendAdapter(RecyclerView.Adapter<? extends RecyclerView.ViewHolder> headerAdapter, RecyclerView.Adapter<? extends RecyclerView.ViewHolder> rootAdapter, boolean needFooter) {
        this.headerAdapter = headerAdapter;
        this.rootAdapter = rootAdapter;
        this.footerAdapter = needFooter ? getFooterAdapterInstance() : null;
        // 构建一个ConcatAdapter来串联三个适配器
        adapter = new ConcatAdapter(rootAdapter);
        if (headerAdapter != null) {
            adapter.addAdapter(0, headerAdapter);
        }
        if (footerAdapter != null) {
            adapter.addAdapter(footerAdapter);
        }
    }

    /*
        获取一个FooterAdapter适配器实例
     */
    private static FooterAdapter getFooterAdapterInstance() {
        return new FooterAdapter();
    }


    /*
        更改Footer状态
     */
    public void changeFooter(int status) {
        if (footerAdapter != null) {
            footerAdapter.changeStatus(status);
        }
    }

    /*
        获取Adapter实例
     */
    public ConcatAdapter getAdapter() {
        return adapter;
    }
}
