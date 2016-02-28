package com.cheng.zenofdesignpatterns.patterns.memento.multibackup;

/**
 *
 */
public class MBCaretaker {
	
	//备忘录对象
	private IMemento memento;

	public IMemento getMemento() {
		return memento;
	}

	public void setMemento(IMemento memento) {
		this.memento = memento;
	}
	
}
