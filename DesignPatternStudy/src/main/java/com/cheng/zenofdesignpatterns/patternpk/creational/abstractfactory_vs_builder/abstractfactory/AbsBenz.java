package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory;

/**
 * 抽象奔驰车
 */
public abstract class AbsBenz implements ICarAF {

	private final static String BENZ_BAND = "奔驰汽车";
	
	public String getBand() {
		return BENZ_BAND;
	}

	// 具体型号由实现类完成
	public abstract String getModel();

}

