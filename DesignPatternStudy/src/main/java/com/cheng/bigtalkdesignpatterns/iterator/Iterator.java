package com.cheng.bigtalkdesignpatterns.iterator;

/**
 * Created by Administrator on 2015/12/22.
 */
public interface Iterator {
    Object first();
    Object next();
    Object currentItem();
    boolean isDone();
}
