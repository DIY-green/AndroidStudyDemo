package com.cheng.zenofdesignpatterns.perfectworld.command_chain;

import com.cheng.zenofdesignpatterns.perfectworld.command_chain.command.Command;

/**
 * 调用类
 */
public class Invoker {

    // 执行命令
    public String exec(String _commandStr) {
        // 定义返回值
        String result = "";
        // 首先解析命令
        CommandVO vo = new CommandVO(_commandStr);
        // 检查是否支持支持该命令
        if (CommandEnum.getNames().contains(vo.getCommandName())) {
            // 产生命令对象
            String className = CommandEnum.valueOf(vo.getCommandName()).getValue();
            Command command;
            try {
                command = (Command) Class.forName(className).newInstance();
                result = command.execute(vo);
            } catch (Exception e) {
                // TODO 异常处理
            }
        } else {
            result = "无法执行命令，请检查命令格式";
        }
        return result;
    }

    public static void main(String[] args) {
        String cmd = "ls -a";
        Invoker invoker = new Invoker();
        System.out.println(invoker.exec(cmd));
    }
}
