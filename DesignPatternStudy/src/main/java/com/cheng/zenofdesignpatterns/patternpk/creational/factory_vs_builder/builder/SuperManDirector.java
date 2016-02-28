package com.cheng.zenofdesignpatterns.patternpk.creational.factory_vs_builder.builder;

/**
 * 导演类
 */
public class SuperManDirector {

	// 两个建造者的应用
	private static Builder adultBuilder = new AdultSuperManBuilder();
	// 未成年超人的建造者
	private static Builder childBuilder = new ChildSuperManBuilder();
	
	// 建造一个成年、会飞行的超人
	public static SuperMan getAdultSuperMan(){
		return adultBuilder.getSuperMan();
	}

	// 建造一个未成年、刀枪不入的超人
	public static SuperMan getChildSuperMan(){
		return childBuilder.getSuperMan();
	}
	
}
