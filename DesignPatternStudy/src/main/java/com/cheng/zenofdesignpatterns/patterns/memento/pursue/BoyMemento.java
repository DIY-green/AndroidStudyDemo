package com.cheng.zenofdesignpatterns.patterns.memento.pursue;

/**
 * 男孩状态备忘录类
 */
public class BoyMemento {
	
	// 男孩的状态
	private String state = "";
	
	// 通过构造函数传递状态信息
	public BoyMemento(String _state){
		this.state = _state;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
