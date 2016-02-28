package com.cheng.zenofdesignpatterns.principle.dip;

/**
 * 司机的实现类
 */
public class Driver implements IDriver {

    private ICar mCar;

    public Driver() {

    }

    // 构造函数依赖注入
    public Driver(ICar _car) {
        this.mCar = _car;
    }

    @Override
    public void drive(ICar _car) {
        _car.run();
    }

    // Setter依赖注入
    public void setCar(ICar _car) {
        this.mCar = _car;
    }

}
