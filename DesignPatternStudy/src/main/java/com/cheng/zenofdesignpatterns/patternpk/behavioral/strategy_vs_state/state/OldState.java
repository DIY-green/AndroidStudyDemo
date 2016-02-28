package com.cheng.zenofdesignpatterns.patternpk.behavioral.strategy_vs_state.state;

/**
 * 老年人状态
 */
public class OldState extends HumanState {

	// 老年人的工作就是回忆
	@Override
	public void work() {
		System.out.println("老年人的工作就是回忆以前的生活！");
	}

}
