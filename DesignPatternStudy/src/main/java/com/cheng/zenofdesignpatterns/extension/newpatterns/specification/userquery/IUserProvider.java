package com.cheng.zenofdesignpatterns.extension.newpatterns.specification.userquery;

import java.util.ArrayList;

/**
 * 用户操作对象接口
 */
public interface IUserProvider {

    // 根据条件查找用户
    ArrayList<User> findUser(IUserSpecification userSpec);

}
