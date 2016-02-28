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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 仿汽车之家下拉刷新动画
 */
public class UiImitateAutoHomePTRA extends AppCompatActivity implements AutoHomeListView.OnAutoHomeRefreshListener {

    private final static int REFRESH_COMPLETE = 0;

    private AutoHomeListView mListView;

    private ArrayAdapter<String> mAdapter;
    private List<String> mDatas;
    private Handler mHandler = new Handler(){
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
        setContentView(R.layout.ui_imitateautohomeptra);

        initView();
        initData();
    }

    private void initView() {
        Logger.TAG = "UiImitateAutoHomePTRA";
        this.mListView = ViewFinder.findViewById(this, R.id.sdi_autohomelv);
    }

    private void initData() {
        String[] data = new String[]{"hello world1",
                "hello world2","hello world3","hello world4",
                "hello world5","hello world6","hello world7",
                "hello world8","hello world9","hello world10",
                "hello world11","hello world12","hello world13",
                "hello world14","hello world15"};
        mDatas = new ArrayList<String>(Arrays.asList(data));
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mDatas);
        mListView.setAdapter(mAdapter);
        mListView.setOnAutoHomeRefreshListener(this);
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
