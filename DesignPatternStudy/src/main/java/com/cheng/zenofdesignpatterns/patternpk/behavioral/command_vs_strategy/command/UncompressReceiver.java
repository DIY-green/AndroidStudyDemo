package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * 解压缩接收者
 */
public class UncompressReceiver implements IReceiverByDuty {

	// 执行gzip解压缩命令
	public boolean gzipExec(String source, String to) {
		System.out.println(source + " --> " +to + " GZIP解压缩成功!");
		return true;
	}

	// 执行zip解压缩命令
	public boolean zipExec(String source, String to) {
		System.out.println(source + " --> " +to + " ZIP解压缩成功!");
		return true;
	}

}
