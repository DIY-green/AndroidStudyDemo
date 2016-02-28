package com.cheng.bigtalkdesignpatterns.abstractfactory;

/**
 * Created by Administrator on 2015/12/21.
 */
public class SqlServerFactory implements IFactory {

    @Override
    public IUser createUser() {
        return new SqlserverUser();
    }

    @Override
    public IDepartment createDepartment() {
        return new SqlserverDepartment();
    }
}
