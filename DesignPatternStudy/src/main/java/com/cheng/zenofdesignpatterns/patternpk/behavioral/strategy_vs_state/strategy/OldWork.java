package com.cheng.zenofdesignpatterns.patternpk.behavioral.strategy_vs_state.strategy;

/**
 * 老年人工作
 */
public class OldWork extends WorkAlgorithm {

	// 老年人的工作
	@Override
	public void work() {
		System.out.println("老年人的工作就是回忆以前的生活！");
	}

}
