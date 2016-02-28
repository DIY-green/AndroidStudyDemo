package com.cheng.zenofdesignpatterns.patterns.composite.transmode;

import java.util.ArrayList;

/**
 * 抽象构件角色
 */
public abstract class TransModeComponent {
	
	// 个体和整体都具有的共享
	public void doSomething(){
		// 编写业务逻辑
	}	
	
	// 增加一个叶子构件或树枝构件
	public abstract void add(TransModeComponent component);
	
	// 删除一个叶子构件或树枝构件
	public abstract void remove(TransModeComponent component);
	
	// 获得分支下的所有叶子构件和树枝构件
	public abstract ArrayList<TransModeComponent> getChildren();

    /**
     * 定义参加组合对象的共有方法和属性，可以定义一些默认的行为或属性
     */

    /**
     * 透明组合模式
     */
}
