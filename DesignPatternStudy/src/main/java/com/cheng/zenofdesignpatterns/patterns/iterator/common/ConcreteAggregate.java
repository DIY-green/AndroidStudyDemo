package com.cheng.zenofdesignpatterns.patterns.iterator.common;

import java.util.Vector;

/**
 * 容器实现类
 */
public class ConcreteAggregate implements Aggregate {

    // 容纳对象的容器
    private Vector vector = new Vector();

    @Override
    public void add(Object object) {
        this.vector.add(object);
    }

    @Override
    public void remove(Object object) {
        this.vector.remove(object);
    }

    @Override
    public Iterator iterator() {
        return new ConcreteIterator(this.vector);
    }
}
