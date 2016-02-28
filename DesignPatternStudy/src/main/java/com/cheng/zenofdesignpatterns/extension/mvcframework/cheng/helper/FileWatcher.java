package com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.helper;

import java.util.Observable;

/**
 *
 */
public class FileWatcher extends Observable implements Watchable {

    // 是否要重新加载XML文件
    private boolean isReload = false;

    // 启动监视
    public void watch() {
        // 启动一个线程，每个15秒扫描一下文件，发现文件日期被修改，立刻通知观察者
        super.addObserver(new Checker());
        super.setChanged();
        super.notifyObservers(isReload);
    }

}
