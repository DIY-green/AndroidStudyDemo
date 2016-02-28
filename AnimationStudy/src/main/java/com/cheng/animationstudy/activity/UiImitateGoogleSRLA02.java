package com.cheng.animationstudy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.googleimitatecode.ListViewDemoAdapter;
import com.cheng.animationstudy.customview.googleimitatecode.SwipyRefreshLayout;
import com.cheng.animationstudy.customview.googleimitatecode.SwipyRefreshLayoutDirection;
import com.cheng.utils.Logger;
import com.cheng.utils.ViewFinder;

public class UiImitateGoogleSRLA02 extends AppCompatActivity  implements SwipyRefreshLayout.OnRefreshListener, View.OnClickListener {

    private ListView mListView;
    private SwipyRefreshLayout mSwipyRefreshLayout;
    private Button mTop;
    private Button mBottom;
    private Button mBoth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_imitategooglesrla02);

        Logger.TAG = "UiImitateGoogleSRLA02";
        initView();
        initListener();
    }

    private void initView() {
        mListView = ViewFinder.findViewById(this, R.id.sdi_swipy_lv);
        mSwipyRefreshLayout = ViewFinder.findViewById(this, R.id.swipyrefreshlayout);
        mTop = ViewFinder.findViewById(this, R.id.sdi_top_btn);
        mBottom = ViewFinder.findViewById(this, R.id.sdi_bottom_btn);
        mBoth = ViewFinder.findViewById(this, R.id.sdi_both_btn);

        mListView.setAdapter(new ListViewDemoAdapter(this));
        mSwipyRefreshLayout.setColorSchemeColors(R.color.red,
                R.color.blue,
                R.color.dark_green);
//        mSwipyRefreshLayout.setColorSchemeColors(
//                android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
        mSwipyRefreshLayout.setOnRefreshListener(this);
    }

    private void initListener() {
        mTop.setOnClickListener(this);
        mBottom.setOnClickListener(this);
        mBoth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sdi_top_btn:
                mSwipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.TOP);
                break;
            case R.id.sdi_bottom_btn:
                mSwipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTTOM);
                break;
            case R.id.sdi_both_btn:
                mSwipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
                break;
        }
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        Logger.d("Refresh triggered at "
                + (direction == SwipyRefreshLayoutDirection.TOP ? "top" : "bottom"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Hide the refresh after 2sec
                UiImitateGoogleSRLA02.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSwipyRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }, 2000);
    }
}
