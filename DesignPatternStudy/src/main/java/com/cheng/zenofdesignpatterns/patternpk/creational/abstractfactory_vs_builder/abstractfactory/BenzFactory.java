package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory;

/**
 * 奔驰车工厂
 */
public class BenzFactory implements CarFactory {
	
	// 生产SUV
	public ICarAF createSuv() {
		return new BenzSuv();
	}
	
	// 生产商务车
	public ICarAF createVan(){
		return new BenzVan();
	}

}
