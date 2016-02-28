package com.cheng.zenofdesignpatterns.patterns.command.requirement;

/**
 * 代码组的职责是实现业务逻辑，当然包括数据库设计了
 */
public class CodeGroup extends Group {
	
	// 客户要求代码组过去和他们谈
	public void find() {
		System.out.println("找到代码组...");
	}

	// 客户要求增加一项功能
	public void add() {
		System.out.println("客户要求增加一项功能...");
	}

	// 客户要求修改一项功能
	public void change() {
		System.out.println("客户要求修改一项功能...");
	}

	// 客户要求删除一项功能
	public void delete() {
		System.out.println("客户要求删除一项功能...");
	}

	// 客户要求出变更计划
	public void plan() {
		System.out.println("客户要求代码变更计划...");
	}

}
