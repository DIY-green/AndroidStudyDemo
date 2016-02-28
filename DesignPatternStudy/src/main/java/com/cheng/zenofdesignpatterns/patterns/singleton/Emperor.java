package com.cheng.zenofdesignpatterns.patterns.singleton;

import java.util.ArrayList;
import java.util.Random;

/**
 * 固定数量的皇帝类
 */
public class Emperor {
    // 定义最多能产生的实例数量
    private static int maxNumOfEmperor = 2;
    // 每个皇帝都有名字，使用一个ArrayList来容纳，每个对象的私有属性
    private static ArrayList<String> nameList = new ArrayList<>();
    // 定义一个列表，容纳所有的皇帝实例
    private static ArrayList<Emperor> emperorList = new ArrayList<>();
    // 当前皇帝序列号
    private static int countNumOfEmperor = 0;
    // 产生所有的对象
    static {
        for (int i = 0; i < maxNumOfEmperor; i++) {
            emperorList.add(new Emperor("皇"+(i+1)+"帝"));
        }
    }
    private Emperor() {
        // 世俗和道德约束你，目的就是不产生第二个皇帝
    }
    // 传入皇帝名称，建立一个皇帝对象
    private Emperor(String _name) {
        nameList.add(_name);
    }
    // 随机获取一个皇帝对象
    public static Emperor getInstance() {
        Random random = new Random();
        // 随机拉出一个皇帝，只要是个精神领袖就成
        countNumOfEmperor = random.nextInt(maxNumOfEmperor);
        return emperorList.get(countNumOfEmperor);
    }
    // 皇帝发话了
    public void say() {
        System.out.println(nameList.get(countNumOfEmperor));
    }

    /**
     * 这种需要生成固定数量对象的模式就叫做有上限的多例模式，它是单例模式的一种扩展，
     * 采用有上限的多例模式，我们可以在设计时决定在内存中有多少个实例，方便系统进行
     * 扩展，修正单例可能存在的性能问题，提高系统的响应速度。例如读取文件，我们可以
     * 在系统启动时完成初始化工作，在内存中启动固定数量的reader实例，然后在需要读取
     * 文件时就可以快速响应
     */
}
