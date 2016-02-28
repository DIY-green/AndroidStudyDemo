package com.cheng.zenofdesignpatterns.patterns.abstractfactory.common;

/**
 * 工厂1，只生产跳线为1的产品
 */
public class Creator1 extends AbstractCreator {
	
	//只生产产品等级为1的A产品
	public AbstractProductA createProductA() {	
		return new ProductA1();
	}

	//只生产铲平等级为1的B产品
	public AbstractProductB createProductB() {
		return new ProductB1();
	}

	/**
	 * 注意
	 * 有M个产品等级就应该有M个实现工厂类，在每个实现工厂中，实现不同产品族的生产任务
	 */
}
