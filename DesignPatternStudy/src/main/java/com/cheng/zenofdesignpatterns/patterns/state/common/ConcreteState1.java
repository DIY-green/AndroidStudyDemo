package com.cheng.zenofdesignpatterns.patterns.state.common;

/**
 * 具体状态类
 */
public class ConcreteState1 extends State {

	@Override
	public void handle1() {
		// 本状态下必须处理的逻辑
	}

	@Override
	public void handle2() {
		// 设置当前状态为stat2
		super.context.setCurrentState(StateContext.STATE2);
		// 过渡到state2状态，由Context实现
		super.context.handle2();
	}

}
