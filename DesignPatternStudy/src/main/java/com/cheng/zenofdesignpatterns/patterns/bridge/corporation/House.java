package com.cheng.zenofdesignpatterns.patterns.bridge.corporation;

/**
 *
 */
public class House extends Product {

	//豆腐渣就豆腐渣呗，好歹也是个房子
	public void beProducted() {
		System.out.println("生产出的房子是这个样子的...");
	}

	//虽然是豆腐渣，也是能够销售出去的
	public void beSelled() {
		System.out.println("生产出的房子卖出去了...");
	}

}

