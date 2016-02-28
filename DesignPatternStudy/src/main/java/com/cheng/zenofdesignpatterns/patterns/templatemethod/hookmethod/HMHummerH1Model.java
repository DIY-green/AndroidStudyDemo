package com.cheng.zenofdesignpatterns.patterns.templatemethod.hookmethod;

/**
 * 悍马车是每个越野者的最爱，其中H1最接近军用系列
 */
public class HMHummerH1Model extends HMHummerModel {
	private boolean alarmFlag = true;  //是否要响喇叭
	
	@Override
	protected void alarm() {
		System.out.println("悍马H1鸣笛...");
	}

	@Override
	protected void engineBoom() {
		System.out.println("悍马H1引擎声音是这样在...");
	}

	@Override
	protected void start() {
		System.out.println("悍马H1发动...");
	}

	@Override
	protected void stop() {
		System.out.println("悍马H1停车...");
	}
	
	protected boolean isAlarm() {
		return this.alarmFlag;
	}

	//要不要响喇叭，是有客户的来决定的
	public void setAlarm(boolean isAlarm){
		this.alarmFlag = isAlarm;
	}

}
