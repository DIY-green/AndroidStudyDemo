package com.cheng.zenofdesignpatterns.principle.ocp;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;

public class ZoDPOpenClosePrincipleActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("开放关闭原则");
        String content = "定义：\n" +
                "Software entities like classes,modules and functions should be open for " +
                "extension but closed for modifications.\n" +
                "一个软件实体如类、模块和函数应该对扩展开发，对修改关闭。\n" +
                "软件实体包括以下几个部分：\n" +
                "- 项目或软件产品中按照一定的逻辑规划划分的模块\n" +
                "- 抽象和类\n" +
                "- 方法\n" +
                "注意 开闭原则告诉我们应该尽量通过扩展软件实体的行为来实现变化，而不是通过修改" +
                "已有的代码来完成变化，它是为软件实体的未来事件而制定的对现行开发设计进行约束的" +
                "一个原则。\n" +
                "开闭原则对扩展开发，对修改关闭，并不意味着不做任何修改，低层模块的变更，必然" +
                "要有高层模块进行耦合，否则就是一个孤立无意义的代码片段。\n\n" +
                "为什么要采用开闭原则\n" +
                "1. 开闭原则对测试的影响\n" +
                "新增加的类，新增加的测试方法，只要保证新增加的类是正确的就可以了\n" +
                "2. 开闭原则可以提高复用性\n" +
                "3. 开闭原则可以提高可维护性\n" +
                "4. 面向对象开发的要求\n\n" +
                "如何使用开闭原则\n" +
                "1. 抽象约束\n" +
                "通过接口或抽象类可以约束一组可能变化的行为，并且能够实现对扩展开放，其包含三层" +
                "含义：第一，通过接口或抽象类约束扩展，对扩展进行边界限定，不允许出现在接口或抽" +
                "象类中不存在的public方法；第二，参数类型、引用对象尽量使用接口或者抽象类，而不" +
                "是实现类；第三，抽象层尽量保持稳定，一旦确定即不允许修改。\n" +
                "2. 元数据（metadata）控制模块行为\n" +
                "什么是元数据？用来描述环境和数据的数据，通俗地说就是配置参数，参数可以从文件中获" +
                "得，也可以从数据库中获得 -- 其中达到极致的就是控制反转（Inversion of Control）\n" +
                "3. 指定项目章程\n" +
                "4. 封装变化\n" +
                "对变化的封装包含两层含义：第一，将相同的变化封装到一个接口或抽象类中；第二，将不" +
                "同的变化封装到不同的接口或抽象类中，不应该有两个不同的变化出现在同一个接口或抽象" +
                "类中。封装变化，也就是受保护的变化（protected variations），找出预计有变化或不" +
                "稳定的点，我们为这些变化点创建文档的接口，准确地讲是封装可能发生的变化，一旦预测" +
                "到或‘第六感’发觉有变化，就可以进行封装。\n\n" +
                "最佳实践\n" +
                "- 开闭原则也只是一个原则\n" +
                "- 项目规章非常重要\n" +
                "- 预知变化\n";
        mContentTV.setText(content);
    }

    public void onClick(View v) {
        BookStore.sell();
    }
}
