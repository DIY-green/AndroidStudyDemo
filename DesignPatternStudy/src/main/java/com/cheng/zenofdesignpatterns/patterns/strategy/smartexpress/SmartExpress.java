package com.cheng.zenofdesignpatterns.patterns.strategy.smartexpress;

/**
 * 计谋有了，那还要有锦囊
 */
public class SmartExpress {

	// 构造函数，你要使用那个妙计
	private ISmartExpressStrategy straegy;
	public SmartExpress(ISmartExpressStrategy strategy){
		this.straegy = strategy;
	}
	
	// 使用计谋了，看我出招了
	public void operate(){
		this.straegy.operate();
	}

}
