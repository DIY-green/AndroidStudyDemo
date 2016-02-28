package com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.helper;

/**
 *
 */
public class LogicXmlValidate implements IXmlValidate {

    // 检查xmlPath是否符合逻辑，比如不会出现一个类中没有的方法
    public boolean validate(String xmlPath) {
        return false;
    }

}
