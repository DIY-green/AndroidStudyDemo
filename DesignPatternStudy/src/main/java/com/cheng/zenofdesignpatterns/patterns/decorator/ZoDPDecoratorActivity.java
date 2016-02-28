package com.cheng.zenofdesignpatterns.patterns.decorator;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.decorator.common.Component;
import com.cheng.zenofdesignpatterns.patterns.decorator.common.ConcreteComponent;
import com.cheng.zenofdesignpatterns.patterns.decorator.common.ConcreteDecorator1;
import com.cheng.zenofdesignpatterns.patterns.decorator.common.ConcreteDecorator2;
import com.cheng.zenofdesignpatterns.patterns.decorator.schoolreport.FouthGradeSchoolReport;
import com.cheng.zenofdesignpatterns.patterns.decorator.schoolreport.HighScoreDecorator;
import com.cheng.zenofdesignpatterns.patterns.decorator.schoolreport.SchoolReport;
import com.cheng.zenofdesignpatterns.patterns.decorator.schoolreport.SortDecorator;

public class ZoDPDecoratorActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("装饰模式");
        String content = "定义：\n" +
                "Attach additional responsibilities to an object dynamically keeping the " +
                "same interface.Decorators provide a flexible alternative to subclassing " +
                "for extending functionality.\n" +
                "动态地给一个对象添加一些额外的职责。就增加功能来说，装饰模式相比生成子类更为灵活。\n\n" +
                "装饰模式的优点\n" +
                "- 装饰类和被装饰类可以独立发展，而不会相互耦合\n" +
                "换句话说，Component类无须知道Decorator类，Decorator类是从外部来扩展Component" +
                "类的功能，而Decorator也不用知道具体的构件。\n" +
                "- 装饰模式是继承关系的一个替代方案\n" +
                "看装饰类Decorator，不管装饰多少层，返回的对象还是Component，实现的还是is-a的关系\n" +
                "- 装饰模式可以动态地扩展一个实现类的功能。\n" +
                "装饰模式的缺点\n" +
                "对于装饰模式记住一点就足够了：多层的装饰是比较复杂的。为什么会复杂呢？想想看，就像" +
                "剥洋葱一样，你剥到了最后才发现是最里层的装饰出现了问题，想象一个工作量吧，因此，尽" +
                "量减少装饰类的数量，以便降低系统的复杂度。\n\n" +
                "使用场景\n" +
                "- 需要扩展一个类的功能，或给一个类增加附加功能\n" +
                "- 需要动态地给一个对象增加功能，这些功能可以再动态地撤销\n" +
                "- 需要为一批的兄弟类进行改装或加装功能，当然是首选装饰模式\n\n" +
                "最佳实践\n" +
                "- 装饰模式是对继承的有力补充，装饰模式可以替代继承，解决类膨胀的问题\n" +
                "要知道继承是静态地给类增加功能，而装饰模式则是动态地增加功能。" +
                "- 装饰模式还有一个非常好的优点：扩展性非常好\n" +
                "通过装饰模式重新封装一个类，而不是通过继承来完成。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟要看成绩单然后签字
        // 成绩单拿过来
        SchoolReport sr;
        sr = new FouthGradeSchoolReport();  // 原装的成绩单
        // 加了最高分说明的成绩单
        sr = new HighScoreDecorator(sr);
        // 又加了成绩排名的说明
        sr = new SortDecorator(sr);
        // 看成绩单
        sr.report();
        // 一看，很开心，就签名了
        sr.sign("老三");

        // 2. 通用装饰模式演示
        Component component = new ConcreteComponent();
        // 第一次装饰
        component = new ConcreteDecorator1(component);
        // 第二次装饰
        component = new ConcreteDecorator2(component);
        // 装饰后运行
        component.operate();
    }
}
