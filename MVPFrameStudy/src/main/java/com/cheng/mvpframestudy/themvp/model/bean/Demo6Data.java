package com.cheng.mvpframestudy.themvp.model.bean;

import com.cheng.mvpframestudy.themvp.frame.model.IModel;

import de.greenrobot.event.EventBus;

/**
 * 一个简单的javabean类
 * javabean必须实现Imodel接口
 *
 * @author kymjs (http://www.kymjs.com/) on 10/30/15.
 */
public class Demo6Data implements IModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        EventBus.getDefault().post(this);
    }
}