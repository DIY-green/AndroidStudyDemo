package com.cheng.zenofdesignpatterns.patterns.prototype.common;

/**
 * 原型模式的核心是一个clone方法，通过该方法进行对象的拷贝
 */
public class PrototypeClass implements Cloneable{
	
	//覆写父类Object方法
	@Override
	public PrototypeClass clone(){
		PrototypeClass prototypeClass = null;
		try {
			prototypeClass = (PrototypeClass)super.clone();
		} catch (CloneNotSupportedException e) {
			//异常处理
		}
		return prototypeClass;
	}
}
