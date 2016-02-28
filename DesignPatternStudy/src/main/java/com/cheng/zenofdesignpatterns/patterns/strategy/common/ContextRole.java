package com.cheng.zenofdesignpatterns.patterns.strategy.common;

/**
 * 封装角色
 */
public class ContextRole {
	// 抽象策略
	private IStrategy strategy = null;
	
	// 构造函数设置具体策略
	public ContextRole(IStrategy _strategy){
		this.strategy = _strategy;
	}
	
	// 封装后的策略方法
	public void doAnythinig(){
		this.strategy.doSomething();
	}

    /**
     * 它也叫做上下文角色，起承上启下封装作用，屏蔽高层模块对策略、算法的直接访问，
     * 封装可能存在的变化
     */
}
