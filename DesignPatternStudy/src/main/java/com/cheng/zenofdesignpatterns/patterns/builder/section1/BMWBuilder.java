package com.cheng.zenofdesignpatterns.patterns.builder.section1;

import java.util.ArrayList;

/**
 * 给定一个顺序，返回一个宝马车
 */
public class BMWBuilder extends CarBuilder {
	private BMWModel bmw = new BMWModel();
	
	@Override
	public CarModel getCarModel() {
		return this.bmw;
	}

	@Override
	public void setSequence(ArrayList<String> sequence) {
		this.bmw.setSequence(sequence);
	}

}
