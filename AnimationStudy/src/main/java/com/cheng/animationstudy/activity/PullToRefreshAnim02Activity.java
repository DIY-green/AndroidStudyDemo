package com.cheng.animationstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.pulltorefresh02.CustomListView;
import com.cheng.utils.ViewFinder;

import java.util.ArrayList;

/**
 * 直接自定义ListView实现下拉刷新和上拉加载功能
 */
public class PullToRefreshAnim02Activity extends AppCompatActivity implements CustomListView.OnRefreshListener, CustomListView.OnLoadMoreListener {

    private CustomListView mListView;
    private Button mCanPullRefBtn;
    private Button mCanLoadMoreBtn;
    private Button mCanAutoLoadMoreBtn;
    private Button mIsMoveToFirstItemBtn;

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
        setContentView(R.layout.activity_pulltorefreshanim02);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        this.mListView = ViewFinder.findViewById(this, R.id.clv_test);
        this.mCanPullRefBtn = ViewFinder.findViewById(this, R.id.btn_canpullref);
        this.mCanLoadMoreBtn = ViewFinder.findViewById(this, R.id.btn_canloadmoreflag);
        this.mCanAutoLoadMoreBtn = ViewFinder.findViewById(this, R.id.btn_autoloadmoreflag);
        this.mIsMoveToFirstItemBtn = ViewFinder.findViewById(this, R.id.btn_ismovetofirstitem);
    }

    private void initListener() {
        this.mListView.setOnRefreshListener(this);
        this.mListView.setOnLoadListener(this);
    }

    private void initData() {
        this.mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mValueList);
        this.mListView.setAdapter(mListAdapter);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_canpullref:
                canPullRef();
                break;
            case R.id.btn_canloadmoreflag:
                canLoadMoreFlag();
                break;
            case R.id.btn_autoloadmoreflag:
                autoLoadMoreFlag();
                break;
            case R.id.btn_ismovetofirstitem:
                isMoveToFirstItem();
                break;
        }
    }

    private void canPullRef() {
        mListView.setCanRefresh(!mListView.isCanRefresh());
        if(mCanPullRefBtn.getText().toString().
                equals("关闭\n下拉刷新")){
            mCanPullRefBtn.setText("启用\n下拉刷新");
        }else{
            mCanPullRefBtn.setText("关闭\n下拉刷新");
        }
    }

    private void canLoadMoreFlag() {
        mListView.setCanLoadMore(!mListView.isCanLoadMore());
        if(mCanLoadMoreBtn.getText().toString().
                equals("关闭\n加载更多")){
            mCanLoadMoreBtn.setText("启用\n加载更多");
        }else{
            mCanLoadMoreBtn.setText("关闭\n加载更多");
        }
    }

    private void autoLoadMoreFlag() {
        mListView.setAutoLoadMore(!mListView.isAutoLoadMore());
        if(mCanAutoLoadMoreBtn.getText().toString().
                equals("关闭自动加载更多")){
            mCanAutoLoadMoreBtn.setText("启用自动加载更多");
        }else{
            mCanAutoLoadMoreBtn.setText("关闭自动加载更多");
        }
    }

    private void isMoveToFirstItem() {
        mListView.setMoveToFirstItemAfterRefresh(
                !mListView.isMoveToFirstItemAfterRefresh());
        if(mIsMoveToFirstItemBtn.getText().toString().
                equals("关闭移动到第一条Item")){
            mIsMoveToFirstItemBtn.setText("启用移动到第一条Item");
        }else{
            mIsMoveToFirstItemBtn.setText("关闭移动到第一条Item");
        }
    }

    @Override
    public void onLoadMore() {
        mListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mListView.onLoadMoreComplete();
            }
        }, C.Int.IMITATE_NET_DELAYED * 2);
    }

    @Override
    public void onRefresh() {
        mListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mListView.onRefreshComplete();
            }
        }, C.Int.IMITATE_NET_DELAYED * 2);
    }
}
