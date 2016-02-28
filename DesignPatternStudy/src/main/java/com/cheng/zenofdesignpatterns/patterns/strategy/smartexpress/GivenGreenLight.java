package com.cheng.zenofdesignpatterns.patterns.strategy.smartexpress;

/**
 * 求吴国太开个绿灯
 */
public class GivenGreenLight implements ISmartExpressStrategy {

	public void operate() {
		System.out.println("求吴国太开个绿灯,放行！");
	}

}
