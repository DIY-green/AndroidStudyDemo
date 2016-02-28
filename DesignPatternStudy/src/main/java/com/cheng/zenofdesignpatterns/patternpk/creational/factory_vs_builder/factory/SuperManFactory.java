package com.cheng.zenofdesignpatterns.patternpk.creational.factory_vs_builder.factory;

/**
 * 超人制造工厂
 */
public class SuperManFactory {

    // 定义一个生产超人的工厂
    public static ISuperMan createSuperMan(String type) {
        // 根据输入参数产生不同的超人
        if (type.equalsIgnoreCase("adult")) {
            // 生产成人超人
            return new AdultSuperMan();
        } else if (type.equalsIgnoreCase("child")) {
            // 生产未成年超人
            return new ChildSuperMan();
        } else {
            return null;
        }
    }

    /**
     * 注意
     * 通过工厂方法模式生产出对象，然后由客户端进行对象的其他操作，但是并不代表
     * 所有生产出的对象都必须具有相同的状态和行为，它是由产品所决定
     */
}
