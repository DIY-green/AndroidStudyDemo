package com.cheng.zenofdesignpatterns.patterns.interpreter.common;

/**
 * 终结符表达式
 */
public class TerminalExpression extends Expression {

	// 通常终结符表达式只有一个，但是有多个对象
	public Object interpreter(Context ctx) {	
		return null;
	}

}
