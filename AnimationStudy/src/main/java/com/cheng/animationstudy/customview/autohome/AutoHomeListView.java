package com.cheng.animationstudy.customview.autohome;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cheng.animationstudy.R;
import com.cheng.utils.ViewFinder;

/**
 * Created by Administrator on 2015/11/9.
 */
public class AutoHomeListView extends ListView implements AbsListView.OnScrollListener {

	private static final int DONE = 0;
	private static final int PULL_TO_REFRESH = 1;
	private static final int RELEASE_TO_REFRESH = 2;
	private static final int REFRESHING = 3;
	private static final int RATIO = 3;

	private LinearLayout mHeaderView;
	private AutoHome mAutoHome;
	private TextView mPullToRefreshTV;
	private FrameLayout mAnimContainer;
	private PointerView mAutoHomeAnim;
	private Animation mAnimation;

	private OnAutoHomeRefreshListener mOnAutoHomeRefreshListener;

	private int mHeaderViewHeight;
	private float mStartY;
	private float mOffsetY;
	private int mState;
	private int mFirstVisibleItem;
	private boolean mIsRecord;
	private boolean mIsEnd;
	private boolean mIsRefreable;

	public interface OnAutoHomeRefreshListener{
		void onRefresh();
	}

	public AutoHomeListView(Context context) {
		super(context);
		init(context);
	}

	public AutoHomeListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public AutoHomeListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {
		setOverScrollMode(View.OVER_SCROLL_NEVER);
		setOnScrollListener(this);

		this.mHeaderView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.imitateautohome_header, null, false);
		this.mAutoHome = ViewFinder.findViewById(mHeaderView, R.id.sdi_autohome);
		this.mPullToRefreshTV = ViewFinder.findViewById(mHeaderView, R.id.sdi_pulltorefresh_tv);
		mAnimContainer = ViewFinder.findViewById(mHeaderView, R.id.sdi_animcontainer_fl);
		mAutoHomeAnim = ViewFinder.findViewById(mHeaderView, R.id.sdi_animpointer_iv);

		mAnimation = AnimationUtils.loadAnimation(context, R.anim.sda_autohome_pointer_rotate);

		measureView(mHeaderView);
		addHeaderView(mHeaderView);
		mHeaderViewHeight = mHeaderView.getMeasuredHeight();
		mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);

		mState = DONE;
		mIsEnd = true;
		mIsRefreable = false;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		this.mFirstVisibleItem = firstVisibleItem;
	}

	public void setOnAutoHomeRefreshListener(OnAutoHomeRefreshListener onRefreshListener){
		mOnAutoHomeRefreshListener = onRefreshListener;
		mIsRefreable = true;
	}

	public void setOnRefreshComplete(){
		mIsEnd = true;
		mState = DONE;
		changeHeaderByState(mState);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (!mIsEnd) return super.onTouchEvent(ev);
		if (!mIsRefreable) return super.onTouchEvent(ev);
		switch (ev.getAction()){
			case MotionEvent.ACTION_DOWN:
				if (mFirstVisibleItem == 0 && !mIsRecord) {
					mIsRecord = true;
					mStartY = ev.getY();
				}
				break;
			case MotionEvent.ACTION_MOVE:
				float tempY = ev.getY();
				if (mFirstVisibleItem == 0 && !mIsRecord) {
					mIsRecord = true;
					mStartY = tempY;
				}
				if (mState!=REFRESHING && mIsRecord ) {
					mOffsetY = tempY - mStartY;
					float currentHeight = (-mHeaderViewHeight+mOffsetY/RATIO);
					float currentProgress = 1+currentHeight/mHeaderViewHeight;
					if (currentProgress >= 1) {
						currentProgress = 1;
					}
					if (mState == RELEASE_TO_REFRESH && mIsRecord) {
						setSelection(0);
						if (-mHeaderViewHeight+mOffsetY/RATIO < 0) {
							mState = PULL_TO_REFRESH;
							changeHeaderByState(mState);
						}else if (mOffsetY <= 0) {
							mState = DONE;
							changeHeaderByState(mState);
						}
					}
					if (mState == PULL_TO_REFRESH && mIsRecord) {
						setSelection(0);
						if (-mHeaderViewHeight+mOffsetY/RATIO >= 0) {
							mState = RELEASE_TO_REFRESH;
							changeHeaderByState(mState);
						}else if (mOffsetY <= 0) {
							mState = DONE;
							changeHeaderByState(mState);
						}
					}
					if (mState == DONE && mIsRecord) {
						if (mOffsetY>=0) {
							mState = PULL_TO_REFRESH;
						}
					}
					if (mState == PULL_TO_REFRESH) {
						mHeaderView.setPadding(0,(int)(-mHeaderViewHeight+mOffsetY/RATIO), 0, 0);
						mAutoHome.setCurrentProgress(currentProgress);
						mAutoHome.postInvalidate();
					}
					if (mState == RELEASE_TO_REFRESH) {
						mHeaderView.setPadding(0,(int)(-mHeaderViewHeight+mOffsetY/RATIO), 0, 0);
						mAutoHome.setCurrentProgress(currentProgress);
						mAutoHome.postInvalidate();
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
					mOnAutoHomeRefreshListener.onRefresh();
					changeHeaderByState(mState);
				}
				mIsRecord = false;
				break;
		}
		return super.onTouchEvent(ev);
	}

	private void changeHeaderByState(int state){
		switch (state) {
			case DONE:
				mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
				//第一状态的view显示出来
				mAutoHome.setVisibility(View.VISIBLE);
				//先停止一下第二阶段view的动画
				mAutoHomeAnim.clearAnimation();
				//将第二阶段view隐藏起来
				mAnimContainer.setVisibility(View.GONE);
				break;
			case RELEASE_TO_REFRESH:
				mPullToRefreshTV.setText("放开刷新");
				break;
			case PULL_TO_REFRESH:
				mPullToRefreshTV.setText("下拉刷新");
				//第一状态view显示出来
				mAutoHome.setVisibility(View.VISIBLE);
				//停止第二阶段动画
				mAutoHomeAnim.clearAnimation();
				//将第二阶段view隐藏
				mAnimContainer.setVisibility(View.GONE);
				break;
			case REFRESHING:
				mPullToRefreshTV.setText("正在刷新");
				//将第一阶段view隐藏
				mAutoHome.setVisibility(View.GONE);
				//将第二阶段view显示出来
				mAnimContainer.setVisibility(View.VISIBLE);
				//先停止第二阶段动画
				mAutoHomeAnim.clearAnimation();
				//启动第二阶段动画
				mAutoHomeAnim.startAnimation(mAnimation);
				break;
			default:
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
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, lp.width);
		int lpHeight = lp.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}
}
