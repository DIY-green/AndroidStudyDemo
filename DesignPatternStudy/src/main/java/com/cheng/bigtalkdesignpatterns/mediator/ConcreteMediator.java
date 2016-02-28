package com.cheng.bigtalkdesignpatterns.mediator;

/**
 * 具体中介者类
 */
public class ConcreteMediator extends Mediator {

    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;

    @Override
    public void send(String _msg, Colleague _colleague) {
        if (_colleague == colleague1) {
            colleague2.notify(_msg);
        } else {
            colleague1.notify(_msg);
        }
    }

    public void setColleague2(ConcreteColleague2 colleague2) {
        this.colleague2 = colleague2;
    }

    public void setColleague1(ConcreteColleague1 colleague1) {
        this.colleague1 = colleague1;
    }
}
