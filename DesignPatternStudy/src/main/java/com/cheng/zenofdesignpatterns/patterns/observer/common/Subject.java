package com.cheng.zenofdesignpatterns.patterns.observer.common;

import java.util.Vector;

/**
 * 被观察者
 */
public abstract class Subject {

	// 定义个一个观察者数组
	private Vector<CommonObserver> obsVector = new Vector<CommonObserver>();
	
	// 增加一个观察者
	public void addObserver(CommonObserver o){
		this.obsVector.add(o);
	}
	
	// 删除一个观察者
	public void delObserver(CommonObserver o){
		this.obsVector.remove(o);
	}
	
	// 通知所有观察者
	public void notifyObserver(){
		for(CommonObserver o:this.obsVector){
			o.update();
		}
	}

    /**
     * 定义被观察者必须实现的职责，它必须能够动态地增加、取消观察者。它一般是抽象类或者
     * 是实现类，仅仅完成作为被观察者必须实现的职责：管理观察者并通知观察者
     */

}
