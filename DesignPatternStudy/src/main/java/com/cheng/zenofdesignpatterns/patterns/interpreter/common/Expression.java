package com.cheng.zenofdesignpatterns.patterns.interpreter.common;

/**
 *
 */
public abstract class Expression {
	
	//每个表达式必须有一个解析任务
	public abstract Object interpreter(Context  ctx);

}
