package com.cheng.bigtalkdesignpatterns.abstractfactory;

/**
 * Created by Administrator on 2015/12/21.
 */
public class AccessFactory implements IFactory {

    @Override
    public IUser createUser() {
        return new AccessUser();
    }

    @Override
    public IDepartment createDepartment() {
        return new AccessDepartment();
    }
}
