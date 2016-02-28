package com.cheng.zenofdesignpatterns.principle.dip;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;

public class ZoDPDependenceInversionPrincipleActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("依赖倒置原则");
        String content = "定义：\n" +
                "High level modules should not depend upon low level modules." +
                "Both should depend upon abstractions.Abstractions should not " +
                "depend upon details.Details should depend upon abstractions.\n" +
                "翻译过来，包含三层含义：\n" +
                "- 高层模块不应该依赖低层模块，两者都应该依赖其抽象\n" +
                "- 抽象不应该依赖细节\n" +
                "- 细节应该依赖抽象\n" +
                "依赖倒置原则在Java语言中的表现就是：\n" +
                "- 模块间的依赖通过抽象发生，实现类之间不发生直接的依赖关系，其依赖关" +
                "系是通过接口或抽象类产生的\n" +
                "- 接口或抽象类不依赖于实现类\n" +
                "- 实现类依赖接口或抽象类\n\n" +
                "最佳实践\n" +
                "依赖倒置原则的本质就是通过抽象（接口或抽象类）使各个类或模块的实现彼此" +
                "独立，不互相影响，实现模块间的松耦合，在项目中只要遵循以下的几个规则就可以：\n" +
                "- 每个类尽量都有接口或抽象类，或者抽象类和接口两者都具备\n" +
                "这是依赖倒置的基本要求，接口和抽象都是属于抽象的，有了抽象才可能依赖倒置\n" +
                "- 变量的表面类型尽量是接口或者抽象类\n" +
                "- 任何类都不应该从具体类派生\n" +
                "- 尽量不要覆写基类的方法\n" +
                "如果基类是一个抽象类，而且这个方法已经实现了，子类尽量不要覆写。类间依赖" +
                "的是抽象，覆写了抽象方法，对依赖的稳定性产生一定的影响\n" +
                "- 结合里氏替换原则使用\n\n" +
                "可以得出一个通俗的规则：接口负责定义public属性和方法，并且声明与其他对象的" +
                "依赖关系，抽象类负责公共构造部分的实现，实现类准确的实现业务逻辑，同时在适当" +
                "的时候对父类进行细化。";
        mContentTV.setText(content);
    }

    public void onClick(View v) {
        IDriver zhangSan = new Driver();
        ICar benz = new Benz();
        // 张三开奔驰车
        zhangSan.drive(benz);
        ICar bwm = new BWM();
        // 张三开宝马车
        zhangSan.drive(bwm);
    }
}
