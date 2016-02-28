package com.cheng.bigtalkdesignpatterns.chainofresponsibility;

/**
 * 申请
 */
public class Request {
    // 申请列别
    private String requestType;
    // 申请内容
    private String requestContent;
    // 数量
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
