package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * Zip命令
 */
public class ZipCompressCmd2 extends AbstractCmd {

    @Override
    public boolean execute(String source, String to) {
        return super.zipreceiver.compress(source, to);
    }
}
