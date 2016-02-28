package com.cheng.zenofdesignpatterns.patterns.interpreter;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.interpreter.calculator.Calculator;
import com.cheng.zenofdesignpatterns.patterns.interpreter.common.Context;
import com.cheng.zenofdesignpatterns.patterns.interpreter.common.Expression;

import java.util.HashMap;
import java.util.Stack;

public class ZoDPInterpreterActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("解释器模式");
        String content = "定义：\n" +
                "Given a language, define a representation for its grammar along with " +
                "an interpreter that uses the representation to interpret sentences in " +
                "the language.\n" +
                "给定一门语言，定义它的文法的一种表示，并定义一个解释器，该解释器使用该表示，" +
                "并定义一个解释器，该解释器使用该表示来解释语言中的句子。\n\n" +
                "解释器模式的优点\n" +
                "最显著的优点就是扩展性，修改语法规则只要修改相应的非终结符表达式就可以了，若扩" +
                "展语法，则只要增加非终结符类就可以了。\n" +
                "解释器模式的缺点\n" +
                "- 会引起类膨胀\n" +
                "- 采用递归调用方法\n" +
                "- 效率问题\n\n" +
                "使用场景\n" +
                "- 重复发生的问题可以使用解释器模式\n" +
                "- 一个简单语法需要解释的场景\n\n" +
                "注意事项\n" +
                "尽量不要在重要的模块中使用解释器模式，否则维护会是一个很大的问题。在项目中可以" +
                "使用shell、JRuby、Groovy等脚本语言来代替解释器模式，弥补Java编译型语言的不足。\n\n" +
                "最佳实践\n" +
                "解释器模式在实际的系统开发中使用得非常少，因为它会引起效率、性能以及维护等问题，" +
                "一般在大中型的框架项目能够找到它的身影，如一些数据分析工具、报表设计工具、科学计" +
                "算工具等，若你确实遇到‘一种特定类型的问题发生的频率足够高’的情况，准备使用解释器" +
                "模式时，可以考虑Expression4J、MESP（Math Expression String Parser），Jep等" +
                "开源的解析工具包。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟计算器加减法实现
        // 要求输入一个公式，然后输入参数，运行结果出来了
        String expStr = getExpStr();
        // 赋值
        HashMap<String, Integer> var = getValue(expStr);
        Calculator calculator = new Calculator(expStr);
        System.out.println("运算结果为：" + expStr + "=" + calculator.run(var));

        // 2. 通用解释器模式演示
        Context ctx = new Context();
        // 通常定一个语法容器，容纳一个具体的表达式，通常为ListArray,LinkedList,Stack等类型
        Stack<Expression> stack = null;
		/*
		for(;;){
			// 进行语法判断，并产生递归调用
		}
		*/
        // 产生一个完整的语法树，由各各个具体的语法分析进行解析
        Expression exp = stack.pop();
        // 具体元素进入场景
        exp.interpreter(ctx);
    }

    // 获得表达式
    private String getExpStr() {
        System.out.println("请输入表达式：");
        return "a+b-c";
    }

    // 获得值映射
    private HashMap<String, Integer> getValue(String expStr) {
        // 模式输入的数值
        String[] nums = {"100", "110", "121"};
        HashMap<String, Integer> map = new HashMap<>();
        int i = 0;
        // 解析有几个参数要传递
        for (char ch : expStr.toCharArray()) {
            if (ch!='+' && ch!='-') {
                if (!map.containsKey(String.valueOf(ch))) { // 解决重复参数的问题
                    System.out.println("请输入" + ch + "的值：");
                    String in = nums[i];
                    i = i++ % 3;
                    map.put(String.valueOf(ch), Integer.valueOf(in));
                }
            }
        }
        return map;
    }
}
