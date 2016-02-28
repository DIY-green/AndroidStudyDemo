package com.cheng.zenofdesignpatterns.patterns.strategy.calculator;

/**
 *
 */
public class Sub implements ICalculator {

	//减法
	public int exec(int a, int b) {
		return a-b;
	}

}
