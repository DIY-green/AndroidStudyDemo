package com.cheng.mvvmstudy.androiddatabinding.model.bean;

/**
 * Created by Administrator on 2015/11/21.
 */
public class Student {
    private String name;
    private String addr;

    public Student() {
    }

    public Student(String addr, String name) {
        this.addr = addr;
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
