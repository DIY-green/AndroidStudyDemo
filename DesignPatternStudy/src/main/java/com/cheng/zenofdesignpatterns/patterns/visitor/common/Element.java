package com.cheng.zenofdesignpatterns.patterns.visitor.common;

/**
 * 抽象元素
 * 声明接受哪一类访问者访问，程序上通过accept方法中的参数来定义的
 */
public abstract class Element {
	//定义业务逻辑
	public abstract void doSomething();
	
	//允许谁来访问
	public abstract void accept(IVisitor visitor);
}
