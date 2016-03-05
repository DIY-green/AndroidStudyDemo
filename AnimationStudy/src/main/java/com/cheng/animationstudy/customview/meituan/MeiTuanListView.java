package com.cheng.animationstudy.customview.meituan;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cheng.animationstudy.R;
import com.cheng.utils.Logger;
import com.cheng.utils.ViewFinder;

import java.text.SimpleDateFormat;

/**
 * 美团下拉刷新ListView
 */
public class MeiTuanListView extends ListView implements AbsListView.OnScrollListener {

    /**
     * 首先我们要定义好几个状态，下拉刷新有这样几个状态：
     * DONE:隐藏的状态
     * PULL_TO_REFRESH:下拉刷新的状态
     * RELEASE_TO_REFRESH:松开刷新的状态
     * REFRESHING:正在刷新的状态
     */
    private static final int DONE = 0;
    private static final int PULL_TO_REFRESH = 1;
    private static final int RELEASE_TO_REFRESH = 2;
    private static final int REFRESHING = 3;
    private static final int RATIO = 3;

    private LinearLayout mHeaderView;
    private TextView mPullToRefreshTV;
    private MeiTuanRefreshFirstStepView mMeiTuanRefreshFirstStepView;
    private MeiTuanRefreshSecondStepView mMeiTuanRefreshSecondStepView;
    private MeiTuanRefreshThirdStepView mMeiTuanRefreshThirdStepView;

    private FrameLayout mAnimContainerFL;
    private OnMeiTuanRefreshListener mOnMeiTuanRefreshListener;
    private AnimationDrawable mSecondAnim;
    private AnimationDrawable mThirdAnim;

    private Animation mAnimation;
    private int mHeaderViewHeight;
    private float mStartY;
    private float mOffsetY;
    private int mState;
    private int mFirstVisibleItem;
    private boolean mIsRecord;
    private boolean mIsEnd;
    private boolean mIsRefreable;
    private SimpleDateFormat mSimpleDateFormat;


    public interface OnMeiTuanRefreshListener{
        void onRefresh();
    }

    public MeiTuanListView(Context context) {
        super(context);
        init(context);
    }

    public MeiTuanListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MeiTuanListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Logger.TAG = "MeiTuanListView";
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        setOnScrollListener(this);

