package com.cheng.zenofdesignpatterns.listadapter;

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
public class ZenofDesignPatternsListAdapter extends BaseAdapter {

    private List<String> mDatas;
    private LayoutInflater mInflater;

    public ZenofDesignPatternsListAdapter(Context _context, List<String> _datas) {
        this.mDatas = _datas;
        this.mInflater = LayoutInflater.from(_context);
    }

    @Override
    public int getCount() {
        return mDatas==null?0:mDatas.size();
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
            view = mInflater.inflate(R.layout.item_zenof_designpatterns_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemTV = (TextView) view.findViewById(R.id.zoi_item_tv);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final String item = mDatas.get(position);
        if (!TextUtils.isEmpty(item)) {
            viewHolder.itemTV.setText(item);
        }
        return view;
    }

    static class ViewHolder {
        TextView itemTV;
    }
}
