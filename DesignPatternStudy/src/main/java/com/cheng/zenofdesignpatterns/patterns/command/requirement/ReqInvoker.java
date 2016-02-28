package com.cheng.zenofdesignpatterns.patterns.command.requirement;


/**
 * 接头人的职责就是接收命令，并执行
 */
public class ReqInvoker {
	// 什么命令
	private ReqCommand command;
	
	// 客户发出命令
	public void setCommand(ReqCommand command){
		this.command = command;
	}
	
	// 执行客户的命令
	public void action(){
		this.command.execute();
	}
}
