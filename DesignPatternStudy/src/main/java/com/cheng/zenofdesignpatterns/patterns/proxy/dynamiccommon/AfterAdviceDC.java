package com.cheng.zenofdesignpatterns.patterns.proxy.dynamiccommon;

/**
 * 后续通知
 */
public class AfterAdviceDC implements IAdviceDC {
	
	public void exec() {
		System.out.println("我是后续通知，我被执行了！");
	}

}
