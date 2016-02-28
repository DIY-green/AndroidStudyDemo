package com.cheng.zenofdesignpatterns.patterns.state.common;

/**
 * 抽象状态类
 */
public abstract class State {

	// 定义一个环境角色，提供子类访问
	protected StateContext context;
	
	// 设置环境角色
	public void setContext(StateContext _context){
		this.context = _context;
	}
	
	// 行为1
	public abstract void handle1();
	
	// 行为2
	public abstract void handle2();

}
