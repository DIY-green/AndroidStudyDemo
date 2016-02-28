package com.cheng.zenofdesignpatterns.patterns.state.liftstate;

/**
 * 电梯在运行状态下能做哪些动作
 */
public class RunningState extends LiftState {
	
	// 电梯门关闭？这是肯定了
	@Override
	public void close() {
		// do nothing
	}

	// 运行的时候开电梯门？你疯了！电梯不会给你开的
	@Override
	public void open() {
		//do nothing
	}

	// 这是在运行状态下要实现的方法
	@Override
	public void run() {
		System.out.println("电梯上下跑...");
	}

	// 这个事绝对是合理的，光运行不停止还有谁敢做这个电梯？！估计只有上帝了
	@Override
	public void stop() {
		super.context.setLiftState(LiftContext.stoppingState); //环境设置为停止状态；
		super.context.getLiftState().stop();
	}

}
