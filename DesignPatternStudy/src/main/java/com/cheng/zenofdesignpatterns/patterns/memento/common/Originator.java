package com.cheng.zenofdesignpatterns.patterns.memento.common;

/**
 * 发起人角色
 * 记录当前时刻的状态，负责定义哪些属于备份范围的状态，负责创建和恢复备忘录数据
 */
public class Originator {
	
	// 内部状态
	private String state = "";
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	// 创建一个备忘录
	public Memento createMemento(){
		return new Memento(this.state);
	}
	
	// 恢复一个备忘录
	public void restoreMemento(Memento _memento){
		this.setState(_memento.getState());
	}
}
