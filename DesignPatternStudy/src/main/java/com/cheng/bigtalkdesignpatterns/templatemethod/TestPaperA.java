package com.cheng.bigtalkdesignpatterns.templatemethod;

/**
 * 学生甲抄的试卷
 */
public class TestPaperA extends TestPaper {

    @Override
    protected String answer1() {
        return "b";
    }

    @Override
    protected String answer2() {
        return "c";
    }

    @Override
    protected String answer3() {
        return "a";
    }
}
