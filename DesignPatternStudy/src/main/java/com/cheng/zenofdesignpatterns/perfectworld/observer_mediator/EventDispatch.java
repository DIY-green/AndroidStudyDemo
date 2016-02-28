package com.cheng.zenofdesignpatterns.perfectworld.observer_mediator;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 *
 */
public class EventDispatch implements Observer {

    // 单例模式
    private final static EventDispatch dispatch = new EventDispatch();
    // 事件消费者
    private Vector<EventCustomer> customer = new Vector<EventCustomer>();

    // 不允许生成新的实例
    private EventDispatch() {

    }

    // 获得单例对象
    public static EventDispatch getEventDispathc() {
        return dispatch;
    }

    // 事件触发
    public void update(Observable o, Object arg) {
        // 事件的源头
        Product product = (Product) arg;
        // 事件
        ProductEvent event = (ProductEvent) o;

        // 处理者处理，这里是中介者模式的核心，可以是很复杂的业务逻辑
        for (EventCustomer e : customer) {
            // 处理能力是否匹配
            for (EventCustomType t : e.getCustomType()) {
                if (t.getValue() == event.getEventType().getValue()) {
                    e.exec(event);
                }
            }

        }
    }

    // 注册事件处理者
    public void registerCustomer(EventCustomer _customer) {
        customer.add(_customer);
    }

}

