package com.cheng.zenofdesignpatterns.patternpk.structural.decorator_vs_adapter.decorator;

/**
 * 美化外观
 */
public class BeautifyAppearance extends Decorator {

	// 要美化谁
	public BeautifyAppearance(Swan _swan){
		super(_swan);
	}
	
	// 外表美化处理
	@Override
	public void desAppaearance(){
		System.out.println("外表是纯白色的，非常惹人喜爱！");
	}

}
