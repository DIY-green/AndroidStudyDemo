package com.diygreen.android6new.newwidget3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<String> mDataList;

    public RecyclerAdapter(List<String> dataList) {
        mDataList = dataList;
    }


    // 自定义的 ViewHolder，持有每个 Item 的所有 View 控件
    // 必须继承自 RecyclerView.ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }

    // 获取Item的数量
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    // 将数据与 View 控件进行绑定
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(mDataList.get(position));
    }

    // 创建新 View，被 LayoutManager 所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),
                android.R.layout.simple_list_item_1, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
}