package com.cheng.bigtalkdesignpatterns.mediator;

/**
 * Created by Administrator on 2015/12/23.
 */
public class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(Mediator _mediator) {
        super(_mediator);
    }

    public void send(String _msg) {
        mediator.send(_msg, this);
    }

    public void notify(String _msg) {
        System.out.println("同事2得到信息：" + _msg);
    }
}
