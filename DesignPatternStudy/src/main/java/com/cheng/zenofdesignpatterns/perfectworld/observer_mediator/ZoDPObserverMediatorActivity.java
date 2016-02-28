package com.cheng.zenofdesignpatterns.perfectworld.observer_mediator;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;

public class ZoDPObserverMediatorActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("观察者模式 + 中介者模式");
        String content = "小结：\n" +
                "看看该案例中使用了哪些设计模式：\n" +
                "- 工厂方法模式\n" +
                "负责产生产品对象，方便产品的修改和扩展，并且实现了产品和工厂的紧耦合，避免产品随" +
                "意被创建而无触发事件的情况发生。\n" +
                "- 桥梁模式\n" +
                "在产品和事件两个对象的关系中使用了桥梁模式，如此设计后，两者都可以自由地扩展（前" +
                "提是需要抽取抽象化）而不会破坏原有的封装。\n" +
                "- 观察者模式\n" +
                "观察者模式解决了事件如何通知处理者的问题，而且观察者模式还有一个优点是可以有多个" +
                "观察者，也就是我们的架构是可以有多层级、多分类的处理者。想重新扩展一个新类型（新" +
                "接口）的观察者？没有问题，扩展ProductEvent即可。\n" +
                "- 中介者模式\n" +
                "事件有了，处理者也有了，这些都会发生变化，并且处理者之间也有耦合关系，中介者则可" +
                "以完美地处理这些复杂的关系。\n" +
                "再来思考一下，如果要扩展这个框架，可能还会用到什么模式？首先是责任链模式，它可以" +
                "帮助我们解决一个处理者处理多个事件的问题；其次是模板方法模式，处理者的启用、停用" +
                "等，都可以通过模板方法模式来实现；再次是装饰模式，事件的包装、处理者功能的强化都" +
                "会用到装饰模式。\n\n" +
                "单来源调用：\n" +
                "一个对象只能由固定的对象初始化的方法就叫做单来源调用（SingleCall）。\n" +
                "注意\n" +
                "采用单来源调用的两个对象一般是组合关系，两者有相同的生命期，它通常适用于有单例模" +
                "式和工厂方法模式的场景中。\n";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 使用观察者模式和中介者模式模拟事件触发器的开发
        // 获得事件分发中心
        EventDispatch dispatch = EventDispatch.getEventDispathc();
        // 接受乞丐对事件的处理
        dispatch.registerCustomer(new Beggar());
        // 接受平民对事件的处理
        dispatch.registerCustomer(new Commoner());
        // 接受贵族对该事件的处理
        dispatch.registerCustomer(new Nobleman());
        // 建立一个原子弹生产工厂
        ProductManager factory = new ProductManager();
        // 制造一个产品
        System.out.println("=====模拟创建产品事件========");
        System.out.println("创建一个叫做小男孩的原子弹");
        Product p = factory.createProduct("小男孩原子弹");
        // 修改一个产品
        System.out.println("\n=====模拟修改产品事件========");
        System.out.println("把小男孩原子弹修改为胖子号原子弹");
        factory.editProduct(p, "胖子号原子弹");
        // 再克隆一个原子弹
        System.out.println("\n=====模拟克隆产品事件========");
        System.out.println("克隆胖子号原子弹");
        factory.clone(p);
        // 销毁一个产品，销毁地点地球人都知道
        System.out.println("\n=====模拟销毁产品事件========");
        System.out.println("遗弃胖子号原子弹");
        factory.abandonProduct(p);
    }
}
