package com.cheng.zenofdesignpatterns.patternpk.behavioral;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command.AbstractCmd;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command.GzipCompressCmd2;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command.GzipUnCompressCmd2;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command.Invoker;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command.ZipCompressCmd;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.command.ZipUnCompressCmd;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.strategy.CompressContext;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.command_vs_strategy.strategy.Zip;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.chain.ChinaTopDnsServer;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.chain.ChainDnsServer;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.chain.Recorder;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.chain.SHDnsServer;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.chain.TopDnsServer;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.observer.ChinaTopObserveDnsServer;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.observer.ObserverDnsServer;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.observer.SHObserveDnsServer;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.observer_vs_chain.observer.TopObserveDnsServer;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.strategy_vs_state.state.ChildState;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.strategy_vs_state.state.Human;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.strategy_vs_state.strategy.AdultWork;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.strategy_vs_state.strategy.ChildWork;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.strategy_vs_state.strategy.OldWork;
import com.cheng.zenofdesignpatterns.patternpk.behavioral.strategy_vs_state.strategy.WorkStrategyContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZoDPBehavioralPKActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("行为类模式PK");
        String content = "包括：\n" +
                "模板方法模式（Template Method）\n" +
                "观察者模式（Observer）\n" +
                "状态模式（State）\n" +
                "策略模式（Strategy）\n" +
                "职责链模式（Chain of Responsibility）\n" +
                "命令模式（Command）\n" +
                "访问者模式（Visitor）\n" +
                "调停者模式（Mediator）\n" +
                "备忘录模式（Memento）\n" +
                "迭代器模式（Iterator）\n" +
                "解释器模式（Interpreter）\n\n" +
                "1. 命令模式 VS 策略模式\n" +
                "命令模式和策略模式的类图确实很相似，只是命令模式多了一个接收者（Receiver）角色，" +
                "它们虽然同为行为类模式，但是两者的区别还是很明显的。策略模式的意图是封装算法，它" +
                "认为“算法”已经是一个完整的、不可拆分的原子业务（注意这里是原子业务，而不是原子对" +
                "象），即其意图是让这些算法独立，并且可以相互替换，让行为的变化独立于拥有行为的客" +
                "户；而命令模式则是对动作的解耦，把一个动作的执行分为执行对象（接收者角色）、执行" +
                "行为（命令角色），让两者相互独立而不相互影响。\n" +
                "同时，由于是一个命令模式，接收者的处理可以进行排队处理，在排队处理的过程中，可以" +
                "进行撤销处理，撤销也是一个命令，这是策略模式所不能实现的。\n\n" +
                "2. 小结\n" +
                "策略模式和命令模式相似，特别是命令模式退化时，比如无接收者（接收者非常简单或者接" +
                "收者是一个Java的基础操作，无需专门编写一个接收者），在这种情况下，命令模式和策略" +
                "模式的类图完全一样，代码实现也比较类似，但是两者还是有区别的。\n" +
                "- 关注点不同\n" +
                "策略模式关注的是算法替换的问题，换句话说，策略模式关注的是算法的完整性、封装性，只" +
                "有具备了这两个条件才能保证其可以自由切换。\n" +
                "命令模式则关注的是解耦问题，如何让请求者和执行者解耦是它需要首先解决的，解耦的要求" +
                "就是把请求的内容封装为一个一个的命令，由接收者执行。由于封装成了命令，就同时可以对" +
                "命令进行多种处理，例如撤销、记录等。\n" +
                "- 角色功能不同\n" +
                "策略模式中的具体算法是负责一个完整算法逻辑，它是不可再拆分的原子业务单元，一旦变更" +
                "就是对算法整体的变更。而命令模式则不同，它关注命令的实现，也就是功能的实现。命令模" +
                "式的接收者只要符合六大设计原则，完全不用关心它是否完成了一个具体逻辑，它的影响范围" +
                "也仅仅是抽象命令和具体命令，对它的修改不会扩散到模式外的模块。当然，如果在命令模式" +
                "中需要指定接收者，则需要考虑接收者的变化和封装，例如一个老顾客每次吃饭都点同一个厨" +
                "师的饭菜，那就必须考虑接收者的抽象化问题。\n" +
                "- 使用场景不同\n" +
                "策略模式适用于算法要求变换的场景，而命令模式适用于解耦两个有紧耦合关系的对象场合或" +
                "者多命令多撤销的场景。\n\n" +
                "3. 策略模式 VS 状态模式\n" +
                "在行为类设计模式中，状态模式和策略模式是亲兄弟，两者非常相似，都是通过Context类封" +
                "装一个具体的行为，都提供了一个封装的方法，是高扩展性的设计模式。但根据两者的定义，" +
                "我们发现两者的区别还是很明显的：策略模式封装的是不同的算法，算法之间没有交互，以达" +
                "到算法可以自由切换的目的；而状态模式封装的是不同的状态，以达到状态切换行为随之发生" +
                "改变的目的。这两种模式虽然都有变换的行为，但是两者的目标却是不同的。\n" +
                "对于相同的业务需求，有很多种实现方法，问题的重点是业务关注的是什么。找准了业务的焦" +
                "点，才能选择一个好的设计模式。\n\n" +
                "4. 小结\n" +
                "策略模式和状态模式的区别\n" +
                "- 环境角色的职责不同\n" +
                "两者都有一个叫做Context环境角色的类，但是两者的区别很大，策略模式的环境角色只是一" +
                "个委托作用，负责算法的替换；而状态模式的环境角色不仅仅是委托行为，它还具有登记状态" +
                "变化的功能，与具体的状态类协作，共同完成状态切换行为随之切换的任务。\n" +
                "- 解决问题的重点不同\n" +
                "策略模式旨在解决内部算法如何改变的问题，也就是将内部算法的改变对外界的影响降低到最" +
                "小，它保证的算法可以自由地切换；而状态模式旨在解决内在状态的改变而引起行为改变的问" +
                "题，它的出发点是事物的状态，封装状态而暴露行为，一个对象的状态改变，从外界看来就好" +
                "像是行为改变。\n" +
                "- 解决问题的方法不同\n" +
                "策略模式只是确保算法可以自由切换，但是什么时候用什么算法它决定不了；而状态模式对外" +
                "暴露的是行为，状态的变化一般是由环境角色和具体状态共同完成的，也就是说状态模式封装" +
                "了状态的变化而暴露了不同的行为或行为结果。\n" +
                "- 应用场景不同\n" +
                "策略模式只是一个算法的封装，可以是一个有意义的对象，也可以是一个无意义的逻辑片段，也" +
                "就是说它们都是一个抽象算法的具体实现类，从这点来看策略模式是一系列平行的、可互相替换" +
                "的算法封装后的结果，这就限定了它的应用场景：算法必须是平行的，否则策略模式就封装了一" +
                "堆垃圾，产生了“坏味道”。状态模式则要求有一系列状态发生变化的场景，它要求的是有动态且" +
                "有行为的场景，也就是一个对象必须就有二维（状态和行为）描述才能采用状态模式，如果只有" +
                "状态而没有行为，则状态的变化就失去了意义。\n" +
                "- 复杂度不同\n" +
                "通常策略模式比较简单，这里的简单指的是结构简单，扩展比较容易，而且代码也容易阅读。而" +
                "状态模式则通常比较复杂，因为它要从两个角色看到一个对象状态和行为的改变，也就是说它封" +
                "装的是变化，要知道变化是无穷尽的，因此相对来说状态模式通常都比较复杂，涉及面很多，虽" +
                "然也很容易扩展，但是一般不会进行大规模的扩张和修正。\n\n" +
                "5. 设计模式 VS 责任链模式\n" +
                "在观察者模式中提到了触发链（也叫做观察者联）的问题，一个具体的角色既可以是观察者，也" +
                "可以是被观察者，这样就形成了一个观察者链。这与责任链模式非常类似，它们都实现了事务的" +
                "链条化处理。\n\n" +
                "6. 小结\n" +
                "通过对DNS解析过程的实现，可以发现触发链和责任链虽然都是链结构，但是还是有区别的。\n" +
                "- 链中的消息对象不同\n" +
                "从首节点开始到最终的尾节点，两个链中传递的消息对象是不同的。责任链模式基本上不改变消" +
                "息对象的结构，虽然每个节点都可以参与消费（一般是不参与消费），类似于“雁过拔毛”，但是" +
                "它的结构不会改变，比如从首节点传递进来一个String对象或者Person对象，不会到链尾的时" +
                "候成了int或者Human对象，这在责任链模式中是不可能的，但是在触发链模式中是允许的，链" +
                "中传递的对象可以自由变化，只要上下级节点对传递对象了解即可，它不要求链中的消息对象不" +
                "变化，它只要求链中相邻两个节点的消息对象固定。\n" +
                "- 上下节点的关系不同\n" +
                "在责任链模式中，上下节点没有关系，都是接收同样的对象，所有传递的对象都是从链首传递过" +
                "来，上一节点是什么没有关系，只要按照自己的逻辑处理就成。而触发链模式就不同了，它的上" +
                "下级关系很亲密，下级对上级顶礼膜拜，上级对下级绝对信任，链中的任意两个相邻节点都是一" +
                "个牢固的独立团体。\n" +
                "- 消息的分销渠道不同\n" +
                "在责任链模式中，一个消息从链首传递进来后，就开始沿着链条向链尾运动，方向是单一的、固定" +
                "的；而触发链模式则不同，由于它采用的是观察者模式，所以有非常大的灵活性，一个消息传递到" +
                "链首后，具体怎么传递是不固定的，可以以广播方式传递，也可以以跳跃方式传递，这取决于处理" +
                "消息的逻辑。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 使用策略模式模拟实现Zip压缩和Gzip压缩
        // 定义环境角色
        CompressContext context;
        // 对文件执行zip压缩算法
        System.out.println("========执行算法========");
        context = new CompressContext(new Zip());
		/*
		 * 算法替换
		 * context = new CompressContext(new Gzip());
		 */
        // 执行压缩算法
        context.compress("c:\\windows","d:\\windows.zip");
        // 执行解压缩算法
        context.uncompress("c:\\windows.zip","d:\\windows");

        // 2. 使用命令模式实现压缩算法
        //定义一个命令,压缩一个文件
        AbstractCmd zipCompressCmd = new ZipCompressCmd();
        AbstractCmd zipUnCompressCmd = new ZipUnCompressCmd();
        AbstractCmd gzipCompressCmd = new GzipCompressCmd2();
        AbstractCmd gzipUnCompressCmd = new GzipUnCompressCmd2();
		/*
		 * 想换一个？执行解压命令
		 * AbstractCmd cmd = new ZipUnCompressCmd();
		 */
        // 定义调用者
        Invoker invoker1 = new Invoker(zipCompressCmd);
        // 我命令你对这个文件进行压缩
        System.out.println("========执行压缩命令========");
        invoker1.execute("c:\\windows", "d:\\windows.zip");
        Invoker invoker2 = new Invoker(zipUnCompressCmd);
        // 我命令你对这个文件进行压缩
        System.out.println("========执行解压缩命令========");
        invoker2.execute("c:\\windows", "d:\\windows.zip");
        Invoker invoker3 = new Invoker(gzipCompressCmd);
        // 我命令你对这个文件进行压缩
        System.out.println("========执行压缩命令========");
        invoker3.execute("c:\\windows", "d:\\windows.zip");
        Invoker invoker4 = new Invoker(gzipUnCompressCmd);
        // 我命令你对这个文件进行压缩
        System.out.println("========执行解压缩命令========");
        invoker4.execute("c:\\windows", "d:\\windows.zip");

        // 3. 策略模式模拟人生三个时期不同工作内容
        // 定义一个环境角色
        WorkStrategyContext workStrategyContext = new WorkStrategyContext();
        System.out.println("====儿童的主要工作=====");
        workStrategyContext.setWork(new ChildWork());
        workStrategyContext.work();
        System.out.println("\n====成年人的主要工作=====");
        workStrategyContext.setWork(new AdultWork());
        workStrategyContext.work();
        System.out.println("\n====老年人的主要工作=====");
        workStrategyContext.setWork(new OldWork());
        workStrategyContext.work();

        // 4. 状态模式模拟人生三个时期不同工作内容
        // 定义一个普通的人
        Human human = new Human();
        // 设置一个人的初始状态
        human.setState(new ChildState());
        System.out.println("====儿童的主要工作=====");
        human.work();
        System.out.println("\n====成年人的主要工作=====");
        human.work();
        System.out.println("\n====老年人的主要工作=====");
        human.work();

        // 5. 责任链模式模拟实现DNS解析过程
        // 上海域名服务器
        ChainDnsServer sh = new SHDnsServer();
        // 中国顶级域名服务器
        ChainDnsServer china = new ChinaTopDnsServer();
        // 顶级域名服务器
        ChainDnsServer top = new TopDnsServer();
        // 定义查询路径
        china.setUpperServer(top);
        sh.setUpperServer(china);
        // 解析域名
        System.out.println("=====域名解析模拟器=====");
        while(true){
            System.out.print("\n请输入域名(输入N退出):");
            String domain = null;
            try {
                domain = (new BufferedReader(new InputStreamReader(System.in))).readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(domain.equalsIgnoreCase("n")){
                break;
            }
            Recorder recorder = sh.resolve(domain);
            System.out.println("----DNS服务器解析结果----");
            System.out.println(recorder);
        }

        // 6. 触发链模式实现DNS解析过程
        // 上海域名服务器
        ObserverDnsServer shObserveDnsServer = new SHObserveDnsServer();
        // 中国顶级域名服务器
        ObserverDnsServer chinaTopObserveDnsServer = new ChinaTopObserveDnsServer();
        // 顶级域名服务器
        ObserverDnsServer topObserveDnsServer = new TopObserveDnsServer();
        // 定义查询路径
        chinaTopObserveDnsServer.setUpperServer(topObserveDnsServer);
        shObserveDnsServer.setUpperServer(chinaTopObserveDnsServer);
        // 解析域名
        System.out.println("=====域名解析模拟器=====");
        while(true){
            System.out.print("\n请输入域名(输入N退出):");
            String domain = null;
            try {
                domain = (new BufferedReader(new InputStreamReader(System.in))).readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(domain.equalsIgnoreCase("n")){
                break;
            }
            Recorder recorder = new Recorder();
            recorder.setDomain(domain);
            shObserveDnsServer.update(null, recorder);
            System.out.println("----DNS服务器解析结果----");
            System.out.println(recorder);
        }
    }
}
