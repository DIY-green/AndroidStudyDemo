package com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa;

/**
 * 人类创建工厂
 */
public class HumanFactory extends AbstractHumanFactory {

    @Override
    public <T extends Human> T createHuman(Class<T> _Clazz) {
        // 定义一个产生的人种
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
