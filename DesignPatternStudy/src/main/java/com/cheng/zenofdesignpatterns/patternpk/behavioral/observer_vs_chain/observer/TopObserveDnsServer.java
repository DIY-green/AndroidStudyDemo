package com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.observer;

/**
 * 全球顶级DNS服务器
 */
public class TopObserveDnsServer extends ObserverDnsServer {

    @Override
    protected void sign(Recorder recorder) {
        recorder.setOwner("全球顶级DNS服务器");
    }

    @Override
    protected boolean isLocal(Recorder recorder) {
        //所有的域名最终的解析地点
        return true;
    }

}
