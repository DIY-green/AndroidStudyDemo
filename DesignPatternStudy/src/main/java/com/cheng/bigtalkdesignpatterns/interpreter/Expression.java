package com.cheng.bigtalkdesignpatterns.interpreter;

/**
 * 表达式类（AbstractExpression）
 */
public abstract class Expression {
    // 解释器
    public void interpret(PlayContext context) {
        if (context.getPlayText().length() == 0) {
            return;
        } else {
            String palyKey = context.getPlayText().substring(0, 1);
            context.setPlayText(context.getPlayText().substring(2));
            double playValue = Double.parseDouble(
                    context.getPlayText().substring(0, context.getPlayText().indexOf(" ")));
            context.setPlayText(context.getPlayText().substring(
                    context.getPlayText().indexOf(" ") + 1));
            execute(palyKey, playValue);
        }
    }

    // 执行
    public abstract void execute(String key, double value);
}
