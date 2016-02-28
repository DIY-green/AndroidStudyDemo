package com.cheng.bigtalkdesignpatterns.adapter;

/**
 * 外籍中锋
 */
public class ForeignCenter {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public void cAttack() {
        System.out.println("外籍中锋" + name + "进攻");
    }

    public void cDefense() {
        System.out.println("外籍中锋" + name + "防守");
    }
}
