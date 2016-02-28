package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.builder;

/**
 * 汽车接口
 */
public interface ICarBuilder {

	// 汽车的车轮
	String getWheel();
	// 汽车引擎
	String getEngine();

}