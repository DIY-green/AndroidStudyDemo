package com.cheng.zenofdesignpatterns.patterns.memento.pursue;

/**
 * 模拟电影《Next》中的男主角
 */
public class Boy {
	
	// 男孩的状态
	private String state = "";
	
	// 任何女孩子后状态肯定改变，比如心情、手中的花朵等等
	public void changeState(){
		this.state = "心情可能很不好";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	// 保留一个备份
	public BoyMemento createMemento(){
		return new BoyMemento(this.state);
	}
	
	// 恢复一个备份
	public void restoreMemento(BoyMemento _memento){
		this.setState(_memento.getState());
	}
}
