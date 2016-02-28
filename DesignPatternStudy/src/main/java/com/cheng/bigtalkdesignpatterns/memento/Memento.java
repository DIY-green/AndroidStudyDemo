package com.cheng.bigtalkdesignpatterns.memento;

/**
 * 备忘录
 */
public class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
