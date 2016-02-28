package com.cheng.bigtalkdesignpatterns.chainofresponsibility;

/**
 * 管理者类
 */
public abstract class Manager {

    protected String name;
    // 管理者的上级
    protected Manager superior;

    public Manager(String name) {
        this.name = name;
    }

    // 设置管理者的上级
    public void setSuperior(Manager _superior) {
        this.superior = _superior;
    }

    // 申请请求
    public abstract void requestApplication(Request request);
}
