package com.cheng.zenofdesignpatterns.principle.ocp;

/**
 * 计算机书籍接口
 */
public interface IComputerBook extends IBook {
    // 计算机书籍是有一个范围
    String getScope();
}
