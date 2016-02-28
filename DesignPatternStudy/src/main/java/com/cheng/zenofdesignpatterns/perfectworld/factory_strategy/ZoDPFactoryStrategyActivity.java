package com.cheng.zenofdesignpatterns.perfectworld.factory_strategy;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZoDPFactoryStrategyActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("工厂方法模式 + 策略模式");
        String content = "小结：\n" +
                "迷你版的交易系统案例中使用了如下几个模式：\n" +
                "- 策略模式\n" +
                "负责对扣款策略进行封装，保证两个策略可以自由切换，而且日后增加扣款策略也非常简单" +
                "容易。\n" +
                "- 工厂方法模式\n" +
                "修正策略模式必须对外暴露具体策略的问题，由工厂方法模式直接产生一个具体策略对象，" +
                "而其他模块则不需要依赖具体的策略。\n" +
                "- 门面模式\n" +
                "负责对复杂的扣款系统进行封装，封装的结果就是避免高层模块深入子系统内部，同时提供" +
                "系统的高内聚、低耦合的特性。\n" +
                "主要使用了这三个模式，它们的好处是灵活、稳定，可以设想一下可能有哪些业务变化。\n" +
                "- 扣款策略变更\n" +
                "增加一个新的扣款策略，三步就可以完成：实现IDeduction接口，增加StrategyMan配置项，" +
                "扩展扣款策略的利用（也就是门面模式的getDeductionType方法，在实际项目中这里只需要" +
                "增加数据库的配置项）。减少一个策略很简单，修改扣款策略的利用即可。变更一个扣款策略" +
                "也很简单，扩展一个实现类就可以了。\n" +
                "- 变更扣款策略的利用规则\n" +
                "系统不想大修改，还记得状态模式吗？这个就是为策略的利用服务的，变更它就能满足要求。" +
                "想把IC卡也纳入策略利用的规则也不复杂。这个还真发生了，系统投产后，业务提出考虑退休" +
                "人员的情况，退休人员的IC卡与普通在职员工一样，但是它的扣款不仅仅是根据交易编码，还" +
                "要根据IC卡对象，系统的变更做法是增加一个扣款策略，同时扩展扣款利用策略，也就是数据" +
                "库的配置项，在getDeductionType中新扩展了一个功能：根据IC卡号，确定是否是退休人员，" +
                "是退休人员，则使用新的扣款策略，这是一个非常简单的扩展。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 使用工厂方法模式和策略模式模拟交易
        // 初始化一张IC卡
        Card card = initIC();
        // 显示一下卡内信息
        System.out.println("========初始卡信息：=========");
        showCard(card);

        // 是否停止运行标志
        boolean flag = true;
        while (flag) {
            Trade trade = createTrade();

            DeductionFacade.deduct(card, trade);
            // 交易成功，打印出成功处理消息
            System.out.println("\n======交易凭证========");
            System.out.println(trade.getTradeNo() + " 交易成功！");
            System.out.println("本次发生的交易金额为：" + trade.getAmount() / 100.0 + " 元");
            // 展示一下卡内信息
            showCard(card);

            System.out.print("\n是否需要退出？(Y/N)");
            if (getInput().equalsIgnoreCase("y")) {
                flag = false;  // 退出；
            }
        }

    }

    // 初始化一个IC卡
    private Card initIC() {
        Card card = new Card();
        card.setCardNo("1100010001000");
        card.setFreeMoney(100000);  // 一千元
        card.setSteadyMoney(80000); // 八百元
        return card;
    }

    // 产生一条交易
    private Trade createTrade() {
        Trade trade = new Trade();
        System.out.print("请输入交易编号：");
        trade.setTradeNo(getInput());
        System.out.print("请输入交易金额：");
        trade.setAmount(Integer.parseInt(getInput()));

        // 返回交易
        return trade;
    }

    // 打印出当前卡内交易余额
    public void showCard(Card card) {
        System.out.println("IC卡编号:" + card.getCardNo());
        System.out.println("固定类型余额：" + card.getSteadyMoney() / 100.0 + " 元");
        System.out.println("自由类型余额：" + card.getFreeMoney() / 100.0 + " 元");
    }

    // 获得键盘输入
    public String getInput() {
        String str = "";
        try {
            str = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        } catch (IOException e) {
            // 异常处理
        }
        return str;
    }
}
