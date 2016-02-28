package com.cheng.zenofdesignpatterns.patterns.decorator.common;

/**
 * 抽象构件
 */
public abstract class Component {
	// 抽象的方法
	public abstract void operate();

	/**
	 * 注意
     * 在装饰模式中，必然有一个最基本、最核心、最原始的接口或抽象类充当Component抽象构件
	 */
}
