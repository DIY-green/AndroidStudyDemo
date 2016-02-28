package com.cheng.animationstudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.bagrefresh.BGANormalRefreshViewHolder;
import com.cheng.animationstudy.customview.bagrefresh.BGARefreshLayout;
import com.cheng.animationstudy.customview.googleimitatecode.ListViewDemoAdapter;
import com.cheng.utils.UiUtil;
import com.cheng.utils.ViewFinder;

public class UiBGANormalListView extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private BGARefreshLayout mRefreshLayout;
    private ListView mDataLV;

    private ListViewDemoAdapter mAdapter;
    private Handler mHandler;
    private int mNewPageNumber = 0;
    private int mMorePageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_bganormallistview);

        initView();
        initListener();
    }

    private void initView() {
        this.mRefreshLayout = ViewFinder.findViewById(this, R.id.sdi_refreshLayout);
        this.mDataLV = ViewFinder.findViewById(this, R.id.sdi_data_lv);

        this.mAdapter = new ListViewDemoAdapter(this);
        this.mHandler = new Handler();
        this.mDataLV.setAdapter(mAdapter);
        this.mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
    }

    private void initListener() {
        this.mRefreshLayout.setDelegate(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sdi_retweet_btn:
                UiUtil.toast(this, "点击了转发");
                break;
            case R.id.sdi_comment_btn:
                UiUtil.toast(this, "点击了评论");
                break;
            case R.id.sdi_praise_btn:
                UiUtil.toast(this, "点击了赞");
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mNewPageNumber++;
        if (mNewPageNumber > 4) {
            mRefreshLayout.endRefreshing();
            UiUtil.toast(this, "没有最新数据了");
            return;
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addString(false);
                mRefreshLayout.endRefreshing();
            }
        }, C.Int.IMITATE_NET_DELAYED);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mMorePageNumber++;
        if (mMorePageNumber > 5) {
            mRefreshLayout.endLoadingMore();
            UiUtil.toast(this, "没有更多数据了");
            return false;
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addString(true);
                mRefreshLayout.endLoadingMore();
            }
        }, C.Int.IMITATE_NET_DELAYED);
        return true;
    }

    private void overlay(Class clazz) {
        Intent intent = new Intent(UiBGANormalListView.this, clazz);
        startActivity(intent);
    }

}
