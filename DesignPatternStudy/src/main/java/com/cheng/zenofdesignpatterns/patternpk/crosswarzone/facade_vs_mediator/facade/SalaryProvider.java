package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.facade;

/**
 * 总工资计算
 */
public class SalaryProvider {

    // 基本工资
    private BasicSalary basicSalary = new BasicSalary();
    // 奖金
    private Bonus bonus = new Bonus();
    // 绩效
    private Performance perf = new Performance();
    // 税收
    private Tax tax = new Tax();

    // 获得用户的总收入
    public int totalSalary() {
        return basicSalary.getBasicSalary() + bonus.getBonus() + perf.getPerformanceValue() - tax.getTax();
    }
}