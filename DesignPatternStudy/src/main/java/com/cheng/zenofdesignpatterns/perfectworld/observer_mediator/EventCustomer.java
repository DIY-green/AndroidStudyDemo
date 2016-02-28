package com.cheng.zenofdesignpatterns.perfectworld.observer_mediator;

import java.util.Vector;

/**
 *
 */
public abstract class EventCustomer {

    // 容纳每个消费者能够处理的级别
    private Vector<EventCustomType> customType = new Vector<EventCustomType>();

    // 每个消费者都要声明自己处理哪一类别的消息
    public EventCustomer(EventCustomType _type) {
        addCustomType(_type);
    }

    // 每个消费者可以消费多个事件
    public void addCustomType(EventCustomType _type) {
        customType.add(_type);
    }

    // 得到自己的处理能力
    public Vector<EventCustomType> getCustomType() {
        return customType;
    }

    // 每个事件都要对事件进行声明式消费
    public abstract void exec(ProductEvent event);

}
