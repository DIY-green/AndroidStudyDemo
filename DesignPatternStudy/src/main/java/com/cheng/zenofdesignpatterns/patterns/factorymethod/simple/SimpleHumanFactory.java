package com.cheng.zenofdesignpatterns.patterns.factorymethod.simple;

import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.Human;

/**
 * 简单工厂模式中的工厂类
 */
public class SimpleHumanFactory {

    public static <T extends Human> T createHuman(Class<T> _Clazz) {
        // 定义一个生产的人种
        Human human = null;
        try {
            // 产生一个人种
            human = (Human) Class.forName(_Clazz.getName()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) human;
    }
}
