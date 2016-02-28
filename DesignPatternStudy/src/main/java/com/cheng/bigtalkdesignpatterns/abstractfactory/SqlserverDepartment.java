package com.cheng.bigtalkdesignpatterns.abstractfactory;

/**
 * 用于访问 SQL Server 的 Department
 */
public class SqlserverDepartment implements IDepartment {

    @Override
    public void insert(Department department) {
        System.out.println("在 SQL Server 中给Department表添加一条记录");
    }

    @Override
    public Department getDepartment(int id) {
        System.out.println("在 SQL Server 中根据ID从Department表中获取一条记录");
        return null;
    }
}
