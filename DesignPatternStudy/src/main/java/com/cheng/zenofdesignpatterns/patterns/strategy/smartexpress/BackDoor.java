package com.cheng.zenofdesignpatterns.patterns.strategy.smartexpress;

/**
 * 找乔国老帮忙，使孙权不能杀刘备
 */
public class BackDoor implements ISmartExpressStrategy {

	public void operate() {
		System.out.println("找乔国老帮忙，让吴国太给孙权施加压力");
	}

}
