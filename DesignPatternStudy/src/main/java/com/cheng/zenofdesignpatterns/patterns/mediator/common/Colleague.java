package com.cheng.zenofdesignpatterns.patterns.mediator.common;

/**
 * 抽象同事类
 */
public abstract class Colleague {
	protected Mediator mediator;
	public Colleague(Mediator _mediator){
		this.mediator = _mediator;
	}

	/**
	 * 为什么要使用构造函数注入中介者？
	 * 因为同事类必须有中介者
	 */
}
