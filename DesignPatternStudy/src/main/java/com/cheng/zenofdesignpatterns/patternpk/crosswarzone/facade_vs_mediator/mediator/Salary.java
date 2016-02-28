package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.mediator;

/**
 * 工资
 */
public class Salary extends AbsColleague implements ISalary {

    public Salary(AbsMediator _mediator) {
        super(_mediator);
    }

    public void decreaseSalary() {
        super.mediator.down(this);
    }

    public void increaseSalary() {
        super.mediator.up(this);
    }
}