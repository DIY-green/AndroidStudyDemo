package com.cheng.zenofdesignpatterns.extension.newpatterns.specification.userquery;

/**
 * 组合规格说明书
 */
public abstract class CompositeSpecification implements IUserSpecification {

    // 是否满足条件有实现类实现
    public abstract boolean isSatisfiedBy(User user);

    // and操作
    public IUserSpecification and(IUserSpecification spec) {
        return new AndSpecification(this, spec);
    }

    // not操作
    public IUserSpecification not() {
        return new NotSpecification(this);
    }

    // or操作
    public IUserSpecification or(IUserSpecification spec) {
        return new OrSpecification(this, spec);
    }

    /**
     * 注意
     * 父类依赖子类的情景只有在非常明确不会发生变化的场景中存在，它不具备扩展性，
     * 是一种固化而不可变化的结构
     */

}
