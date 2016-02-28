package com.cheng.zenofdesignpatterns.principle.srp;

/**
 * 协议管理接口
 */
public interface IConnectionManager {
    void dial(String phoneNumber);
    void hangup();
}
