package com.cheng.zenofdesignpatterns.patterns.command.requirement;

/**
 * 删除一个页面的命令
 */
public class DeletePageCommand extends ReqCommand {
	
	// 执行删除一个页面的命令
	public void execute() {
		// 找到页面组
		super.pg.find();
		
		// 删除一个页面
		super.rg.delete();
		
		// 给出计划
		super.rg.plan();
	}

}
