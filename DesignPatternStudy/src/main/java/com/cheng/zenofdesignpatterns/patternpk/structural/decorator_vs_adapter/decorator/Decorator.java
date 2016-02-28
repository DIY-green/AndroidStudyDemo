package com.cheng.zenofdesignpatterns.patternpk.structural.decorator_vs_adapter.decorator;

/**
 * 装饰类
 */
public class Decorator implements Swan {

	private Swan swan;

	// 修饰的是谁
	public Decorator(Swan _swan){
		this.swan =_swan;
	}
	
	public void cry() {
		swan.cry();
	}

	public void desAppaearance() {
		swan.desAppaearance();
	}

	public void fly() {
		swan.fly();
	}

}