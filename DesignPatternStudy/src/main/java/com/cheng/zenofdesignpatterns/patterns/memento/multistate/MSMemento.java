package com.cheng.zenofdesignpatterns.patterns.memento.multistate;

import java.util.HashMap;

/**
 *
 */
public class MSMemento {
	
	// 接受HashMap作为状态
	private HashMap<String,Object> stateMap;
	
	// 接受一个对象，建立一个备份
	public MSMemento(HashMap<String, Object> map){
		this.stateMap = map;
	}

	public HashMap<String,Object> getStateMap() {
		return stateMap;
	}

	public void setStateMap(HashMap<String,Object> stateMap) {
		this.stateMap = stateMap;
	}

}
