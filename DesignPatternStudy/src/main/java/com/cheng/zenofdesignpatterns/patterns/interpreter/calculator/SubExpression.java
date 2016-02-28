package com.cheng.zenofdesignpatterns.patterns.interpreter.calculator;

import java.util.HashMap;

/**
 * 减法表达式解析
 */
public class SubExpression extends SymbolExpression {

    public SubExpression(Expression _left, Expression _right) {
        super(_left, _right);
    }

    // 解析就是减法运算
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var) - super.right.interpreter(var);
    }
}
