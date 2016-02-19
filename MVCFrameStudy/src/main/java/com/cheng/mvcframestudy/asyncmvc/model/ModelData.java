package com.cheng.mvcframestudy.asyncmvc.model;

import java.io.Serializable;

@Immutable
public final class ModelData implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int answer;
	
	public ModelData(int answer) {
		this.answer = answer;
	}
	
	public final int getAnswer() {
		return answer;
	}
}
