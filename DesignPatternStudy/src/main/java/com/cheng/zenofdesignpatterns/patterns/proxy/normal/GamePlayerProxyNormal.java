package com.cheng.zenofdesignpatterns.patterns.proxy.normal;

/**
 * 普通代理的代理者
 * 游戏代练者
 */
public class GamePlayerProxyNormal implements IGamePlayerNormal {

    private IGamePlayerNormal gamePlayer = null;

    // 通过构造函数传递要对谁进行代练
    public GamePlayerProxyNormal(String _name) {
        try {
            gamePlayer = new GamePlayerNormal(this, _name);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
