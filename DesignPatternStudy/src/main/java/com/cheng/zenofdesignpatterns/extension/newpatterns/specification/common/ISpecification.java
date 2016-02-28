package com.cheng.zenofdesignpatterns.extension.newpatterns.specification.common;


/**
 * 抽象规格书
 */
public interface ISpecification {

    // 候选者是否满足要求
    boolean isSatisfiedBy(Object candidate);

    // and操作
    ISpecification and(ISpecification spec);

    // or操作
    ISpecification or(ISpecification spec);

    // not操作
    ISpecification not();

}
