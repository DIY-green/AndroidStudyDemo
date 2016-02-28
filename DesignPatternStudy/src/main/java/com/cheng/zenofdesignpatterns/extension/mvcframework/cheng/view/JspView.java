package com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.view;

import java.util.Map;

/**
 *
 */
public class JspView extends AbsView {

    // 传递一个语言配置文件
    public JspView(AbsLangData _langData) {
        super(_langData);
    }

    @Override
    public void assemble() {
        Map<String, String> langMap = getLangData().getItems();
        for (String key : langMap.keySet()) {
            /*
			 * 直接替换文件中的语言条目
			 */
        }

    }

}
