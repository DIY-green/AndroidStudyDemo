package com.cheng.bigtalkdesignpatterns.flyweight;

/**
 * Created by Administrator on 2015/12/23.
 */
public class ConcreteWebSite extends WebSite {

    private String name = "";

    public ConcreteWebSite(String _name) {
        this.name = _name;
    }

    @Override
    public void use(User _user) {
        System.out.println("网站分类：" + name + "用户：" + _user.getName());
    }
}
