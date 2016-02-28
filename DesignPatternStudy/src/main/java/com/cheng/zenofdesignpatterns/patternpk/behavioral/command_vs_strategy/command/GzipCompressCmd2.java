package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * Gzip命令
 */
public class GzipCompressCmd2 extends AbstractCmd {

    @Override
    public boolean execute(String source, String to) {
        return super.gzipreceiver.compress(source, to);
    }
}
