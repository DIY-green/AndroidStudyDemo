package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * Zip命令
 */
public class ZipUnCompressCmd2 extends AbstractCmd {

    @Override
    public boolean execute(String source, String to) {
        return super.zipreceiver.uncompress(source, to);
    }
}
