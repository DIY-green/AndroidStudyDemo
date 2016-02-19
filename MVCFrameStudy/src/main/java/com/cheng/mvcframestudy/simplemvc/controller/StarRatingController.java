package com.cheng.mvcframestudy.simplemvc.controller;

import android.view.MotionEvent;

import com.cheng.mvcframestudy.simplemvc.model.StarRatingModel;

public final class StarRatingController {
	private StarRatingModel model;
	
	public StarRatingController(StarRatingModel model) {
		this.model = model;
	}
	
	public void handleTap(MotionEvent event) {
		// the old trick with % to wrap around values
		model.setStars((model.getStars() + 1) % (StarRatingModel.MAX_STARS + 1));
	}
}
