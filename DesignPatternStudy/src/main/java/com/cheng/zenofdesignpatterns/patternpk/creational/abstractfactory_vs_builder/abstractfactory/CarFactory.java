package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory;

/**
 * 抽象工厂
 */
public interface CarFactory {
	
	// 生产SUV
	ICarAF createSuv();
	// 生产商务车
	ICarAF createVan();

}

