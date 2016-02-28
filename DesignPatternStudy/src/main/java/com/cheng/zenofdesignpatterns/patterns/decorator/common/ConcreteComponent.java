package com.cheng.zenofdesignpatterns.patterns.decorator.common;

/**
 * 具体构件
 */
public class ConcreteComponent extends Component {

	// 具体实现
	@Override
	public void operate() {
		System.out.println("do Something");
	}

	/**
	 * ConcreteComponent是最核心、最原始、最基本的接口或抽象类的实现，要装饰的就是它
	 */
}
