package com.cheng.zenofdesignpatterns.patterns.visitor.common;

import java.util.Random;

/**
 * 结构对象
 * 元素生成者，一般容纳在多个不同类、不同接口的容器，如List、Set、Map等，在项目中
 * 一般很少抽象出这个角色
 */
public class ObjectStruture {

	// 对象生成器，这里通过一个工厂方法模式模拟
	public static Element createElement(){
		Random rand = new Random();
		if (rand.nextInt(100) > 50) {
			return new ConcreteElement1();
		} else {
			return new ConcreteElement2();
		}
	}
}
