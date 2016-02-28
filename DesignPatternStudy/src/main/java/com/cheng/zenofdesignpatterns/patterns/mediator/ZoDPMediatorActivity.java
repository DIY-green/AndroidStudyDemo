package com.cheng.zenofdesignpatterns.patterns.mediator;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.mediator.common.ConcreteColleague1;
import com.cheng.zenofdesignpatterns.patterns.mediator.common.ConcreteColleague2;
import com.cheng.zenofdesignpatterns.patterns.mediator.common.ConcreteMediator;
import com.cheng.zenofdesignpatterns.patterns.mediator.common.Mediator;
import com.cheng.zenofdesignpatterns.patterns.mediator.invoicing.AbstractMediator;
import com.cheng.zenofdesignpatterns.patterns.mediator.invoicing.InvoicingMediator;
import com.cheng.zenofdesignpatterns.patterns.mediator.invoicing.Purchase;
import com.cheng.zenofdesignpatterns.patterns.mediator.invoicing.Sale;
import com.cheng.zenofdesignpatterns.patterns.mediator.invoicing.Stock;

public class ZoDPMediatorActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("中介者模式");
        String content = "定义：\n" +
                "Define an object that encapsulates how a set of objects interact.InvoicingMediator " +
                "promotes loose coupling by keeping objects from referring to each other " +
                "explicitly,and it lets you vary their interaction independently.\n" +
                "用一个中介者对象封装一系列的对象交互，中介者使各个对象不需要显式地相互作用，从而" +
                "使其耦合松散，而且可以独立地改变它们之间的交互。\n\n" +
                "中介者模式的优点\n" +
                "减少类间的依赖，把原有的一对多的依赖变成了一对一的依赖，同事类只依赖中介者，减少了" +
                "依赖，当然同时也降低了类间的耦合。\n" +
                "中介者模式的缺点\n" +
                "中介者会膨胀得很大，而且逻辑复杂，原本N个对象直接的相互依赖关系转换为中介者和同事类" +
                "的依赖关系，同事类越多，中介者的逻辑就越复杂。\n\n" +
                "使用场景\n" +
                "中介者模式适用于多个对象之间紧密耦合的情况，紧密耦合的标准是：在类图中出现了蜘蛛网状" +
                "结构。在这种情况下一定要考虑使用中介者模式，这有利于把蜘蛛网梳理为星型结构，使原本复" +
                "杂混乱的关系变得清晰简单。\n\n" +
                "实际应用\n" +
                "- 机场调度中心\n" +
                "- MVC框架中的C就是一个中介者\n" +
                "- 媒体网关\n" +
                "- 中介服务\n\n" +
                "最佳实践\n" +
                "在如下的情况下可以尝试使用中介者模式：\n" +
                "- N个对象之间产生了相互的依赖关系（N > 2）\n" +
                "- 多个对象有依赖关系，但是依赖的行为尚不确定或者有发生改变的可能，在这种情况下一般建议" +
                "采用中介者模式，降低变更引起的风险扩散\n" +
                "- 产品开发。把中介者模式应用到产品中，可以提升产品的性能和扩展性";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟进销存
        AbstractMediator mediator = new InvoicingMediator();
        // 采购人员采购电脑
        System.out.println("------采购人员采购电脑--------");
        Purchase purchase = new Purchase(mediator);
        purchase.buyIBMcomputer(100);
        // 销售人员销售电脑
        System.out.println("\n------销售人员销售电脑--------");
        Sale sale = new Sale(mediator);
        sale.sellIBMComputer(1);
        // 库房管理人员管理库存
        System.out.println("\n------库房管理人员清库处理--------");
        Stock stock = new Stock(mediator);
        stock.clearStock();

        // 2. 通用中介者演示
        Mediator mediator1 = new ConcreteMediator();
        ConcreteColleague1 colleague1 = new ConcreteColleague1(mediator1);
        ConcreteColleague2 colleague2 = new ConcreteColleague2(mediator1);
        colleague1.depMethod1();
        colleague2.depMethod2();
    }
}
