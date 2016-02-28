package com.cheng.bigtalkdesignpatterns.abstractfactory;

/**
 * IDepartment接口，用户客户端访问，解除与具体数据库访问的耦合
 */
public interface IDepartment {
    void insert(Department department);
    Department getDepartment(int id);
}
