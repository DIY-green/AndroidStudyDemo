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

public class ImitateGoogleSRLA02Activity extends AppCompatActivity  implements SwipyRefreshLayout.OnRefreshListener, View.OnClickListener {

    private ListView mSwipyLV;
    private SwipyRefreshLayout mSwipyRL;
    private Button mTopBtn;
    private Button mBottomBtn;
    private Button mBothBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imitategooglesrla02);

        Logger.TAG = "ImitateGoogleSRLA02Activity";
        initView();
        initListener();
    }

    private void initView() {
        mSwipyLV = ViewFinder.findViewById(this, R.id.lv_swipy);
        mSwipyRL = ViewFinder.findViewById(this, R.id.rl_swipy);
        mTopBtn = ViewFinder.findViewById(this, R.id.btn_top);
        mBottomBtn = ViewFinder.findViewById(this, R.id.btn_bottom);
        mBothBtn = ViewFinder.findViewById(this, R.id.btn_both);

        mSwipyLV.setAdapter(new ListViewDemoAdapter(this));
        mSwipyRL.setColorSchemeColors(R.color.red,
                R.color.blue,
                R.color.dark_green);
//        mSwipyRL.setColorSchemeColors(
//                android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
        mSwipyRL.setOnRefreshListener(this);
    }

    private void initListener() {
        mTopBtn.setOnClickListener(this);
        mBottomBtn.setOnClickListener(this);
        mBothBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_top:
                mSwipyRL.setDirection(SwipyRefreshLayoutDirection.TOP);
                break;
            case R.id.btn_bottom:
                mSwipyRL.setDirection(SwipyRefreshLayoutDirection.BOTTOM);
                break;
            case R.id.btn_both:
                mSwipyRL.setDirection(SwipyRefreshLayoutDirection.BOTH);
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
                ImitateGoogleSRLA02Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSwipyRL.setRefreshing(false);
                    }
                });
            }
        }, 2000);
    }
}
