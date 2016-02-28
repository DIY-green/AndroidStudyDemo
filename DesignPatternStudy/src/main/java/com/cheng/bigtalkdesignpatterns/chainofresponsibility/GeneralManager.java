package com.cheng.bigtalkdesignpatterns.chainofresponsibility;

/**
 * 总经理
 */
public class GeneralManager extends Manager {

    public GeneralManager(String name) {
        super(name);
    }

    @Override
    public void requestApplication(Request request) {
        if ("请假".equals(request.getRequestType())) { // 总经理可以准许下属任意天数的假期
            System.out.println(name + ":" + request.getRequestContent()
                    + "数量 " + request.getNumber() + "被批准");
        } else if ("加薪".equals(request.getRequestType())
                && request.getNumber()<=500) { // 加薪在500以内没有问题
            System.out.println(name + ":" + request.getRequestContent()
                    + "数量 " + request.getNumber() + "被批准");
        } else if ("加薪".equals(request.getRequestType())
                && request.getNumber()>500) { // 加薪大于500要考虑考虑了
            System.out.println(name + ":" + request.getRequestContent()
                    + "数量 " + request.getNumber() + "在说吧");
        }
    }
}
