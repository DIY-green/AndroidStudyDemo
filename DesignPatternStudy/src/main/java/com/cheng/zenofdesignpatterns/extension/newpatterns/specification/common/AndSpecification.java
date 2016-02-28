package com.cheng.zenofdesignpatterns.extension.newpatterns.specification.common;

/**
 * 与规格书
 */
// 实现and操作
public class AndSpecification extends CompositeSpecification {
    // 传递两个规格书进行and操作
    private ISpecification left;
    private ISpecification right;

    public AndSpecification(ISpecification _left, ISpecification _right) {
        this.left = _left;
        this.right = _right;
    }

    // 进行and运算
    @Override
    public boolean isSatisfiedBy(Object candidate) {
        return left.isSatisfiedBy(candidate) && right.isSatisfiedBy(candidate);
    }

}
