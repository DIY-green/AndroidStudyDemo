package com.cheng.zenofdesignpatterns.patternpk.behavioral.strategy_vs_state.strategy;

/**
 * 环境角色
 */
public class WorkStrategyContext {

	private WorkAlgorithm workMethod;

	public WorkAlgorithm getWork() {
		return workMethod;
	}

	public void setWork(WorkAlgorithm work) {
		this.workMethod = work;
	}
	
	// 每个算法都有必须具有的功能
	public void work(){
		this.workMethod.work();
	}

}
