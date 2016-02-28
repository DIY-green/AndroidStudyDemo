package com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.view;

import com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.valuestack.ValueStack;
import com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.helper.ValueStackHelper;

/**
 *
 */
public class ViewManager {

    // Action的名称
    private String actionName;
    // 当前的值栈
    private ValueStack valueStack = ValueStackHelper.getValueStack();

    // 接受一个ActionName，初始化所有的视图
    public ViewManager(String _actionName) {
        this.actionName = _actionName;
    }

    // 根据model的返回结果提供视图
    public String getViewPath(String result) {
        // 根据值栈查找到需要提供的语言包
        AbsLangData langData = new GBLangData();
        // 根据action和result查找到指定的视图,并加载语言
        AbsView view = new JspView(langData);
        // 返回视图的地址
        return view.getURI();
    }
}
