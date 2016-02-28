package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory;

/**
 * 奔驰商务车
 */
public class BenzVan extends AbsBenz {

	private final static String R_SERIES = "R系列商务车";
	
	@Override
	public String getModel() {
		return R_SERIES;
	}

}
