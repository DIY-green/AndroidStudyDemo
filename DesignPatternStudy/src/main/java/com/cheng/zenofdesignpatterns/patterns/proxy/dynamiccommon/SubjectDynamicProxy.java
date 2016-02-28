package com.cheng.zenofdesignpatterns.patterns.proxy.dynamiccommon;

import java.lang.reflect.InvocationHandler;

/**
 * 具体业务的动态代理
 */
public class SubjectDynamicProxy extends DynamicProxyDC {

	public static <T> T newProxyInstance(SubjectDC subject){
		//获得ClassLoader
		ClassLoader loader = subject.getClass().getClassLoader();
		//获得接口数组
		Class<?>[] classes = subject.getClass().getInterfaces();
		//获得handler
		InvocationHandler handler = new MyInvocationHandler(subject);
		return newProxyInstance(loader, classes, handler);
	}
}
