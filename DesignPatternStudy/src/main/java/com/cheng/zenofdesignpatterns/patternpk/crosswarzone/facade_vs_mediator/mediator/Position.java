package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.mediator;

/**
 * 职位
 */
public class Position extends AbsColleague implements IPosition {

    public Position(AbsMediator _mediator) {
        super(_mediator);
    }

    public void demote() {
        super.mediator.down(this);
    }

    public void promote() {
        super.mediator.up(this);
    }
}