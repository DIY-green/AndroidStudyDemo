package com.cheng.zenofdesignpatterns.patternpk.behavioral.strategy_vs_state.strategy;

/**
 * 成年人工作
 */
public class AdultWork extends WorkAlgorithm {

	// 成年人的工作
	@Override
	public void work() {
		System.out.println("成年人的工作就是挣钱！");
	}

}
