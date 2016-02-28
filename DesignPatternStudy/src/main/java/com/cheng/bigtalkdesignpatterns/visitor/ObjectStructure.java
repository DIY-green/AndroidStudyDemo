package com.cheng.bigtalkdesignpatterns.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象结构
 */
public class ObjectStructure {
    private List<Person> elements = new ArrayList<>();

    // 增加
    public void attach(Person _element) {
        elements.add(_element);
    }

    // 移除
    public void detach(Person _element) {
        elements.remove(_element);
    }

    // 查看显示
    public void display(Action visitor) {
        for (Person e : elements) {
            e.accept(visitor);
        }
    }
}
