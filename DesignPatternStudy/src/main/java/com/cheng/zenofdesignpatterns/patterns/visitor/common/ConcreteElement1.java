package com.cheng.zenofdesignpatterns.patterns.visitor.common;

/**
 * 具体元素
 */
public class ConcreteElement1 extends Element{
	
	// 完善业务逻辑
	public void doSomething(){
		// 业务处理
	}
	
	// 允许那个访问者访问
	public void accept(IVisitor visitor){
		visitor.visit(this);
	}

}
