package com.cheng.zenofdesignpatterns.patterns.flyweight.common;

/**
 *
 */
public class ConcreteFlyweight1 extends Flyweight{
	
	// 接受外部状态
	public ConcreteFlyweight1(String _Extrinsic){
		super(_Extrinsic);
	}
	
	// 根据外部状态进行逻辑处理
	public void operate(){
		// 业务逻辑
	}


}
