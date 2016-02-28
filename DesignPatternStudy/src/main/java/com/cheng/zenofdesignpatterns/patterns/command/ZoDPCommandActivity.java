package com.cheng.zenofdesignpatterns.patterns.command;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.command.common.Command;
import com.cheng.zenofdesignpatterns.patterns.command.common.ConcreteCommand1;
import com.cheng.zenofdesignpatterns.patterns.command.common.ConcreteReciver1;
import com.cheng.zenofdesignpatterns.patterns.command.common.Invoker;
import com.cheng.zenofdesignpatterns.patterns.command.common.Receiver;
import com.cheng.zenofdesignpatterns.patterns.command.requirement.AddRequirementCommand;
import com.cheng.zenofdesignpatterns.patterns.command.requirement.ReqCommand;
import com.cheng.zenofdesignpatterns.patterns.command.requirement.DeletePageCommand;
import com.cheng.zenofdesignpatterns.patterns.command.requirement.ReqInvoker;

public class ZoDPCommandActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("命令模式");
        String content = "定义：\n" +
                "Encapsulate a request as an object, thereby letting you parameterize clients " +
                "with different requests, queue or log requests, and support undoable operations.\n" +
                "将一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化，对请求排队或者记录" +
                "请求日志，可以提供命令的撤销和恢复功能。\n\n" +
                "命令模式的优点\n" +
                "- 类间解耦\n" +
                "调用者角色与接收者角色之间没有任何依赖关系，调用者实现功能时只需调用Command抽象类的" +
                "execute方法就可以，不需要了解到底是哪个接收者执行。\n" +
                "- 可扩展性\n" +
                "Command的子类可以非常容易地扩展，而调用者Invoker和高层次的模块Client不产生严重的代" +
                "码耦合。\n" +
                "- 命令模式结合其他模式会更优秀\n" +
                "命令模式可以结合责任链模式，实现命令族解析任务；结合模板方法模式，则可以减少Command" +
                "子类的膨胀问题。\n" +
                "命令模式的缺点\n" +
                "请看Command的子类：如果有N个命令，问题就出来了，Command的子类就可不是几个，而是N个，" +
                "这个类膨胀得非常大，这个就需要在项目中慎重考虑使用。\n\n" +
                "使用场景\n" +
                "只要你认为是命令的地方就可以采用命令模式，例如，在GUI开发中，一个按钮的点击是一个命令，" +
                "可以采用命令模式；模拟DOS命令的时候，当然也要采用命令模式。\n\n" +
                "最佳实践\n" +
                "每一个模式到实际应用的时候都有一些变形，命令模式的Receiver在实际应用中一般都会被封装掉" +
                "（除非非常必要，例如撤销处理），那是因为在项目中：约定的优先级最高，每一个命令是对一个或" +
                "多个Receiver的封装，我们可以在项目中通过有意义的类名或命令名处理命令角色和接收者角色的" +
                "耦合关系（这就是约定），减少高层模块（Client类）对低层模块（Receiver角色类）的依赖关系，" +
                "提高系统整体的稳定性。因此，建议在实际的项目开发时采用封闭Receiver的方式，减少Client对" +
                "Receiver的依赖。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟需求变更命令下达后项目组中的响应
        // 定义我们的接头人
        ReqInvoker xiaoSan = new ReqInvoker();
        // 客户要求增加一项需求
        System.out.println("-------------客户要求删除一个页面-----------------");
        // 客户给我们下达增加一个页面的需求
        ReqCommand addCommand = new AddRequirementCommand();
        // 客户给我们下达删除一个页面的需求
        ReqCommand delCommand = new DeletePageCommand();
        // 接头人接受到命令
        xiaoSan.setCommand(addCommand);
        // 接头人执行命令
        xiaoSan.action();
        xiaoSan.setCommand(delCommand);
        xiaoSan.action();

        // 2. 通用命令模式演示
        // 2.1 基本实现
        // 首先声明出调用者Invoker
        Invoker invoker = new Invoker();
        // 定义接收者
        Receiver receiver = new ConcreteReciver1();
        // 定义一个发送给接收者的命令
        Command command = new ConcreteCommand1(receiver);
        // 把命令交给调用者去执行
        invoker.setCommand(command);
        invoker.action();

        // 2.2 封装掉Receiver
        // 首先声明出调用者Invoker
        Invoker invoker22 = new Invoker();
        // 定义一个发送给接收者的命令
        Command command22 = new ConcreteCommand1();
        // 把命令交给调用者去执行
        invoker22.setCommand(command22);
        invoker22.action();
    }
}
