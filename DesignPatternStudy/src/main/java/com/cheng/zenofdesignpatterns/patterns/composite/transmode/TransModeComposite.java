package com.cheng.zenofdesignpatterns.patterns.composite.transmode;

import java.util.ArrayList;

/**
 * 树枝构件
 */
public class TransModeComposite extends TransModeComponent {
	// 构件容器
	private ArrayList<TransModeComponent> componentArrayList = new ArrayList<TransModeComponent>();
	
	// 增加一个叶子构件或树枝构件
	public void add(TransModeComponent component){
		this.componentArrayList.add(component);
	}
	
	// 删除一个叶子构件或树枝构件
	public void remove(TransModeComponent component){
		this.componentArrayList.remove(component);
	}
	
	// 获得分支下的所有叶子构件和树枝构件
	public ArrayList<TransModeComponent> getChildren(){
		return this.componentArrayList;
	}

    /**
     * 树枝对象，它的作用是组合树枝节点和叶子节点形成一个树形结构
     */
}
