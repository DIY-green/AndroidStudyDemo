package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory;

/**
 * 宝马车工厂
 */
public class BMWFactory implements CarFactory {

	// 生产SUV
	public ICarAF createSuv() {
		return new BMWSuv();
	}
	
	// 生产商务车
	public ICarAF createVan(){
		return new BMWVan();
	}

}
