package com.cheng.animationstudy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

import com.cheng.animationstudy.C;
import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.bagrefresh.BGANormalRefreshViewHolder;
import com.cheng.animationstudy.customview.bagrefresh.BGARefreshLayout;
import com.cheng.animationstudy.customview.bagrefresh.RefreshModel;
import com.cheng.animationstudy.customview.bagrefresh.SwipeAdapterViewAdapter;
import com.cheng.utils.UiUtil;
import com.cheng.utils.ViewFinder;

import java.util.ArrayList;
import java.util.List;

public class UiBGASwipeListView extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private BGARefreshLayout mRefreshLayout;
    private ListView mDataLV;

    private SwipeAdapterViewAdapter mAdapter;
    private Handler mHandler;

    private int mNewPageNumber = 0;
    private int mMorePageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_bgaswipelistview);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        this.mRefreshLayout = ViewFinder.findViewById(this, R.id.sdi_refreshLayout);
        this.mDataLV = ViewFinder.findViewById(this, R.id.sdi_data_lv);

        this.mAdapter = new SwipeAdapterViewAdapter(this);
        this.mHandler = new Handler();
        this.mDataLV.setAdapter(mAdapter);
    }

    private void initListener() {
        this.mRefreshLayout.setDelegate(this);
        this.mDataLV.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL == scrollState) {
                    mAdapter.closeOpenedSwipeItemLayoutWithAnim();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    private void initData() {
        this.mAdapter = new SwipeAdapterViewAdapter(this);

        List<RefreshModel> datas = new ArrayList<>();
        for (int i=0; i<10; i++) {
            RefreshModel model = new RefreshModel("title"+i, "detail"+i);
            datas.add(model);
        }
        mAdapter.setDatas(datas);

        this.mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
        this.mDataLV.setAdapter(mAdapter);
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
