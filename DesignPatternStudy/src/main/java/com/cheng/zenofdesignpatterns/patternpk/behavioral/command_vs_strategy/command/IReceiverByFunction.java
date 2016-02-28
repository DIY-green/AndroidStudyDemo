package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * 依照功能设计的接收者接口
 * 抽象接收者
 */
public interface IReceiverByFunction {

    // 压缩
    boolean compress(String source, String to);

    // 解压缩
    boolean uncompress(String source, String to);

}
