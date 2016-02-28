package com.cheng.zenofdesignpatterns.extension.newpatterns;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.extension.newpatterns.servant.clean.Cleaner;
import com.cheng.zenofdesignpatterns.extension.newpatterns.servant.clean.Cloth;
import com.cheng.zenofdesignpatterns.extension.newpatterns.servant.clean.Garden;
import com.cheng.zenofdesignpatterns.extension.newpatterns.servant.clean.Kitchen;
import com.cheng.zenofdesignpatterns.extension.newpatterns.specification.common.BizSpecification;
import com.cheng.zenofdesignpatterns.extension.newpatterns.specification.common.ISpecification;
import com.cheng.zenofdesignpatterns.extension.newpatterns.specification.userquery.IUserProvider;
import com.cheng.zenofdesignpatterns.extension.newpatterns.specification.userquery.IUserSpecification;
import com.cheng.zenofdesignpatterns.extension.newpatterns.specification.userquery.User;
import com.cheng.zenofdesignpatterns.extension.newpatterns.specification.userquery.UserByAgeThan;
import com.cheng.zenofdesignpatterns.extension.newpatterns.specification.userquery.UserByNameLike;
import com.cheng.zenofdesignpatterns.extension.newpatterns.specification.userquery.UserProvider;

import java.util.ArrayList;

