package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory;

/**
 * 奔驰SUV
 */
public class BenzSuv extends AbsBenz {

	private final static String G_SERIES = "G系列SUV";
	
	@Override
	public String getModel() {
		return G_SERIES;
	}

}
