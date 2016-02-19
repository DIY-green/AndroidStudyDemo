package com.cheng.mvcframestudy.simplemvc.model;

import java.util.ArrayList;
import java.util.List;

public final class StarRatingModel {
	public static final int MAX_STARS = 5;

	public interface Listener {
		void handleStarRatingChanged(StarRatingModel sender);
	}
	
	private int stars = 1;
	
	private List<Listener> listeners = new ArrayList<Listener>();

	public StarRatingModel() {
		
	}
	
	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		if (stars > MAX_STARS) {
			stars = MAX_STARS;
		} else if (stars < 0) {
			stars = 0;
		}
		if (stars != this.stars) {
			this.stars = stars;
			
			for (Listener listener : listeners) {
				listener.handleStarRatingChanged(this);
			}
		}
	}
	
	public void addListener(Listener listener) {
		this.listeners.add(listener);
	}
	
	public void removeListener(Listener listener) {
		this.listeners.remove(listener);
	}
}
