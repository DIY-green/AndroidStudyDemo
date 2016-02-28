package com.cheng.zenofdesignpatterns.patternpk.behavioral.strategy_vs_state.state;

/**
 * 环境角色
 */
public class Human {

	// 定义人类都具备哪些状态
	public static final HumanState CHIILD_STATE = new ChildState();
	public static final HumanState ADULT_STATE = new AdultState();
	public static final HumanState OLD_STATE = new OldState();
	
	// 定义一个人的状态
	private HumanState state;
	
	// 设置一个状态
	public void setState(HumanState _state){
		this.state = _state;
		this.state.setHuman(this);
	}
	
	// 人类的工作
	public void work(){
		this.state.work();
	}

}
