package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.mediator;

/**
 * 抽象同事类
 */
public abstract class AbsColleague {

    // 每个同事类都对中介者非常了解
    protected AbsMediator mediator;

    public AbsColleague(AbsMediator _mediator) {
        this.mediator = _mediator;
    }

}