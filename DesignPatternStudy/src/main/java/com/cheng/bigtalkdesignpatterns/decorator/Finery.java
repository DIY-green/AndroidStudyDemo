package com.cheng.bigtalkdesignpatterns.decorator;

/**
 * 服饰类（Decorator）
 */
public class Finery extends Person {

    protected Person component;

    // 打扮
    public void decorate(Person _person) {
        this.component = _person;
    }

    @Override
    public void show() {
        if (component != null) {
            component.show();
        }
    }
}
