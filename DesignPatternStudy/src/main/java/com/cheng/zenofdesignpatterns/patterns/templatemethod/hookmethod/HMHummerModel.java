package com.cheng.zenofdesignpatterns.patterns.templatemethod.hookmethod;

/**
 * Hummer Model是悍马车辆模型的意思，不是悍马美女车模
 */
public abstract class HMHummerModel {

	/*
	 * 首先，这个模型要能够被发动起来，别管是手摇发动，还是电力发动，反正
	 * 是要能够发动起来，那这个实现要在实现类里了
	 */
	protected abstract void start();
	
	//能发动，那还要能停下来，那才是真本事
	protected abstract void stop();
	
	//喇叭会出声音，是滴滴叫，还是哔哔叫
	protected abstract void alarm();
	
	//引擎会轰隆隆的响，不响那是假的
	protected abstract void engineBoom();
	
	//那模型应该会跑吧，别管是人退的，还是电力驱动，总之要会跑
	final public void run() {
		
		//先发动汽车
		this.start();
		
		//引擎开始轰鸣
		this.engineBoom();
		
		//要让它叫的就是就叫，喇嘛不想让它响就不响
		if(this.isAlarm()){
			this.alarm();
		}
		
		
		//到达目的地就停车
		this.stop();
	}
	
	//钩子方法，默认喇叭是会响的
	protected  boolean isAlarm(){
		return true;
	}
}
