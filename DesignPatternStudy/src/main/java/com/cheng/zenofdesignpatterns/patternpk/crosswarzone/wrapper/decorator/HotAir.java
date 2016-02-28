package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.decorator;

/**
 * 吹大话
 */
public class HotAir extends Decorator {

	public HotAir(IStarDecorator _star){
		super(_star);
	}
	
	@Override
	public void act(){
		System.out.println("排演前：夸夸其谈，没有自己不能演的角色");
		super.act();
	}

}
