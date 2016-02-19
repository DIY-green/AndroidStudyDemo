package com.cheng.mvpframestudy.rxjavamvp.model.bean;

/**
 * Created by Android on 2015/12/4.
 */
public class User {

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
