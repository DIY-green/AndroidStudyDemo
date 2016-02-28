package com.cheng.zenofdesignpatterns.patterns.facade.letter;

/**
 * 模拟邮局
 */
public class ModenPostOffice {

    private ILetterProcess letterProcess = new LetterProcessImpl();
    private Police letterPolice = new Police();

    // 写信、封装、投递，一体化了
    public void sendLetter(String content, String address) {
        // 帮你写信
        letterProcess.writeContent(content);
        // 写好信封
        letterProcess.fillEnvelope(address);
        // 警察要检查信件了
        letterPolice.checkLetter(letterProcess);
        // 把信放到信封中
        letterProcess.letterIntoEnvelope();
        // 邮递信件
        letterProcess.sendLetter();
    }
}
