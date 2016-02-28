package com.cheng.zenofdesignpatterns.patterns.observer;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.observer.common.ConcreteObserver;
import com.cheng.zenofdesignpatterns.patterns.observer.common.ConcreteSubject;
import com.cheng.zenofdesignpatterns.patterns.observer.common.CommonObserver;
import com.cheng.zenofdesignpatterns.patterns.observer.monitor.HanFeiZi1;
import com.cheng.zenofdesignpatterns.patterns.observer.monitor.LiSi1;
import com.cheng.zenofdesignpatterns.patterns.observer.monitor.LiuSi1;
import com.cheng.zenofdesignpatterns.patterns.observer.monitor.MonitorObserver;
import com.cheng.zenofdesignpatterns.patterns.observer.monitor.WangSi1;
import com.cheng.zenofdesignpatterns.patterns.observer.monitorpro.HanFeiZi;
import com.cheng.zenofdesignpatterns.patterns.observer.monitorpro.LiSi;
import com.cheng.zenofdesignpatterns.patterns.observer.monitorpro.LiuSi;
import com.cheng.zenofdesignpatterns.patterns.observer.monitorpro.WangSi;

import java.util.Observer;

public class ZoDPObserverActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("观察者模式");
        String content = "定义：\n" +
                "Define a one-to-many dependency between objects so that when one object " +
                "changes state, all its dependents are notified and updated automatically.\n" +
                "定义对象间一种一对多的依赖关系，使得每当一个对象改变状态，则所有依赖于它的对象都" +
                "会得到通知并被自动更新。\n\n" +
                "观察者模式的优点\n" +
                "- 观察者和被观察者之间是抽象耦合\n" +
                "- 建立一套触发机制，形成一个触发链\n" +
                "观察者模式的缺点\n" +
                "需要考虑一下开发效率和运行效率问题，一个被观察者，多个观察者，开发和调试就会比较" +
                "复杂，而且在Java中消息的通知默认是顺序执行，一个观察者卡壳，会影响整体的执行效率。" +
                "在这种情况下，一般考虑采用异步的方式。\n\n" +
                "使用场景\n" +
                "- 关联行为场景。需要注意的是，关联行为是可拆分的，而不是’组合‘关系\n" +
                "- 事件多级触发场景\n" +
                "- 跨系统的消息交互场景，如消息队列的处理机制\n\n" +
                "注意事项\n" +
                "- 广播链的问题\n" +
                "观察者模式有一个问题，一个观察者可以有双重身份，既是观察者，也是被观察者，这没什" +
                "么问题呀，但是链一旦建立，这个逻辑就比较复杂，可维护性非常差，根据经验建议，在一" +
                "个观察者模式中最多出现一个对象既是观察者也是被观察者，也就是说消息最多转发一次（传" +
                "递两次），这还是比较好控制的。\n" +
                "注意 它与责任链模式的最大区别就是观察者广播链在传播的过程中消息是随时更改的，它是由" +
                "相邻的两个节点协商的消息结构；而责任链模式在消息传递过程中基本上保持消息不可变，如" +
                "果要改变，也只是在原有的消息上进行修正\n" +
                "- 异步处理问题\n\n" +
                "Java世界中的观察者模式\n" +
                "JDK中提供了：java.util.Observable实现类和java.util.Observer接口\n\n" +
                "项目中真实的观察者模式\n" +
                "在系统设计中会对观察者模式进行改造或改装，主要在以下3个方面\n" +
                "- 观察者和被观察者之间的消息沟通\n" +
                "- 观察者的响应方式\n" +
                "观察者如何快速响应？有两个办法：一是采用多线程技术，也就是大家通常所说的异步架构；" +
                "二是缓存技术，也就是大家说的同步架构\n" +
                "- 被观察者尽量自己做主\n" +
                "这是什么意思呢？被观察者的状态改变是否一定要通知观察者呢？不一定吧，在设计的时候要" +
                "灵活考虑，否则会加重观察者的处理逻辑，一般是这样做的，对被观察者的业务逻辑doSomething" +
                "方法实现重载，如增加一个doSomething(boolean isNotifyObs)方法，决定是否通知观察者，" +
                "而不是在消息到达观察者时才判断是否要消费。\n\n" +
                "最佳实践\n" +
                "- 文件系统\n" +
                "- 广播收音机\n" +
                "- 发布订阅模型\n" +
                "- RxJava（扩展的观察者模式）";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟监视韩非子
        // 三个观察者产生出来
        MonitorObserver lisi = new LiSi1();
        MonitorObserver wangsi = new WangSi1();
        MonitorObserver liusi = new LiuSi1();
        // 定义出韩非子
        HanFeiZi1 hanFeiZi = new HanFeiZi1();
        // 我们后人根据历史，描述这个场景，有三个人在观察韩非子
        hanFeiZi.addObserver(lisi);
        hanFeiZi.addObserver(wangsi);
        hanFeiZi.addObserver(liusi);
        // 然后这里我们看看韩非子在干什么
        hanFeiZi.haveBreakfast();
        hanFeiZi.haveFun();

        // 2. 通用观察者模式演示
        // 创建一个被观察者
        ConcreteSubject subject = new ConcreteSubject();
        // 定义一个观察则
        CommonObserver obs= new ConcreteObserver();
        // 观察者观察被被观察则
        subject.addObserver(obs);
        // 观察者开始活动了
        subject.doSomething();

        // 3. 使用JDK提供的接口实现观察者模式
        // 三个观察者产生出来
        Observer liSi2 = new LiSi();
        Observer wangSi2 = new WangSi();
        Observer liuSi2 = new LiuSi();
        // 定义出韩非子
        HanFeiZi hanFeiZi2 = new HanFeiZi();
        // 我们后人根据历史，描述这个场景，有三个人在观察韩非子
        hanFeiZi2.addObserver(wangSi2);
        hanFeiZi2.addObserver(liuSi2);
        hanFeiZi2.addObserver(liSi2);
        // 然后这里我们看看韩非子在干什么
        hanFeiZi2.haveBreakfast();
        hanFeiZi2.haveFun();
    }
}
