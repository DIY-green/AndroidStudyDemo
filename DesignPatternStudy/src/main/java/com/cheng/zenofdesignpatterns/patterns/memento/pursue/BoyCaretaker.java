package com.cheng.zenofdesignpatterns.patterns.memento.pursue;

/**
 * 备忘录管理类
 */
public class BoyCaretaker {
	
	// 备忘录对象
	private BoyMemento memento;

	public BoyMemento getMemento() {
		return memento;
	}

	public void setMemento(BoyMemento memento) {
		this.memento = memento;
	}
	
}
