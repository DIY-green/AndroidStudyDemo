package com.cheng.zenofdesignpatterns.perfectworld.factory_strategy;

/**
 * 策略枚举
 * 策略登记
 * 就是一个登记容器，所有的具体策略都在这里登记
 */
public enum StrategyMan {

    SteadyDeduction("com.company.SteadyDeduction"),
    FreeDeduction("com.company.FreeDeduction");

    String value = "";

    private StrategyMan(String _value) {
        this.value = _value;
    }

    public String getValue() {
        return this.value;
    }

}
