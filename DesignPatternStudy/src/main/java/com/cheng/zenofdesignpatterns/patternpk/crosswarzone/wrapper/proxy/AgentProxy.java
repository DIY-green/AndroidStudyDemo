package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.proxy;

/**
 * 代理人
 */
public class AgentProxy implements IStarProxy {

    // 定义是谁的代理人
    private IStarProxy star;

    // 构造函数传递明星
    public AgentProxy(IStarProxy _star) {
        this.star = _star;
    }

    // 代理人是不会签字的，签字了歌迷也不认呀
    public void sign() {
        star.sign();
    }

}
