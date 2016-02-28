package com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.helper;

import org.dom4j.Attribute;
import org.dom4j.Element;

/**
 *
 */
public class XmlActionNode extends ActionNode {

    //需要转换的element
    private Element el;

    //通过构造函数传递
    public XmlActionNode(Element _el) {
        this.el = _el;
    }


    @Override
    public String getActionName() {
        return getAttValue("name");
    }

    @Override
    public String getActionClass() {
        return getAttValue("class");
    }

    @Override
    public String getMethodName() {
        return getAttValue("method");
    }

    public String getView(String result) {
        ViewPathVisitor visitor = new ViewPathVisitor("success");
        el.accept(visitor);
        return visitor.getViewPath();
    }


    //获得指定属性值
    private String getAttValue(String attName) {
        Attribute att = el.attribute(attName);
        return att.getText();
    }

}
