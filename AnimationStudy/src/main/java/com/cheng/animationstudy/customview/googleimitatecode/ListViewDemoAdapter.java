package com.cheng.animationstudy.customview.googleimitatecode;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.cheng.animationstudy.R;
import com.cheng.utils.ViewFinder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ListViewDemoAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mDummyStrings;

    public ListViewDemoAdapter(Context mContext) {
        this.mContext = mContext;
        mDummyStrings = getDummyStrings();
    }

    @Override
    public int getCount() {
        return mDummyStrings.size();
    }

    @Override
    public Object getItem(int position) {
        return mDummyStrings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView==null){
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();

            convertView = inflater.inflate(R.layout.ui_swipylv_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mCellNumberTV = ViewFinder.findViewById(convertView, R.id.sdi_cellnumber_tv);
            viewHolder.mCellTextTV = ViewFinder.findViewById(convertView, R.id.sdi_celltext_tv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mCellNumberTV.setText("" + position);
        viewHolder.mCellTextTV.setText(mDummyStrings.get(position));

        return convertView;
    }

    private static class ViewHolder {
        TextView mCellNumberTV;
        TextView mCellTextTV;
    }

    public void addString(boolean isLoadMore) {
        if (mDummyStrings == null) return;
        int size = mDummyStrings.size();
        mDummyStrings.add(isLoadMore?size:0, isLoadMore?"Load New Data":"Refresh New Data");
    }

    private List<String> getDummyStrings() {
        List<String> dummyStrings = new ArrayList<>();

        dummyStrings.add("You want");
        dummyStrings.add("to test");
        dummyStrings.add("this library");
        dummyStrings.add("from both");
        dummyStrings.add("direction.");
        dummyStrings.add("You may");
        dummyStrings.add("be amazed");
        dummyStrings.add("when done");
        dummyStrings.add("so!");
        dummyStrings.add("I am");
        dummyStrings.add("going to");
        dummyStrings.add("add a little");
        dummyStrings.add("more lines");
        dummyStrings.add("for big");
        dummyStrings.add("smartphones.");

        return dummyStrings;
    }
}
