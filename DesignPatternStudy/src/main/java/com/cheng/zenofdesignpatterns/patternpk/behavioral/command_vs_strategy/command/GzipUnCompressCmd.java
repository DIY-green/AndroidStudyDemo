package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * Gzip解压命令
 */
public class GzipUnCompressCmd extends AbstractCmd {

	public boolean execute(String source,String to) {
		return super.uncompress.gzipExec(source, to);
	}

}
