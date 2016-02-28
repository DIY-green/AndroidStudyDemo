package com.cheng.zenofdesignpatterns.patterns.prototype.notes;

import java.util.ArrayList;

/**
 * 演示深拷贝
 */
public class DeepCopy implements Cloneable {

    // 定义一个私有变量
    private ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    public DeepCopy clone(){
        DeepCopy DeepCopy=null;
        try {
            DeepCopy = (DeepCopy)super.clone();
            this.arrayList = (ArrayList<String>)this.arrayList.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return DeepCopy;
    }

    // 设置arrayList的值
    public void setValue(String value){
        this.arrayList.add(value);
    }

    // 取得arrayList的值
    public ArrayList<String> getValue(){
        return this.arrayList;
    }
}
