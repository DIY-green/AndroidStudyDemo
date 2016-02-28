package com.cheng.zenofdesignpatterns.principle.lkp;

import java.util.Random;

/**
 * 模拟软件安装的导向类
 */
public class Wizard {
    private Random rand = new Random(System.currentTimeMillis());

    // 第一步
    private int first() {
        System.out.println("执行第一个方法");
        return rand.nextInt(100);
    }

    // 第二步
    private int second() {
        System.out.println("执行第二个方法");
        return rand.nextInt(100);
    }

    // 第一步
    private int third() {
        System.out.println("执行第三个方法");
        return rand.nextInt(100);
    }

    // 软件安装过程
    public void installWizard() {
        int first = this.first();
        // 根据first返回的结果，看是否需要执行second
        if (first > 50) {
            int second = this.second();
            if (second > 50) {
                int third = this.third();
                if (third > 50) {
                    this.first();
                }
            }
        }
    }
}
