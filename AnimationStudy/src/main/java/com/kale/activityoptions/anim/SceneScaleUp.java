package com.kale.activityoptions.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;

import com.kale.activityoptions.transition.TransitionAnims;

public class SceneScaleUp extends TransitionAnims{

	private float mStartX;
	private float mStartY;
	private float mHeight;
	private float mWidth;
	
	/**
	 * @param time
	 * @param isShowing:是否是从无到有的显示状态，如果是true就是显示
	 */
	public SceneScaleUp(Activity activity, 
			float startX, float startY, float width, float height) {
		super(activity);
		// TODO 自动生成的构造函数存根
		mStartX = startX;
		mStartY = startY;
		mWidth = width;
		mHeight = height;
		
	}
	
	public void playScreenAnims(final boolean isEnter) {
		//Activity activity = getActivity();
		View sceneRoot = getSceneRoot();
		
		float fromAlpha,toAlpha;
		float fromX, toX ;
		float fromY, toY;
		float fromScaleX, toScaleX;
		float fromScaleY, toScaleY;
		
		if (isEnter) {
			fromAlpha = 0f;
			toAlpha = 1f;
			fromX = mStartX;
			toX = 0f;
			fromY = mStartY;
			toY = 0f;
			fromScaleX = (float)mWidth / getSceneRoot().getWidth();
			toScaleX = 1f;
			fromScaleY = (float)mHeight / getSceneRoot().getHeight();
			toScaleY = 1f;
		}else {
			fromAlpha = 1f;
			toAlpha = 0f;
			fromX = 0f;
			toX = mStartX;
			fromY = 0f;
			toY = mStartY;
			fromScaleX = 1f;
			toScaleX = (float)mWidth / getSceneRoot().getWidth();
			fromScaleY = 1f;
			toScaleY = (float)mHeight / getSceneRoot().getHeight();
		}
		//定义移动的起始位置，如果不定义就是相对于自身中心
		sceneRoot.setPivotX(0f);
		sceneRoot.setPivotY(0f);
		
		// TODO 自动生成的方法存根
		AnimatorSet set = new AnimatorSet();
		ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(sceneRoot, "alpha", fromAlpha, toAlpha);
		ObjectAnimator xAnim = ObjectAnimator.ofFloat(sceneRoot, "x", fromX, toX);
		ObjectAnimator yAnim = ObjectAnimator.ofFloat(sceneRoot, "y", fromY, toY);
		ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(sceneRoot, "scaleX", fromScaleX, toScaleX);
		ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(sceneRoot, "scaleY",fromScaleY, toScaleY);
		
		set.addListener(new TransitionAnimsListener() {
			@Override
			public void onAnimationEnd(Animator animator) {
				// TODO 自动生成的方法存根
				super.onAnimationEnd(animator);
				if (isEnter) {
					enterAnimsEnd();
				}else {
					exitAnimsEnd();
				}
			}
		});
		
		set.play(alphaAnim).with(xAnim).with(yAnim).with(scaleXAnim).with(scaleYAnim);
		set.setDuration(getAnimsDuration());
		set.setStartDelay(getAnimsStartDelay());
		set.setInterpolator(getAnimsInterpolator());
		set.start();
		
	}

	@Override
	public void playScreenEnterAnims() {
		// TODO 自动生成的方法存根
		playScreenAnims(true);
	}

	@Override
	public void playScreenExitAnims() {
		// TODO 自动生成的方法存根
		playScreenAnims(false);
	}

	
	
}
