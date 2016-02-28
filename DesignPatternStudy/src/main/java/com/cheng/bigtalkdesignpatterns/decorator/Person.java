package com.cheng.bigtalkdesignpatterns.decorator;

import android.util.Log;

/**
 * Person类（ConcreteComponent）
 */
public class Person {

    private static final String TAG = "Person";

    protected static StringBuilder stringBuilder = new StringBuilder();
    private String name;

    public Person() {}

    public Person(String _name) {
        this.name = _name;
    }

    public void show() {
        Log.e(TAG, "装扮的" + name + stringBuilder);
        stringBuilder.append("\n装扮的" + name);
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public void clearStringBuilder() {
        this.stringBuilder = null;
        this.stringBuilder = new StringBuilder();
    }
}
