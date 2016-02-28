package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * Zip接收者
 */
public class ZipReceiver implements IReceiverByFunction {

    // Zip格式的压缩算法
    @Override
    public boolean compress(String source, String to) {
        System.out.println(source + " --> " +to + " ZIP压缩成功!");
        return true;
    }

    // Zip格式的解压缩算法
    @Override
    public boolean uncompress(String source, String to) {
        System.out.println(source + " --> " +to + " ZIP解压缩成功!");
        return true;
    }
}
