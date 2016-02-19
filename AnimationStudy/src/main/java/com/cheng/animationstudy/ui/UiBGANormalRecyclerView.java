package com.cheng.animationstudy.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.bagrefresh.BGAMoocStyleRefreshViewHolder;
import com.cheng.animationstudy.customview.bagrefresh.BGARefreshLayout;
import com.cheng.animationstudy.customview.bagrefresh.Divider;
import com.cheng.animationstudy.customview.bagrefresh.NormalRecyclerViewAdapter;
import com.cheng.animationstudy.customview.bagrefresh.RefreshModel;
import com.cheng.utils.UiUtil;
import com.cheng.utils.ViewFinder;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemLongClickListener;
import cn.bingoogolapple.bgabanner.BGABanner;

public class UiBGANormalRecyclerView extends AppCompatActivity implements BGAOnRVItemClickListener, BGAOnRVItemLongClickListener, BGAOnItemChildClickListener, BGARefreshLayout.BGARefreshLayoutDelegate  {

    private BGARefreshLayout mRefreshLayout;
    private BGABanner mBanner;
    private RecyclerView mDataRV;
    private NormalRecyclerViewAdapter mAdapter;
    private Handler mHandler;
    private int mNewPageNumber = 0;
    private int mMorePageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_bganormalrecyclerview);

        initView();
        initListener();
        initBanner();
        initData();
    }

    private void initView() {
        this.mRefreshLayout = ViewFinder.findViewById(this, R.id.sdi_normalrecyclerview_rl);
        this.mBanner = ViewFinder.findViewById(this, R.id.sdi_normalrecyclerview_banner);
        this.mDataRV = ViewFinder.findViewById(this, R.id.sdi_normalrecyclerview_datarv);
    }

    private void initListener() {
        this.mRefreshLayout.setDelegate(this);

        this.mAdapter = new NormalRecyclerViewAdapter(mDataRV);
        this.mHandler = new Handler();
        this.mAdapter.setOnRVItemClickListener(this);
        this.mAdapter.setOnRVItemLongClickListener(this);
        this.mAdapter.setOnItemChildClickListener(this);
    }

    private void initBanner() {
        final List<View> views = new ArrayList<>();
        final List<String> tips = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            views.add(View.inflate(this, R.layout.ui_bgabanner_imgitem, null));
            tips.add("我是图片00" + i);
        }
        mBanner.setViews(views);
        mBanner.setTips(tips);
    }

    private void initData() {
        List<RefreshModel> datas = new ArrayList<>();
        for (int i=0; i<10; i++) {
            RefreshModel model = new RefreshModel("title"+i, "detail"+i);
            datas.add(model);
        }
        mAdapter.addMoreDatas(datas);

        mRefreshLayout.setRefreshViewHolder(new BGAMoocStyleRefreshViewHolder(this, true));
        mDataRV.addItemDecoration(new Divider(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDataRV.setLayoutManager(linearLayoutManager);
        mDataRV.setAdapter(mAdapter);
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
    public void onItemChildClick(ViewGroup viewGroup, View childView, int position) {
        if (childView.getId() == R.id.tv_item_normal_delete) {
            mAdapter.removeItem(position);
        }
    }

    @Override
    public void onRVItemClick(ViewGroup viewGroup, View itemView, int position) {
        UiUtil.toast(this, "点击了条目 " + mAdapter.getItem(position).title);
    }

    @Override
    public boolean onRVItemLongClick(ViewGroup viewGroup, View itemView, int position) {
        UiUtil.toast(this, "长按了条目 " + mAdapter.getItem(position).title);
        return true;
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
                RefreshModel model = new RefreshModel("Refresh Title", "Refresh Detail");
                mAdapter.addFirstItem(model);
                mRefreshLayout.endRefreshing();
                mDataRV.smoothScrollToPosition(0);
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
                RefreshModel model = new RefreshModel("Refresh Title", "Refresh Detail");
                mAdapter.addLastItem(model);
                mRefreshLayout.endLoadingMore();
            }
        }, C.Int.IMITATE_NET_DELAYED);
        return true;
    }

}
