package com.cheng.zenofdesignpatterns.principle.isp;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;

public class ZoDPInterfaceSegregationPrincipleActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("接口隔离原则");
        String content = "定义：\n" +
                "接口的定义：\n" +
                "- 实例接口（Object Interface）\n" +
                "在Java中声明一个类，然后用new关键字产生一个实例，它是对一个类型的事物的" +
                "描述，这是一种接口 -- Java中的类也是一种接口\n" +
                "- 类接口（Class Interface）\n" +
                "Java中经常使用的interface关键字定义的接口\n" +
                "隔离的定义：\n" +
                "- Clients should not be forced to depend upon interfaces that they " +
                "don't use.（客户端不应该依赖它不需要的接口）\n" +
                "- The dependency of one class to another one should depend on the " +
                "smallest possible interface.（类间的依赖关系应该建立在最小的接口上）\n" +
                "概括为一句话：建立单一接口，不要建立臃肿庞大的接口。在通俗一点讲：接口尽量" +
                "细化，同时接口中的方法尽量少。\n" +
                "注意 接口隔离原则与单一职责的审视角度是不相同的，单一职责要求的是类和接口" +
                "职责单一，注重的是职责，这是业务逻辑上的划分，而接口隔离原则要求接口的方法" +
                "尽量少。\n\n" +
                "接口隔离原则是对接口进行规范约束，其包含以下4层含义：\n" +
                "- 接口要尽量小\n" +
                "根据接口隔离原则拆分接口时，首先必须满足单一职责原则\n" +
                "- 接口要高内聚\n" +
                "高内聚就是提高接口、类、模块的处理能力，减少对外的交互。具体到接口隔离原则就是，" +
                "要求在接口中尽量少公布public方法，接口是对外的承诺，承诺越少对系统的开发越有利，" +
                "变更的风险也就越少，同时也有利于降低成本\n" +
                "- 定制服务\n" +
                "定制服务就是单独为一个个体提供优良的服务。在做系统设计时也需要考虑对系统之间或" +
                "模块之间的接口采用定制服务。采用定制服务就必然有一个要求：只提供访问者需要的方法\n" +
                "- 接口设计是有限度的\n\n" +
                "最佳实践：\n" +
                "接口隔离原则是对接口的定义，同时也是对类的定义，接口和类尽量使用原子接口或原子类" +
                "来组装。\n" +
                "关于原子划分，可以根据以下几个规则来衡量：\n" +
                "- 一个接口只服务于一个子模块或业务逻辑\n" +
                "- 通过业务逻辑压缩接口中的public方法" +
                "- 已经被污染了的接口，尽量去修改，若变更的风险较大，则采用适配器模式进行转化处理\n" +
                "了解环境，拒绝盲从。环境不同，接口拆分的标准就不同";
        mContentTV.setText(content);
    }

    public void onClick(View v) {
        IGoodBodyGirl girl1 = new PettyGirl("嫣嫣");
        AbstractSearcher searcher = new Searcher(girl1);
        searcher.show();

        IGreatTemperamentGirl girl2 = new PettyGirl("嫣嫣");
        searcher = new Searcher(girl2);
        searcher.show();
    }
}
