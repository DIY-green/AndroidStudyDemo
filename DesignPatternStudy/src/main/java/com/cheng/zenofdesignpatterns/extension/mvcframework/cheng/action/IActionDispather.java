package com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.action;

/**
 *
 */
public interface IActionDispather {

    // 根据action的名字，返回处理结果
    String actionInvoke(String actionName);
}
