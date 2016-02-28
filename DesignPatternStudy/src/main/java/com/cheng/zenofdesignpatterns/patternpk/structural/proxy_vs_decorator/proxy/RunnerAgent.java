package com.cheng.zenofdesignpatterns.patternpk.structural.proxy_vs_decorator.proxy;

import java.util.Random;

/**
 * 代理人
 */
public class RunnerAgent implements IPRunner {

	private IPRunner runner;
	
	public RunnerAgent(IPRunner _runner){
		this.runner = _runner;
	}
	
	// 代理人是不会跑的
	public void run() {
		Random rand = new Random();
		if(rand.nextBoolean()){
			System.out.println("代理人同意安排运动员跑步");
			runner.run();
		}else{
			System.out.println("代理人心情不好，不安排运动品跑步");
		}
	}

    /**
     * 证实了代理的一个功能：在不改变接口的前提下，对过程进行控制。
     */

}
