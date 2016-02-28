package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory;

/**
 * 抽象宝马车
 */
public abstract class AbsBMW implements ICarAF {

	private final static String BMW_BAND = "宝马汽车";
	
	// 宝马车
	public String getBand() {
		return BMW_BAND;
	}
	
	// 型号由具体的实现类实现
	public abstract String getModel();

}
