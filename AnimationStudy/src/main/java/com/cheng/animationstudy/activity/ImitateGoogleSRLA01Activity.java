package com.cheng.animationstudy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.googleimitatecode.DiySwipeRefreshLayout;

import java.util.ArrayList;

public class ImitateGoogleSRLA01Activity extends AppCompatActivity implements DiySwipeRefreshLayout.OnRefreshListener, DiySwipeRefreshLayout.OnLoadListener {

    private ListView mDiySwipeLV;
    private ArrayAdapter<String> mListAdapter;
    private DiySwipeRefreshLayout mDiySwipeRL;

    private ArrayList<String> mValueList = new ArrayList<String>() {{
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
        setContentView(R.layout.activity_imitategooglesrla01);

        initView();
    }

    private void initView() {
        this.mDiySwipeLV = (ListView) findViewById(R.id.lv_diyswipe);
        this.mDiySwipeRL = (DiySwipeRefreshLayout) findViewById(R.id.rl_diyswipe);

        this.mListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mValueList);
        mDiySwipeLV.setAdapter(mListAdapter);
        mDiySwipeRL.setOnRefreshListener(this);
        mDiySwipeRL.setOnLoadListener(this);
        mDiySwipeRL.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mDiySwipeRL.setMode(DiySwipeRefreshLayout.Mode.BOTH);
        mDiySwipeRL.setLoadNoFull(false);
    }

    @Override
    public void onRefresh() {
        mValueList.add(0, "Add " + mValueList.size());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDiySwipeRL.setRefreshing(false);
                mListAdapter.notifyDataSetChanged();
            }
        }, C.Int.IMITATE_NET_DELAYED * 4);
    }

    @Override
    public void onLoad() {
        mValueList.add("Add " + mValueList.size());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDiySwipeRL.setLoading(false);
                mListAdapter.notifyDataSetChanged();
            }
        }, C.Int.IMITATE_NET_DELAYED * 2);
    }

}
