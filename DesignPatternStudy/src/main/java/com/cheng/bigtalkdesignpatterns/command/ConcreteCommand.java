package com.cheng.bigtalkdesignpatterns.command;

/**
 * Created by Administrator on 2015/12/22.
 */
public class ConcreteCommand extends Command {

    public ConcreteCommand(Receiver _receiver) {
        super(_receiver);
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
