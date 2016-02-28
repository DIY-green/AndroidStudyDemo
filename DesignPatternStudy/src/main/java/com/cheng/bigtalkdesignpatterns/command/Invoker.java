package com.cheng.bigtalkdesignpatterns.command;

/**
 * 要求该命令执行这个请求
 */
public class Invoker {
    private Command command;

    public void setCommand(Command _command) {
        this.command = _command;
    }

    public void executeCommand() {
        command.execute();
    }
}
