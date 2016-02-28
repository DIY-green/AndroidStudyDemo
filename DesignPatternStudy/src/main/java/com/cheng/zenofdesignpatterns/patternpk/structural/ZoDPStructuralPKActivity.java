package com.cheng.zenofdesignpatterns.patternpk.structural;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patternpk.structural.decorator_vs_adapter.adapter.AUglyDuckling;
import com.cheng.zenofdesignpatterns.patternpk.structural.decorator_vs_adapter.adapter.Duck;
import com.cheng.zenofdesignpatterns.patternpk.structural.decorator_vs_adapter.adapter.Duckling;
import com.cheng.zenofdesignpatterns.patternpk.structural.decorator_vs_adapter.decorator.BeautifyAppearance;
import com.cheng.zenofdesignpatterns.patternpk.structural.decorator_vs_adapter.decorator.StrongBehavior;
import com.cheng.zenofdesignpatterns.patternpk.structural.decorator_vs_adapter.decorator.Swan;
import com.cheng.zenofdesignpatterns.patternpk.structural.decorator_vs_adapter.decorator.DUglyDuckling;
import com.cheng.zenofdesignpatterns.patternpk.structural.proxy_vs_decorator.decorator.DRunner;
import com.cheng.zenofdesignpatterns.patternpk.structural.proxy_vs_decorator.decorator.IDRunner;
import com.cheng.zenofdesignpatterns.patternpk.structural.proxy_vs_decorator.decorator.RunnerWithJet;
import com.cheng.zenofdesignpatterns.patternpk.structural.proxy_vs_decorator.proxy.IPRunner;
import com.cheng.zenofdesignpatterns.patternpk.structural.proxy_vs_decorator.proxy.PRunner;
import com.cheng.zenofdesignpatterns.patternpk.structural.proxy_vs_decorator.proxy.RunnerAgent;

