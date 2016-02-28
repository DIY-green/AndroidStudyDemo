package com.cheng.zenofdesignpatterns.patternpk.creational.factory_vs_builder.builder;

/**
 * 未成年超人建造者
 */
public class ChildSuperManBuilder extends Builder {

	@Override
	public SuperMan getSuperMan() {
		super.setBody("强壮的躯体");
		super.setSpecialTalent("刀枪不入");
		super.setSpecialSymbol("胸前带小S标记");
		return super.superMan;
	}

}
