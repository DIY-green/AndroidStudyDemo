package com.cheng.bigtalkdesignpatterns.chainofresponsibility;

/**
 * 经理
 */
public class CommonManager extends Manager {

    public CommonManager(String name) {
        super(name);
    }

    @Override
    public void requestApplication(Request request) {
        if ("请假".equals(request.getRequestType())
                && request.getNumber()<=2) { // 经理能够有的权限就是准许下属两天内的假期
            System.out.println(name + ":" + request.getRequestContent()
                    + "数量 " + request.getNumber() + "被批准");
        } else {
            if (superior != null) {
                superior.requestApplication(request);
            }
        }
    }
}
