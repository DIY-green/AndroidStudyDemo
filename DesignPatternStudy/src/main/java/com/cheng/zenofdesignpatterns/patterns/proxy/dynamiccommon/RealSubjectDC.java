package com.cheng.zenofdesignpatterns.patterns.proxy.dynamiccommon;

/**
 * 真实主题
 */
public class RealSubjectDC implements SubjectDC {

	//业务操作
	public void doSomething(String str) {
		System.out.println("do something!---->" + str);
	}

}
