package com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.helper;

/**
 * 普通的XML检查，也就是XML语法检查
 */
public class CommonXmlValidate implements IXmlValidate {

    // XML语法检查，比如是否少写了一个结束标志
    public boolean validate(String xmlPath) {
        return false;
    }

}
