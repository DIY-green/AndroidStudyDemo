package com.cheng.zenofdesignpatterns.patterns.visitor.multivisitor;

/**
 * 负责统计报表的产生
 */
public interface ITotalVisitor extends IVisitor {

	//统计所有员工工资总和
	void totalSalary();

}
