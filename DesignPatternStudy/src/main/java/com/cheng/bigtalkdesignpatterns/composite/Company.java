package com.cheng.bigtalkdesignpatterns.composite;

/**
 * 公司类
 */
public abstract class Company {
    protected String name;

    public Company(String _name) {
        this.name = _name;
    }

    public abstract void add(Company c); // 添加
    public abstract void remove(Company c); // 移除
    public abstract void display(int depth); // 显示
    public abstract void lineOfDuty(); // 履行职责
}
