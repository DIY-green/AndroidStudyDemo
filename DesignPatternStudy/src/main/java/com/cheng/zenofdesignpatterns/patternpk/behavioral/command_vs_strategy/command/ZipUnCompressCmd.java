package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * Zip解压命令
 */
public class ZipUnCompressCmd extends AbstractCmd {

	public boolean execute(String source,String to) {
		return super.uncompress.zipExec(source, to);
	}

}
