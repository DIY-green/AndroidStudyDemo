package com.cheng.zenofdesignpatterns.patterns.proxy.force;

/**
 * 强制代理的代理者
 * 游戏代练者
 */
public class GamePlayerProxyForce implements IGamePlayerForce {

    private IGamePlayerForce gamePlayer = null;

    // 通过构造函数传递要对谁进行代练
    public GamePlayerProxyForce(IGamePlayerForce _gamePlayer) {
        this.gamePlayer = _gamePlayer;
    }

    @Override
    public void login(String user, String password) {
        // 代练登录
        this.gamePlayer.login(user, password);
    }

    @Override
    public void killBoss() {
        // 代练杀怪
        this.gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        // 代练升级
        this.gamePlayer.upgrade();
    }

    @Override
    public IGamePlayerForce getProxy() {
        // 代理的代理暂时还没有,就是自己
        return this;
    }
}
