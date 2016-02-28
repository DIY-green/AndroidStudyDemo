package com.cheng.zenofdesignpatterns.patternpk.creational;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory.BenzFactory;
import com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory.CarFactory;
import com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.abstractfactory.ICarAF;
import com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.builder.CarDirector;
import com.cheng.zenofdesignpatterns.patternpk.creational.abstractfactory_vs_builder.builder.ICarBuilder;
import com.cheng.zenofdesignpatterns.patternpk.creational.factory_vs_builder.builder.SuperManDirector;
import com.cheng.zenofdesignpatterns.patternpk.creational.factory_vs_builder.builder.SuperMan;
import com.cheng.zenofdesignpatterns.patternpk.creational.factory_vs_builder.factory.ISuperMan;
import com.cheng.zenofdesignpatterns.patternpk.creational.factory_vs_builder.factory.SuperManFactory;

public class ZoDPCreationalPKActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("创建型模式大PK");
        String content = "包括：\n" +
                "简单工厂模式（Simple Factory）\n" +
                "工厂方法模式（Factory Method）\n" +
                "抽象工厂模式（Abstract Factory）\n" +
                "创建者模式（Builder）\n" +
                "原型模式（Prototype）\n" +
                "单例模式（Singleton）\n\n" +
                "1. 工厂方法模式 VS 建造者模式\n" +
                "工厂方法模式注重的是整体对象的创建方法，而建造者模式注重的是部件构建的过程，旨在" +
                "通过一步一步地精确构造创建出一个复杂的对象。举个简单的例子来说明两者的差异，如要" +
                "制造一个超人，如果使用工厂方法模式，直接生产出来的就是一个力大无穷、能够飞翔、内" +
                "裤外穿的超人；而如果使用建造者模式，则需要组装手、头、脚、躯干等部分，然后再把内" +
                "裤外穿，于是一个超人就诞生了。\n" +
                "可以看到，在建立超人的过程中，建造者必须关注超人的各个部件，而工厂方法模式则只关" +
                "注超人的整体，这就是两者的区别。\n\n" +
                "2. 最佳实践\n" +
                "工厂方法模式和建造者模式都属于对象创建类模式，都用来创建类的对象。但它们之间的区" +
                "别还是比较明显的。\n" +
                "- 意图不同\n" +
                "在工厂方法模式里，我们关注的是一个产品整体，如超人整体，无须关心产品的各部分是如" +
                "何创建出来的；但在建造者模式中，一个具体产品的生产是依赖各个部件的产生以及装配顺" +
                "序，它关注的是‘由零件一步一步地组装出产品对象’。简单地说，工厂模式是一个对象创建" +
                "的粗线条应用，建造者模式则是通过细线条勾勒出一个复杂对象，关注的是产品组成部分的" +
                "创建过程。\n" +
                "- 产品的复杂度不同\n" +
                "工厂方法模式创建的产品一般都是单一性质产品，如成年超人，都是一个模样，而建造者模" +
                "式创建的则是一个复合产品，它由各个部件复合而成，部件不同产品对象当然不同。这不是" +
                "说工厂方法模式创建的对象简单，而是指它们的粒度大小不同。一般来说，工厂方法模式的" +
                "粒度比较粗，建造者模式的产品对象粒度比较细。\n" +
                "两者的区别有了，那在具体的应用中，我们该如何选择呢？是用工厂方法模式来创建对象，" +
                "还是用建造者模式来创建对象，这完全取决于我们在做系统设计时的意图，如果需要详细" +
                "关注一个产品部件的生产、安装步骤，则选择建造者，否则选择工厂方法模式。\n\n" +
                "3. 抽象工厂模式 VS 建造者模式\n" +
                "抽象工厂模式实现对产品家族的创建，一个产品家族是这样的一个系列产品：具有不同分" +
                "类维度的产品组合，采用抽象工厂模式则是不需要关心构建过程，只关心什么产品由什么" +
                "工厂生产即可。而建造者模式则是要求按照指定的蓝图建造产品，它的主要目的是通过组" +
                "装零配件而产生一个新产品，两者的区别还是比较明显的，下面通过一个例子来说明。\n" +
                "现代化的汽车工厂能够批量生产汽车（不考虑手工打造的豪华车）。不同的工厂生产不同" +
                "的汽车，宝马工厂生产宝马牌子的车，奔驰工厂生产奔驰牌子的车。车不仅具有不同品牌，" +
                "还有不同的用途分类，如商务车Van，运动型车SUV等，按照两种设计模式分别实现车辆的" +
                "生产过程。\n\n" +
                "4. 最佳实践\n" +
                "注意看上面的描述，在抽象工厂模式中使用‘工厂’来描述构建者，而在建造者模式中使用‘" +
                "车间’来描述构建者，其实已经在说它们两者的区别了，抽象工厂模式就好比是一个一个的" +
                "工厂，宝马车工厂生产宝马SUV和宝马VAN，奔驰车工厂生产奔驰SUV和奔驰VAN，它是从一" +
                "个更高层次去看对象的构建，具体到工厂内部还有很多的车间，如制造引擎的车间、装配引" +
                "擎的车间等，但这些都是隐藏在工厂内部的细节，对外不公布。也就是对领导者来说，他只" +
                "关心一个工厂到底是生产什么产品的，不用关心具体怎么生产。而建造者模式就不同了，它" +
                "是由车间组成，不同的车间完成不同的创建和装配任务，一个完整的汽车生产过程需要引擎" +
                "制造车间、引擎装配车间的配合才能完成，它们配合的基础就是设计蓝图，而这个蓝图是掌" +
                "握在车间主任（导演类）手中，它给建造车间什么蓝图就能生产什么产品，建造者模式更关" +
                "心建造过程。虽然从外界看来一个车间还是生产车辆，但是这个车间的转型是非常快的，只" +
                "要重新设计一个蓝图，即可产生不同的产品，这有赖于建造者模式的功劳。\n" +
                "相对来说，抽象工厂模式比建造者模式的尺度要大，它关注产品整体，而建造者模式关注构" +
                "建过程，因此建造者模式可以很容易地构建出一个崭新的产品，只要导演类能够提供具体的" +
                "工艺流程。也正因为如此，两者的应用场景截然不同，如果希望屏蔽对象的创建过程，只提" +
                "供一个封装良好的对象，则可以选择抽象工厂方法模式。而建造者模式可以用在构件的装配" +
                "方面，如通过装配不同的组件或者相同组件的不同顺序，可以产生一个新的对象，它可以产" +
                "生一个非常灵活的架构，方便地扩展和维护系统。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 使用工厂方法生产超人
        // 生产一个成年超人
        ISuperMan adultSuperMan = SuperManFactory.createSuperMan("adult");
        // 展示一下超人的技能
        adultSuperMan.specialTalent();
        // 生产一个未成年超人
        ISuperMan childSuperMan = SuperManFactory.createSuperMan("child");
        // 展示一下超人的技能
        childSuperMan.specialTalent();

        // 2. 使用建造者模式生产超人
        // 建造一个成年超人
        SuperMan adultSuperMan2 = SuperManDirector.getAdultSuperMan();
        // 展示一下超人的信息
        adultSuperMan2.getSpecialTalent();
        // 建造一个未成年超人
        SuperMan childSuperMan2 = SuperManDirector.getChildSuperMan();
        // 展示一下超人的信息
        childSuperMan2.getSpecialTalent();

        // 3. 使用抽象工厂生产汽车
        // 给我生产一辆奔驰SUV
        System.out.println("===要求生产一辆奔驰SUV===");
        // 首先找到生产奔驰车的工厂
        System.out.println("A、找到奔驰车工厂");
        CarFactory carFactory= new BenzFactory();
        // 开始生产奔驰SUV
        System.out.println("B、开始生产奔驰SUV");
        ICarAF benzSuv = carFactory.createSuv();
        // 生产完毕，展示一下车辆信息
        System.out.println("C、生产出的汽车如下：");
        System.out.println("汽车品牌："+benzSuv.getBand());
        System.out.println("汽车型号：" + benzSuv.getModel());

        // 4. 使用建造者模式生产汽车
        // 定义出导演类
        CarDirector carDirector =new CarDirector();
        // 给我一辆奔驰车SUV
        System.out.println("===制造一辆奔驰的SUV===");
        ICarBuilder benzSuv2 = carDirector.createBenzSuv();
        System.out.println(benzSuv2);
        // 给我一辆宝马的商务车
        System.out.println("\n===制造一辆宝马的商务车===");
        ICarBuilder bmwVan2 = carDirector.createBMWVan();
        System.out.println(bmwVan2);
        // 给我一辆混合车型
        System.out.println("\n===制造一辆混合车===");
        ICarBuilder complexCar2 = carDirector.createComplexCar();
        System.out.println(complexCar2);
    }

}
