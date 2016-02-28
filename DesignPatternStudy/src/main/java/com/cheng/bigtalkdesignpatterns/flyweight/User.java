package com.cheng.bigtalkdesignpatterns.flyweight;

/**
 * 用户
 */
public class User {
    private String name;

    public User(String _name) {
        this.name = _name;
    }

    public String getName() {
        return name;
    }
}
