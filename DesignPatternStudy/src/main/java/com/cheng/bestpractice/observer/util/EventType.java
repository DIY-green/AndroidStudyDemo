package com.cheng.bestpractice.observer.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 所有的业务类型，在这里写，方便管理
 */
public class EventType {

    public final static String UPDATE_MAIN = "com.updateMain";
    public final static String UPDATE_Text = "com.updateText";

    private final static Set<String> mEventTypeSet = new HashSet<>();
    private static volatile EventType mEventType;

    private EventType(){
        mEventTypeSet.add(UPDATE_MAIN);
        mEventTypeSet.add(UPDATE_Text);
    }

    public static EventType getInstance(){
       if(mEventType == null){
           mEventType = new EventType();
       }
        return mEventType;
    }

    public boolean contains(String eventType){
        return mEventTypeSet.contains(eventType);
    }

}