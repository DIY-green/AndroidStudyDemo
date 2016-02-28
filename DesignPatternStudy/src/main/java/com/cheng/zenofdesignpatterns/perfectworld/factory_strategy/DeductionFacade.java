package com.cheng.zenofdesignpatterns.perfectworld.factory_strategy;

/**
 * 扣款模块封装
 */
public class DeductionFacade {

    // 对外公布的扣款信息
    public static Card deduct(Card card, Trade trade) {
        // 获得消费策略
        StrategyMan reg = getDeductionType(trade);
        // 初始化一个消费策略对象
        IDeduction deduction = StrategyFactory.getDeduction(reg);
        // 产生一个策略上下问
        DeductionContext context = new DeductionContext(deduction);
        // 进行扣款处理
        context.exec(card, trade);
        // 返回扣款处理完毕后的数据
        return card;
    }

    // 获得对应的商户消费策略
    private static StrategyMan getDeductionType(Trade trade) {
        // 模拟操作
        if (trade.getTradeNo().contains("abc")) {
            return StrategyMan.FreeDeduction;
        } else {
            return StrategyMan.SteadyDeduction;
        }
    }
}
