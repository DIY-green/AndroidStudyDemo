package com.cheng.zenofdesignpatterns.patterns.command.requirement;

/**
 * 项目组分成了三个组，每个组还是要接受增删改的命令
 */
public abstract class Group {

	// 甲乙双方分开办公，你要和那个组讨论，你首先要找到这个组
	public abstract void find();
	
	// 被要求增加功能
	public abstract void add();
	
	// 被要求删除功能
	public abstract void delete();
	
	// 被要求修改功能
	public abstract void change();
	
	// 被要求给出所有的变更计划
	public abstract void plan();
	
	// 每个接收者都要对直接执行的任务可以回滚
	public void rollBack(){
		// 根据日志进行回滚
	}
}
