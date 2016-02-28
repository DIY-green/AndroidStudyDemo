package com.cheng.bestpractice.observer.observer.impl;

import android.os.Handler;
import android.os.Looper;

import com.cheng.bestpractice.observer.observer.i.IEventObserver;

/**
 * 用于更新UI，这里执行更新UI的onChange方法
 */
public abstract class EventObserver implements IEventObserver {

    private Handler mHandler;

    public EventObserver(){
        mHandler=new Handler(Looper.getMainLooper());
    }


    public abstract void onChange(String eventType);

    @Override
    public void dispatchChange(String eventType){
        mHandler.post(new NotificationRunnable(eventType));
    }

    private final class NotificationRunnable implements Runnable{
        private String mEventType;
        public NotificationRunnable(String eventType){
            this.mEventType=eventType;
        }
        @Override
        public void run() {
            EventObserver.this.onChange(mEventType);
        }
    }
}
