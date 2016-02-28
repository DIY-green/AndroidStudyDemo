package com.cheng.bigtalkdesignpatterns.strategy;

/**
 * 返利收费子类
 */
public class CashReturn extends CashSuper {

    private double moneyCondition = 0.0d;
    private double moneyReturn = 0.0d;

    public CashReturn(String _moneyCondition, String _moneyReturn) {
        this.moneyCondition = Double.parseDouble(_moneyCondition);
        this.moneyReturn = Double.parseDouble(_moneyReturn);
    }

    @Override
    public double acceptCash(double money) {
        double result = money;
        if (money >= moneyCondition) {
            result = money - Math.floor(money / moneyCondition) * moneyReturn;
        }
        return result;
    }
}
