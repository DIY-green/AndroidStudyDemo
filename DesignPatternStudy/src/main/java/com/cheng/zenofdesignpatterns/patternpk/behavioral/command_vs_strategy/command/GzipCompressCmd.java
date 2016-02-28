package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * Gzip压缩命令
 */
public class GzipCompressCmd extends AbstractCmd {

	public boolean execute(String source,String to) {
		return super.compress.gzipExec(source, to);
	}

}
