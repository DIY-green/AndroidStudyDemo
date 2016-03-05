package com.cheng.animationstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioGroup;

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
public class PullToRefreshAnim01Activity extends AppCompatActivity implements PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterRefreshListener {

    private RadioGroup mChangeContentRG;
    private PullToRefreshView mListViewPTR;
    private PullToRefreshView mGridViewPTR;
    private PullToRefreshView mScrollViewPTR;
    private ListView mTestLV;
    private GridView mTestGV;

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
        setContentView(R.layout.activity_pulltorefreshanim01);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        this.mChangeContentRG = ViewFinder.findViewById(this, R.id.rg_changecontent);
        this.mListViewPTR = ViewFinder.findViewById(this, R.id.ptr_listview);
        this.mGridViewPTR = ViewFinder.findViewById(this, R.id.ptr_gridview);
        this.mScrollViewPTR = ViewFinder.findViewById(this, R.id.ptr_scrollview);
        this.mTestLV = ViewFinder.findViewById(this, R.id.lv_test);
        this.mTestGV = ViewFinder.findViewById(this, R.id.gv_test);
    }

    private void initListener() {
        this.mChangeContentRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_showlistview:
                        mListViewPTR.setVisibility(View.VISIBLE);
                        mGridViewPTR.setVisibility(View.GONE);
                        mScrollViewPTR.setVisibility(View.GONE);
                        break;
                    case R.id.rb_showgridview:
                        mListViewPTR.setVisibility(View.GONE);
                        mGridViewPTR.setVisibility(View.VISIBLE);
                        mScrollViewPTR.setVisibility(View.GONE);
                        break;
                    case R.id.rb_showscrollview:
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
        this.mListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mValueList);
        this.mTestLV.setAdapter(mListAdapter);
        this.mTestGV.setAdapter(mListAdapter);
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
