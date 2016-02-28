package com.cheng.zenofdesignpatterns.patterns.facade;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.facade.common.Facade;
import com.cheng.zenofdesignpatterns.patterns.facade.letter.ModenPostOffice;

public class ZoDPFacadeActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("门面模式");
        String content = "定义：\n" +
                "Provide a unified interface to a set of interfaces in a subsystem." +
                "Facade defines a higher-level interface that makes the subsystem " +
                "easier to use.\n" +
                "要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行。门面模式提供" +
                "一个高层次的接口，使得子系统更易于使用。\n\n" +
                "门面模式的优点\n" +
                "- 减少系统的相互依赖\n" +
                "- 提高了灵活性\n" +
                "- 提高了安全性\n" +
                "门面模式的缺点\n" +
                "最大的缺点就是不符合开闭原则，对修改关闭，对扩展开放，看看那个门面对象吧，它" +
                "可是重中之重，一旦在系统投产后发现有一个小错误，怎么解决？完全遵守开闭原则，" +
                "根本没办法解决。继承？复写？都顶不上用，唯一能做的一件事就是修改门面角色的代" +
                "码，这个风险相当大，这就需要大家在设计的时候慎之又慎，多思考几遍才会有好收获。\n\n" +
                "使用场景\n" +
                "- 为一个复杂的模块或子系统提供一个供外界访问的接口\n" +
                "- 子系统相对独立--外界对子系统的访问只要黑箱操作即可\n" +
                "比如利息的计算问题。\n" +
                "- 预防低水平人员带来的风险扩散\n\n" +
                "注意事项" +
                "1. 一个系统可以有多个门面\n" +
                "- 门面已经庞大到不能忍受的程度\n" +
                "那怎么拆分呢？按照功能拆分是一个非常好的原则。\n" +
                "- 子系统可以提供不同的访问路径\n" +
                "2. 门面不参与子系统内的业务逻辑\n" +
                "门面对象只是提供一个访问子系统的一个路径而已，它不应该也不能参与具体的业务逻辑，" +
                "否则就会产生一个倒依赖的问题：子系统必须依赖门面才能被访问，这是设计一个严重错误，" +
                "不仅违反了单一职责原则，同时也破坏了系统的封装性。\n\n" +
                "最佳实践\n" +
                "门面模式是一个很好的封装方法，一个子系统比较复杂时，比如算法或者业务比较复杂，就" +
                "可以封装出一个或多个门面来，项目的结构简单，而且扩展性非常好。还有，对于一个较大" +
                "项目，为了避免人员带来的风险，也可以使用门面模式，技术水平比较差的成员，尽量安排" +
                "独立的模块，然后把他写的程序封装到一个门面里，尽量让其他项目成员不用看到这些人的" +
                "代码。使用门面模式后，对门面进行单元测试，约束项目成员的代码质量，对项目整体质量" +
                "的提升也是一个比较好的帮助。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟写信邮寄的过程
        // 现代化的邮局，有这项服务，邮局名称叫HellRoad
        ModenPostOffice hellRoadPostOffice = new ModenPostOffice();
        // 你只要把信的内容和收信人地址给他，他会帮你完成一系列工作
        // 定义一个地址
        String address = "Happy Road No. 666,God Province,Heaven";
        // 信的内容
        String content = "Hello,It's me,do you know who I am? I'm your old lover. I'd like to....";
        // 你给我发送吧
        hellRoadPostOffice.sendLetter(content, address);

        // 2. 通用门面模式演示
        Facade facade = new Facade();
        facade.methodA();
        facade.methodB();
        facade.methodC();
    }
}
