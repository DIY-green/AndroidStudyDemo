package com.cheng.zenofdesignpatterns.patterns.flyweight;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.flyweight.signup.SignInfo;
import com.cheng.zenofdesignpatterns.patterns.flyweight.signup.SignInfoFactory;

public class ZoDPFlyweightActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("享元模式");
        String content = "定义：\n" +
                "Use sharing to support large numbers of fine-grained objects efficiently.\n" +
                "使用共享对象可有效地支持大量的细粒度的对象。\n\n" +
                "对象信息的内部状态和外部状态：\n" +
                "- 内部状态\n" +
                "是对象可共享出来的信息，存储在享元对象内部并且不会随环境改变而改变，它们可以作" +
                "为一个对象的动态附加信息，不必直接存储在具体某个对象中，属于可以共享的部分。\n" +
                "- 外部状态\n" +
                "是对象得以依赖的一个标记，是随环境改变而改变的、不可以共享的状态，它是一批对象" +
                "的统一标识，是唯一的一个索引值。\n\n" +
                "享元模式的优缺点\n" +
                "享元模式是一个非常简单的模式，它可以大大减少应用程序创建的对象，降低程序内存的" +
                "占用，增强程序的性能，但它同时也提高了系统的复杂性，需要分离出外部状态和内部状" +
                "态，而且外部状态具有固化特性，不应该随内部状态改变而改变，否则导致系统的逻辑混乱。\n\n" +
                "使用场景\n" +
                "- 系统中存在大量的相似对象\n" +
                "- 细粒度的对象都具备较接近的外部状态，而且外部状态与环境无关，也就是说对象没有特" +
                "定身份\n" +
                "- 需要缓冲池的场景\n\n" +
                "享元模式的扩展\n" +
                "1. 线程安全问题\n" +
                "该问题没什么可以参考的标准，只有依靠经验，在需要的地方考虑一下线程安全，在大部分" +
                "场景下都不用考虑。在使用享元模式时，对象池中的享元对象尽量多，多到足够满足业务为止。\n" +
                "2. 性能平衡\n" +
                "尽量使用Java基本类型作为外部状态（String类型也是可以考虑的）。\n\n" +
                "最佳实践\n" +
                "Flyweight是拳击比赛中的特有名词，意思是‘特轻量级’，指的是51公斤级比赛，用到设计" +
                "模式中是指我们的类要轻量级，粒度要小，这才是它要表达的意思。粒度小了，带来的问题" +
                "就是对象太多，那就用共享技术来解决。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟报名时候高并发访问生成大量对象
        //初始化对象池
        for(int i=0;i<4;i++){
            String subject = "科目" + i;
            //初始化地址
            for(int j=0;j<30;j++){
                String key = subject + "考试地点"+j;
                SignInfoFactory.getSignInfo(key);
            }
        }
        SignInfo signInfo = SignInfoFactory.getSignInfo("科目1考试地点1");
    }
}
