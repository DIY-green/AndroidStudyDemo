package com.cheng.bigtalkdesignpatterns.templatemethod;

import android.util.Log;

/**
 * 金庸小说考题试卷
 */
public abstract class TestPaper {

    private static final String TAG = "TestPaper";

    public void testQuestion1() {
        Log.e(TAG, "杨过得到，后来给了郭靖，练成倚天剑、屠龙刀的玄铁可能是（）：\n"
        + "a. 球磨铸铁 b. 马口铁 c. 高速合金钢 d. 碳素纤维");
        Log.e(TAG, "答案：" + answer1());
    }

    public void testQuestion2() {
        Log.e(TAG, "杨过、程英、陆无双铲除了情花，造成（）：\n"
        + "a. 使这种植物不再害人 b. 使一种珍稀物种灭绝 "
        + "c. 破坏了那个生物圈的生态平衡 d. 造成该地区沙漠化");
        Log.e(TAG, "答案：" + answer2());
    }

    public void testQuestion3() {
        Log.e(TAG, "蓝凤凰致使华山师徒、桃谷六仙呕吐不止，如果你是大夫，会给他们开什么药（）：\n"
        + "a. 阿司匹林 b. 牛黄解毒片 c. 氟哌酸 "
        + "d. 让他们喝大量的生牛奶 e. 以上全不对");
        Log.e(TAG, "答案：" + answer3());
    }

    protected abstract String answer1();
    protected abstract String answer2();
    protected abstract String answer3();
}
