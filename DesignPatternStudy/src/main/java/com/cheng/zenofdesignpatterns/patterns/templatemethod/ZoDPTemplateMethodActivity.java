package com.cheng.zenofdesignpatterns.patterns.templatemethod;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.templatemethod.common.AbstractClass;
import com.cheng.zenofdesignpatterns.patterns.templatemethod.common.ConcreteClass1;
import com.cheng.zenofdesignpatterns.patterns.templatemethod.common.ConcreteClass2;
import com.cheng.zenofdesignpatterns.patterns.templatemethod.hookmethod.HMHummerH1Model;
import com.cheng.zenofdesignpatterns.patterns.templatemethod.hookmethod.HMHummerH2Model;
import com.cheng.zenofdesignpatterns.patterns.templatemethod.section1.HummerH1Model;
import com.cheng.zenofdesignpatterns.patterns.templatemethod.section1.HummerModel;

public class ZoDPTemplateMethodActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("模板方法模式");
        String content = "定义：\n" +
                "Define the skeleton of an algorithm in an operation,deferring some steps " +
                "to subclasses.Template Method lets subclasses redefine certain steps of " +
                "an algorithm without changing the algorithm's structure.\n" +
                "定义一个操作中的算法的框架，而将一些步骤延迟到子类中。使得子类可以不改变一个" +
                "算法的结构即可重定义该算法的某些特定步骤。\n\n" +
                "模板方法模式的优点\n" +
                "封装不变部分，扩展可变部分\n" +
                "- 把认为是不变的部分的算法封装到父类实现，而可变部分的则可以通过继承来继续扩展\n" +
                "- 提取公共部分代码，便于维护\n" +
                "- 行为由父类控制，子类实现\n" +
                "模板方法模式的缺点\n" +
                "按照我们的设计习惯，抽象类负责声明最抽象、最一般的事务属性和方法，实现类完成具体\n" +
                "的事物属性和方法。但是模板方法模式却颠倒了，抽象类定义了部分抽象方法，由子类实现，\n" +
                "子类执行的结果影响了父类的结果，也就是子类对父类产生了影响，这在复杂的项目中，会\n" +
                "带来代码阅读的难度，而且也会让新手产生不适感。\n\n" +
                "使用场景\n" +
                "- 多个子类有公有的方法，并且逻辑基本相同时\n" +
                "- 重要、复杂的算法，可以把核心算法设计为模板方法，周边的相关细节功能则由各个子类实现\n" +
                "- 重构时，模板方法模式是一个经常使用的模式，把相同的代码抽取到父类中，然后通过" +
                "钩子函数约束其行为\n\n" +
                "最佳实践\n" +
                "模板方法在一些开源框架中应用非常多，它提供了一个抽象类，然后开源框架写了一堆子类。\n" +
                "在《xxx In Action》中就说明了，如果你需要扩展功能，可以继承这个抽象类，然后覆写\n" +
                "protected方法，再然后就是调用一个类似execute方法，就完成你的扩展开发，非常容易\n" +
                "扩展的一种模式。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. Section1
        //牛叉公司要H1型号的悍马
        HummerModel h1 = new HummerH1Model();
        //H1模型演示
        h1.run();

        // 2. 通用模板方法模式演示
        AbstractClass class1 = new ConcreteClass1();
        AbstractClass class2 = new ConcreteClass2();
        //调用模版方法
        class1.templateMethod();
        class2.templateMethod();

        // 3. 钩子方法演示
        System.out.println("-------H1型号悍马--------");
        System.out.println("H1型号的悍马是否需要喇叭声响？0-不需要   1-需要");
        String type = "1";
        HMHummerH1Model h11 = new HMHummerH1Model();
        if(type.equals("0")){
            h11.setAlarm(false);
        }
        h11.run();
        System.out.println("\n-------H2型号悍马--------");
        HMHummerH2Model h22 = new HMHummerH2Model();
        h22.run();
    }
}
