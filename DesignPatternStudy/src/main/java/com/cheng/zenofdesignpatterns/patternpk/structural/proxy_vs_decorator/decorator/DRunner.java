package com.cheng.zenofdesignpatterns.patternpk.structural.proxy_vs_decorator.decorator;

/**
 * 具体运动员
 */
public class DRunner implements IDRunner {

	public void run() {
		System.out.println("运动员跑步：动作很潇洒");
	}

}
