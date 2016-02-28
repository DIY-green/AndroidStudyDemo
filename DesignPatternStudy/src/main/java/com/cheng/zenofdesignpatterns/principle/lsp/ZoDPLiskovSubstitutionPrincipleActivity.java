package com.cheng.zenofdesignpatterns.principle.lsp;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;

import java.util.HashMap;
import java.util.Map;

public class ZoDPLiskovSubstitutionPrincipleActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("里氏替换原则");
        String content = "定义：\n" +
                "If for each Object o1 of type S there is an Object o2 of type " +
                "T such that for all programs P defined in terms of T, the " +
                "behavior of P is unchanged when o1 is substituted for o2 then " +
                "S is a subtype of T.\n" +
                "如果对每一个类型为S的对象o1，都有类型为T的对象o2，使得以T定义的所有" +
                "程序P在所有的对象o1都替换成o2时，程序P的行为没有发生变化，那么类型S" +
                "是类型T的子类型。" +
                "里氏替换原则为良好的继承定义了一个规范，一句简单的定义包含了4层含义：\n" +
                "1.子类必须完全实现父类的方法\n" +
                "注意 在类中调用其他类时务必要使用父类或接口，如果不能使用父类或接口，则" +
                "说明类的设计已经违背了LSP原则\n" +
                "注意 如果子类不能完整地实现父类的方法，或者父类的某些方法在子类中已经发\n" +
                "生‘畸变’，则建议断开父子继承关系，采用依赖、聚集、组合等关系代替继承\n" +
                "2.子类可以有自己的个性\n" +
                "3.覆盖或实现父类的方法时输入参数可以被放大\n" +
                "4.覆写或实现父类的方法时输出结果可以被缩小\n\n" +
                "最佳实践：\n" +
                "在项目中，采用里氏替换原则时，尽量避免子类的‘个性’，一旦子类有‘个性’，这个" +
                "子类和父类之间的关系就很难调和了，把子类当做父类使用，子类的‘个性’被抹杀--" +
                "委屈了点；把子类单独作为一个业务来使用，则会让代码间的耦合关系变得扑朔迷离--" +
                "缺乏类替换的标准。";
        mContentTV.setText(content);
    }

    public void onClick(View v) {
        // 父类存在的地方，子类就应该能够存在
        Father f = new Father();
        HashMap map1 = new HashMap();
        Map map11 = new HashMap();
        f.doSomething(map1);
        f.doSomething2(map11);

        // 替换为子类
        Son s = new Son();
        HashMap map2 = new HashMap();
        Map map22 = new HashMap();
        s.doSomething(map2);
        s.doSomething2(map22);

    }
}
