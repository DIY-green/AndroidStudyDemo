package com.cheng.zenofdesignpatterns.patterns.composite.treestat;

/**
 * 叶子节点
 * 普通员工很简单，就写一个构造函数就可以了
 */
public class TreestatLeaf extends Corp {
	
	// 就写一个构造函数，这个是必须的
	public TreestatLeaf(String _name, String _position, int _salary){
		super(_name,_position,_salary);
	}
}
