package com.cheng.multithreadstudy.sunframework.eventbus;

import java.io.Serializable;

/**
 * Created by sunfusheng on 15/9/24.
 */
public class EventEntity implements Serializable {

    private String type;
    private int arg1;
    private int arg2;
    private Object obj;

    public EventEntity(String type) {
        this.type = type;
    }

    public EventEntity(String type, Object obj) {
        this.type = type;
        this.obj = obj;
    }

    public EventEntity(String type, int arg1, Object obj) {
        this.type = type;
        this.arg1 = arg1;
        this.obj = obj;
    }

    public EventEntity(String type, int arg1, int arg2, Object obj) {
        this.type = type;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.obj = obj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getArg1() {
        return arg1;
    }

    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
