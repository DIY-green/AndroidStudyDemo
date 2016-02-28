package com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.chain;

/**
 * 中国顶级DNS服务器
 */
public class ChinaTopDnsServer extends ChainDnsServer {

	@Override
	protected Recorder echo(String domain) {
		Recorder recorder = super.echo(domain);
		recorder.setOwner("中国顶级DNS服务器");
		return recorder;
	}

	@Override
	protected boolean isLocal(String domain) {
		return domain.endsWith(".cn");
	}

}
