package com.cheng.zenofdesignpatterns.patterns.chainofresponsibility.common;

/**
 * 抽象请求处理类
 */
public abstract class AbstractHandler {
    private AbstractHandler nextHandler;

    // 每个处理者都必须对请求做出处理
    public final Response handleMessage(Request request) {
        Response response = null;
        // 判断是否是自己的处理级别
        if (this.getHandleLevel().equals(request.getRequestLevel())) {
            response = this.echo(request);
        } else {// 不属于自己的处理级别
            // 判断是否有下一个处理者
            if(this.nextHandler != null){
                response = this.nextHandler.handleMessage(request);
            }else{
                // 没有适当的处理者，业务自行处理
            }
        }
        return response;
    }

    // 设置下一个处理者是谁
    public void setNext(AbstractHandler _handler){
        this.nextHandler = _handler;
    }

    // 每个处理者都有一个处理级别
    protected abstract Level getHandleLevel();

    // 每个处理者都必须实现处理任务
    protected abstract Response echo(Request request);

    /**
     * 抽象的处理者实现三个职责：一是定义一个请求的处理方法handleMessage，唯一对外
     * 开放的方法；二是定义一个链的编排方法setNext，设置下一个处理者；三是定义了具
     * 体的请求者必须实现的两个方法：定义自己能够处理的级别getHandleLevel和具体的
     * 处理任务echo。
     * 注意
     * 在责任链模式中一个请求发送到链中后，前一节点消费部分消息，然后交由后续节点继
     * 续处理，最终可以有处理结果也可以没有处理结果，可以不用理会什么纯的、不纯的责
     * 任链模式。同时，注意handleMessage方法前的final关键字--模版方法模式。
     */
}
