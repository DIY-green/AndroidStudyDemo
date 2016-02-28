package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.builder;

/**
 * 具体建造者
 * 宝马车建造车间
 */
public class BMWBuilder extends CarBuilder {

	@Override
	public String buildEngine() {
		return super.getBlueprint().getEngine();
	}

	@Override
	public String buildWheel() {
		return super.getBlueprint().getWheel();
	}

}
