package com.cheng.bigtalkdesignpatterns.memento;

/**
 * 发起人
 */
public class Originator {
    private String state;

    public Memento createMemento() {
        return new Memento(state);
    }

    public void setMemento(Memento _memento) {
        state = _memento.getState();
    }

    public void show() {
        System.out.println("State = " + state);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
