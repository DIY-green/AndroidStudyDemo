package com.cheng.zenofdesignpatterns.patterns.state.common;

/**
 *
 */
public class ConcreteState2 extends State {

	@Override
	public void handle1() {		
		// 设置当前状态为stat1
		super.context.setCurrentState(StateContext.STATE1);
		// 过渡到state1状态，由Context实现
		super.context.handle1();
	}

	@Override
	public void handle2() {
		// 本状态下必须处理的逻辑
	}

}
