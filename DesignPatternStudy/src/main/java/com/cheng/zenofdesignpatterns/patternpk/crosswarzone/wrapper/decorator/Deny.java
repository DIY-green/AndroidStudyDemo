package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.decorator;

/**
 * 抵赖
 */
public class Deny extends Decorator {
	
	public Deny(IStarDecorator _star){
		super(_star);
	}
	
	@Override
	public void act(){
		super.act();
		System.out.println("排演后：百般抵赖，死不承认");
	}

}