public class UiZoDPNewPatterns extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("新模式");
        String content = "五种常用的新模式：\n" +
                "- 规格模式\n" +
                "- 对象池模式\n" +
                "- 雇工模式\n" +
                "- 黑板模式\n" +
                "- 空对象模式\n\n" +
                "1. 规格模式\n" +
                "规格模式，是组合模式的一种特殊应用。\n" +
                "在示例中可以看到，基类代表的是所有的规格书，它的目的是描述一个完整的、可组合的规" +
                "格书，它代表的是一个整体，其下的And规格书、Oe规格书、Not规格书、年龄大于基准年龄" +
                "规格书等都是一个真实的实现，也就是一个局部。\n\n" +
                "2. 对象池模式\n" +
                "对象池模式，或者称为对象池服务，其意图如下：\n" +
                "通过循环使用对象，减少资源在初始化和释放时的昂贵损耗。\n" +
                "注意 这里的“昂贵”可能是时间效益（如性能），也可能是空间效益（如并行处理），在大" +
                "多数的情况下，“昂贵”指性能。\n" +
                "通常情况下，在重复生成对象的操作成为影响性能的关键因素时，才适合进行对象池化。但" +
                "是若池化所带来的性能提高并不显著或重要的话，建议放弃对象池化技术，以保持代码的简" +
                "明，转而使用更好的硬件来提高性能为佳。\n" +
                "对象池技术在Java领域已经非常成熟，C3P0、DBCP、Proxool等连接池，都是对象池模式" +
                "的典型应用。在实际开发中若需要对象池，建议使用common-pool工具包来实现，简单、" +
                "快捷、高效。\n\n" +
                "3. 雇工模式\n" +
                "雇工模式也叫做仆人模式（Servant Design Pattern），其意图是：\n" +
                "雇工模式是行为模式的一种，它为一组类提供通用的功能，而不需要类实现这些功能，它是" +
                "命令模式的一种扩展（是命令模式的一种简化）。\n\n" +
                "4. 黑板模式\n" +
                "黑板模式是观察者模式的一个扩展，其意图是：\n" +
                "允许消息的读写同时进行，广泛地交互消息。\n" +
                "简单地说，黑板模式允许多个消息读写者同时存在，消息的生产者和消费者完全分开。这就" +
                "像一个黑板，任何一个教授（消息的生产者）都可以在其上书写消息，任何一个学生（消息" +
                "的消费者）都可以从黑板上读取消息，两者在空间和时间上可以解耦，并且互不干扰。。\n" +
                "黑板模式是消息的广播，主要解决的问题是消息的生产者和消费者之间的耦合问题，它的核" +
                "心是消息存储（黑板），它存储所有消息，并可以随时被读取。当消息生产者把消息写入到" +
                "消息仓库后，其他消费者就可以从仓库中读取。当然，此时消息的写入者也可以变身为消息" +
                "的阅读者，读写者在时间上解耦。对于这些消息，消费者只需要关注特定消息，不处理与自" +
                "己不相关的消息，这一点通常通过过滤器来实现。\n" +
                "黑板模式的实现方法\n" +
                "黑板模式一般不会对架构产生什么影响，但它通常会要求有一个清晰的消息结构。黑板模式" +
                "一般都会提供一系列的过滤器，以便消息的消费者不再接触到与自己无关的消息。在实际开" +
                "发中，黑板模式常见的有两种实现方式。\n" +
                "- 数据库作为黑板\n" +
                "利用数据库充当黑板，生产者更新数据信息，不同的消费者共享数据库中信息，这是最常见" +
                "的实现方式。该方式在技术上容易实现，开发量较少，熟悉度较高。缺点是在大量消息和高" +
                "频率访问的情况下，性能会受到一定影响。\n" +
                "在该模式下，消息的读取是通过消费者主动“拉取”，因此该模式也叫做“拉模式”。\n" +
                "- 消息队列作为黑板\n" +
                "以消息队列作为黑板，通过订阅-发布模型即可实现黑板模式。这也是黑板模式被淡忘的一" +
                "个重要原因：消息队列（Message Queue）已经非常普及了，做Java开发的已经没有几个" +
                "不知道消息队列的。\n" +
                "在该模式下，消费者接收到的消息是被主动推送过来的，因此该模式也称为“推模式”。\n" +
                "提示 　黑板模式不做详细讲解，因为我们现在已经在大量使用消息队列，既可以做到消息" +
                "的同步处理，也可以实现异步处理，相信大家已经在开发中广泛使用了，它已经成为跨系" +
                "统交互的一个事实标准了。\n\n" +
                "5. 空对象模式\n" +
                "空对象模式是通过实现一个默认的无意义对象来避免null值出现，简单地说，就是为了避" +
                "免在程序中出现null值判断而诞生的一种常用设计方法。\n" +
                "空对象模式是通过空代码实现一个接口或抽象类的所有方法，以满足开发需求，简化程序。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 使用规格模式模拟仿SQL查询集合中数据
        // 首先初始化一批用户
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(new User("苏国庆",23));
        userList.add(new User("国庆牛",82));
        userList.add(new User("张国庆三",10));
        userList.add(new User("李四",10));
        // 定义一个用户查询类
        IUserProvider userProvider = new UserProvider(userList);
        // 打印出名字以国庆开头的人员
        System.out.println("===名字以国庆开头的人员===");
        // 定义一个规格
        IUserSpecification userSpec1 = new UserByNameLike("%国庆%");
        IUserSpecification userSpec2 = new UserByAgeThan(20);
        userList = userProvider.findUser(userSpec1);
        for(User u : userProvider.findUser(userSpec2)){
            System.out.println(u);
        }

        // 2. 通用规格模式演示
        // 待分析的对象
        ArrayList<Object> list = new ArrayList<Object>();
        // 定义两个业务规格
        ISpecification bizSpec1 = new BizSpecification(new Object());
        ISpecification bizSpec2 = new BizSpecification(new Object());
        // 规则的调用
        for (Object obj : list) {
            if (bizSpec1.and(bizSpec2).isSatisfiedBy(obj)) {  // and操作
                System.out.println(obj);
            }
        }

        // 3. 使用雇工模式模拟清洁者清洁
        // 厨师清洁厨房
        Cleaner cookie = new Cleaner();
        cookie.clean(new Kitchen());
        // 园丁清洁花园
        Cleaner gardener = new Cleaner();
        gardener.clean(new Garden());
        // 裁缝清洁衣服
        Cleaner tailer = new Cleaner();
        tailer.clean(new Cloth());
    }
}
