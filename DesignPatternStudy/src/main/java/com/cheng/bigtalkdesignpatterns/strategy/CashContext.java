package com.cheng.bigtalkdesignpatterns.strategy;

/**
 * CashContext类
 */
public class CashContext {

    private CashSuper cashSuper = null;

    /**
     * 改造前单纯的策略模式
     * 通过构造函数传入具体的收费策略
     */
    @Deprecated
    public CashContext(CashSuper _cashSuper) {
        this.cashSuper = _cashSuper;
    }

    /**
     * 改造后 -- 策略与简单工厂结合
     */
    public CashContext(String _type) {
        switch (_type) {
            case "正常收费":
                CashNormal cs0 = new CashNormal();
                cashSuper = cs0;
                break;
            case "满300返100":
                CashReturn cs1 = new CashReturn("300", "100");
                cashSuper = cs1;
                break;
            case "打8折":
                CashRebate cs2 = new CashRebate("0.8");
                cashSuper = cs2;
                break;
        }
    }

    // 根据收费策略的不同，获得计算结果
    public double getResult(double _money) {
        return cashSuper.acceptCash(_money);
    }
}
