package com.cheng.animationstudy.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.bagrefresh.BGANormalRefreshViewHolder;
import com.cheng.animationstudy.customview.bagrefresh.BGARefreshLayout;
import com.cheng.utils.UiUtil;
import com.cheng.utils.ViewFinder;

public class UiBGAScrollView extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {

    public static final int LOADING_DURATION = 2000;

    private BGARefreshLayout mRefreshLayout;
    private TextView mClickableLabelTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_bgascrollview);

        initView();
        initListener();
    }

    private void initView() {
        this.mRefreshLayout = ViewFinder.findViewById(this, R.id.refreshLayout);
        this.mClickableLabelTv = ViewFinder.findViewById(this, R.id.tv_scrollview_clickablelabel);
    }

    private void initListener() {
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
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
            case R.id.tv_scrollview_clickablelabel:
                UiUtil.toast(this, "点击了测试文本");
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(LOADING_DURATION);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mRefreshLayout.endRefreshing();
                mClickableLabelTv.setText("加载最新数据完成");
            }
        }.execute();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(LOADING_DURATION);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mRefreshLayout.endLoadingMore();
            }
        }.execute();
        return true;
    }
}
