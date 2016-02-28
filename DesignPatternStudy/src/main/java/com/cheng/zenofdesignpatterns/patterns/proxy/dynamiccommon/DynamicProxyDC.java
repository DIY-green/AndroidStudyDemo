package com.cheng.zenofdesignpatterns.patterns.proxy.dynamiccommon;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理类
 */
public class DynamicProxyDC<T> {

    public static <T> T newProxyInstance(ClassLoader _loader,
             Class<?>[] _interfaces, InvocationHandler _handler) {
        // 寻找JoinPoint连接点，AOP框架使用元数据定义
        if (true) {
            // 执行一个前置通知
            new BeforeAdviceDC().exec();
        }
        T newProxyInstance = (T) Proxy.newProxyInstance(_loader, _interfaces, _handler);
        return newProxyInstance;
    }
}
