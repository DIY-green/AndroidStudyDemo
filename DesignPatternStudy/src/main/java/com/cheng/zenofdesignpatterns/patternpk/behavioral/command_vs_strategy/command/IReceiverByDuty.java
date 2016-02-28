package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * 依照职责设计的接收者接口
 * 抽象接收者
 */
public interface IReceiverByDuty {
	
	// 执行zip命令
	boolean zipExec(String source,String to);
	
	// 执行gzip命令
	boolean gzipExec(String source,String to);

}
