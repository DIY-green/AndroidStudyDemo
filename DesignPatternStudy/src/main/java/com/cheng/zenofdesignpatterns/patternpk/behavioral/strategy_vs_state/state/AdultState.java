package com.cheng.zenofdesignpatterns.patternpk.behavioral.strategy_vs_state.state;

/**
 * 成年人状态
 */
public class AdultState extends HumanState {

	// 成年人的工作就是挣钱
	@Override
	public void work() {
		System.out.println("成年人的工作就是挣钱！");
		super.human.setState(Human.OLD_STATE);
	}

}
