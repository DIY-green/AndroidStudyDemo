package com.cheng.bigtalkdesignpatterns.interpreter;

/**
 * 音速类
 */
public class Speed extends Expression {

    @Override
    public void execute(String key, double value) {
        String speed;
        if (value < 500) {
            speed = "快速";
        } else if (value >= 1000) {
            speed = "慢速";
        } else {
            speed = "中速";
        }
        System.out.println(speed);
    }
}
