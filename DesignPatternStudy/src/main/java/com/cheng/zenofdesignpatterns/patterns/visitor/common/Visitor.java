package com.cheng.zenofdesignpatterns.patterns.visitor.common;

/**
 * 具体访问者
 */
public class Visitor implements IVisitor {

	// 访问el1元素
	public void visit(ConcreteElement1 el1) {
		el1.doSomething();
	}

	// 访问el2元素
	public void visit(ConcreteElement2 el2) {
		el2.doSomething();
	}

}
