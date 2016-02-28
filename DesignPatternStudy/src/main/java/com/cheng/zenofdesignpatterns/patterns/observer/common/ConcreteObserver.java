package com.cheng.zenofdesignpatterns.patterns.observer.common;

/**
 * 具体的观察者
 */
public class ConcreteObserver implements CommonObserver {

	//实现更新方法
	public void update() {
		System.out.println("接收到信息，并进行处理！");
	}

}
