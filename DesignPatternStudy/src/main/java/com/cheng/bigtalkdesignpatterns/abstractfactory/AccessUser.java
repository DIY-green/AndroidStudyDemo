package com.cheng.bigtalkdesignpatterns.abstractfactory;

/**
 * 用于访问 Access 的 User
 */
public class AccessUser implements IUser {

    @Override
    public void insert(User User) {
        System.out.println("在 Access 中给User表添加一条记录");
    }

    @Override
    public User getUser(int id) {
        System.out.println("在 Access 中根据ID从User表中获取一条记录");
        return null;
    }
}
