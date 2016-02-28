package com.cheng.zenofdesignpatterns.patterns.observer.common;


/**
 * 观察者
 */
public interface CommonObserver {
	
	//更新方法
	void update();

    /**
     * 观察者接收到消息后，即进行update操作，对接收到的消息进行处理
     */
}
