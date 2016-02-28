package com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command;

/**
 * 抽象压缩命令
 */
public abstract class AbstractCmd {

	// 对接收者的应用
	protected IReceiverByDuty compress = new CompressReceiver();
	protected IReceiverByDuty uncompress = new UncompressReceiver();

    protected IReceiverByFunction zipreceiver = new ZipReceiver();
    protected IReceiverByFunction gzipreceiver = new GzipReceiver();

	// 抽象方法，命令的具体单元
	public abstract boolean execute(String source, String to);

}
