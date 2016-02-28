package com.cheng.zenofdesignpatterns.principle.isp;

import java.util.Map;

/**
 * 提供给管理员的混合查询的接口
 */
public interface IComplexBookSeacher {
    void complexSearch(Map map);
}
