package com.cheng.bigtalkdesignpatterns.iterator;

import java.util.Vector;

/**
 * Created by Administrator on 2015/12/22.
 */
public class ConcreteAggregat implements Aggregat {

    private Vector vector = null;

    public Vector getVector() {
        return vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }

    public ConcreteAggregat() {
        vector = new Vector();
        vector.add("vector 0");
        vector.add("vector 1");
        vector.add("vector 2");
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(vector);
    }
}
