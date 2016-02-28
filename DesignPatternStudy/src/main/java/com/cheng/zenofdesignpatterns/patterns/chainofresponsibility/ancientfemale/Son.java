package com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.ancientfemale;

/**
 * 儿子类
 */
public class Son extends WomanHandler {

	// 儿子只处理母亲的请求
	public Son(){
		super(SON_LEVEL_REQUEST);
	}
	
	// 儿子的答复
	protected void response(IWomen women) {
		System.out.println("--------母亲向儿子请示-------");
		System.out.println(women.getRequest());
		System.out.println("儿子的答复是：同意\n");
	}

}
