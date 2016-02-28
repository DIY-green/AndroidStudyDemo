package com.cheng.bigtalkdesignpatterns.abstractfactory;

/**
 * IUser接口
 */
public interface IUser {
    void insert(User user);
    User getUser(int id);
}
