package com.cheng.zenofdesignpatterns.patterns.strategy.smartexpress;

/**
 * 孙夫人断后，挡住追兵
 */
public class BlockEnemy implements ISmartExpressStrategy {

	public void operate() {
		System.out.println("孙夫人断后，挡住追兵");
	}

}
