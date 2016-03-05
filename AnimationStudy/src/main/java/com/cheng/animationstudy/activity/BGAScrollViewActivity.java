package com.cheng.animationstudy.activity;

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

public class BGAScrollViewActivity extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {

    public static final int LOADING_DURATION = 2000;

    private BGARefreshLayout mRefreshLayout;
    private TextView mClickableLabelTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bgascrollview);

        initView();
        initListener();
    }

    private void initView() {
        this.mRefreshLayout = ViewFinder.findViewById(this, R.id.rl_root);
        this.mClickableLabelTV = ViewFinder.findViewById(this, R.id.tv_clickablelabel);
    }

    private void initListener() {
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retweet:
                UiUtil.toast(this, "点击了转发");
                break;
            case R.id.btn_comment:
                UiUtil.toast(this, "点击了评论");
                break;
            case R.id.btn_praise:
                UiUtil.toast(this, "点击了赞");
                break;
            case R.id.tv_clickablelabel:
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
                mClickableLabelTV.setText("加载最新数据完成");
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
