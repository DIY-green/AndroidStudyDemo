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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 仿美团下拉刷新动画
 */
public class ImitateMeiTuanPTRAActivity extends AppCompatActivity implements MeiTuanListView.OnMeiTuanRefreshListener {

    /**
     * 分析
     * 美团的下拉刷新分为三个状态：
     * 第一个状态为下拉刷新状态(pull to refresh)，在这个状态下是一个绿色的椭圆随着下拉的距离动态改变其大小
     * 第二个部分为放开刷新状态(release to refresh)，在这个状态下是一个帧动画，效果为从躺着变为站起来的动画。
     * 第三个部分为刷新状态(refreshing)，在这个状态下也是一个帧动画，是摇头的动画
     */

    private final static int REFRESH_COMPLETE = 0;

    private MeiTuanListView mMeiTuanLV;

    private List<String> mDataList;
    private ArrayAdapter<String> mMeiTuanAdapter;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imitatemeituanptra);

        initView();
        initData();
    }

    private void initView() {
        Logger.TAG = "ImitateMeiTuanPTRAActivity";
        this.mMeiTuanLV = ViewFinder.findViewById(this, R.id.lv_meituan);
    }

    private void initData() {
        this.mHandler = new InnerHandler(this);
        String[] data = new String[]{"hello world","hello world","hello world","hello world",
                "hello world","hello world","hello world","hello world","hello world",
                "hello world","hello world","hello world","hello world","hello world"};
        this.mDataList = new ArrayList<String>(Arrays.asList(data));
        this.mMeiTuanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDataList);
        this.mMeiTuanLV.setAdapter(mMeiTuanAdapter);
        this.mMeiTuanLV.setOnMeiTuanRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SystemClock.sleep(3000);
                    mDataList.add(0, "new data");
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (Exception e) {
                    Logger.e(e);
                }
            }
        }).start();
    }

    private void refreshComplete() {
        mMeiTuanLV.setOnRefreshComplete();
        mMeiTuanAdapter.notifyDataSetChanged();
        mMeiTuanLV.setSelection(0);
    }

    private static final class InnerHandler extends Handler {

        private WeakReference<ImitateMeiTuanPTRAActivity> mActivityWR;

        public InnerHandler(ImitateMeiTuanPTRAActivity theActivity) {
            mActivityWR = new WeakReference<>(theActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    ImitateMeiTuanPTRAActivity theActivity = mActivityWR.get();
                    if (theActivity == null) return;
                    theActivity.refreshComplete();
                    break;
            }
        }
    }

}
