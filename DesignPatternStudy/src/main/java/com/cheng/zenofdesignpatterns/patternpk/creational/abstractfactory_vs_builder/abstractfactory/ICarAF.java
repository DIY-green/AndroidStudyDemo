package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory;

/**
 * 汽车接口
 */
public interface ICarAF {
	
	// 汽车的生产商，也就是牌子
	String getBand();
	
	// 汽车的型号
	String getModel();

}

