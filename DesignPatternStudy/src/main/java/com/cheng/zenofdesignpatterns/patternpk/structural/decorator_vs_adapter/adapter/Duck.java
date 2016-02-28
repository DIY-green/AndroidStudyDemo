package com.cheng.zenofdesignpatterns.patternpk.structural.decorator_vs_adapter.adapter;

/**
 * 抽象鸭子
 */
public interface Duck {
	
	// 会叫
	void cry();
	
	// 鸭子的外形
	void desAppearance();
	
	// 描述鸭子的其他行为
	void desBehavior();

}

