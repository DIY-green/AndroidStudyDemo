package com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.common;

/**
 * 具体处理类
 */
public class ConcreteHandler1 extends AbstractHandler {

	// 定义自己的处理逻辑
	protected Response echo(Request request) {
		// 完成处理逻辑
		return null;
	}

	// 设置自己的处理级别
	protected Level getHandleLevel() {
		// 设置自己的处理级别
		return null;
	}

}
