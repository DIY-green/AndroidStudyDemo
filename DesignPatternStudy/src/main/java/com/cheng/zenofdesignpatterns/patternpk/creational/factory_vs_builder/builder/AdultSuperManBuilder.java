package com.cheng.zenofdesignpatterns.patternpk.creational.factory_vs_builder.builder;

/**
 * 成年超人建造者
 */
public class AdultSuperManBuilder extends Builder {

	@Override
	public SuperMan getSuperMan() {
		super.setBody("强壮的躯体");
		super.setSpecialTalent("会飞行");
		super.setSpecialSymbol("胸前带S标记");
		return super.superMan;
	}

}
