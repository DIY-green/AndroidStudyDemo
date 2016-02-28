package com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.observer;

/**
 * 上海DNS服务器
 */
public class SHObserveDnsServer extends ObserverDnsServer {

	@Override
	protected void sign(Recorder recorder) {		
		recorder.setOwner("上海DNS服务器");
	}

	// 定义上海的DNS服务器能处理的级别
	@Override
	protected boolean isLocal(Recorder recorder) {
		return recorder.getDomain().endsWith(".sh.cn");
	}

}
