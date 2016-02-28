package com.cheng.zenofdesignpatterns.patterns.abstractfactory.common;

/**
 * 工厂2，只生产跳线为2的产品
 */
public class Creator2 extends AbstractCreator {
	
	//只生产产品等级为2的A产品
	public AbstractProductA createProductA() {	
		return new ProductA2();
	}

	//只生产铲平等级为2的B产品
	public AbstractProductB createProductB() {
		return new ProductB2();
	}

	/**
	 * 注意
	 * 有M个产品等级就应该有M个实现工厂类，在每个实现工厂中，实现不同产品族的生产任务
	 */
}
