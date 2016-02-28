package com.cheng.zenofdesignpatterns.patterns.mediator.common;

/**
 * 具体中介者
 */
public class ConcreteMediator extends Mediator {

	@Override
	public void doSomething1() {
		System.out.println("ConcreteMediator==>doSomething1()");
		// 调用同事类的方法，只要是public方法都可以调用
		super.c1.selfMethod1();
		super.c2.selfMethod2();
	}

	public void doSomething2() {
		System.out.println("ConcreteMediator==>doSomething2()");
		super.c1.selfMethod1();
		super.c2.selfMethod2();
	}

}
