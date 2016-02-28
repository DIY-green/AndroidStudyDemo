package com.cheng.bigtalkdesignpatterns.abstractfactory;

/**
 * 用于访问 Access 的 Department
 */
public class AccessDepartment implements IDepartment {

    @Override
    public void insert(Department department) {
        System.out.println("在 Access 中给Department表添加一条记录");
    }

    @Override
    public Department getDepartment(int id) {
        System.out.println("在 Access 中根据ID从Department表中获取一条记录");
        return null;
    }
}
