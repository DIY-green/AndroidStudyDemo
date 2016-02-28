package com.cheng.zenofdesignpatterns.patterns.strategy.common;

/**
 * 抽象策略角色
 */
public interface IStrategy {
	
	//策略模式的运算法则
	void doSomething();
    /**
     * 策略、算法家族的抽象，通常为接口，定义每个策略或算法必须具有的方法和属性
     */
}
