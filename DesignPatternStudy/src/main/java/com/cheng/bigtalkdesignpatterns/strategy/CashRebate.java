package com.cheng.bigtalkdesignpatterns.strategy;

/**
 * 打折收费子类
 */
public class CashRebate extends CashSuper {

    private double moneyRebate = 1d;

    public CashRebate(String _moneyRebate) {
        this.moneyRebate = Double.parseDouble(_moneyRebate);
    }

    @Override
    public double acceptCash(double money) {
        return money * moneyRebate;
    }

}
