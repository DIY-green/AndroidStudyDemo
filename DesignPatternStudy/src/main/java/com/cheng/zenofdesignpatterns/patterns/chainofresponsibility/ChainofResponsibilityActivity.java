package com.cheng.zenofdesignpatterns.patterns.chainofresponsibility;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.ancientfemale.Father;
import com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.ancientfemale.WomanHandler;
import com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.ancientfemale.Husband;
import com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.ancientfemale.IWomen;
import com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.ancientfemale.Son;
import com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.ancientfemale.Women;
import com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.common.ConcreteHandler1;
import com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.common.ConcreteHandler2;
import com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.common.ConcreteHandler3;
import com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.common.AbstractHandler;
import com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.common.Request;
import com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.common.Response;

import java.util.ArrayList;
import java.util.Random;

public class ChainofResponsibilityActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("责任链模式");
        String content = "定义：\n" +
                "Avoid coupling the sender of a request to its receiver by giving more than " +
                "one object a chance to handler the request. Chain the receiving objects and " +
                "pass the request along the chain until an object handles it.\n" +
                "使多个对象都有机会处理请求，从而避免了请求的发送者和接受者之间的耦合关系。将这些对象" +
                "连成一条链，并沿着这条链传递该请求，直到有对象处理它为止。\n\n" +
                "责任链模式的优点\n" +
                "非常显著的优点是将请求和处理分开。请求者可以不用知道是谁处理的，处理者可以不用知道请" +
                "求的全貌，两者解耦，提高系统的灵活性。\n" +
                "责任链模式的缺点\n" +
                "有两个非常显著的缺点：一是性能问题，每个请求都是从链头遍历到链尾，特别是在链比较长的" +
                "时候，性能是一个非常大的问题。二是调试不是很方便，特别是链条比较长，环节比较多的时候，" +
                "由于采用了类似递归的方式，调试的时候逻辑可能比较复杂。\n\n" +
                "注意事项\n" +
                "链中节点数量需要控制，避免出现超长链的情况，一般的做法是在Handler中设置一个最大节点" +
                "数量，在setNext方法中判断是否已经是超过其阈值，超过则不允许该链建立，避免无意识地破" +
                "坏系统性能。\n\n" +
                "最佳实践\n" +
                "在例子和通用源码中Handler是抽象类，融合了模板方法模式，每个实现类只要实现两个方法：" +
                "echo方法处理请求和getHandleLevel获得处理级别，想想单一职责原则和迪米特法则吧，通过" +
                "融合模板方法模式，各个实现类只要关注自己的业务逻辑就成了，至于说什么事要自己处理，那" +
                "就让父类去决定好了，也就是说父类实现了请求传递的功能，子类实现请求的处理，符合单一职" +
                "责原则，各个类只完成一个动作或逻辑，也就是只有一个原因引起类的改变，建议在使用的时候" +
                "用这种方法，好处非常明显了，子类的实现非常简单，责任链的建立也是非常灵活的。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟古代女性的三从四德
        // 随机挑选几个女性
        Random rand = new Random();
        ArrayList<IWomen> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList.add(new Women(rand.nextInt(4), "我要出去逛街"));
        }
        // 定义三个请示对象
        WomanHandler father = new Father();
        WomanHandler husband = new Husband();
        WomanHandler son = new Son();
        // 设置请示顺序
        father.setNext(husband);
        husband.setNext(son);
        for (IWomen women : arrayList) {
            father.handleMessage(women);
        }

        // 2. 通用责任链模式演示
        // 声明出所有的处理节点
        AbstractHandler handler1 = new ConcreteHandler1();
        AbstractHandler handler2 = new ConcreteHandler2();
        AbstractHandler handler3 = new ConcreteHandler3();
        // 设置链中的阶段顺序,1-->2-->3
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        // 提交请求，返回结果
        Response response = handler1.handleMessage(new Request());
        /**
         * 在实际应用中，一般会有一个封装类对责任链模式进行封装，也就是替代场景类，直接返回
         * 链中的第一个处理者，具体链的设置不需要高层次模块关心，这样更简化了高层次模块的
         * 调用，减少模块间的耦合，提高系统的灵活性。
         */
    }
}
