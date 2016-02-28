package com.cheng.zenofdesignpatterns.patterns.mediator.invoicing;

/**
 * 抽象中介者
 */
public abstract class AbstractMediator {
	protected Purchase purchase;
	protected Sale sale;
	protected Stock stock;
	
	// 构造函数
	public AbstractMediator(){
		purchase = new Purchase(this);
		sale = new Sale(this);
		stock = new Stock(this);
	}
	
	// 中介者最重要的方法，叫做事件方法，处理多个对象之间的关系
	public abstract void execute(String str,Object...objects);
}