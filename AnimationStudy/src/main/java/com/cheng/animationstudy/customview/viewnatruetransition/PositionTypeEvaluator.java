package com.cheng.animationstudy.customview.viewnatruetransition;

import android.animation.TypeEvaluator;

public class PositionTypeEvaluator implements TypeEvaluator<Positions> {

	public PositionTypeEvaluator() {
	}

	@Override
	public Positions evaluate(float fraction, Positions startValue, Positions endValue) {
		// onFocus
		float focusY = startValue.getFocusY() + (endValue.getFocusY() -  startValue.getFocusY()) * fraction;

		// slideToTop
		float[] slideToTop = new float[startValue.getSlideToTop().length];
		float[] slideToTopStart = startValue.getSlideToTop();
		float[] slideToTopEnd = endValue.getSlideToTop();
		for(int i = 0; i < slideToTop.length; i++){
			slideToTop[i] = slideToTopStart[i] + (slideToTopEnd[i] - slideToTopStart[i]) * fraction;
		}

		// stickyTo
		float stickToY = startValue.getStickToY() + (endValue.getStickToY() - startValue.getStickToY()) * fraction;

		// fadeOutToBottom
		float[] nextContainersY = new float[startValue.getNextContainersY().length];
		float[] nextContainersStart = startValue.getNextContainersY();
		float[] nextContainersEnd = endValue.getNextContainersY();
		for(int i = 0; i < nextContainersY.length; i++){
			nextContainersY[i] = nextContainersStart[i] + (nextContainersEnd[i] - nextContainersStart[i]) * fraction;
		}
		float nextContainersAlpha = startValue.getNextContainersAlpha() + (endValue.getNextContainersAlpha() - startValue.getNextContainersAlpha()) * fraction;

		// fadeInToTop
		float editY = startValue.getEditY() + (endValue.getEditY() - startValue.getEditY()) * fraction;
		float editAlpha = startValue.getEditAlpha() + (endValue.getEditAlpha() - startValue.getEditAlpha()) * fraction;

		return new Positions(focusY, nextContainersY, nextContainersAlpha, stickToY, editY, editAlpha, slideToTop);
	}
}
