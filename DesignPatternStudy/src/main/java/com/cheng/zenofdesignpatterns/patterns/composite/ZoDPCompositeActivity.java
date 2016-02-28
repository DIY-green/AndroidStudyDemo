package com.cheng.zenofdesignpatterns.patterns.composite;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.composite.common.Component;
import com.cheng.zenofdesignpatterns.patterns.composite.common.Composite;
import com.cheng.zenofdesignpatterns.patterns.composite.common.Leaf;
import com.cheng.zenofdesignpatterns.patterns.composite.transmode.TransModeComponent;
import com.cheng.zenofdesignpatterns.patterns.composite.transmode.TransModeComposite;
import com.cheng.zenofdesignpatterns.patterns.composite.transmode.TransModeLeaf;
import com.cheng.zenofdesignpatterns.patterns.composite.treestat.Branch;
import com.cheng.zenofdesignpatterns.patterns.composite.treestat.Corp;
import com.cheng.zenofdesignpatterns.patterns.composite.treestat.TreestatLeaf;

import java.util.ArrayList;

public class ZoDPCompositeActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("组合模式");
        String content = "定义：\n" +
                "Compose objects into tree structures to represent part-whole hierarchies." +
                "TransModeComposite lets clients treat individual objects and compositions " +
                "of objects uniformly.\n" +
                "将对象组合成树形结构以表示‘部分-整体’的层次结构，使得用户对单个对象和组合对象的" +
                "使用具有一致性。\n\n" +
                "组合模式的优点\n" +
                "- 高层模块调用简单\n" +
                "一棵树结构中的所有节点都是Component，局部和整体对地拥着来说没有任何区别，也就是" +
                "说，高层模块不必关系自己处理的是单个对象还是整个组合结构，简化了高层模块的代码。\n" +
                "- 节点自由增加\n" +
                "使用了组合模式后，可以看看，如果想增加一个树枝节点、树叶节点都很容易，只要找到它" +
                "的父节点就成，非常容易扩展，符合开闭原则，对以后的维护非常有利。\n" +
                "组合模式的缺点\n" +
                "在树叶和树枝使用时，直接使用了实现类。这在面向接口编程上是很不恰当的，与依赖倒置" +
                "原则冲突。\n\n" +
                "使用场景\n" +
                "- 维护和展示部分-整体关系的场景，如树形菜单、文件和文件夹管理\n" +
                "- 从一个整体中能够独立出部分模块或功能的场景\n\n" +
                "注意事项\n" +
                "只要是树形结构，就要考虑使用组合模式，这个一定要记住，只要是要体现局部和整体的关" +
                "系还可能比较深，考虑一下组合模式吧。\n\n" +
                "最佳实践\n" +
                "树状结构、XML、成员结构。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟公司人员结构
        // 首先是组装一个组织结构出来
        Branch ceo = compositeCorpTree();
        // 首先把CEO的信息打印出来：
        System.out.println(ceo.getInfo());
        // 然后是所有员工信息
        System.out.println(getTreeInfo(ceo));

        // 2. 通用组合模式演示
        // 2.1 安全组合模式
        Composite root = new Composite();
        root.doSomething();
        Composite branch = new Composite();
        Leaf leaf = new Leaf();
        root.add(branch);
        branch.add(leaf);
        display(root);

        // 2.2 透明组合模式
        // 创建一个根节点
        TransModeComposite tmroot = new TransModeComposite();
        tmroot.doSomething();
        // 创建一个树枝构件
        TransModeComposite tmbranch = new TransModeComposite();
        // 创建一个叶子节点
        TransModeLeaf tmleaf = new TransModeLeaf();
        // 建立整体
        tmroot.add(tmbranch);
        tmbranch.add(tmleaf);
        display(tmroot);
    }

    // 把整个树组装出来
    private Branch compositeCorpTree(){
        // 首先产生总经理CEO
        Branch root = new Branch("王大麻子","总经理",100000);
        // 把三个部门经理产生出来
        Branch developDep = new Branch("刘大瘸子","研发部门经理",10000);
        Branch salesDep = new Branch("马二拐子","销售部门经理",20000);
        Branch financeDep = new Branch("赵三驼子","财务部经理",30000);

        // 再把三个小组长产生出来
        Branch firstDevGroup = new Branch("杨三乜斜","开发一组组长",5000);
        Branch secondDevGroup = new Branch("吴大棒槌","开发二组组长",6000);

        // 把所有的小兵都产生出来
        TreestatLeaf a = new TreestatLeaf("a","开发人员",2000);
        TreestatLeaf b = new TreestatLeaf("b","开发人员",2000);
        TreestatLeaf c = new TreestatLeaf("c","开发人员",2000);
        TreestatLeaf d = new TreestatLeaf("d","开发人员",2000);
        TreestatLeaf e = new TreestatLeaf("e","开发人员",2000);
        TreestatLeaf f = new TreestatLeaf("f","开发人员",2000);
        TreestatLeaf g = new TreestatLeaf("g","开发人员",2000);
        TreestatLeaf h = new TreestatLeaf("h","销售人员",5000);
        TreestatLeaf i = new TreestatLeaf("i","销售人员",4000);
        TreestatLeaf j = new TreestatLeaf("j","财务人员",5000);
        TreestatLeaf k = new TreestatLeaf("k","CEO秘书",8000);
        TreestatLeaf zhengLaoLiu = new TreestatLeaf("郑老六","研发部副经理",20000);

        // 开始组装
        // CEO下有三个部门经理和一个秘书
        root.addSubordinate(k);
        root.addSubordinate(developDep);
        root.addSubordinate(salesDep);
        root.addSubordinate(financeDep);

        //研发部经理
        developDep.addSubordinate(zhengLaoLiu);
        developDep.addSubordinate(firstDevGroup);
        developDep.addSubordinate(secondDevGroup);

        // 看看开发两个开发小组下有什么
        firstDevGroup.addSubordinate(a);
        firstDevGroup.addSubordinate(b);
        firstDevGroup.addSubordinate(c);
        secondDevGroup.addSubordinate(d);
        secondDevGroup.addSubordinate(e);
        secondDevGroup.addSubordinate(f);

        // 再看销售部下的人员情况
        salesDep.addSubordinate(h);
        salesDep.addSubordinate(i);

        // 最后一个财务
        financeDep.addSubordinate(j);

        return root;
    }

    // 遍历整棵树,只要给我根节点，我就能遍历出所有的节点
    private String getTreeInfo(Branch root){
        ArrayList<Corp> subordinateList = root.getSubordinate();
        String info = "";
        for (Corp s : subordinateList) {
            if (s instanceof TreestatLeaf) { //是员工就直接获得信息
                info = info+ s.getInfo()+"\n";
            } else { // 是个小头目
                info = info +s.getInfo() +"\n"+ getTreeInfo((Branch)s);
            }
        }
        return info;
    }

    private void display(Composite root) {
        // 通过递归遍历树
        for (Component c : root.getChildren()) {
            if (c instanceof Leaf) { // 叶子节点
                c.doSomething();
            } else {
                display((Composite) c); // 普通模式下需要强转
            }
        }
    }

    private void display(TransModeComponent root) {
        // 通过递归遍历树
        for (TransModeComponent c : root.getChildren()) {
            if (c instanceof TransModeLeaf) { // 叶子节点
                c.doSomething();
            } else {
                display(c); // 透明模式下不需要强转
            }
        }
    }
}
