package com.cheng.zenofdesignpatterns.patternpk.crosswarzone.strategy_vs_bridge.bridge;

/**
 * 抽象邮件
 */
public abstract class MailTemplate {

    // 邮件发件人
    private String from;
    // 收件人
    private String to;
    // 邮件标题
    private String subject;
    // 邮件内容
    private String context;

    // 通过构造函数传递足够多的信息
    public MailTemplate(String _from, String _to, String _subject, String _context) {
        this.from = _from;
        this.to = _to;
        this.subject = _subject;
        this.context = _context;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContext(String context) {
        this.context = context;
    }

    // 邮件都有内容
    public String getContext() {
        return context;
    }

    // 允许增加邮件发送标志
    public void add(String sendInfo) {
        context = sendInfo + context;
    }

}
