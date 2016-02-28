package com.cheng.zenofdesignpatterns.patterns.composite.common;

import java.util.ArrayList;

/**
 * 安全组合模式
 * 树枝构件
 */
public class Composite extends Component {
	// 构件容器
	private ArrayList<Component> componentArrayList = new ArrayList<Component>();

	// 增加一个叶子构件或树枝构件
	public void add(Component component){
		this.componentArrayList.add(component);
	}

	// 删除一个叶子构件或树枝构件
	public void remove(Component component){
		this.componentArrayList.remove(component);
	}

	// 获得分支下的所有叶子构件和树枝构件
	public ArrayList<Component> getChildren(){
		return this.componentArrayList;
	}

    /**
	 * 树枝对象，它的作用是组合树枝节点和叶子节点形成一个树形结构
     */
}
