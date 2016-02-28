package com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.action;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class Interceptors implements Iterable<AbstractInterceptor> {

	//根据拦截器建立一个拦截器链条
	public Interceptors(ArrayList<AbstractInterceptor> list){
		
	}

	
	//列出所有的拦截器
	public Iterator<AbstractInterceptor> iterator() {
		return null;
	}
	
	//拦截器的执行方法
	public void intercept(){
		//委托拦截器执行
	}
}
