package com.cheng.zenofdesignpatterns.extension.newpatterns.specification.userquery;

/**
 * 用户类
 */
public class User {

    // 姓名
    private String name;
    // 年龄
    private int age;

    public User(String _name, int _age) {
        this.name = _name;
        this.age = _age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "用户名：" + name + "\t年龄：" + age;
    }
}
