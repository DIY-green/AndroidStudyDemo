package com.cheng.zenofdesignpatterns.patterns.visitor.multivisitor;

/**
 * 负责展示报表的产生
 */
public interface IShowVisitor extends IVisitor {

	//展示报表
	void report();

}
