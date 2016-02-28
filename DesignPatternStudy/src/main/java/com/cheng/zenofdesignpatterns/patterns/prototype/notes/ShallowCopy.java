package com.cheng.zenofdesignpatterns.patterns.prototype.notes;

import java.util.ArrayList;

/**
 * 演示浅拷贝
 */
public class ShallowCopy implements Cloneable {

    // 定义一个私有变量
    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    public ShallowCopy clone() {
        ShallowCopy shallowCopy = null;
        try {
            shallowCopy = (ShallowCopy) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return shallowCopy;
    }

    // 设置ArrayList的值
    public void setValue(String value){
        this.arrayList.add(value);
    }

    // 取得arrayList的值
    public ArrayList<String> getValue(){
        return this.arrayList;
    }
}
