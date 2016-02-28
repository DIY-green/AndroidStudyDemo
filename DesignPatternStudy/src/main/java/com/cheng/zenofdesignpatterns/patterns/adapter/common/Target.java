package com.cheng.zenofdesignpatterns.patterns.adapter.common;

/**
 * 目标角色
 */
public interface Target {
	
	//目标角色有自己的方法
	void request();
    /**
     * 该角色定义把其他类型转换为何种接口，也就是期望的接口
     */
}
