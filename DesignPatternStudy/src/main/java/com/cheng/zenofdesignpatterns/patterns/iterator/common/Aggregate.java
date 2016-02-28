package com.cheng.zenofdesignpatterns.patterns.iterator.common;

/**
 * 容器类
 */
public interface Aggregate {
	
	// 是容器必然有元素的增加
	void add(Object object);
	
	// 减少元素
	void remove(Object object);
	
	// 由迭代器来遍历所有的元素
	Iterator iterator();

}
