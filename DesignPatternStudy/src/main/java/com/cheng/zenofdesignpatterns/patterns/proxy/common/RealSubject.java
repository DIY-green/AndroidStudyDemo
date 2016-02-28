package com.cheng.zenofdesignpatterns.patterns.proxy.common;

/**
 * 具体主题角色
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        // 业务逻辑处理
        System.out.println("RealSubject==>request()");
    }
}
