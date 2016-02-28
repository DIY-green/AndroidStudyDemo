package com.cheng.zenofdesignpatterns.patternpk.structural.decorator_vs_adapter.decorator;

/**
 * 强化行为
 */
public class StrongBehavior extends Decorator {

	// 强化谁
	public StrongBehavior(Swan _swan){
		super(_swan);
	}
	
	// 会飞行了
	public void fly(){
		System.out.println("会飞行了！");
	}
}