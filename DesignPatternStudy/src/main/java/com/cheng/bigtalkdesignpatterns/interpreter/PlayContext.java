package com.cheng.bigtalkdesignpatterns.interpreter;

/**
 * 演奏内容类（Context）
 */
public class PlayContext {
    // 演奏文本
    private String playText;

    public String getPlayText() {
        return playText;
    }

    public void setPlayText(String playText) {
        this.playText = playText;
    }
}
