package com.cheng.zenofdesignpatterns.patterns.memento.clone;

/**
 * clone方式的备忘录
 */
public class CloneOriginator implements Cloneable {

    private CloneOriginator backup;

    // 内部状态
    private String state = "";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // 创建一个备忘录
    public void createMemento() {
        this.backup = this.clone();
    }

    // 恢复一个备忘录
    public void restoreMemento() {
        // 在进行恢复前应该进行断言，防止空指针
        this.setState(this.backup.getState());
    }

    /**
     * clone当前对象
     */
    @Override
    protected CloneOriginator clone() {
        try {
            return (CloneOriginator) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
