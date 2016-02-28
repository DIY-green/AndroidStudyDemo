package com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.valuestack;

/**
 *
 */
public class ValueStack {

    /**
     * 值栈的两个职责
     * - 管理堆栈
     * 不仅仅是出栈、入栈这么简单，它要管理栈中数据，同时还要允许前置拦截器对栈中
     * 数据进行修改，限制后置拦截器对栈的修改，还要把栈中数据与HTTPServletRequest
     * 中的数据建立关联。
     * - 值映射
     * 从HTTP传递过来的数据都是字符串结构，那怎么才能转化成一个业务对象呢？比如在
     * 页面上有一个登录框，输入用户名（userName）和密码（password）。提交到MVC框
     * 架中怎么才能转为一个User对象呢？这也是值栈要完成的职责。
     * 怎么实现一个值的映射，这也是一个反射操作的结果。首先是HTTP传递过来的参数名
     * 称中要明确映射到哪一个对象，例如使用点号（.）区分，点号1前是对象名称，点号
     * 后是属性名，如此规定后就可以轻松地处理了。（可以考虑使用一些开源工具，如dozer）
     */

}
