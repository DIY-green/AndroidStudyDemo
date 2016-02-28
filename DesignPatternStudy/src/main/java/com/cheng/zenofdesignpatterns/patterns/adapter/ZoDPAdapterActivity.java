package com.cheng.zenofdesignpatterns.patterns.adapter;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.adapter.common.Adapter;
import com.cheng.zenofdesignpatterns.patterns.adapter.common.ConcreteTarget;
import com.cheng.zenofdesignpatterns.patterns.adapter.common.Target;
import com.cheng.zenofdesignpatterns.patterns.adapter.mmssection1.IUserInfo1;
import com.cheng.zenofdesignpatterns.patterns.adapter.mmssection1.OuterUserInfo1;
import com.cheng.zenofdesignpatterns.patterns.adapter.mmssection2.IOuterUserBaseInfo;
import com.cheng.zenofdesignpatterns.patterns.adapter.mmssection2.IOuterUserHomeInfo;
import com.cheng.zenofdesignpatterns.patterns.adapter.mmssection2.IOuterUserOfficeInfo;
import com.cheng.zenofdesignpatterns.patterns.adapter.mmssection2.IUserInfo;
import com.cheng.zenofdesignpatterns.patterns.adapter.mmssection2.OuterUserBaseInfo;
import com.cheng.zenofdesignpatterns.patterns.adapter.mmssection2.OuterUserHomeInfo;
import com.cheng.zenofdesignpatterns.patterns.adapter.mmssection2.OuterUserInfo;
import com.cheng.zenofdesignpatterns.patterns.adapter.mmssection2.OuterUserOfficeInfo;

public class ZoDPAdapterActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("适配器模式");
        String content = "定义：\n" +
                "Convert the interface of a class into another interface clients expect." +
                "Adapter lets classes work together that couldn't otherwise because of " +
                "incompatible interfaces.\n" +
                "将一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起" +
                "工作的两个类能够在一起工作。\n" +
                "适配器模式又叫变压器模式，也叫做包装模式（Wrapper），但是包装模式可不止一个，还" +
                "包括装饰模式。\n\n" +
                "适配器模式的优点\n" +
                "- 适配器模式可以让两个没有任何关系的类在一起运行，只要适配器角色能够搞定他们就成\n" +
                "- 增加了类的透明性\n" +
                "- 提高了类的复用性\n" +
                "- 灵活性非常好\n\n" +
                "使用场景\n" +
                "有动机修改一个已经投产中的接口时，适配器模式可能是最适合的模式。比如系统扩展了，" +
                "需要使用一个已有或新建立的类，但这个类又不符合系统的接口，怎么办？使用适配器模式。\n\n" +
                "注意事项\n" +
                "适配器模式最好在详细设计阶段不要考虑它，它不是为了解决还在开发阶段的问题，而是解决" +
                "正在服役的项目问题。这个模式使用的主要场景是扩展应用中，系统扩展了，不符合原有设计" +
                "的时候才考虑通过适配器模式减少代码修改带来的风险。再次提醒一点，项目一定要遵守依赖" +
                "倒置原则和里氏替换原则，否则即使在适合使用适配器的场合，也会带来非常大的改造。\n\n" +
                "最佳实践\n" +
                "适配器模式是一个补偿模式，或者说是一个‘补救’模式，通常用来解决接口不相容的问题。它" +
                "通过把非本系统接口的对象包装成系统可以接受的对象，从而简化了系统大规模变更风险的存在。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟人员管理系统扩展可以管理外借的人员
        // 没有与外系统连接的时候，是这样写的
        // IUserInfo1 youngGirl = new UserInfo1();

        //老板一想不对呀，兔子不吃窝边草，还是找人力资源的员工好点
        IUserInfo1 youngGirl1 = new OuterUserInfo1();  // 我们只修改了这一句好
        //从数据库中查到101个
        for(int i=0;i<101;i++){
            youngGirl1.getMobileNumber();
        }

        // 2. 适配器模式通用实现演示
        // 原有的业务逻辑
        Target target = new ConcreteTarget();
        target.request();

        // 现在增加了适配器角色后的业务逻辑
        Target target2 = new Adapter();
        target2.request();

        // 3. 对象适配器实现演示
        // 外系统的人员信息
        IOuterUserBaseInfo baseInfo = new OuterUserBaseInfo();
        IOuterUserHomeInfo homeInfo = new OuterUserHomeInfo();
        IOuterUserOfficeInfo officeInfo = new OuterUserOfficeInfo();
        // 传递三个对象
        IUserInfo youngGirl2 = new OuterUserInfo(baseInfo,homeInfo,officeInfo);
        // 从数据库中查到101个
        for(int i=0;i<101;i++){
            youngGirl2.getMobileNumber();
        }
    }
}
