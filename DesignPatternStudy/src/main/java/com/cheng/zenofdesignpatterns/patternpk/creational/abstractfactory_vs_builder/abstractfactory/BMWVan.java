package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory;

/**
 * 宝马商务车
 */
public class BMWVan extends AbsBMW {

	private final static String SEVENT_SEARIES = "7系列车型商务车";
	
	@Override
	public String getModel() {
		return SEVENT_SEARIES;
	}

}
