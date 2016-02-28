package com.cheng.zenofdesignpatterns.perfectworld.factory_strategy;

/**
 * 策略上下文角色
 * 扣款策略的封装
 */
public class DeductionContext {

    // 扣款策略
    private IDeduction deduction = null;

    // 构造函数传递策略
    public DeductionContext(IDeduction _deduction) {
        this.deduction = _deduction;
    }

    // 执行扣款
    public boolean exec(Card card, Trade trade) {
        return this.deduction.exec(card, trade);
    }
}
