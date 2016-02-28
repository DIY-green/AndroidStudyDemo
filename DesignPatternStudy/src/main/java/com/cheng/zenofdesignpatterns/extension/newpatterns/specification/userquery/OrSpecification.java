package com.cheng.zenofdesignpatterns.extension.newpatterns.specification.userquery;

/**
 * 或规格说明书
 */
public class OrSpecification extends CompositeSpecification {

    // 左右两个规格书
    private IUserSpecification left;
    private IUserSpecification right;

    public OrSpecification(IUserSpecification _left, IUserSpecification _right) {
        this.left = _left;
        this.right = _right;
    }

    // or运算
    @Override
    public boolean isSatisfiedBy(User user) {
        return left.isSatisfiedBy(user) || right.isSatisfiedBy(user);
    }

}
