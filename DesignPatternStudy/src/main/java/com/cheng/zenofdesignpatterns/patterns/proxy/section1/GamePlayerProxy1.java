package com.cheng.zenofdesignpatterns.patterns.proxy.section1;

/**
 * 代练者
 */
public class GamePlayerProxy1 implements IGamePlayer1 {
	private IGamePlayer1 gamePlayer = null;
	
	//通过构造函数传递要对谁进行代练
	public GamePlayerProxy1(IGamePlayer1 _gamePlayer){
		this.gamePlayer = _gamePlayer;
	}
	
	//代练杀怪
	public void killBoss() {
		this.gamePlayer.killBoss();
	}

	//代练登录
	public void login(String user, String password) {
		this.gamePlayer.login(user, password);
	}

	//代练升级
	public void upgrade() {
		this.gamePlayer.upgrade();
	}

}
