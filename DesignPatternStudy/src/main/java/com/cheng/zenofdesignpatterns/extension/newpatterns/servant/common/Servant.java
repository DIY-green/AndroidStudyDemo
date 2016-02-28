package com.cheng.zenofdesignpatterns.extension.newpatterns.servant.common;

/**
 * 雇工类
 */
public class Servant {

    // 服务内容
    public void service(IServiced serviceFuture) {
        serviceFuture.serviced();
    }
}
