package com.cheng.zenofdesignpatterns.patterns.visitor.report;

/**
 * 访问者，我要去访问人家的数据了
 */
public interface IVisitor {
	
	// 首先定义我可以访问普通员工
	void visit(CommonEmployee commonEmployee);
	
	// 其次定义，我还可以访问部门经理
	void visit(Manager manager);
	
}
