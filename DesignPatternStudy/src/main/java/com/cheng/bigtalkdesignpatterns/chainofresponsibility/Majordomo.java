package com.cheng.bigtalkdesignpatterns.chainofresponsibility;

/**
 * 总监
 */
public class Majordomo extends Manager {

    public Majordomo(String name) {
        super(name);
    }

    @Override
    public void requestApplication(Request request) {
        if ("请假".equals(request.getRequestType())
                && request.getNumber()<=5) { // 总监能够有的权限就是准许下属五天内的假期
            System.out.println(name + ":" + request.getRequestContent()
                    + "数量 " + request.getNumber() + "被批准");
        } else {
            if (superior != null) {
                superior.requestApplication(request);
            }
        }
    }
}
