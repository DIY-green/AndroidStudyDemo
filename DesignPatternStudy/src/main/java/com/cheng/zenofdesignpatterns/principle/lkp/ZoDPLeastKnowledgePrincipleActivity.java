package com.cheng.zenofdesignpatterns.principle.lkp;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ZoDPLeastKnowledgePrincipleActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("最少知识原则（迪米特法则）");
        String content = "定义：\n" +
                "Only talk to your immediate friends.\n" +
                "一个对象应该对其他对象有最少的了解。通俗地讲，一个类应该对自己需要耦合或" +
                "调用的类知道得最少，你（被耦合或调用的类）的内部是如何复杂都和我没关系，" +
                "那是你的事情，我就知道你提供的这么多public方法，我就调用这么多，其他的我" +
                "一概不关心。\n\n" +
                "迪米特法则对类的低耦合提出了明确的要求，其包含以下4层含义：\n" +
                "1. 只和朋友交流\n" +
                "两个对象之间的耦合就成为朋友关系，这种关系的类型有很多，例如组合、聚合、依赖等\n" +
                "2. 朋友间也是有距离的\n" +
                "注意 迪米特法则要求类‘羞涩’一点，尽量不要对外公布太多的public方法和非静态的" +
                "public变量，尽量内敛，多使用private、package-private、protected等访问权限\n" +
                "3. 是自己的就是自己的\n" +
                "如果一个方法放在本类中，既不增加类间关系，也对本类不产生负面影响，那就放置在本类中\n" +
                "4. 谨慎使用Serializable\n\n" +
                "最佳实践\n" +
                "迪米特法则要求类间解耦，但解耦是有限度的，除非是计算机的最小单元--二进制的0和1." +
                "那才是完全解耦，在实际的项目中，需要适度地考虑这个原则，别为了套用原则而做项目。" +
                "原则只是提供参考，如果违背了这个原则，项目也未必会失败，这就需要大家在采用原则" +
                "时反复度量，不遵循是不对的，严格执行就是‘过犹不及’。";
        mContentTV.setText(content);
    }

    public void onClick(View v) {
        // 体育老师让体育委员清点班上女生的人数
        List<Girl> listGirl = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listGirl.add(new Girl());
        }
        Teacher teacher = new Teacher();
        teacher.commond(new GroupLeader(listGirl));

        // 模拟软件安装
        InstallSoftware invoker = new InstallSoftware();
        invoker.installWizard(new Wizard());
    }
}
