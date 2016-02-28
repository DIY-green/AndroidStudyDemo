package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory;

/**
 * 宝马SUV
 */
public class BMWSuv extends AbsBMW {

	private final static String X_SEARIES = "X系列车型SUV";
	
	@Override
	public String getModel() {
		return X_SEARIES;
	}

}