package com.cheng.zenofdesignpatterns.patterns.observer.monitor;

/**
 * 所有观察者，通用接口
 */
public interface MonitorObserver {
	
	// 一发现别人有动静，自己也要行动起来
	void update(String context);
}
