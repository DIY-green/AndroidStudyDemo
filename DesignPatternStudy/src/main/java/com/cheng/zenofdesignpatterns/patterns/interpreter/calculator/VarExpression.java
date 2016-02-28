package com.cheng.zenofdesignpatterns.patterns.interpreter.calculator;

import java.util.HashMap;

/**
 * 变量解析器，就是把变量和数值对应起来
 */
public class VarExpression extends Expression {

    private String key;

    public VarExpression(String _key) {
        this.key = _key;
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(this.key);
    }
}
