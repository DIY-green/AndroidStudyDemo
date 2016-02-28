package com.cheng.bestpractice.observer.observer.impl;

import com.cheng.bestpractice.observer.observer.i.IEventObserver;
import com.cheng.bestpractice.observer.observer.i.IEventSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体的主题角色的实现，这里用来监听事件的发生，采用单例模式来实现
 */
public class EventSubject implements IEventSubject {

    private List<IEventObserver> mEventObservers=new ArrayList<IEventObserver>();
    private static volatile EventSubject mEventSubject;
    private EventSubject(){

    }

    public synchronized static EventSubject getInstance(){
        if(mEventSubject ==null){
            mEventSubject =new EventSubject();
        }
        return mEventSubject;
    }

    @Override
    public void registerObserver(IEventObserver observer) {
        synchronized (mEventObservers){
            if(observer!=null){
                if(mEventObservers.contains(observer)){
                    return;
                }
                mEventObservers.add(observer);
            }
        }

    }

    @Override
    public void removeObserver(IEventObserver observer) {
        synchronized (mEventObservers){
            int index = mEventObservers.indexOf(observer);
            if (index >= 0) {
                mEventObservers.remove(observer);
            }
        }
    }

    @Override
    public void notifyObserver(String eventType) {
        if(mEventObservers!=null && mEventObservers.size()>0 && eventType!=null){
            for(IEventObserver observer : mEventObservers){
                observer.dispatchChange(eventType);
            }
        }

    }
}
