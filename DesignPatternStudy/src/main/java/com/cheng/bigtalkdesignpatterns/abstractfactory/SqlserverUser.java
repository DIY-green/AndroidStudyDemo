package com.cheng.bigtalkdesignpatterns.abstractfactory;

/**
 * 用于访问 SQL Server 的 User
 */
public class SqlserverUser implements IUser {

    @Override
    public void insert(User user) {
        System.out.println("在 SQL Server 中给User表添加一条记录");
    }

    @Override
    public User getUser(int id) {
        System.out.println("在 SQL Server 中根据ID从User表中获取一条记录");
        return null;
    }
}
