package com.cheng.zenofdesignpatterns.patterns.bridge;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.bridge.common.Abstraction;
import com.cheng.zenofdesignpatterns.patterns.bridge.common.ConcreteImplementor1;
import com.cheng.zenofdesignpatterns.patterns.bridge.common.Implementor;
import com.cheng.zenofdesignpatterns.patterns.bridge.common.RefinedAbstraction;
import com.cheng.zenofdesignpatterns.patterns.bridge.corporation.Clothes;
import com.cheng.zenofdesignpatterns.patterns.bridge.corporation.House;
import com.cheng.zenofdesignpatterns.patterns.bridge.corporation.HouseCorp;
import com.cheng.zenofdesignpatterns.patterns.bridge.corporation.IPod;
import com.cheng.zenofdesignpatterns.patterns.bridge.corporation.ShanZhaiCorp;

public class ZoDPBridgeActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("桥梁模式");
        String content = "定义：\n" +
                "Decouple an abstraction from its implementation so that the two can vary " +
                "independently.\n" +
                "将抽象和实现解耦，使得两者可以独立地变化。\n\n" +
                "桥梁模式的优点\n" +
                "- 抽象和实现分离\n" +
                "这也是桥梁模式的主要特点，它完全是为了解决继承的缺点而提出的设计模式。在该模式下，" +
                "实现可以不受抽象的约束，不用再绑定在一个固定的抽象层次上。\n" +
                "- 优秀的扩充能力\n" +
                "- 实现细节对客户透明\n" +
                "客户不用关心细节的实现，它已经由抽象层通过聚合关系完成了封装。\n\n" +
                "使用场景\n" +
                "- 不希望或不适用使用继承的场景\n" +
                "例如继承层次过多、无法更细化设计颗粒等场景，需要考虑使用桥梁模式。\n" +
                "- 接口或抽象类不稳定的场景\n" +
                "- 重用性要求较高的场景\n" +
                "设计的粒度越细，则被重用的可能性就越大，而采用继承则受父类的限制，不可能出现太细的" +
                "颗粒度。\n\n" +
                "注意事项\n" +
                "使用该模式时主要考虑如何拆分抽象和实现，并不是一涉及继承就考虑使用该模式，那还要继承" +
                "干什么呢？桥梁模式的意图还是对变化的封装，尽量把可能变化的因素封装到最细、最小的逻辑" +
                "单元中，避免风险扩散。在进行系统设计时，发现类的继承有N层时，可以考虑使用桥梁模式。\n\n" +
                "最佳实践\n" +
                "继承非常好，但是有缺点，可以扬长避短，对于比较明确不发生变化的，则通过继承来完成；若" +
                "不能确定是否会发生变化的，那就认为会发生变化，则通过桥梁模式来解决，这才是一个完美的世界。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟公司生产产品赚钱
        House house = new House();
        System.out.println("-------房地产公司是这个样子运行的-------");
        // 先找到房地产公司
        HouseCorp houseCorp = new HouseCorp(house);
        // 看我怎么挣钱
        houseCorp.makeMoney();
        System.out.println();
        // 山寨公司生产的产品很多，不过我只要指定产品就成了
        System.out.println("-------山寨公司是这样运行的-------");
        ShanZhaiCorp shanZhaiCorp = new ShanZhaiCorp(new Clothes());
        shanZhaiCorp.makeMoney();
        shanZhaiCorp = null;
        shanZhaiCorp = new ShanZhaiCorp(new IPod());
        shanZhaiCorp.makeMoney();

        // 2. 通用桥梁模式演示
        /**
         * 桥梁模式中的几个名词比较拗口，只要记住一句话就成：抽象角色引用实现角色，或者说
         * 抽象角色的部分实现是由实现角色完成的
         */
        // 定义一个实现化角色
        Implementor imp = new ConcreteImplementor1();
        // 定义一个抽象化角色
        Abstraction abs = new RefinedAbstraction(imp);
        // 执行行文
        abs.request();
    }
}
