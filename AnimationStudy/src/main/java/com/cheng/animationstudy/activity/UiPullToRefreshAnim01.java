package com.cheng.animationstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.pulltorefresh01.PullToRefreshView;
import com.cheng.utils.ViewFinder;

import java.util.ArrayList;

/**
 * 下拉刷新下拉加载
 * ListView、GridView、ScrollView都可以集成使用
 * 特点：集成简单，类似SwipeRefreshLayout
 */
public class UiPullToRefreshAnim01 extends AppCompatActivity implements PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterRefreshListener {

    private RadioGroup mChangeContentRG;
    private PullToRefreshView mListViewPTR;
    private PullToRefreshView mGridViewPTR;
    private PullToRefreshView mScrollViewPTR;
    private ListView mTestLV;
    private GridView mTestGV;
    private ScrollView mTestSV;

    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> values = new ArrayList<String>() {{
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
        setContentView(R.layout.ui_pulltorefreshanim01);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        this.mChangeContentRG = ViewFinder.findViewById(this, R.id.sdi_changecontent_rg);
        this.mListViewPTR = ViewFinder.findViewById(this, R.id.sdi_listview_ptr);
        this.mGridViewPTR = ViewFinder.findViewById(this, R.id.sdi_gridview_ptr);
        this.mScrollViewPTR = ViewFinder.findViewById(this, R.id.sdi_scrollview_ptr);
        this.mTestLV = ViewFinder.findViewById(this, R.id.sdi_test_lv);
        this.mTestGV = ViewFinder.findViewById(this, R.id.sdi_test_gv);
        this.mTestSV = ViewFinder.findViewById(this, R.id.sdi_test_sv);
    }

    private void initListener() {
        this.mChangeContentRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.sdi_showlistview_rb:
                        mListViewPTR.setVisibility(View.VISIBLE);
                        mGridViewPTR.setVisibility(View.GONE);
                        mScrollViewPTR.setVisibility(View.GONE);
                        break;
                    case R.id.sdi_showgridview_rb:
                        mListViewPTR.setVisibility(View.GONE);
                        mGridViewPTR.setVisibility(View.VISIBLE);
                        mScrollViewPTR.setVisibility(View.GONE);
                        break;
                    case R.id.sdi_showscrollview_rb:
                        mListViewPTR.setVisibility(View.GONE);
                        mGridViewPTR.setVisibility(View.GONE);
                        mScrollViewPTR.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        this.mListViewPTR.setOnHeaderRefreshListener(this);
        this.mGridViewPTR.setOnHeaderRefreshListener(this);
        this.mScrollViewPTR.setOnHeaderRefreshListener(this);
        this.mListViewPTR.setOnFooterRefreshListener(this);
        this.mGridViewPTR.setOnFooterRefreshListener(this);
        this.mScrollViewPTR.setOnFooterRefreshListener(this);
    }

    private void initData() {
        this.mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        this.mTestLV.setAdapter(mAdapter);
        this.mTestGV.setAdapter(mAdapter);
    }

    @Override
    public void onHeaderRefresh(final PullToRefreshView pullToRefreshView) {
        mChangeContentRG.postDelayed(new Runnable() {
            @Override
            public void run() {
                pullToRefreshView.onHeaderRefreshComplete();
            }
        }, C.Int.IMITATE_NET_DELAYED * 2);
    }

    @Override
    public void onFooterRefresh(final PullToRefreshView pullToRefreshView) {
        mChangeContentRG.postDelayed(new Runnable() {
            @Override
            public void run() {
                pullToRefreshView.onFooterRefreshComplete();
            }
        }, C.Int.IMITATE_NET_DELAYED * 2);
    }
}
