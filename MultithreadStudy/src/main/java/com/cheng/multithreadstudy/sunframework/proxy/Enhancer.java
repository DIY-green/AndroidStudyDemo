package com.cheng.multithreadstudy.sunframework.proxy;

import com.android.dx.stock.ProxyBuilder;
import com.cheng.multithreadstudy.sunframework.proxy.filter.InterceptorFilter;
import com.cheng.multithreadstudy.sunframework.proxy.callback.Interceptor;
import com.cheng.utils.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Enhancer<T> implements InvocationHandler {

    private Interceptor[] callBacks;
    private InterceptorFilter filter;
    private Class<T> superClazz;
    private Class<?>[] constructorArgTypes;
    private Object[] constructorArgValues;
    private File mCacheDir;

    /**
     * @param superClazz 构造函数需要的class
     */
    Enhancer(File cacheFileDir, Class<T> superClazz) {
        this.superClazz = superClazz;
        this.mCacheDir = cacheFileDir;
    }

    /**
     * @param superClazz
     * @param clazzes
     * @param args
     */
    Enhancer(File cacheFileDir, Class<T> superClazz, Class<?>[] clazzes, Object[] args) {
        this(cacheFileDir, superClazz);
        this.constructorArgTypes = clazzes;
        this.constructorArgValues = args;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (filter == null) {
            return null;
        }
        int accept = filter.accept(method);
        if (accept >= callBacks.length) {
            return null;
        }
        if (method.getName().equals("hashCode") || method.getName().equals("toString")) {
            return ProxyBuilder.callSuper(proxy, method, args);
        }
        return callBacks[accept].intercept(proxy, method, args);
    }

    public void setCacheDir(File file) {
        this.mCacheDir = file;
    }

    /*
     *
     */
    public void addCallBacks(Interceptor[] callBacks) {
        this.callBacks = callBacks;
    }

    /**
     * @param filter
     */
    public void addFilter(InterceptorFilter filter) {
        this.filter = filter;
    }

    /**
     * 生成代理
     *
     * @return
     */
    public T create() {
        ProxyBuilder<T> proxy = ProxyBuilder.forClass(superClazz);
        if (constructorArgTypes != null && constructorArgValues != null && constructorArgValues.length == constructorArgTypes.length) {
            proxy.constructorArgTypes(constructorArgTypes).constructorArgValues(constructorArgValues);
        }
        proxy.handler(this);
        proxy.dexCache(mCacheDir);
        try {
            return proxy.build();
        } catch (IOException e) {
            Logger.i("app", "error" + e.getClass() + "");
            e.printStackTrace();
        }
        return null;
    }
}
