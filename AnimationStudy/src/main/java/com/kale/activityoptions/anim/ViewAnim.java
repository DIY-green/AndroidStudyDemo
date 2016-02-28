package com.kale.activityoptions.anim;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.kale.activityoptions.transition.TransitionCompat.ViewAnimationListener;
import com.kale.activityoptions.util.Position;


public class ViewAnim {
	
	private View targetView;// 目标view，这里用于在没有传入toView，但需要动画结束后将某个view可见的情况
	
	private long mAnimTime = 300;// 默认
	private long mStartDelay = 0;
	//private Animator currentAnimator = null;// 当前的动画对象
	private TimeInterpolator mInterpolator = new AccelerateDecelerateInterpolator();
	private ViewAnimationListener mListener;
	
	private Rect mFinalBounds = null;

	/**
	 * 开始执行补间动画的方法
	 * @param fromView：开始执行动画的view
	 * @param finalBounds：动画执行后view的目标位置，这里用rect做了封装
	 * @param startOffsetY：开始位置的Y轴偏移量
	 * @param finalOffsetY：结束位置的Y轴偏移量
	 */
	public void startViewTweensAnim(final View fromView,Rect finalBounds,int startOffsetY,int finalOffsetY) {
		mFinalBounds = new Rect();
		mFinalBounds = finalBounds;
		startViewTweensAnim(fromView, (View)null, startOffsetY, finalOffsetY);
	}
	
	/**
	 * 开始执行补间动画的方法
	 * @param fromView：开始执行动画的view
	 * @param toView：用来给执行动画的view设定目标位置的view，两个view之间会自动产生补间动画
	 * @param startOffsetY：开始位置的偏移量
	 * @param finalOffsetY：目标位置的偏移量
	 */
	public void startViewTweensAnim(final View fromView,final View toView,int startOffsetY,int finalOffsetY) {
		Rect startBounds  = new Rect();
		startBounds.set(Position.getGlobalVisibleRect(fromView));
		Rect finalBounds = new Rect();
		if (mFinalBounds != null) {
			finalBounds.set(mFinalBounds);
		}else {
			toView.setVisibility(View.INVISIBLE);
			finalBounds.set(Position.getGlobalVisibleRect(toView));
		}
		startBounds.offset(0, -startOffsetY);
		finalBounds.offset(0, -finalOffsetY);
		
		//设定拉伸或者旋转动画的中心位置，这里是相对于自身左上角
		fromView.setPivotX(0f);
		fromView.setPivotY(0f);
		/**
		 * 通过动画来拉伸view时，view在动画过程中无法动态布局，因此要自己在update方法中重新写拉伸的算法
		 * 不能用scale的原因是，动画在执行时会通过：fromView.setScaleX(scaleX)来进行设置，这里仅仅是设置了拉伸比
		 * 但我们要的是重新绘制，不是拉伸，所以这里不能用拉伸动画
		 */
		//float scaleX = (float)finalBounds.width() / startBounds.width();
		//float scaleY = (float)finalBounds.height() / startBounds.height();
		
		AnimatorSet set = new AnimatorSet();
		ObjectAnimator xAnim = ObjectAnimator.ofFloat(fromView, "x", startBounds.left, finalBounds.left);
		ObjectAnimator yAnim = ObjectAnimator.ofFloat(fromView, "y", startBounds.top, finalBounds.top);
		//ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(fromView, View.SCALE_X, 1f, scaleX);
		//ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(fromView, View.SCALE_Y,1f, scaleY);
		xAnim.addUpdateListener(new ViewAnim.AnimUpdateListener(fromView, startBounds, finalBounds));

		set.play(xAnim).with(yAnim);
		
		set.setStartDelay(mStartDelay);
		set.setDuration(mAnimTime);
		set.setInterpolator(mInterpolator);
		set.addListener(new AnimListener(fromView,toView));

		set.start();
	}
	
	public void startViewSimpleAnim(final View fromView,Rect finalBounds,int 
			startOffsetY,int finalOffsetY, float startAlpha, float finalAlpha) {
		Rect startBounds  = new Rect();
		startBounds.set(Position.getGlobalVisibleRect(fromView));
		//设置偏移量
		startBounds.offset(0, -startOffsetY);
		finalBounds.offset(0, -finalOffsetY);
		//设定拉伸或者旋转动画的中心位置，这里是相对于自身左上角
		fromView.setPivotX(0f);
		fromView.setPivotY(0f);
		//计算拉伸比例
		float scaleX = (float)finalBounds.width() / startBounds.width();
		float scaleY = (float)finalBounds.height() / startBounds.height();
		
		AnimatorSet set = new AnimatorSet();
		ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(fromView, "alpha", startAlpha, finalAlpha);
		ObjectAnimator xAnim = ObjectAnimator.ofFloat(fromView, "x", startBounds.left, finalBounds.left);
		ObjectAnimator yAnim = ObjectAnimator.ofFloat(fromView, "y", startBounds.top, finalBounds.top);
		ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(fromView, View.SCALE_X, 1f, scaleX);
		ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(fromView, View.SCALE_Y,1f, scaleY);
		
		set.play(alphaAnim).with(xAnim).with(yAnim).with(scaleXAnim).with(scaleYAnim);
		
		set.setStartDelay(mStartDelay);
		set.setDuration(mAnimTime);
		set.setInterpolator(mInterpolator);
		set.addListener(new AnimListener(fromView,null));

		set.start();
	}
	
