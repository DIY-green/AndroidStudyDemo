package com.cheng.zenofdesignpatterns.patterns.observer.monitor;

/**
 * 李斯这个人，是个观察者，只要韩非子一有动静，这边就知道
 */
public class LiSi1 implements MonitorObserver {
	
	// 首先李斯是个观察者，一旦韩非子有活动，他就知道，他就要向老板汇报
	public void update(String str){
		System.out.println("李斯：观察到李斯活动，开始向老板汇报了...");
		this.reportToQiShiHuang(str);
		System.out.println("李斯：汇报完毕，秦老板赏给他两个萝卜吃吃...\n");
	}
	
	// 汇报给秦始皇
	private void reportToQiShiHuang(String reportContext){
		System.out.println("李斯：报告，秦老板！韩非子有活动了--->"+reportContext);
	}

}
