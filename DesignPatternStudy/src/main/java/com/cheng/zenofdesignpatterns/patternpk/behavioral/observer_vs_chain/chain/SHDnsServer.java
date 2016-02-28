package com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.chain;

/**
 * 上海DNS服务器
 */
public class SHDnsServer extends ChainDnsServer {

	@Override
	protected Recorder echo(String domain) {
		Recorder recorder= super.echo(domain);
		recorder.setOwner("上海DNS服务器");
		return recorder;
	}

	// 定义上海的DNS服务器能处理的级别
	@Override
	protected boolean isLocal(String domain) {
		return domain.endsWith(".sh.cn");
	}

}
