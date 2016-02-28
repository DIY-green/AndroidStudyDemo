package com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.view;

import java.util.Map;

/**
 *
 */
public class SwfView extends AbsView {

    public SwfView(AbsLangData _langData) {
        super(_langData);
    }

    @Override
    public void assemble() {
        Map<String, String> langMap = getLangData().getItems();
        for (String key : langMap.keySet()) {
            /*
			 * 组装一个HTTP的请求格式：
			 * http://abc.com/xxx.swf?key1=value&key2=value
			 */
        }
    }

}
