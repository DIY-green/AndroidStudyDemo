package com.cheng.zenofdesignpatterns.patterns.decorator.common;

/**
 * 具体装饰角色
 */
public class ConcreteDecorator1 extends Decorator {
	
	// 定义被修饰者
	public ConcreteDecorator1(Component _component){
		super(_component);
	}
	
	// 定义自己的修饰方法
	private void method1(){
		System.out.println("method1 修饰");
	}
	
	// 重写父类的Operation方法
	public void operate(){
		this.method1();
		super.operate();
	}
	/**
	 * 注意
	 * 原始方法和装饰方法的执行顺序在具体的装饰类是固定的，可以通过方法重载实现多种执行顺序
	 */
}
