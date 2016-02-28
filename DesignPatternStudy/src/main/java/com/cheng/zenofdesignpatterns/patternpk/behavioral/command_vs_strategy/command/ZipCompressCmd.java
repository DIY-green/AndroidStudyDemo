package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * Zip压缩命令
 */
public class ZipCompressCmd extends AbstractCmd {

	public boolean execute(String source,String to) {
		return super.compress.zipExec(source, to);
	}

}
