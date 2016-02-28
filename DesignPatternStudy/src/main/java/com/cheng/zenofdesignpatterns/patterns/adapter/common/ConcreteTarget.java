package com.cheng.zenofdesignpatterns.patterns.adapter.common;

/**
 * 目标角色实现类
 */
public class ConcreteTarget implements Target {

	public void request() {
		System.out.println("I have nothing to do. if you need any help,pls call me!");
	}

}
