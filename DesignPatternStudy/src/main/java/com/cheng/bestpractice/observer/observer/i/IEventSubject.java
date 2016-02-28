package com.cheng.bestpractice.observer.observer.i;

/**
 * 抽象的主题角色
 */
public interface IEventSubject {
    /**
     * 注册观察者
     * @param observer
     */
    void registerObserver(IEventObserver observer);

    /**
     * 反注册观察者
     * @param observer
     */
    void removeObserver(IEventObserver observer);

    /**
     * 通知注册的观察者进行数据或者UI的更新
     */
    void notifyObserver(String eventType);
}

