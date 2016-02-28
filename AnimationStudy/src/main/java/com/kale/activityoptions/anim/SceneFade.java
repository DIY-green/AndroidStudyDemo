package com.kale.activityoptions.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;

import com.kale.activityoptions.transition.TransitionAnims;

public class SceneFade extends TransitionAnims{
	
	public SceneFade(Activity activity) {
		super(activity);
		// TODO 自动生成的构造函数存根
	}

	public void playScreenAnims(final boolean isEnter) {
		float fromAlpha,toAlpha;
		if (isEnter) {
			fromAlpha = 0f;
			toAlpha = 1f;
		}else {
			fromAlpha = 1f;
			toAlpha = 0f;
		}
		
		// TODO 自动生成的方法存根
		AnimatorSet set = new AnimatorSet();
		ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(getSceneRoot(), "alpha", fromAlpha, toAlpha);
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
		set.play(alphaAnim);
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
