package com.cheng.bigtalkdesignpatterns.listadapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cheng.designpatternstudy.R;

import java.util.List;

/**
 *
 */
public class BigTalkDesignPatternsListAdapter extends BaseAdapter {

    private List<String> mChapters;
    private LayoutInflater mInflater;

    public BigTalkDesignPatternsListAdapter(Context context, List<String> chapters) {
        this.mChapters = chapters;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mChapters==null?0:mChapters.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_bigtalk_designpatternslist, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemBtn = (TextView) view.findViewById(R.id.bti_item_btn);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final String item = mChapters.get(position);
        if (!TextUtils.isEmpty(item)) {
            viewHolder.itemBtn.setText(item);
        }
        return view;
    }

    static class ViewHolder {
        TextView itemBtn;
    }
}
