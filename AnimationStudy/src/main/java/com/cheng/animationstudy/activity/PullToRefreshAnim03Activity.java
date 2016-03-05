package com.cheng.animationstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.pulltorefresh03.MyListener;
import com.cheng.animationstudy.customview.pulltorefresh03.PullToRefreshLayout;
import com.cheng.utils.ViewFinder;

import java.util.ArrayList;

public class PullToRefreshAnim03Activity extends AppCompatActivity {

    private RadioGroup mChangeContentRG;
    private PullToRefreshLayout mPullToRefreshLayout01;
    private PullToRefreshLayout mPullToRefreshLayout02;
    private PullToRefreshLayout mPullToRefreshLayout03;
    private ListView mTestLV;
    private WebView mTestWV;

    private MyListener mListener;
    private ArrayAdapter<String> mListAdapter;
    private ArrayList<String> mValueList = new ArrayList<String>() {{
        add("value 0");
        add("value 1");
        add("value 2");
        add("value 3");
        add("value 4");
        add("value 5");
        add("value 6");
        add("value 7");
        add("value 8");
        add("value 9");
        add("value 10");
    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefreshanim03);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        this.mChangeContentRG = ViewFinder.findViewById(this, R.id.rg_changecontent);
        this.mPullToRefreshLayout01 = ViewFinder.findViewById(this, R.id.refresh_view_1);
        this.mPullToRefreshLayout02 = ViewFinder.findViewById(this, R.id.refresh_view_2);
        this.mPullToRefreshLayout03 = ViewFinder.findViewById(this, R.id.refresh_view_3);
        this.mTestLV = ViewFinder.findViewById(this, R.id.lv_test);
        this.mTestWV = ViewFinder.findViewById(this, R.id.wv_test);
    }

    private void initListener() {
        this.mChangeContentRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_showlistview:
                    showListView();
                    break;
                case R.id.rb_showscrollview:
                    showScrollView();
                    break;
                case R.id.rb_showwebview:
                    showWebView();
                    break;
            }
            }
        });
        this.mListener = new MyListener();
        this.mPullToRefreshLayout01.setOnRefreshListener(mListener);
        this.mPullToRefreshLayout02.setOnRefreshListener(mListener);
        this.mPullToRefreshLayout03.setOnRefreshListener(mListener);
    }

    private void showListView() {
        mPullToRefreshLayout01.setVisibility(View.VISIBLE);
        mPullToRefreshLayout02.setVisibility(View.GONE);
        mPullToRefreshLayout03.setVisibility(View.GONE);
    }

    private void showScrollView() {
        mPullToRefreshLayout01.setVisibility(View.GONE);
        mPullToRefreshLayout02.setVisibility(View.VISIBLE);
        mPullToRefreshLayout03.setVisibility(View.GONE);
    }

    private void showWebView() {
        mPullToRefreshLayout01.setVisibility(View.GONE);
        mPullToRefreshLayout02.setVisibility(View.GONE);
        mPullToRefreshLayout03.setVisibility(View.VISIBLE);
        mTestWV.loadUrl("http://blog.csdn.net/zhongkejingwang?viewmode=list");
    }

    private void initData() {
        this.mListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mValueList);
        this.mTestLV.setAdapter(mListAdapter);
    }

}
