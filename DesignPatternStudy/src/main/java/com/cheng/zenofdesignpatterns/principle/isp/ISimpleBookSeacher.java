package com.cheng.zenofdesignpatterns.principle.isp;

/**
 * 提供给公网的简单查询接口
 */
public interface ISimpleBookSeacher {
    void searchByAuthor();
    void searchByTitle();
    void searchByPublisher();
    void searchByCategory();
}
