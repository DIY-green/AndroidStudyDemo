package com.cheng.bigtalkdesignpatterns.state;

/**
 * 工作
 */
public class Work {

    private int hour;
    private boolean isfinish;
    private State current;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public boolean isfinish() {
        return isfinish;
    }

    public void setIsfinish(boolean isfinish) {
        this.isfinish = isfinish;
    }

    public State getCurrent() {
        return current;
    }

    public void setCurrent(State current) {
        this.current = current;
    }

    public void writeProgram() {
        current.writeProgram(this);
    }
}
