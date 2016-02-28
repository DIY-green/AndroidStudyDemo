package com.cheng.zenofdesignpatterns.patternpk.structural.proxy_vs_decorator.decorator;

/**
 * 增强运动员
 */
public class RunnerWithJet implements IDRunner {

	private IDRunner runner;
	
	public RunnerWithJet(IDRunner _runner){
		this.runner = _runner;
	}

    public void run() {
		System.out.println("加快运动员的速度：为运动员增加喷气装置");
		runner.run();
	}

}