        this.mHeaderView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.meituan_header, null, false);
        this.mMeiTuanRefreshFirstStepView = ViewFinder.findViewById(mHeaderView, R.id.first_view);
        this.mMeiTuanRefreshSecondStepView = ViewFinder.findViewById(mHeaderView, R.id.second_view);
        this.mMeiTuanRefreshThirdStepView = ViewFinder.findViewById(mHeaderView, R.id.third_view);
        this.mPullToRefreshTV = ViewFinder.findViewById(mHeaderView, R.id.sdi_pulltorefresh_tv);
        this.mMeiTuanRefreshSecondStepView.setBackgroundResource(R.drawable.sdd_meituan_pulltorefreshsecondanim);
        this.mMeiTuanRefreshThirdStepView.setBackgroundResource(R.drawable.sdd_meituan_pulltorefreshthirdanim);

        this.mSecondAnim = (AnimationDrawable) this.mMeiTuanRefreshSecondStepView.getBackground();
        this.mThirdAnim = (AnimationDrawable) this.mMeiTuanRefreshThirdStepView.getBackground();

        measureView(mHeaderView);
        addHeaderView(mHeaderView);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
        Logger.i("MeiTuanPullToRefresh--HeaderViewHeight : " + mHeaderViewHeight);

        this.mState = DONE;
        this.mIsEnd = true;
        this.mIsRefreable = false;
    }

    public void setOnMeiTuanRefreshListener(OnMeiTuanRefreshListener onMeiTuanRefreshListener) {
        this.mOnMeiTuanRefreshListener = onMeiTuanRefreshListener;
        this.mIsRefreable = true;
    }

    public void setOnRefreshComplete() {
        mIsEnd = true;
        mState = DONE;

        changeHeaderByState(mState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mIsEnd) return super.onTouchEvent(ev);
        if (!mIsRefreable) return super.onTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mFirstVisibleItem==0 && !mIsRecord) {
                    mIsRecord = true;
                    mStartY = ev.getY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float tempY = ev.getY();
                if (mFirstVisibleItem==0 && !mIsRecord) {
                    mIsRecord = true;
                    mStartY = tempY;
                }
                if (mState!=REFRESHING && mIsRecord) {
                    mOffsetY = tempY - mStartY;
                    float currentHeight = (-mHeaderViewHeight+mOffsetY/3);
                    float currentProgress = 1+currentHeight/mHeaderViewHeight;
                    if (currentProgress >= 1) {
                        currentProgress = 1;
                    }
                    if (mState==RELEASE_TO_REFRESH && mIsRecord) {
                        setSelection(0);
                        if (-mHeaderViewHeight+mOffsetY/RATIO < 0) {
                            mState = PULL_TO_REFRESH;
                            changeHeaderByState(mState);
                        } else if (mOffsetY <= 0) {
                            mState = DONE;
                            changeHeaderByState(mState);
                        }
                    }
                    if (mState==PULL_TO_REFRESH && mIsRecord) {
                        setSelection(0);
                        if (-mHeaderViewHeight+mOffsetY/RATIO >= 0) {
                            mState = RELEASE_TO_REFRESH;
                            changeHeaderByState(mState);
                        } else if (mOffsetY <= 0) {
                            mState = DONE;
                            changeHeaderByState(mState);
                        }
                    }
                    if (mState==DONE && mIsRecord) {
                        if (mOffsetY >= 0) {
                            mState = PULL_TO_REFRESH;
                        }
                    }
                    if (mState == PULL_TO_REFRESH) {
                        mHeaderView.setPadding(0, (int)(-mHeaderViewHeight+mOffsetY/RATIO), 0, 0);
                        mMeiTuanRefreshFirstStepView.setCurrentProgress(currentProgress);
                        mMeiTuanRefreshFirstStepView.postInvalidate();
                    }
                    if (mState == RELEASE_TO_REFRESH) {
                        mHeaderView.setPadding(0, (int)(-mHeaderViewHeight+mOffsetY/RATIO), 0, 0);
                        mMeiTuanRefreshFirstStepView.setCurrentProgress(currentProgress);
                        mMeiTuanRefreshFirstStepView.postInvalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mState == PULL_TO_REFRESH) {
                    this.smoothScrollBy((int)(-mHeaderViewHeight+mOffsetY/RATIO)+mHeaderViewHeight, 500);
                    changeHeaderByState(mState);
                }
                if (mState == RELEASE_TO_REFRESH) {
                    this.smoothScrollBy((int)(-mHeaderViewHeight+mOffsetY/RATIO), 500);
                    mState = REFRESHING;
                    mOnMeiTuanRefreshListener.onRefresh();
                    changeHeaderByState(mState);
                }
                mIsRecord = false;
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void changeHeaderByState(int state) {
        switch (state) {
            case DONE:
                mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
                mMeiTuanRefreshFirstStepView.setVisibility(VISIBLE);
                mMeiTuanRefreshSecondStepView.setVisibility(GONE);
                mMeiTuanRefreshThirdStepView.setVisibility(GONE);
                mSecondAnim.stop();
                mThirdAnim.stop();
                break;
            case RELEASE_TO_REFRESH:
                mPullToRefreshTV.setText("放开刷新");
                mMeiTuanRefreshFirstStepView.setVisibility(GONE);
                mMeiTuanRefreshSecondStepView.setVisibility(VISIBLE);
                mMeiTuanRefreshThirdStepView.setVisibility(GONE);
                mSecondAnim.start();
                mThirdAnim.stop();
                break;
            case PULL_TO_REFRESH:
                mPullToRefreshTV.setText("下拉刷新");
                mMeiTuanRefreshFirstStepView.setVisibility(VISIBLE);
                mMeiTuanRefreshSecondStepView.setVisibility(GONE);
                mMeiTuanRefreshThirdStepView.setVisibility(GONE);
                mSecondAnim.stop();
                mThirdAnim.stop();
                mState = DONE;
                break;
            case REFRESHING:
                mPullToRefreshTV.setText("正在刷新");
                mMeiTuanRefreshFirstStepView.setVisibility(GONE);
                mMeiTuanRefreshSecondStepView.setVisibility(GONE);
                mMeiTuanRefreshThirdStepView.setVisibility(VISIBLE);
                mSecondAnim.stop();
                mThirdAnim.start();
                break;
        }
    }

    private void measureView(View child) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0+0, lp.width);
        int lpHeight = lp.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.mFirstVisibleItem = firstVisibleItem;
    }
}
