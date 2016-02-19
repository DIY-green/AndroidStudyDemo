package com.cheng.multithreadstudy.sunframework.proxy.callback;

import java.lang.reflect.Method;

public interface Interceptor {

    Object intercept(final Object proxy, Method method, final Object[] args) throws Throwable;
}
