package com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.view;

/**
 *
 */
public abstract class AbsView {

    private AbsLangData langData;

    // 必须要一个语言文件
    public AbsView(AbsLangData _langData) {
        this.langData = _langData;
    }

    // 获得当前的语言
    public AbsLangData getLangData() {
        return langData;
    }

    // 页面的URL路径
    public String getURI() {
        return null;
    }

    // 组装一个页面
    public abstract void assemble();

}
