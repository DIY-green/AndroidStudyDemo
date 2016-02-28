package com.cheng.zenofdesignpatterns.patternpk.creational.factory_vs_builder.builder;

/**
 * 抽象建造者
 */
public abstract class Builder {
	// 定义一个超人的应用
	protected final SuperMan superMan = new SuperMan();
	
	// 构建出超人的躯体
	public void setBody(String body){
		this.superMan.setBody(body);
	}
	
	// 构建出超人的特殊技能
	public void setSpecialTalent(String st){
		this.superMan.setSpecialTalent(st);
	}
	
	// 构建出超人的特殊标记
	public void setSpecialSymbol(String ss){
		this.superMan.setSpecialSymbol(ss);
	}
	
	// 构建出完整的一个超人
	public abstract SuperMan getSuperMan();

    /**
     * 注意
     * 仔细看两个具体的建造者，它们都关注了产品的各个部分，在某些应用场景下甚至会
     * 关心产品的构建顺序，即使是相同的部件，装配顺序不同，产生的结果也不同，这也
     * 正是建造者模式的意图：通过不同的部件、不同装配产生不同的复杂对象。
     */
}
