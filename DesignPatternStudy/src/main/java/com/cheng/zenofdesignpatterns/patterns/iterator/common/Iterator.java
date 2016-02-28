package com.cheng.zenofdesignpatterns.patterns.iterator.common;

/**
 * 迭代器
 */
public interface Iterator {
	
	// 遍历到下一个元素
	Object next();
	
	// 是否已经遍历到尾部
	boolean hasNext();
	
	// 删除当前指向的元素
	boolean remove();
}
