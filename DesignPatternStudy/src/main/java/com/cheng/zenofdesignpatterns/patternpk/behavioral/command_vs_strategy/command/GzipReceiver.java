package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * Gzip接收者
 */
public class GzipReceiver implements IReceiverByFunction {

    // Gzip格式的压缩算法
    @Override
    public boolean compress(String source, String to) {
        System.out.println(source + " --> " +to + " GZIP压缩成功!");
        return true;
    }

    // Gzip格式的解压缩算法
    @Override
    public boolean uncompress(String source, String to) {
        System.out.println(source + " --> " +to + " GZIP解压缩成功!");
        return true;
    }
}
