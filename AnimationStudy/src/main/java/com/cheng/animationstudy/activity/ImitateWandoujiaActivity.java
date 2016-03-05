package com.cheng.animationstudy.activity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheng.animationstudy.R;
import com.cheng.animationstudy.customview.wandoujia.MyScrollView;
import com.cheng.utils.Logger;
import com.cheng.utils.ViewFinder;

import java.lang.ref.WeakReference;

/**
 * 模仿豌豆荚首页
 */
public class ImitateWandoujiaActivity extends AppCompatActivity implements MyScrollView.OnScrollChangedListener, View.OnTouchListener,View.OnClickListener {

    private final static int MSG_PULL_RECOVER = 0x999;

    private ViewPager mMainVP;

    private MyScrollView mMainSV;
    private View mVHeaderContainer;
    private View mSearchBarView;

    private View mMainNavView;
    private View mMainNavLeftContainerView;
    private RadioGroup mMNavMidRG;
    private View mMNavRightContainerView;
    private RadioButton mMainNavIndexRB;
    private RadioButton mMainNavDiscoverRB;
    private RadioButton mMainNavBlogRB;
    private RadioButton mMainNavMineRB;
    private RadioButton mMainNavMoreRB;

    private View mIndexHeaderNavView;
    private View mIndexNavDiscoverView;
    private View mIndexNavBlogView;
    private View mIndexNavMineView;
    private View mIndexNavMoreView;
    private TextView mIndexNavDiscoverTV;
    private TextView mIndexNavBlogTV;
    private TextView mIndexNavMineTV;
    private TextView mIndexNavMoreTV;

