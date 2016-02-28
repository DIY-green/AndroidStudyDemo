package com.cheng.zenofdesignpatterns.patterns.abstractfactory;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.abstractfactory.common.AbstractCreator;
import com.cheng.zenofdesignpatterns.patterns.abstractfactory.common.AbstractProductA;
import com.cheng.zenofdesignpatterns.patterns.abstractfactory.common.AbstractProductB;
import com.cheng.zenofdesignpatterns.patterns.abstractfactory.common.Creator1;
import com.cheng.zenofdesignpatterns.patterns.abstractfactory.common.Creator2;
import com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa.AndrogyneFactory;
import com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa.FemaleFactory;
import com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa.Human;
import com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa.HumanFactory;
import com.cheng.zenofdesignpatterns.patterns.abstractfactory.nvwa.MaleFactory;

public class ZoDPAbstractFactoryActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("抽象工厂模式");
        String content = "定义：\n" +
                "Provide an interface for creating families of related or dependent " +
                "objects without specifying their concrete classes.\n" +
                "为创建一组相关或相互依赖的对象提供一个接口，而且无须指定它们的具体类。\n\n" +
                "抽象工厂模式的优点\n" +
                "- 封装性\n" +
                "每个产品的实现类不是高层模块要关心的，它要关心的是接口，是抽象，它不关心对象" +
                "是如何创建出来，这由谁负责呢？工厂类，只要知道工厂类是谁，我就能创建出一个需" +
                "要的对象，省时省力，优秀的设计就应该如此。\n" +
                "- 产品族内的约束为非公开状态\n" +
                "例如生产产品比例的问题上，具体的产品族内的约束是在工厂内实现的，这样的生产过" +
                "程对调用工厂类的高层模块来说是透明的，它不需要知道这个约束。\n" +
                "抽象工厂模式的缺点\n" +
                "最大缺点就是产品族扩展非常困难\n\n" +
                "使用场景\n" +
                "一个对象族（或是一组没有任何关系的对象）都有相同的约束，则可以使用抽象工厂模式。" +
                "什么意思呢？例如一个文本编辑器和一个图片处理器，都是软件实体，但是*nix下的文本" +
                "编辑器和Windows下的文本编辑器虽然功能和界面都相同，但是代码实现是不同的，图片" +
                "处理器也有类似情况。也就是具有了共同的约束条件：操作系统类型。于是我们可以使用" +
                "抽象工厂模式，产生不同操作系统下的编辑器和图片处理器。\n\n" +
                "注意事项\n" +
                "抽象工厂模式的缺点是产品族扩展比较困难，但是一定要清楚，是产品族扩展困难，而不" +
                "是产品等级。在该模式下，产品等级是非常容易扩展的，增加一个产品等级，只要增加一" +
                "个工厂类负责新增加出来的产品生产任务即可。也就是说横向扩展容易，纵向扩展困难。" +
                "以人类为例子，产品等级中只有男、女两个性别，现实世界还有一种性别：双性人，那我" +
                "们要扩展这个产品等级也是非常容易的，增加三个产品类，分别对应不同的肤色，然后再" +
                "创建一个工厂类，专门负责不同肤色的双性人的创建任务，完全通过扩展来实现需求的变" +
                "更，从这一点上看，抽象工厂模式是符合开闭原则的。\n\n" +
                "最佳实践\n" +
                "抽象工厂模式是一个简单的模式，使用的场景非常多，大家在软件产品开发过程中，涉及不" +
                "同操作系统的时候，都可以考虑使用抽象工厂模式，例如一个应用，需要在三个不同平台（" +
                "Windows、Linux、Android）上运行，可以通过抽象工厂模式屏蔽掉操作系统对应用的影响。" +
                "三个不同操作系统上的软件功能、应用逻辑、UI都应该是非常类似的，唯一不同的是调用不同" +
                "的工厂方法，由不同的产品类去处理与操作系统交互的信息。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟女娲造人
        // 第一条生产线，男性生产线
        HumanFactory maleHumanFactory = new MaleFactory();
        // 第二条生产线，女性生产线
        HumanFactory femaleHumanFactory = new FemaleFactory();
        // 生产线建立完毕，开始生产人了
        Human maleYellowHuman = maleHumanFactory.createYellowHuman();
        Human femaleYellowHuman = femaleHumanFactory.createYellowHuman();
        System.out.println("--生产一个黄色女性--");
        femaleYellowHuman.getColor();
        femaleYellowHuman.talk();
        femaleYellowHuman.getSex();
        System.out.println("--生产一个黄色男性--");
        maleYellowHuman.getColor();
        maleYellowHuman.talk();
        maleYellowHuman.getSex();

        Human maleWhiteHuman = maleHumanFactory.createWhiteHuman();
        Human femaleWhiteHuman = femaleHumanFactory.createWhiteHuman();
        System.out.println("--生产一个白色女性--");
        femaleWhiteHuman.getColor();
        femaleWhiteHuman.talk();
        femaleWhiteHuman.getSex();
        System.out.println("--生产一个白色男性--");
        maleWhiteHuman.getColor();
        maleWhiteHuman.talk();
        maleWhiteHuman.getSex();

        Human maleBlackHuman = maleHumanFactory.createBlackHuman();
        Human femaleBlackHuman = femaleHumanFactory.createBlackHuman();
        System.out.println("--生产一个黑色女性--");
        femaleBlackHuman.getColor();
        femaleBlackHuman.talk();
        femaleBlackHuman.getSex();
        System.out.println("--生产一个黑色男性--");
        maleBlackHuman.getColor();
        maleBlackHuman.talk();
        maleBlackHuman.getSex();

        // 2. 抽象工厂模式的通用写法演示
        // 定义出两个工厂
        AbstractCreator creator1 = new Creator1();
        AbstractCreator creator2 = new Creator2();

        // 产生A1对象
        AbstractProductA a1 = creator1.createProductA();
        //产生A2对象
        AbstractProductA a2 = creator2.createProductA();
        //产生B1对象
        AbstractProductB b1 = creator1.createProductB();
        //产生B2对象
        AbstractProductB b2 = creator2.createProductB();

        a1.shareMethod();
        a1.doSomething();
        a2.shareMethod();
        a2.doSomething();
        b1.shareMethod();
        b1.doSomething();
        b1.shareMethod();
        b2.doSomething();

        // 3. 模拟女娲造人 -- 扩展一个双性人
        // 生产线，双性人生产线
        HumanFactory androgyneHumanFactory = new AndrogyneFactory();
        // 生产线建立完毕，开始生产人了
        Human androgyneYellowHuman = androgyneHumanFactory.createYellowHuman();
        Human androgyneWhiteHuman = androgyneHumanFactory.createWhiteHuman();
        Human androgyneBlackHuman = androgyneHumanFactory.createBlackHuman();
        System.out.println("--生产一个黄色双性人--");
        androgyneYellowHuman.getColor();
        androgyneYellowHuman.talk();
        androgyneYellowHuman.getSex();
        System.out.println("--生产一个白色双性人--");
        androgyneWhiteHuman.getColor();
        androgyneWhiteHuman.talk();
        androgyneWhiteHuman.getSex();
        System.out.println("--生产一个黑色双性人--");
        androgyneBlackHuman.getColor();
        androgyneBlackHuman.talk();
        androgyneBlackHuman.getSex();
    }
}
