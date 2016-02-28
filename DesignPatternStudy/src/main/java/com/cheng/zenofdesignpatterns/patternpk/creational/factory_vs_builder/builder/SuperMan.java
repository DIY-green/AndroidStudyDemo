package com.cheng.zenofdesignpatterns.patternpk.creational.factory_vs_builder.builder;

/**
 * 超人产品
 */
public class SuperMan {
	
	// 超人的躯体
	private String body;
	// 超人的特殊技能
	private String specialTalent;
	// 超人的标志
	private String specialSymbol;
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSpecialTalent() {
		return specialTalent;
	}
	public void setSpecialTalent(String specialTalent) {
		this.specialTalent = specialTalent;
	}
	public String getSpecialSymbol() {
		return specialSymbol;
	}
	public void setSpecialSymbol(String specialSymbol) {
		this.specialSymbol = specialSymbol;
	}
		
}
