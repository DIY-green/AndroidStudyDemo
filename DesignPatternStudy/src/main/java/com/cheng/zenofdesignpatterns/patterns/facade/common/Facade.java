package com.cheng.zenofdesignpatterns.patterns.facade.common;

/**
 *
 */
public class Facade {
	// 被委托的对象
	private ClassA a = new ClassA();
	private ClassB b = new ClassB();
	private Context context = new Context();
	
	// 提供给外部访问的方法
	public void methodA(){
		this.a.doSomethingA();
	}
	
	public void methodB(){
		this.b.doSomethingB();
	}
	
	public void methodC(){
		this.context.complexMethod();
	}
}
