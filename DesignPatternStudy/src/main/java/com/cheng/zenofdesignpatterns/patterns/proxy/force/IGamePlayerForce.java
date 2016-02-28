package com.cheng.zenofdesignpatterns.patterns.proxy.force;

/**
 * 强制代理
 * 游戏玩家
 */
public interface IGamePlayerForce {

	// 登录游戏
	void login(String user, String password);
	
	// 杀怪，这是网络游戏的主要特色
	void killBoss();
	
	// 升级
	void upgrade();

	// 每个人都可以找一下自己的代理
	// 指定要访问自己必须通过哪个代理
	IGamePlayerForce getProxy();
}
