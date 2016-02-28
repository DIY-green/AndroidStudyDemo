package com.cheng.zenofdesignpatterns.patterns.facade.letter;

/**
 * 模拟写信过程实现类
 */
public class LetterProcessImpl implements ILetterProcess {

    @Override
    public void writeContent(String content) {
        System.out.println("填写信的内容...." + content);
    }

    @Override
    public void fillEnvelope(String address) {
        System.out.println("填写收件人地址及姓名...." + address);
    }

    @Override
    public void letterIntoEnvelope() {
        System.out.println("把信放到信封中....");
    }

    @Override
    public void sendLetter() {
        System.out.println("邮递信件...");
    }
}
