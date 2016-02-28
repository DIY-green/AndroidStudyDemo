package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.mediator;

public class Tax extends AbsColleague implements ITax {

    // 注入中介者
    public Tax(AbsMediator _mediator) {
        super(_mediator);
    }

    public void drop() {
        super.mediator.down(this);
    }

    public void raise() {
        super.mediator.up(this);
    }
}