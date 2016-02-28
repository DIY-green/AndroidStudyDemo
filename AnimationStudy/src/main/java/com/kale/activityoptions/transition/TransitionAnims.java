package com.kale.activityoptions.transition;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;


import com.cheng.animationstudy.R;
import com.kale.activityoptions.transition.TransitionCompat.TransitionListener;

public abstract class TransitionAnims {
	//private String TAG = getClass().getSimpleName();
	
	private Activity mActivity;
	private View mSceneRoot;
	private Drawable mBackground;	
	
	private long mTime = 300;
	private long mStartDelay = 0;
	private TimeInterpolator mTimeInterpolator = new AccelerateDecelerateInterpolator();
	private TransitionListener mListener;
	
	/**
	 * @param activity
	 */
	public TransitionAnims(Activity activity) {
		mActivity = activity;
		//设置窗口的背景图，用来做渐变
		mBackground = activity.getResources().getDrawable(R.drawable.background);
		mBackground.setAlpha(0);//初始背景透明度为0
		// http://ask.csdn.net/questions/209
		// http://blog.csdn.net/u011494050/article/details/38775589
		// 得到当前视图的顶级视图，用来做布局的动画
		mSceneRoot = ((ViewGroup) getActivity().getWindow().getDecorView()).getChildAt(0);
		//mSceneRoot = ((ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
		mActivity.getWindow().setBackgroundDrawable(mBackground);
	}
	
	/**
	 * 进入动画结束时应该有的操作
	 */
	protected void enterAnimsEnd() {
		mSceneRoot.setAlpha(1.0f);
		mBackground.setAlpha(255);
	}
	
	/**
	 * 退出动画结束时应该有的动画
	 */
	protected void exitAnimsEnd() {
		mSceneRoot.setAlpha(0);
		mBackground.setAlpha(0);
		mActivity.finish();
		mActivity.overridePendingTransition(0, 0);
	}
	
	/**
	 * @return 当前的activity
	 */
	public Activity getActivity() {
		return mActivity;
	}
	
	/**
	 * @return 要进行动画的view
	 */
	public View getSceneRoot() {
		return mSceneRoot;
	}

	/**
	 * @return 当前activity的背景图
	 */
	public Drawable getBackground() {
		return mBackground;
	}
	
	/**
	 * 设置动画延迟的时间
	 * @param delayTime
	 */
	public void setAnimsStartDelay(long delayTime) {
		mStartDelay = delayTime;
	}
	
	public long getAnimsStartDelay() {
		return mStartDelay;
	}
	
	/**
	 * 设置动画变化效果
	 * @param interpolator
	 */
	public void setAnimsInterpolator(TimeInterpolator interpolator) {
		mTimeInterpolator = interpolator;
	}
	
	public TimeInterpolator getAnimsInterpolator() {
		return mTimeInterpolator;
	}
	
	/**
	 * 设置动画持续的时间
	 * @param time
	 */
	public void setAnimsDuration(long time) {
		mTime = time;
	}
	
	
	public long getAnimsDuration() {
		return mTime;
	}

	
	/**
	 * 开始执行activity进入的动画
	 */
	public abstract void playScreenEnterAnims();
	/**
	 * 开始执行activity退出的动画
	 */
	public abstract void playScreenExitAnims();
	
	
	
	/**
	 * 添加动画的监听器
	 * @param transitionListener
	 */
	public void addListener(TransitionListener transitionListener) {
		mListener = transitionListener;
	}
	/**
	 * 这里面传入的false没有任何意义
	 * @author:Jack Tony
	 * @tips  :
	 * @date  :2014-11-27
	 */
	protected class TransitionAnimsListener implements AnimatorListener, AnimationListener{

		@Override
		public void onAnimationStart(Animator animator) {
			// TODO 自动生成的方法存根
			if (mListener != null) {
				mListener.onTransitionStart(animator, null, false);
			}
		}

		@Override
		public void onAnimationEnd(Animator animator) {
			// TODO 自动生成的方法存根
			if (mListener != null) {
				mListener.onTransitionEnd(animator, null, false);
			}
		}

		@Override
		public void onAnimationCancel(Animator animator) {
			// TODO 自动生成的方法存根
			if (mListener != null) {
				mListener.onTransitionCancel(animator, null, false);
			}
		}

		@Override
		public void onAnimationRepeat(Animator animator) {
			// TODO 自动生成的方法存根
		}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO 自动生成的方法存根
			if (mListener != null) {
				mListener.onTransitionStart(null, animation, false);
			}
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO 自动生成的方法存根
			if (mListener != null) {
				mListener.onTransitionEnd(null, animation, false);
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO 自动生成的方法存根
		}
		
	}
}
