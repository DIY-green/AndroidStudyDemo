package com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa;

/**
 * 抽象人类创建工厂
 */
public abstract class AbstractHumanFactory {
    public abstract <T extends Human> T createHuman(Class<T> _Clazz);
    /**
     * 注意
     * 我们在这里采用了泛型（Generic），通过定义泛型对createHuman的输入参数产生两层
     * 限制：
     * - 必须是Class类型
     * - 必须是Human的实现类
     */
}
