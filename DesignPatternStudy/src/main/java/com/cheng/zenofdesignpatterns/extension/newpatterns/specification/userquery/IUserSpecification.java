package com.cheng.zenofdesignpatterns.extension.newpatterns.specification.userquery;

/**
 * 带与或非的规格书接口
 */
public interface IUserSpecification {
	
	// 候选者是否满足要求
	boolean isSatisfiedBy(User user);

    // and操作
    IUserSpecification and(IUserSpecification spec);

    // or操作
    IUserSpecification or(IUserSpecification spec);

    // not操作
    IUserSpecification not();

}

