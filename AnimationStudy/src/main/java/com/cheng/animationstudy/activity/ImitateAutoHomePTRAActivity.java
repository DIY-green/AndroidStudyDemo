package com.cheng.animationstudy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.autohome.AutoHomeListView;
import com.cheng.utils.Logger;
import com.cheng.utils.ViewFinder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 仿汽车之家下拉刷新动画
 */
public class ImitateAutoHomePTRAActivity extends AppCompatActivity implements AutoHomeListView.OnAutoHomeRefreshListener {

    private final static int REFRESH_COMPLETE = 0;

    private AutoHomeListView mListView;

    private ArrayAdapter<String> mAdapter;
    private List<String> mDataList;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imitateautohomeptra);

        initView();
        initData();
    }

    private void initView() {
        Logger.TAG = "ImitateAutoHomePTRAActivity";
        this.mListView = ViewFinder.findViewById(this, R.id.sdi_autohomelv);
    }

    private void initData() {
        this.mHandler = new InnerHandler(this);
        String[] data = new String[]{"hello world1",
                "hello world2","hello world3","hello world4",
                "hello world5","hello world6","hello world7",
                "hello world8","hello world9","hello world10",
                "hello world11","hello world12","hello world13",
                "hello world14","hello world15"};
        this.mDataList = new ArrayList<String>(Arrays.asList(data));
        this.mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mDataList);
        this.mListView.setAdapter(mAdapter);
        this.mListView.setOnAutoHomeRefreshListener(this);
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
        mListView.setOnRefreshComplete();
        mAdapter.notifyDataSetChanged();
        mListView.setSelection(0);
    }

    private static final class InnerHandler extends Handler {

        private WeakReference<ImitateAutoHomePTRAActivity> mActivityWR;

        public InnerHandler(ImitateAutoHomePTRAActivity activity) {
            mActivityWR = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    ImitateAutoHomePTRAActivity theActivity = mActivityWR.get();
                    if (theActivity == null) return;
                    theActivity.refreshComplete();
                    break;
            }
        }
    }

}
