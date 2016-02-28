package com.cheng.bigtalkdesignpatterns.command;

/**
 * 用来声明执行操作的接口
 */
public abstract class Command {
    protected Receiver receiver;

    public Command(Receiver _receiver) {
        this.receiver = _receiver;
    }

    public abstract void execute();
}
