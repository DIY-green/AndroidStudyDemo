package com.cheng.zenofdesignpatterns.patterns.visitor.common;

/**
 * 抽象访问者
 * 声明访问者可以访问哪些元素，具体到程序中就是visit方法的参数定义哪些对象是可以被访问的
 */
public interface IVisitor {
	
	//可以访问哪些对象
	void visit(ConcreteElement1 el1);
	
	void visit(ConcreteElement2 el2);
}
