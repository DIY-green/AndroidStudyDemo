package com.cheng.zenofdesignpatterns.principle.dip;

/**
 * 司机接口
 */
public interface IDriver {
    // 是司机就应该会驾驶汽车
    void drive(ICar car); // 所谓的接口依赖注入
}
