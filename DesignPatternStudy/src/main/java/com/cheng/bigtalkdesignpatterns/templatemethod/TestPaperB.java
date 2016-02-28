package com.cheng.bigtalkdesignpatterns.templatemethod;

/**
 * 学生乙抄的试卷
 */
public class TestPaperB extends TestPaper {

    @Override
    protected String answer1() {
        return "c";
    }

    @Override
    protected String answer2() {
        return "a";
    }

    @Override
    protected String answer3() {
        return "a";
    }
}
