package com.cheng.zenofdesignpatterns.patterns.proxy.dynamiccommon;

/**
 * 前置通知
 */
public class BeforeAdviceDC implements IAdviceDC {
	
	public void exec(){
		System.out.println("我是前置通知，我被执行了！");
	}

}
