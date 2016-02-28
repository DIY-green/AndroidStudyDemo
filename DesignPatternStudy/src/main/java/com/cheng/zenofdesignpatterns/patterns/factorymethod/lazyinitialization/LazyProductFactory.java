package com.cheng.zenofdesignpatterns.patterns.factorymethod.lazyinitialization;

import com.cheng.zenofdesignpatterns.patterns.factorymethod.common.ConcreteProduct1;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.common.ConcreteProduct2;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.common.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟加载的工具类
 */
public class LazyProductFactory {

    private static final Map<String, Product> prMap = new HashMap<>();

    public static synchronized Product createProduct(String type) {
        Product product = null;
        // 如果Map中已经有这个对象
        if (prMap.containsKey(type)) {
            product = prMap.get(type);
        } else {
            if (type.equals("Product1")) {
                product = new ConcreteProduct1();
            } else {
                product = new ConcreteProduct2();
            }
            // 同时把对象放到缓存容器中
            prMap.put(type, product);
        }
        return product;
    }

    /**
     * 延迟加载框架是可以扩展的，例如限制某一个产品类的最大实例化数量，可以通过判断Map中
     * 已有的对象数量来实现，这样的处理是非常有意义的，例如JDBC连接数据库，都会要求设置
     * 一个MaxConnections最大连接数量，该数量就是内存中最大实例化的数量。
     * 延迟加载还可以用在对象初始化比较复杂的情况下，例如硬件访问，涉及多方面的交互，则
     * 可以通过延迟加载降低对象的产生和销毁带来的复杂性。
     */
}
