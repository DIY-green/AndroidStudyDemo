package com.cheng.bigtalkdesignpatterns.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 网站工厂类
 */
public class WebSiteFactory {
    private Map<String, WebSite> flyweights = new HashMap<>();

    // 获得网站分类
    public WebSite getWebSiteCategory(String _key) {
        if (!flyweights.containsKey(_key)) {
            flyweights.put(_key, new ConcreteWebSite(_key));
        }
        return flyweights.get(_key);
    }

    // 获得网站分类总数
    public int getWebSiteCount() {
        return flyweights.size();
    }
}
