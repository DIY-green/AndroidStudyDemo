package com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.observer;

/**
 * 中国顶级DNS服务器
 */
public class ChinaTopObserveDnsServer extends ObserverDnsServer {

	@Override
	protected void sign(Recorder recorder) {
		recorder.setOwner("中国顶级DNS服务器");
	}
	
	@Override
	protected boolean isLocal(Recorder recorder) {
		return recorder.getDomain().endsWith(".cn");
	}

}