	/**
	 * @author:Jack Tony
	 * @tips  :动画在执行时触发的方法，在这里自己重新设定view的参数，让内部布局重新绘制
	 * @date  :2014-11-24
	 */
	private class AnimUpdateListener implements AnimatorUpdateListener{
		View mView;
		Rect mStartBounds, mFinalBounds;
		
		public AnimUpdateListener(View view, Rect startBounds, Rect finalBounds) {
			// TODO 自动生成的构造函数存根
			mView = view;
			mStartBounds = startBounds;
			mFinalBounds = finalBounds;
		}
		
		@Override
		public void onAnimationUpdate(ValueAnimator valueAnimator) {
			
			if (valueAnimator.getCurrentPlayTime() <= valueAnimator.getDuration()) {
				float fraction = valueAnimator.getAnimatedFraction();// 动画进度
				
				float scaleXDuration = mFinalBounds.width() - mStartBounds.width();
				float scaleYDuration = mFinalBounds.height() - mStartBounds.height();
				mView.getLayoutParams().width = (int)(mStartBounds.width() + (scaleXDuration * fraction));
				mView.getLayoutParams().height = (int)(mStartBounds.height() + (scaleYDuration * fraction));
				
				if (mListener != null) {
					mListener.onViewAnimationUpdate(mView, valueAnimator, fraction);
				}
			}
			mView.requestLayout();
		}
	}
	
	/**
	 * @author:Jack Tony
	 * @tips  :动画执行的监听器，结束时进行图片的隐藏操作
	 * @date  :2014-11-24
	 */
	private class AnimListener implements AnimatorListener{
		private View mFromView,mToView;
		
		public AnimListener(View fromView, View toView) {
			// TODO 自动生成的构造函数存根
			mFromView = fromView;
			mToView = toView;
		}
		
		@Override
		public void onAnimationStart(Animator animator) {
			// TODO 自动生成的方法存根
			mFromView.setVisibility(View.VISIBLE);
			if (mListener != null) {
				mListener.onViewAnimationStart(mFromView, animator);
			}
		}
		
		@Override
		public void onAnimationEnd(Animator animator) {
			// 动画结束后开始动画的那个view变为不可见并且从父控件中移除，目标的view可见（如果有的话）
			mFromView.setVisibility(View.INVISIBLE);
			((ViewGroup)mFromView.getParent()).removeView(mFromView);
			
			if (mToView != null) {
				mToView.setVisibility(View.VISIBLE);
			}
			// targetView是在没有传入toView时，用来做目标view可见性改变的
			if (targetView != null) {
				targetView.setVisibility(View.VISIBLE);
			}
			if (mListener != null) {
				mListener.onViewAnimationEnd(mFromView, animator);
			}
		}
		
		@Override
		public void onAnimationCancel(Animator animator) {
			// TODO 自动生成的方法存根
			if (mListener != null) {
				mListener.onViewAnimationCancel(mFromView, animator);
			}
		}

		@Override
		public void onAnimationRepeat(Animator animation) {
			// TODO 自动生成的方法存根
		}
		
	}
	
	/**
	 * 设置动画差值器
	 * @param interpolator
	 */
	public void setTimeInterpolator(TimeInterpolator interpolator) {
		mInterpolator = interpolator;
	}
	
	/**
	 * 动画初始化的监听器，用来初始化控件的各种属性
	 */
	
	public void addListener(ViewAnimationListener listener) {
		mListener = listener;
	}
	
	public void setDuration(long time) {
		mAnimTime = time;
	}
	
	public long getDuration() {
		return mAnimTime;
	}
	
	public void setStartDelay(long delay) {
		mStartDelay = delay;
	}
	
	public long getStartDelay() {
		return mStartDelay;
	}
	
	/**
	 * 设定目标图片，这个图片仅仅会在动画结束后可见。没有其他作用
	 * @param view
	 */
	public void setTargetView(View view) {
		targetView = view;
	}
	
}