public class ZoDPStructuralPKActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("结构类模式PK");
        String content = "包括：\n" +
                "外观模式（Facade）\n" +
                "适配器模式（Adapter）\n" +
                "代理模式（Proxy）\n" +
                "装饰模式（Decorator）\n" +
                "桥梁模式（Bridge）\n" +
                "组合模式（Composite）\n" +
                "享元模式（Flyweight）\n" +
                "为什么叫结构类模式呢？因为它们都是通过组合类或对象产生更大结构以适应更高层次的" +
                "逻辑需求。\n\n" +
                "1. 代理模式 VS 装饰模式\n" +
                "对于两个模式，首先要说的是，装饰模式就是代理模式的一个特殊应用，两者的共同点是" +
                "都具有相同的接口，不同点则是代理模式着重对代理过程的控制，而装饰模式则是对类的" +
                "功能进行加强或减弱，它着重类的功能变化。\n" +
                "一个著名的短跑运动员有自己的代理人。如果你很仰慕他，你找运动员说“你跑个我看看”，" +
                "运动员肯定不搭理你，不过你找到他的代理人就不一样了，你可能和代理人比较熟，可以" +
                "称兄道弟，这个代理人还是可以帮的，于是代理人同意让你欣赏运动员的练习赛。\n" +
                "装饰模式是对类功能的加强，装饰类对类的行为没有决定权，只有增强作用，也就是说它" +
                "不决定被代理的方法是否执行，它只是再次增加被代理的功能。\n\n" +
                "2. 最佳实践\n" +
                "代理模式是把当前的行为或功能委托给其他对象执行，代理类负责接口限定：是否可以调" +
                "用真实角色，以及是否对发送到真实角色的消息进行变形处理，它不对被主题角色（也就" +
                "是被代理类）的功能做任何处理，保证原汁原味的调用。代理模式使用到极致开发就是AOP，" +
                "这是各位采用Spring框架开发必然使用到的技术，它就是使用了代理和反射的技术。\n" +
                "装饰模式是在要保证接口不变的情况下加强类的功能，它保证的是被修饰的对象功能比原" +
                "始对象丰富（当然，也可以减弱），但不做准入条件判断和准入参数过滤，如是否可以执" +
                "行累的功能，过滤输入参数是否合规等，这不是装饰模式关心的。\n\n" +
                "3. 装饰模式 VS 适配器模式\n" +
                "装饰模式和适配器模式在通用类图上没有太多的相似点，差别比较大，但是它们的功能有" +
                "相似的地方：都是包装作用，都是通过委托方式实现其功能。不同点是：装饰模式包装的" +
                "是自己的兄弟类，隶属于同一个家族（相同接口或父类），适配器模式则修饰非血缘关系" +
                "类，把一个非本家族的对象伪装成本家族的对象，注意是伪装，因为它的本质还是非相同" +
                "接口的对象。\n\n" +
                "4. 最佳实践\n" +
                "装饰模式和适配器模式有较多的不同点\n" +
                "- 意图不同\n" +
                "装饰模式的意图是加强对象的功能，而适配器模式关注的则是转化，它的主要意图是两个" +
                "不同对象之间的转化。\n" +
                "- 施与对象不同\n" +
                "装饰模式装饰的对象必须是自己的同宗，也就是相同的接口或父类，只要在具有相同的属" +
                "性和行为的情况下，才能比较行为是增强还是减弱；适配器模式则必须是两个不同的对象，" +
                "因为它着重于转换，只有两个不同的对象才有转换的必要，如果是相同对象还转换什么？\n" +
                "- 场景不同\n" +
                "装饰模式在任何时候都可以使用，只要是增强类的功能，而适配器模式则是一个补救模式，" +
                "一般出现在系统成熟或已经构建完毕的项目中，作为一个紧急处理手段采用。\n" +
                "- 扩展性不同\n" +
                "装饰模式很容易扩展，适配器模式就没那么容易扩展了。\n";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 使用代理模式模拟运动员跑步
        // 定义一个短跑运动员
        IPRunner liuP = new PRunner();
        // 定义liu的代理人
        IPRunner agent = new RunnerAgent(liuP);
        // 要求运动员跑步
        System.out.println("====客人找到运动员的代理要求其去跑步===");
        agent.run();

        // 2. 使用装饰模式增强运动员
        // 定义出运动员
        IDRunner liuD = new DRunner();
        //对其功能加强
        liuD = new RunnerWithJet(liuD);
        // 看看它的跑步情况如何
        System.out.println("===增强后的运动员的功能===");
        liuD.run();

        // 3. 使用装饰模式模拟丑小鸭变成白天鹅
        // 很久很久以前，这里有一个丑陋的小鸭子
        System.out.println("===很久很久以前，这里有一只丑陋的小鸭子===");
        Swan duckling = new DUglyDuckling();
        // 展示一下小鸭
        duckling.desAppaearance();  //小鸭子的外形
        duckling.cry();  //小鸭子的叫声
        duckling.fly();  //小鸭子的行为

        System.out.println("\n===小鸭子终于发现自己是一只天鹅====");
        // 首先外形变化了
        duckling = new BeautifyAppearance(duckling);
        // 其次行为也发生了改变
        duckling = new StrongBehavior(duckling);
        // 虽然还是叫丑小鸭，但是已经发生了很大变化
        duckling.desAppaearance();  //小鸭子的新外形
        duckling.cry();  //小鸭子的叫声
        duckling.fly();  //小鸭子的新行为

        // 4. 使用适配器演示丑小鸭变成白天鹅
        //鸭妈妈有五个孩子，其中四个都是一个模样
        System.out.println("===妈妈有五个孩子，其中四个模样是这样的：===");
        Duck duck = new Duckling();
        duck.cry();  // 小鸭子的叫声
        duck.desAppearance(); // 小鸭子的外形
        duck.desBehavior(); // 小鸭子的其他行为
        System.out.println("\n===一只独特的小鸭子，模样是这样的：===");
        Duck uglyDuckling = new AUglyDuckling();
        uglyDuckling.cry(); // 丑小鸭的叫声
        uglyDuckling.desAppearance(); // 丑小鸭的外形
        uglyDuckling.desBehavior(); // 丑小鸭的其他行为
    }
}
