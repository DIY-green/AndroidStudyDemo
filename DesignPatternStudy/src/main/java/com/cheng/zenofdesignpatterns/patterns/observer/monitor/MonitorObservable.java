package com.cheng.zenofdesignpatterns.patterns.observer.monitor;

/**
 * 所有被观察者者，通用接口
 */
public interface MonitorObservable {
	
	// 增加一个观察者
	void addObserver(MonitorObserver observer);
	
	// 删除一个观察者，——我不想让你看了
	void deleteObserver(MonitorObserver observer);
	
	// 既然要观察，我发生改变了他也应该用所动作——通知观察者
	void notifyObservers(String context);
}
