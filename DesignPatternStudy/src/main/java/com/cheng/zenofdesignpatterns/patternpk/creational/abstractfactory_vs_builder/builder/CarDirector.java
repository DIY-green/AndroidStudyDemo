package com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.builder;

/**
 * 导演类
 */
public class CarDirector {

    // 声明对建造者的引用
    private CarBuilder benzBuilder = new BenzBuilder();
    private CarBuilder bmwBuilder = new BMWBuilder();

    // 生产奔驰SUV车型
    public ICarBuilder createBenzSuv() {
        // 制造出汽车
        return createCar(benzBuilder, "benz的引擎", "benz的轮胎");
    }

    // 生产出一辆宝马的商务车
    public ICarBuilder createBMWVan() {
        return createCar(bmwBuilder, "BMW的引擎", "BMW的轮胎");
    }

    // 生产出一个混合车型
    public ICarBuilder createComplexCar() {
        return createCar(bmwBuilder, "BMW的引擎", "benz的轮胎");
    }

    // 生产车辆
    private ICarBuilder createCar(CarBuilder _carBuilder, String _engine, String _wheel) {
        // 导演怀揣蓝图
        Blueprint bp = new Blueprint();
        bp.setEngine(_engine);
        bp.setWheel(_wheel);

        System.out.println("获得生产蓝图");
        _carBuilder.receiveBlueprint(bp);
        return _carBuilder.buildCar();
    }

}