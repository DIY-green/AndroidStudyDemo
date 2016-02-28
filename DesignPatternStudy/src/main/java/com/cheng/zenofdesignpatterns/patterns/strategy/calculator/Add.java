package com.cheng.zenofdesignpatterns.patterns.strategy.calculator;

/**
 *
 */
public class Add implements ICalculator {
	//加法运算
	public int exec(int a, int b) {
		return a+b;
	}

}
