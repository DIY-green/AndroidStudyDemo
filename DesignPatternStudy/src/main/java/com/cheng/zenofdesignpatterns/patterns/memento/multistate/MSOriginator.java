package com.cheng.zenofdesignpatterns.patterns.memento.multistate;


/**
 *
 */
public class MSOriginator {
	
	// 内部状态
	private String state1 = "";
	private String state2 = "";
	private String state3 = ""; 
	
	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public String getState3() {
		return state3;
	}

	public void setState3(String state3) {
		this.state3 = state3;
	}

	// 创建一个备忘录
	public MSMemento createMemento(){
		return new MSMemento(BeanUtils.backupProp(this));
	}
	
	// 恢复一个备忘录
	public void restoreMemento(MSMemento _memento){
		BeanUtils.restoreProp(this, _memento.getStateMap());
	}
	
	// 增加一个toString方法
	@Override
	public String toString(){
		return "state1=" +state1+"\nstat2="+state2+"\nstate3="+state3; 
	}

}
