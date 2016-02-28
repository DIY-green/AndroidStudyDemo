package com.cheng.zenofdesignpatterns.patterns.composite.common;

/**
 * 安全组合模式
 * 叶子构件（没有分支中的add等方法所以‘安全’）
 */
public class Leaf extends Component {

	// 可以覆写父类方法
	public void doSomething(){
		// 编写业务逻辑
	}
	/**
	 * 叶子对象，其下再也没有其他的分支，也就是遍历的最小单位
     */
}
