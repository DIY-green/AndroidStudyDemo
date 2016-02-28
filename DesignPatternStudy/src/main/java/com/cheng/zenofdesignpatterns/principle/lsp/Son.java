package com.cheng.zenofdesignpatterns.principle.lsp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/25.
 */
public class Son extends Father {

    // 放大输入参数类型(这里不是覆写，是重载)
    public Collection doSomething(Map map) {
        System.out.println("子类被执行...1");
        return map.values();
    }

    // 缩小输出参数类型
    public Collection doSomething2(HashMap map) {
        System.out.println("子类被执行了...2");
        return map.values();
    }
}
