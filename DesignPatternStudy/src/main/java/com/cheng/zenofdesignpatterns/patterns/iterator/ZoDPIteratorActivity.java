package com.cheng.zenofdesignpatterns.patterns.iterator;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.iterator.common.Aggregate;
import com.cheng.zenofdesignpatterns.patterns.iterator.common.ConcreteAggregate;
import com.cheng.zenofdesignpatterns.patterns.iterator.common.Iterator;
import com.cheng.zenofdesignpatterns.patterns.iterator.project.IProject;
import com.cheng.zenofdesignpatterns.patterns.iterator.project.IProjectIterator;
import com.cheng.zenofdesignpatterns.patterns.iterator.project.Project;

public class ZoDPIteratorActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("迭代器模式");
        String content = "定义：\n" +
                "Provide a way to access the elements of an aggregate object sequentially " +
                "without exposing its underlying representation.\n" +
                "它提供一种方法访问一个容器对象中各个元素，而又不需要暴露该对象的内部细节。\n\n" +
                "最佳实践\n" +
                "如果是做Java开发，尽量不要自己写迭代器模式！省省吧，使用Java提供的Iterator一般" +
                "能满足你的要求了。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟给老大汇报项目信息
        // 定义一个List，存放所有的项目对象
        IProject project = new Project();
        // 增加星球大战项目
        project.add("星球大战项目ddddd",10,100000);
        // 增加扭转时空项目
        project.add("扭转时空项目",100,10000000);
        // 增加超人改造项目
        project.add("超人改造项目",10000,1000000000);
        // 这边100个项目
        for(int i=4;i<104;i++){
            project.add("第"+i+"个项目",i*5,i*1000000);
        }
        //遍历一下ArrayList，把所有的数据都取出
        IProjectIterator projectIterator = project.iterator();
        while(projectIterator.hasNext()){
            IProject p = (IProject)projectIterator.next();
            System.out.println(p.getProjectInfo());
        }

        // 2. 通用迭代器模式演示
        //声明出容器
        Aggregate agg = new ConcreteAggregate();
        // 产生对象数据放进去
        agg.add("abc");
        agg.add("aaa");
        agg.add("1234");
        // 遍历一下
        Iterator iterator = agg.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
