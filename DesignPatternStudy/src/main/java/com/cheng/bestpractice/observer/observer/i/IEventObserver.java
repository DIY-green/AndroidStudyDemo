package com.cheng.bestpractice.observer.observer.i;

/**
 * 观察者接口
 */
public interface IEventObserver {
    /**
     * 根据事件进行数据或者UI的更新
     * @param eventType
     */
    public void dispatchChange(String eventType);
}
