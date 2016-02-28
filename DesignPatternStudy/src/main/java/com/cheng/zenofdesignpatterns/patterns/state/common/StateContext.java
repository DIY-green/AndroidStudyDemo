package com.cheng.zenofdesignpatterns.patterns.state.common;

/**
 * 环境角色
 */
public class StateContext {

	// 定义状态
	public final static State STATE1 = new ConcreteState1();
	public final static State STATE2 = new ConcreteState2();
	
	// 当前状态
	private State CurrentState;
	
	// 获得当前状态
	public State getCurrentState() {
		return CurrentState;
	}
	
	// 设置当前状态
	public void setCurrentState(State currentState) {
		this.CurrentState = currentState;
		// 切换状态
		this.CurrentState.setContext(this);
	}
	
	// 行为委托
	public void handle1(){
		this.CurrentState.handle1();
	}
	
	public void handle2(){
		this.CurrentState.handle2();
	}

    /**
     * 环境角色有两个不成文的约束：
     * - 把状态对象声明为静态常量，有几个状态对象就声明几个静态常量
     * - 环境角色具有状态抽象角色定义的所有行为，具体执行使用委托方式
     */

}
