package com.cheng.zenofdesignpatterns.principle.ocp;

/**
 * 书籍接口
 */
public interface IBook {
    // 书籍有名称
    String getName();
    // 书籍有售价
    int getPrice();
    // 书籍有作者
    String getAuthor();
}
