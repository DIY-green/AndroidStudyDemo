package com.cheng.bigtalkdesignpatterns.singleton;

/**
 * 第一种（懒汉，线程不安全）
 */
public class Singleton1 {
    private static Singleton1 instance;

    private Singleton1() {}

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }

    /**
     * 这种写法lazy loading很明显，但是致命的是在多线程不能正常工作。
     */
}
