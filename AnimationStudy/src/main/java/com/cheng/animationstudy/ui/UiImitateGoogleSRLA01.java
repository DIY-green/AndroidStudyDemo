package com.cheng.animationstudy.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.googleimitatecode.DiySwipeRefreshLayout;

import java.util.ArrayList;

public class UiImitateGoogleSRLA01 extends AppCompatActivity implements DiySwipeRefreshLayout.OnRefreshListener, DiySwipeRefreshLayout.OnLoadListener {

    private ListView mListView;
    private ArrayAdapter<String> mListAdapter;
    private DiySwipeRefreshLayout mSwipeLayout;

    private ArrayList<String> values = new ArrayList<String>() {{
        add("value 0");
        add("value 1");
        add("value 2");
        add("value 3");
        add("value 4");
        add("value 5");
        add("value 6");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_imitategooglesrla01);

        initView();
    }

    private void initView() {
        this.mListView = (ListView) findViewById(R.id.sdi_diyswipe_lv);
        this.mSwipeLayout = (DiySwipeRefreshLayout) findViewById(R.id.sdi_diyswipy);

        this.mListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        mListView.setAdapter(mListAdapter);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadListener(this);
        mSwipeLayout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeLayout.setMode(DiySwipeRefreshLayout.Mode.BOTH);
        mSwipeLayout.setLoadNoFull(false);
    }

    @Override
    public void onRefresh() {
        values.add(0, "Add " + values.size());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(false);
                mListAdapter.notifyDataSetChanged();
            }
        }, C.Int.IMITATE_NET_DELAYED * 4);
    }

    @Override
    public void onLoad() {
        values.add("Add " + values.size());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setLoading(false);
                mListAdapter.notifyDataSetChanged();
            }
        }, C.Int.IMITATE_NET_DELAYED * 2);
    }

}