    private boolean mIsInitFactor = false;
    private int mMaxScrollTop;
    private int mScrollerOldNewTop;
    private int mTextFadeBeginScrollTop;
    private int mTextFadeEndScrollTop;
    private float mTextFadeFactor;
    private int mCurrentTextAlpha;
    private int mMainNavFadeBeginScrollTop;
    private int mMainNavFadeEndScrollTop;
    private float mMainNavFadeFactor;
    private float mCurrentMainNavAlpha;
    private int mIndexNavZoomBeginScrollTop;
    private int mIndexNavZoomEndScrollTop;
    private float mIndexNavMoveLeftFactor;
    private float mIndexNavMoveRightFactor;
    private int mFinalIndexNavPaddingLeft;
    private int mFinalIndexNavPaddingRight;
    private int mCurrentIndexNavPaddingLeft;
    private int mCurrentIndexNavPaddingRight;
    private int mMainNavTranBeginScrollTop;
    private int mMainNavTranEndScrollTop;
    private float mMNavIndexFactor;
    private float mMNavMidTGroupFactor;
    private float mMNavRightContainerFactor;
    private int mOrginMNavIndexMarginLeft;
    private int mOrginMNavMidTGroupMarginTop;
    private int mOrginMNavRightContainerMarginRight;
    private int mCurrentMNavIndexMarginLeft;
    private int mCurrentMNavMidTGroupMarginTop;
    private int mCurrentMNavRightContainerMarginRight;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imitatewandoujia);
        
        initView();
        initListener();
        initData();
    }

    private void initView() {
        this.mMainSV = (MyScrollView) findViewById(R.id.sdi_wdj_msv);
        this.mMainVP = (ViewPager) findViewById(R.id.sdi_wdj_mainvp);

        this.mVHeaderContainer = findViewById(R.id.header_container);

        this.mIndexHeaderNavView = ViewFinder.findViewById(mVHeaderContainer, R.id.index_header_nav);
        this.mIndexNavDiscoverView = ViewFinder.findViewById(mIndexHeaderNavView,R.id.discover_item);
        this.mIndexNavBlogView = ViewFinder.findViewById(mIndexHeaderNavView,R.id.blog_item);
        this.mIndexNavMineView = ViewFinder.findViewById(mIndexHeaderNavView,R.id.mine_item);
        this.mIndexNavMoreView = ViewFinder.findViewById(mIndexHeaderNavView,R.id.more_item);

        this.mIndexNavDiscoverTV = ViewFinder.findViewById(mIndexNavDiscoverView,R.id.nav_text_tv);
        this.mIndexNavBlogTV = ViewFinder.findViewById(mIndexNavBlogView,R.id.nav_text_tv);
        this.mIndexNavMineTV = ViewFinder.findViewById(mIndexNavMineView,R.id.nav_text_tv);
        this.mIndexNavMoreTV = ViewFinder.findViewById(mIndexNavMoreView,R.id.nav_text_tv);

        this.mSearchBarView = ViewFinder.findViewById(mVHeaderContainer,R.id.search_bar);

        this.mMainNavView = findViewById(R.id.main_nav);
        this.mMNavRightContainerView = ViewFinder.findViewById(mMainNavView,R.id.right_container);
        this.mMainNavLeftContainerView = ViewFinder.findViewById(mMainNavView,R.id.main_nav_layout);
        this.mMNavMidRG = ViewFinder.findViewById(mMainNavView,R.id.middle_tab_group);
        this.mMainNavIndexRB = ViewFinder.findViewById(mMainNavView,R.id.main_tab_index);
        this.mMainNavDiscoverRB = ViewFinder.findViewById(mMainNavView,R.id.main_tab_ranking);
        this.mMainNavBlogRB = ViewFinder.findViewById(mMainNavView,R.id.main_tab_blog);
        this.mMainNavMineRB = ViewFinder.findViewById(mMainNavView,R.id.main_tab_mine);
        this.mMainNavMoreRB = ViewFinder.findViewById(mMainNavView,R.id.main_tab_more);
    }

    private void initListener() {
        this.mMainNavIndexRB.setOnClickListener(this);
        this.mMainNavDiscoverRB.setOnClickListener(this);
        this.mMainNavBlogRB.setOnClickListener(this);
        this.mMainNavMineRB.setOnClickListener(this);
        this.mMainNavMoreRB.setOnClickListener(this);
        this.mMainSV.setOnScrollChangedListener(this);
        this.mMainSV.setOnTouchListener(this);
    }

    private void initData() {
        this.mHandler = new InnerHandler(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_tab_index:
                tabIndex();
                break;
            case R.id.main_tab_ranking:
                tabRanking();
                break;
        }
    }

    private void tabIndex() {
        mMainSV.setVisibility(View.VISIBLE);
        mMainVP.setVisibility(View.GONE);
        RadioButton rbChild;
        int childCount = mMNavMidRG.getChildCount();
        for (int childIndex=0; childIndex<childCount; childIndex++) {
            rbChild = (RadioButton) mMNavMidRG.getChildAt(childIndex);
            rbChild.setChecked(false);
        }
    }

    private void tabRanking() {
        mMainSV.setVisibility(View.GONE);
        mMainNavIndexRB.setChecked(false);
        mMainNavDiscoverRB.setChecked(true);
        mMainVP.setVisibility(View.VISIBLE);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!mIsInitFactor) {
            int mMainNavHeight = mMainNavView.getBottom();
            int mSearchBarTop = mSearchBarView.getTop();
            int mSearchBarBottom = mSearchBarView.getBottom();
            int mIndexNavTop = mIndexHeaderNavView.getTop();
            int mTextFadeDis = getResources().getDimensionPixelOffset(R.dimen.app_padding);
            int mVMainNavIndexWidth = mMainNavIndexRB.getWidth();
            int mVIndexHeaderNavBottom = mIndexHeaderNavView.getBottom();
            int mMNRightContainerWidth = mMNavRightContainerView.getWidth();

            mMaxScrollTop = mVIndexHeaderNavBottom;

            mTextFadeEndScrollTop = (int) (mSearchBarTop - mMainNavHeight * 0.5f);
            mTextFadeBeginScrollTop = mTextFadeEndScrollTop - mTextFadeDis;
            mTextFadeFactor = 255.0f / mTextFadeDis;
            mCurrentTextAlpha = 255;

            mMainNavFadeBeginScrollTop = mTextFadeEndScrollTop;
            mMainNavFadeEndScrollTop = mMainNavFadeBeginScrollTop + mMainNavHeight;
            mCurrentMainNavAlpha = 0.0f;
            mMainNavFadeFactor = 1.0f / mMainNavHeight;
            changeMainNavAlpha(0);

            mIndexNavZoomBeginScrollTop = mSearchBarBottom - mMainNavHeight;
            mIndexNavZoomEndScrollTop = mIndexNavTop - mMainNavHeight;
            float zoomDistance = mIndexNavZoomEndScrollTop - mIndexNavZoomBeginScrollTop;
            mFinalIndexNavPaddingLeft = mMainNavIndexRB.getWidth();
            mFinalIndexNavPaddingRight = (mIndexHeaderNavView.getRight() - mMainNavLeftContainerView.getWidth());
            mIndexNavMoveLeftFactor = mFinalIndexNavPaddingLeft / zoomDistance;
            mIndexNavMoveRightFactor = mFinalIndexNavPaddingRight / zoomDistance;

            mMainNavTranBeginScrollTop = mIndexNavZoomEndScrollTop;
            mMainNavTranEndScrollTop = mIndexNavZoomEndScrollTop + mMainNavHeight;
            mOrginMNavIndexMarginLeft = -mVMainNavIndexWidth;
            mIndexNavMoveLeftFactor = mFinalIndexNavPaddingLeft / zoomDistance;
            mIndexNavMoveRightFactor =  mFinalIndexNavPaddingRight / zoomDistance;

            mMainNavTranBeginScrollTop = mIndexNavZoomEndScrollTop;
            mMainNavTranEndScrollTop = mIndexNavZoomEndScrollTop + mMainNavHeight;
            mOrginMNavIndexMarginLeft = -mVMainNavIndexWidth;
            mOrginMNavMidTGroupMarginTop = mMainNavHeight;
            mOrginMNavRightContainerMarginRight = -mMNRightContainerWidth;
            mMNavIndexFactor = mVMainNavIndexWidth / (float)mMainNavHeight;
            mMNavRightContainerFactor = mMNRightContainerWidth / (float)mMainNavHeight;
            mMNavMidTGroupFactor = 1.0f;
            mCurrentMNavIndexMarginLeft = mOrginMNavIndexMarginLeft;
            mCurrentMNavMidTGroupMarginTop = mOrginMNavMidTGroupMarginTop;
            mCurrentMNavRightContainerMarginRight = mOrginMNavRightContainerMarginRight;
            setLeftMargin(mMainNavIndexRB, mCurrentMNavIndexMarginLeft);
            setRightMargin(mMNavRightContainerView, mOrginMNavRightContainerMarginRight);
            setTopMargin(mMNavMidRG, mOrginMNavMidTGroupMarginTop);

            mIsInitFactor = true;
        }
    }

    @Override
    public void onScrollChanged(int newLeft, int newTop, int oldLeft, int oldTop) {
        if (mScrollerOldNewTop==newTop || oldTop>mMaxScrollTop) {
            return;
        }
        mScrollerOldNewTop = newTop;

        fadeTextOnScroll(newLeft, newTop, oldLeft, oldTop);

        fadeMainNavOnScroll(newLeft, newTop, oldLeft, oldTop);

        zoomIndexNavOnScroll(newLeft, newTop, oldLeft, oldTop);

        transferMainNavOnScroll(newLeft, newTop, oldLeft, oldTop);
    }

    /**
     * 文字随着活动逐步消失或者显示
     */
    private void fadeTextOnScroll(int newLeft, int newTop, int oldLeft, int oldTop){
        // 上滑上界
        if (newTop >= mTextFadeEndScrollTop) {
            mCurrentTextAlpha = 0;
            changeIndexNavTextAlpha(0);
            return;
        }

        //下滑下界
        if(newTop <= mTextFadeBeginScrollTop){
            mCurrentTextAlpha = 255;
            changeIndexNavTextAlpha(255);
            return;
        }

        //上滑下交界
        if(mTextFadeBeginScrollTop<oldTop && newTop >mTextFadeBeginScrollTop){
            mCurrentTextAlpha = (int) (255 - (newTop - mTextFadeBeginScrollTop) * mTextFadeFactor);
            ensureFadeTextAlphaRight(newTop, oldTop);
            changeIndexNavTextAlpha(mCurrentTextAlpha);
            return;
        }

        //下滑上交界
        if(mTextFadeEndScrollTop < oldTop && newTop < mTextFadeEndScrollTop){
            mCurrentTextAlpha = (int) ((mTextFadeEndScrollTop - oldTop) * mTextFadeFactor);
            ensureFadeTextAlphaRight(newTop, oldTop);
            changeIndexNavTextAlpha(mCurrentTextAlpha);
            return;
        }

        //中间状态
        if( mTextFadeBeginScrollTop <= oldTop && oldTop <= mTextFadeEndScrollTop){
            mCurrentTextAlpha = (int) (mCurrentTextAlpha - (newTop - oldTop) * mTextFadeFactor);
            ensureFadeTextAlphaRight(newTop, oldTop);
            changeIndexNavTextAlpha(mCurrentTextAlpha);
        }
    }

    private void ensureFadeTextAlphaRight(int newTop,int oldTop){
        if (newTop > oldTop) {
            mCurrentTextAlpha = mCurrentTextAlpha<0 ? 0 : mCurrentTextAlpha;
        } else {
            mCurrentTextAlpha = mCurrentTextAlpha>255 ? 255 : mCurrentTextAlpha;
        }
    }

    /**
     * 主导航条消失或者显示
     */
    private void fadeMainNavOnScroll(int newLeft, int newTop, int oldLeft, int oldTop){
        //上滑上界
        if(newTop >= mMainNavFadeEndScrollTop){
            changeMainNavAlpha(1.0f);
            mCurrentMainNavAlpha = 1.0f;
            return;
        }
        //下滑下界
        if(newTop <=  mMainNavFadeBeginScrollTop){
            changeMainNavAlpha(0.0f);
            mCurrentMainNavAlpha = 0.0f;
            return;
        }

        //上滑下交界
        if(mMainNavFadeBeginScrollTop > oldTop && newTop >mMainNavFadeBeginScrollTop){
            mCurrentMainNavAlpha = (newTop - mMainNavFadeBeginScrollTop) * mMainNavFadeFactor;
            Logger.i("newTop :" + newTop + ",oldTop :" + oldTop + "mCurrentMainNavAlpha :" + mCurrentMainNavAlpha);
            ensureFadeMainNavAlphaRight(newTop,oldTop);
            changeMainNavAlpha(mCurrentMainNavAlpha);
            return;
        }

        //下滑上交界
        if(mMainNavFadeEndScrollTop <oldTop && newTop < mMainNavFadeEndScrollTop){
            mCurrentMainNavAlpha = 1.0f - (mMainNavFadeEndScrollTop - newTop)* mMainNavFadeFactor;
            ensureFadeMainNavAlphaRight(newTop,oldTop);
            changeMainNavAlpha(mCurrentMainNavAlpha);
            return;
        }

        //中间状态
        if( mMainNavFadeBeginScrollTop <= oldTop && oldTop <= mMainNavFadeEndScrollTop){
            mCurrentMainNavAlpha = mCurrentMainNavAlpha + (newTop - oldTop) * mMainNavFadeFactor;
            ensureFadeMainNavAlphaRight(newTop,oldTop);
            changeMainNavAlpha(mCurrentMainNavAlpha);
        }
    }

    private void ensureFadeMainNavAlphaRight(int newTop,int oldTop){
        if(newTop > oldTop){
            mCurrentMainNavAlpha = mCurrentMainNavAlpha > 1? 1: mCurrentMainNavAlpha;
        }else{
            mCurrentMainNavAlpha = mCurrentMainNavAlpha < 0? 0: mCurrentMainNavAlpha;
        }
    }

    /**
     * 缩放首页导航条
     */
    private void zoomIndexNavOnScroll(int newLeft, int newTop, int oldLeft, int oldTop){

        //上滑上界
        if(newTop >= mIndexNavZoomEndScrollTop){
            changeIndexNavPadding(mFinalIndexNavPaddingLeft, mFinalIndexNavPaddingRight);
            mCurrentIndexNavPaddingLeft = mFinalIndexNavPaddingLeft;
            mCurrentIndexNavPaddingRight = mFinalIndexNavPaddingRight;
            return;
        }

        //下滑下界
        if(newTop <= mIndexNavZoomBeginScrollTop){
            changeIndexNavPadding(0, 0);
            mCurrentIndexNavPaddingLeft = 0;
            mCurrentIndexNavPaddingRight = 0;
            return;
        }

        //上滑下交界
        if( mIndexNavZoomBeginScrollTop > oldTop && newTop > mIndexNavZoomBeginScrollTop){
            mCurrentIndexNavPaddingLeft = (int) ((newTop - mIndexNavZoomBeginScrollTop) * mIndexNavMoveLeftFactor);
            mCurrentIndexNavPaddingRight = (int) ((newTop - mIndexNavZoomBeginScrollTop) * mIndexNavMoveRightFactor);
            ensureIndexNavPaddingRight(newTop, oldTop);
            changeIndexNavPadding(mCurrentIndexNavPaddingLeft, mCurrentIndexNavPaddingRight);
            return;
        }

        //下滑上交界
        if( mIndexNavZoomBeginScrollTop < oldTop && newTop <mIndexNavZoomBeginScrollTop){
            mCurrentIndexNavPaddingLeft = (int) (mFinalIndexNavPaddingLeft - (mIndexNavZoomBeginScrollTop-newTop)*mIndexNavMoveLeftFactor);
            mCurrentIndexNavPaddingRight = (int) (mCurrentIndexNavPaddingRight - (mIndexNavZoomBeginScrollTop-newTop)*mIndexNavMoveRightFactor);
            ensureIndexNavPaddingRight(newTop, oldTop);
            changeIndexNavPadding(mCurrentIndexNavPaddingLeft, mCurrentIndexNavPaddingRight);
            return;
        }

        //中间状态
        if( mIndexNavZoomBeginScrollTop <= oldTop && oldTop <= mIndexNavZoomEndScrollTop){
            mCurrentIndexNavPaddingLeft = (int) (mCurrentIndexNavPaddingLeft + (newTop - oldTop) * mIndexNavMoveLeftFactor);
            mCurrentIndexNavPaddingRight = (int) (mCurrentIndexNavPaddingRight + (newTop - oldTop) * mIndexNavMoveRightFactor);
            ensureIndexNavPaddingRight(newTop, oldTop);
            changeIndexNavPadding(mCurrentIndexNavPaddingLeft, mCurrentIndexNavPaddingRight);
        }
    }

    private void ensureIndexNavPaddingRight(int newTop, int oldTop){
        if(newTop > oldTop){
            mCurrentIndexNavPaddingLeft = mCurrentIndexNavPaddingLeft > mFinalIndexNavPaddingLeft? mFinalIndexNavPaddingLeft: mCurrentIndexNavPaddingLeft;
            mCurrentIndexNavPaddingRight = mCurrentIndexNavPaddingRight > mFinalIndexNavPaddingRight? mFinalIndexNavPaddingRight: mCurrentIndexNavPaddingRight;
        }else{
            mCurrentIndexNavPaddingLeft = mCurrentIndexNavPaddingLeft < 0? 0: mCurrentIndexNavPaddingLeft;
            mCurrentIndexNavPaddingRight = mCurrentIndexNavPaddingRight < 0? 0: mCurrentIndexNavPaddingRight;
        }
    }

    /**
     * 移动主导航栏的元素
     */
    private void transferMainNavOnScroll(int newLeft, int newTop, int oldLeft, int oldTop){

        //上滑上界
        if(newTop >= mMainNavTranEndScrollTop){
            mCurrentMNavIndexMarginLeft = 0;
            mCurrentMNavMidTGroupMarginTop = 0;
            mCurrentMNavRightContainerMarginRight = 0;
            setLeftMargin(mMainNavIndexRB, 0);
            setTopMargin(mMNavMidRG, 0);
            setRightMargin(mMNavRightContainerView, mCurrentMNavRightContainerMarginRight);
            return;
        }

        //下滑下界
        if(newTop <= mMainNavTranBeginScrollTop){
            mCurrentMNavIndexMarginLeft = mOrginMNavIndexMarginLeft;
            mCurrentMNavMidTGroupMarginTop = mOrginMNavMidTGroupMarginTop;
            mCurrentMNavRightContainerMarginRight = mOrginMNavRightContainerMarginRight;
            setLeftMargin(mMainNavIndexRB, mOrginMNavIndexMarginLeft);
            setTopMargin(mMNavMidRG, mOrginMNavMidTGroupMarginTop);
            setRightMargin(mMNavRightContainerView, mCurrentMNavRightContainerMarginRight);
            return;
        }

        //上滑下交界
        if(mMainNavTranBeginScrollTop > oldTop && newTop > mMainNavTranBeginScrollTop){
            mCurrentMNavIndexMarginLeft = (int) (mOrginMNavIndexMarginLeft + (newTop - mMainNavTranBeginScrollTop) * mMNavIndexFactor);
            mCurrentMNavMidTGroupMarginTop = (int) (mOrginMNavMidTGroupMarginTop - (newTop - mMainNavTranBeginScrollTop) * mMNavMidTGroupFactor);
            mCurrentMNavRightContainerMarginRight = (int) (mOrginMNavRightContainerMarginRight -   (newTop - mMainNavTranBeginScrollTop) * mMNavRightContainerFactor);
            ensureTransferMainNavRight(newTop, oldTop);
            setLeftMargin(mMainNavIndexRB, mCurrentMNavIndexMarginLeft);
            setTopMargin(mMNavMidRG, mOrginMNavMidTGroupMarginTop);
            setRightMargin(mMNavRightContainerView, mCurrentMNavRightContainerMarginRight);
            return;
        }

        //下滑上交界
        if(mMainNavTranEndScrollTop<oldTop && newTop < mMainNavTranEndScrollTop){
            mCurrentMNavIndexMarginLeft = (int) ((newTop - mMainNavTranEndScrollTop) * mMNavIndexFactor);
            mCurrentMNavMidTGroupMarginTop = (int) ((mMainNavTranEndScrollTop - newTop) * mMNavMidTGroupFactor);
            mCurrentMNavRightContainerMarginRight = (int) ((newTop - mMainNavTranEndScrollTop) * mMNavRightContainerFactor);
            ensureTransferMainNavRight(newTop, oldTop);
            setLeftMargin(mMainNavIndexRB, mCurrentMNavIndexMarginLeft);
            setTopMargin(mMNavMidRG, mOrginMNavMidTGroupMarginTop);
            setRightMargin(mMNavRightContainerView, mCurrentMNavRightContainerMarginRight);
            return;
        }

        //中间
        if( mMainNavTranBeginScrollTop < oldTop && oldTop <= mMainNavTranEndScrollTop){
            mCurrentMNavIndexMarginLeft = (int) (mCurrentMNavIndexMarginLeft + (newTop - oldTop) * mMNavIndexFactor);
            mCurrentMNavMidTGroupMarginTop = (int) (mCurrentMNavMidTGroupMarginTop - (newTop - oldTop) *mMNavMidTGroupFactor);
            mCurrentMNavRightContainerMarginRight = (int) (mCurrentMNavRightContainerMarginRight + (newTop - oldTop) *mMNavRightContainerFactor);
            ensureTransferMainNavRight(newTop, oldTop);
            setLeftMargin(mMainNavIndexRB, mCurrentMNavIndexMarginLeft);
            setTopMargin(mMNavMidRG, mCurrentMNavMidTGroupMarginTop);
            setRightMargin(mMNavRightContainerView, mCurrentMNavRightContainerMarginRight);
        }
    }

    private void ensureTransferMainNavRight(int newTop, int oldTop){
        if(newTop > oldTop){
            mCurrentMNavIndexMarginLeft = mCurrentMNavIndexMarginLeft > 0? 0: mCurrentMNavIndexMarginLeft;
            mCurrentMNavRightContainerMarginRight = mCurrentMNavRightContainerMarginRight >0 ? 0 :mCurrentMNavRightContainerMarginRight;
            mCurrentMNavMidTGroupMarginTop = mCurrentMNavMidTGroupMarginTop < 0? 0: mCurrentMNavMidTGroupMarginTop;
        }else{
            mCurrentMNavIndexMarginLeft = mCurrentMNavIndexMarginLeft < mOrginMNavIndexMarginLeft ? mOrginMNavIndexMarginLeft : mCurrentMNavIndexMarginLeft;
            mCurrentMNavRightContainerMarginRight = mCurrentMNavRightContainerMarginRight < mOrginMNavRightContainerMarginRight ? mOrginMNavRightContainerMarginRight : mCurrentMNavRightContainerMarginRight;
            mCurrentMNavMidTGroupMarginTop = mCurrentMNavMidTGroupMarginTop > mOrginMNavMidTGroupMarginTop ? mOrginMNavMidTGroupMarginTop : mCurrentMNavMidTGroupMarginTop;
        }
    }

    private void changeIndexNavTextAlpha(int alpha){
        setViewTextAlpha(mIndexNavDiscoverTV,alpha);
        setViewTextAlpha(mIndexNavBlogTV,alpha);
        setViewTextAlpha(mIndexNavMineTV,alpha);
        setViewTextAlpha(mIndexNavMoreTV,alpha);
    }

    private void changeMainNavAlpha(float alpha){
        setViewAlpha(mMainNavView, alpha);
    }

    private void changeIndexNavPadding(int paddingLeft, int paddingRight){
        mIndexHeaderNavView.setPadding(paddingLeft, mIndexHeaderNavView.getPaddingTop(), paddingRight, mIndexHeaderNavView.getPaddingBottom());
    }

    private void setViewTextAlpha(TextView v ,int alpha){
        ColorStateList colorStateList = v.getTextColors();
        v.setTextColor(colorStateList.withAlpha(alpha));
    }

    @SuppressLint("NewApi")
    private void setViewAlpha(View v, float alpha){
        if(android.os.Build.VERSION.SDK_INT >= 11){
            v.setAlpha(alpha);
        }else{
            AlphaAnimation alphaAnim = new AlphaAnimation(alpha, alpha);
            alphaAnim.setDuration(0);
            alphaAnim.setFillAfter(true);
            v.startAnimation(alphaAnim);
        }
    }

    private void setLeftMargin(View v, int leftMargin){
        RelativeLayout.LayoutParams lp = (android.widget.RelativeLayout.LayoutParams) v.getLayoutParams();
        lp.leftMargin = leftMargin;
        v.setLayoutParams(lp);
    }

    private void setTopMargin(View v, int topMargin){
        RelativeLayout.LayoutParams lp = (android.widget.RelativeLayout.LayoutParams) v.getLayoutParams();
        lp.topMargin = topMargin;
        v.setLayoutParams(lp);
    }

    private void setRightMargin(View v, int rightMargin){
        RelativeLayout.LayoutParams lp = (android.widget.RelativeLayout.LayoutParams) v.getLayoutParams();
        lp.rightMargin = rightMargin;
        v.setLayoutParams(lp);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            mHandler.removeMessages(MSG_PULL_RECOVER);
            mHandler.sendEmptyMessageDelayed(MSG_PULL_RECOVER, 200);
        }
        return false;
    }

    private void showMainNav(){
        fadeTextOnScroll(0, mMaxScrollTop, 0, mMaxScrollTop);
        fadeMainNavOnScroll(0, mMaxScrollTop, 0, mMaxScrollTop);
        zoomIndexNavOnScroll(0, mMaxScrollTop, 0, mMaxScrollTop);
        transferMainNavOnScroll(0, mMaxScrollTop, 0, mMaxScrollTop);
        mMainSV.scrollTo(0, mMaxScrollTop);
    }

    private void hideMainNav(){
        fadeTextOnScroll(0, 0, 0, 0);
        fadeMainNavOnScroll(0, 0, 0, 0);
        zoomIndexNavOnScroll(0, 0, 0, 0);
        transferMainNavOnScroll(0, 0, 0, 0);
        mMainSV.scrollTo(0, 0);
    }

    private void pullRecover() {
        int upY = mMainSV.getScrollY();
        int divideScrollY  = mMaxScrollTop /2;
        if(upY<= divideScrollY){
            hideMainNav();
        }else if(upY < mMaxScrollTop){
            showMainNav();
        }
    }

    private static final class InnerHandler extends Handler {

        private WeakReference<ImitateWandoujiaActivity> mActivityWR;

        public InnerHandler(ImitateWandoujiaActivity theActivity) {
            mActivityWR = new WeakReference<>(theActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_PULL_RECOVER:
                    ImitateWandoujiaActivity theActivity = mActivityWR.get();
                    if (theActivity == null) return;
                    theActivity.pullRecover();
                    break;
            }
        }
    }

}
