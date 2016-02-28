package com.cheng.zenofdesignpatterns.patterns.state.liftstate;

/**
 * 在停止状态下能做什么事情
 */
public class StoppingState extends LiftState {
	
	// 停止状态关门？电梯门本来就是关着的！
	@Override
	public void close() {
		// do nothing;
	}

	// 停止状态，开门，那是要的！
	@Override
	public void open() {
		super.context.setLiftState(LiftContext.openningState);
		super.context.getLiftState().open();
	}

	// 停止状态再跑起来，正常的很
	@Override
	public void run() {
		super.context.setLiftState(LiftContext.runningState);
		super.context.getLiftState().run();
	}

	// 停止状态是怎么发生的呢？当然是停止方法执行了
	@Override
	public void stop() {
		System.out.println("电梯停止了...");
	}

}
