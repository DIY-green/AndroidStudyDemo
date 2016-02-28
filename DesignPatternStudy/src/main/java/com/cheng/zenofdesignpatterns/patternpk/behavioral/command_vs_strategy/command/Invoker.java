package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * 调用者
 */
public class Invoker {

	// 抽象命令的引用
	private AbstractCmd cmd;

	public Invoker(AbstractCmd _cmd){
		this.cmd = _cmd;
	}
	
	// 执行命令
	public boolean execute(String source,String to){
		return cmd.execute(source, to);
	}
	
}
