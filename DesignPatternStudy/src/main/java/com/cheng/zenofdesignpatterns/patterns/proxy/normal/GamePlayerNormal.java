package com.cheng.zenofdesignpatterns.patterns.proxy.normal;

/**
 * 普通代理的游戏者
 */
public class GamePlayerNormal implements IGamePlayerNormal {

    private String name = "";

    // 构造函数限制谁能创建对象，并同时传递姓名
    public GamePlayerNormal(IGamePlayerNormal _gamePlayer, String _name) throws Exception {
        if (_gamePlayer == null) {
            throw new Exception("不能创建真实角色！");
        } else {
            this.name = _name;
        }
    }

    @Override
    public void login(String user, String password) {
        // 进游戏之前你肯定要登录吧，这是一个必要条件
        System.out.println("登录名为"+user + " 的用户 " + this.name + "登录成功！");
    }

    @Override
    public void killBoss() {
        // 打怪，最期望的就是杀老怪
        System.out.println(this.name + "在打怪！");
    }

    @Override
    public void upgrade() {
        // 升级，升级有很多方法，花钱买是一种，做任务也是一种
        System.out.println(this.name + " 又升了一级！");
    }
}
