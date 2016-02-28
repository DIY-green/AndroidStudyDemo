package com.cheng.zenofdesignpatterns.patterns.proxy.dynamic;

/**
 * 游戏玩家
 */
public interface IGamePlayerDynamic {

    // 登录游戏
    void login(String user,String password);

    // 杀怪，这是网络游戏的主要特色
    void killBoss();

    // 升级
    void upgrade();
}
