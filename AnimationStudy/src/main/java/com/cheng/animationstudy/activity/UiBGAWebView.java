package com.cheng.animationstudy.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.bagrefresh.BGAMoocStyleRefreshViewHolder;
import com.cheng.animationstudy.customview.bagrefresh.BGARefreshLayout;
import com.cheng.utils.UiUtil;
import com.cheng.utils.ViewFinder;

public class UiBGAWebView extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private BGARefreshLayout mRefreshLayout;
    private WebView mContentWV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_bgawebview);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        this.mRefreshLayout = ViewFinder.findViewById(this, R.id.sdi_refreshLayout);
        this.mContentWV = ViewFinder.findViewById(this, R.id.wv_webview_content);
    }

    private void initListener() {
        this.mRefreshLayout.setDelegate(this);
        this.mContentWV.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mRefreshLayout.endRefreshing();
            }
        });
    }

    private void initData() {
        mRefreshLayout.setRefreshViewHolder(new BGAMoocStyleRefreshViewHolder(this, false));
        mContentWV.getSettings().setJavaScriptEnabled(true);
        mContentWV.loadUrl("https://github.com/bingoogolapple");
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
        mContentWV.reload();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
