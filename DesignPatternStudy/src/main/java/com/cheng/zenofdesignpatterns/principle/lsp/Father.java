package com.cheng.zenofdesignpatterns.principle.lsp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/25.
 */
public class Father {

    public Collection doSomething(HashMap map) {
        System.out.println("父类被执行了...1");
        return map.values();
    }

    public Collection doSomething2(Map map) {
        System.out.println("父类被执行了...2");
        return map.values();
    }
}
