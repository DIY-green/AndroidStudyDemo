package com.cheng.zenofdesignpatterns.patterns.factorymethod.common;

/**
 * 具体工厂
 * 具体如何生成一个产品的对象，是由具体的工厂类实现的
 */
public class ConcreteCreator extends Creator {

    @Override
    public <T extends Product> T createProduct(Class<T> _Clazz) {
        Product product = null;
        try {
            product = (Product) Class.forName(_Clazz.getName()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}
