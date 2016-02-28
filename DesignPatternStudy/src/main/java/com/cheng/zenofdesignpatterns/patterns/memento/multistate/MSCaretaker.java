package com.cheng.zenofdesignpatterns.patterns.memento.multistate;

/**
 *
 */
public class MSCaretaker {
	
	// 备忘录对象
	private MSMemento memento;

	public MSMemento getMemento() {
		return memento;
	}

	public void setMemento(MSMemento memento) {
		this.memento = memento;
	}
	
}
