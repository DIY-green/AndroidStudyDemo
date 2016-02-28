package com.cheng.bigtalkdesignpatterns.abstractfactory;

/**
 * Created by Administrator on 2015/12/21.
 */
public interface IFactory {
    IUser createUser();
    IDepartment createDepartment();
}
