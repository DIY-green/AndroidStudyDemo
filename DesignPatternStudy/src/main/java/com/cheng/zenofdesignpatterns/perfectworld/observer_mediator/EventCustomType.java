package com.cheng.zenofdesignpatterns.perfectworld.observer_mediator;

/**
 *
 */
public enum EventCustomType {

    // 新建立事件
    NEW(1),
    // 删除事件
    DEL(2),
    // 修改事件
    EDIT(3),
    // 克隆事件
    CLONE(4);

    private int value = 0;

    private EventCustomType(int _value) {
        this.value = _value;
    }

    public int getValue() {
        return value;
    }

}