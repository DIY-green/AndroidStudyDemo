package com.cheng.zenofdesignpatterns.patterns.bridge.corporation;

/**
 *
 */
public class Clothes extends Product {

	public void beProducted() {
		System.out.println("生产出的衣服是这个样子的...");
	}
	
	public void beSelled() {
		System.out.println("生产出的衣服卖出去了...");
	}

}


