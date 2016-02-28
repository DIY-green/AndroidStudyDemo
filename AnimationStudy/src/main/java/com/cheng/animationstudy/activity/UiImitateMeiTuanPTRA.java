package com.cheng.animationstudy.activity;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.meituan.MeiTuanListView;
import com.cheng.utils.Logger;
import com.cheng.utils.ViewFinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 仿美团下拉刷新动画
 */
public class UiImitateMeiTuanPTRA extends AppCompatActivity implements MeiTuanListView.OnMeiTuanRefreshListener {

    /**
     * 分析
     * 美团的下拉刷新分为三个状态：
     * 第一个状态为下拉刷新状态(pull to refresh)，在这个状态下是一个绿色的椭圆随着下拉的距离动态改变其大小
     * 第二个部分为放开刷新状态(release to refresh)，在这个状态下是一个帧动画，效果为从躺着变为站起来的动画。
     * 第三个部分为刷新状态(refreshing)，在这个状态下也是一个帧动画，是摇头的动画
     */

    private final static int REFRESH_COMPLETE = 0;

    private MeiTuanListView mListView;

    private List<String> mDatas;
    private ArrayAdapter<String> mAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    mListView.setOnRefreshComplete();
                    mAdapter.notifyDataSetChanged();
                    mListView.setSelection(0);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_imitatemeituanptra);

        initView();
        initData();
    }

    private void initView() {
        Logger.TAG = "UiImitateMeiTuanPTRA";
        this.mListView = ViewFinder.findViewById(this, R.id.sdi_meituan_lv);
    }

    private void initData() {
        String[] data = new String[]{"hello world","hello world","hello world","hello world",
                "hello world","hello world","hello world","hello world","hello world",
                "hello world","hello world","hello world","hello world","hello world"};
        mDatas = new ArrayList<String>(Arrays.asList(data));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mDatas);
        mListView.setAdapter(mAdapter);
        mListView.setOnMeiTuanRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SystemClock.sleep(3000);
                    mDatas.add(0, "new data");
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (Exception e) {
                    Logger.e(e);
                }
            }
        }).start();
    }
}
