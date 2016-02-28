package com.cheng.zenofdesignpatterns.patterns.factorymethod;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.common.ConcreteCreator;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.common.ConcreteProduct1;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.common.ConcreteProduct2;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.common.Creator;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.common.Product;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.lazyinitialization.LazyProductFactory;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.multi.BlackHumanFactory;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.multi.WhiteHumanFactory;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.multi.YellowHumanFactory;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.AbstractHumanFactory;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.BlackHuman;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.Human;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.HumanFactory;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.WhiteHuman;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.nvwa.YellowHuman;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.replacesingleton.Singleton;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.replacesingleton.SingletonFactory;
import com.cheng.zenofdesignpatterns.patterns.factorymethod.simple.SimpleHumanFactory;

public class ZoDPFactoryMethodActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("工厂方法模式");
        String content = "定义：\n" +
                "Define an interface for creating an object,but let subclasses decide " +
                "which class to instantiate.Factory Method lets a class defer instantiation " +
                "to subclasses.\n" +
                "定义一个用于创建对象的接口，让子类决定实例化哪个类。工厂方法使一个类的实例化" +
                "延迟到其子类。\n\n" +
                "工厂方法模式的优点\n" +
                "- 首先，良好的封装性，代码结构清晰\n" +
                "- 其次，工厂方法模式的扩展性非常优秀\n" +
                "- 再次，屏蔽产品类\n" +
                "- 最后，工厂方法模式是典型的解耦框架\n" +
                "高层模块只需要知道产品的抽象类，其他的实现类都不用关心，符合迪米特法则，我不需" +
                "要的就不要去交流；也符合依赖倒置原则，只依赖产品类的抽象；当然也符合里氏替换原" +
                "则，使用产品子类替换产品父类，没问题。\n\n" +
                "使用场景\n" +
                "- 首先，工厂方法模式是new一个对象的替代品\n" +
                "在所有需要生成对象的地方都可以使用，但是需要慎重地考虑是否要增加一个工厂类进行" +
                "管理，增加代码的复杂度。\n" +
                "- 其次，需要灵活的、可扩展的框架时，可以考虑采用工厂方法模式\n" +
                "- 再次，工厂方法模式可以用在异构项目中\n" +
                "- 最后，可以使用在测试驱动开发的框架下（现在可以直接考虑使用JMock或EasyMock）\n\n" +
                "工厂方法模式的扩展\n" +
                "1. 缩小为简单工厂模式\n" +
                "2. 升级为多个工厂\n" +
                "3. 代替单例模式\n" +
                "4. 延迟初始化\n\n" +
                "最佳实践\n" +
                "该模式几乎尽人皆知，但不是每个人都能用得好。熟能生巧，熟练掌握该模式，多思考工厂" +
                "方法如何应用，而且工厂方法模式还可以与其他模式混合使用（例如模板方法模式、单例模" +
                "式、原型模式等），变化出无穷的优秀设计。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟女娲造人
        // 声明阴阳八卦炉
        AbstractHumanFactory yinYanLv = new HumanFactory();
        // 女娲第一次造人，火候不足，于是白人产生了
        System.out.println("--造出的第一批人是白色人种--");
        Human whiteHuman = yinYanLv.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();
        // 女娲第二次造人，火候过足，于是黑人产生了
        System.out.println("--造出的第二批人是黑色人种--");
        Human blackHuman = yinYanLv.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();
        // 女娲第三次造人，火候刚刚好，于是黄色人种产生了
        System.out.println("--造出的第三批人是黄色人种--");
        Human yellowHuman = yinYanLv.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();

        // 2. 通用工厂方法模式演示
        Creator creator = new ConcreteCreator();
        Product product1 = creator.createProduct(ConcreteProduct1.class);
        Product product2 = creator.createProduct(ConcreteProduct2.class);
        product1.method1();
        product1.method2();
        product2.method1();
        product2.method2();

        // 3. 简单工厂模式演示
        System.out.println("--造出的第一批人是白色人种--");
        Human whiteHuman1 = SimpleHumanFactory.createHuman(WhiteHuman.class);
        whiteHuman1.getColor();
        whiteHuman1.talk();
        System.out.println("--造出的第二批人是黑色人种--");
        Human blackHuman1 = SimpleHumanFactory.createHuman(BlackHuman.class);
        blackHuman1.getColor();
        blackHuman1.talk();
        System.out.println("--造出的第三批人是黄色人种--");
        Human yellowHuman1 = SimpleHumanFactory.createHuman(YellowHuman.class);
        yellowHuman1.getColor();
        yellowHuman1.talk();

        // 4. 多个工厂类演示
        System.out.println("--造出的第一批人是白色人种--");
        Human whiteHuman2 = new WhiteHumanFactory().createHuman();
        whiteHuman2.getColor();
        whiteHuman2.talk();
        System.out.println("--造出的第二批人是黑色人种--");
        Human blackHuman2 = new BlackHumanFactory().createHuman();
        blackHuman2.getColor();
        blackHuman2.talk();
        System.out.println("--造出的第三批人是黄色人种--");
        Human yellowHuman2 = new YellowHumanFactory().createHuman();
        yellowHuman2.getColor();
        yellowHuman2.talk();

        // 5. 替代单例模式
        Singleton singleton = SingletonFactory.getSingleton();
        singleton.doSomething();

        // 6. 延迟初始化
        Product product21 = LazyProductFactory.createProduct("Product1");
        Product product22 = LazyProductFactory.createProduct("Product2");
        product21.method1();
        product21.method2();
        product22.method1();
        product22.method2();
    }
}
