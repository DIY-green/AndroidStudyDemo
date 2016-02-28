package com.cheng.zenofdesignpatterns.patternpk.structural.decorator_vs_adapter.adapter;

/**
 * 丑小鸭
 */
public class AUglyDuckling extends WhiteSwan implements Duck {
	
	// 丑小鸭的叫声
	public void cry() {
		super.cry();
	}
	
	// 丑小鸭的外形
	public void desAppearance() {
		super.desAppaearance();
	}
	
	// 丑小鸭的其他行为
	public void desBehavior(){
		// 丑小鸭不仅会游泳
		System.out.println("会游泳");
		// 还会飞行
		super.fly();
	}

}
