package com.cheng.zenofdesignpatterns.patternpk.crosswarzone;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.facade.HRFacade;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.mediator.IPosition;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.mediator.ISalary;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.mediator.ITax;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.mediator.Mediator;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.mediator.Position;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.mediator.Salary;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.facade_vs_mediator.mediator.Tax;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.strategy_vs_bridge.strategy.MailServer;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.strategy_vs_bridge.strategy.MailTemplate;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.strategy_vs_bridge.strategy.TextMail;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.adapter.FilmStar;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.adapter.IActor;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.adapter.IStarAdapter;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.adapter.Standin;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.adapter.UnknownActor;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.bridge.AbstractStar;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.bridge.ActFilm;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.bridge.FilmStarBrdige;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.bridge.Singer;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.decorator.Deny;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.decorator.FreakStar;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.decorator.HotAir;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.decorator.IStarDecorator;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.proxy.AgentProxy;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.proxy.IStarProxy;
import com.cheng.zenofdesignpatterns.patternpk.crosswarzone.wrapper.proxy.SingerProxy;

import java.util.Date;

public class ZoDPCrossWarZonePKActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("跨战区PK");
        String content = "概述：\n" +
                "创建类模式描述如何创建对象\n" +
                "行为类模式关注如何管理对象的行为\n" +
                "结构类模式着重于如何建立一个软件结构\n\n" +
                "1. 策略模式 VS 桥梁模式\n" +
                "策略模式与桥梁模式的通用类图非常相似，如果把策略模式的环境角色变更为一个抽象类加" +
                "一个实现类，或者桥梁模式的抽象角色未实现，只有修正抽象化角色，想想看，这两个类图" +
                "有什么地方不一样？完全一样！\n" +
                "以发送文本邮件和Html邮件为例：\n" +
                "使用策略模式实现两种算法的自由切换，它提供了这样的保证：封装邮件的两种行为是可选" +
                "择的，至于选择哪个算法是由上层模块决定的。策略模式要完成的任务就是提供两种可以替" +
                "换的算法。\n" +
                "桥梁模式关注的是抽象和实现的分离，它是结构型模式，结构型模式研究的是如何建立一个" +
                "软件架构。\n\n" +
                "2. 最佳实践\n" +
                "策略模式和桥梁模式是如此相似，我们只能从它们的意图上来分析。策略模式是一个行为模" +
                "式，旨在封装一系列的行为，在例子中我们认为把邮件的必要信息（发件人、收件人、标题、" +
                "内容）封装成一个对象就是一个行为，封装的格式（算法）不同，行为也就不同。而桥梁模" +
                "式则是解决在不破坏封装的情况下如何抽取出它的抽象部分和实现部分，它的前提是不破坏" +
                "封装，让抽象部分和实现部分都可以独立地变化，在例子中，我们的邮件服务器和邮件模板" +
                "是不是都可以独立地变化？不管是邮件服务器还是邮件模板，只要继承了抽象类就可以继续" +
                "扩展，它的主旨是建立一个不破坏封装性的可扩展架构。\n" +
                "简单来说，策略模式是使用继承和多态建立一套可以自由切换算法的模式，桥梁模式是在不" +
                "破坏封装的前提下解决抽象和实现都可以独立扩展的模式。桥梁模式必然有两个“桥墩”--抽" +
                "象化角色和实现化角色，只要桥墩搭建好，桥就有了，而策略模式只有一个抽象角色，可以" +
                "没有实现，也可以有很多实现。\n" +
                "还是很难区分，是吧？多想想两者的意图，就可以理解为什么要建立两个相似的模式了。我" +
                "们在做系统设计时，可以不考虑到底使用的策略模式还是桥梁模式，只要好用，能够解决问" +
                "题就成。\n\n" +
                "3. 门面模式 VS 中介者模式\n" +
                "门面模式为复杂的子系统提供一个统一的访问界面，它定义的是一个高层接口，该接口使得" +
                "子系统更加容易使用，避免外部模块深入到子系统内部而产生与子系统内部细节耦合的问题。" +
                "中介者模式使用一个中介对象来封装一系列同事对象的交互行为，它使各对象之间不再显式" +
                "地引用，从而使其耦合松散，建立一个可扩展的应用架构。\n" +
                "我们回过头来分析一下设计，在接收到需求后我们发现职位、工资、税收之间有着紧密的耦" +
                "合关系，如果不采用中介者模式，则每个对象都要与其他两个对象进行通信，这势必会增加" +
                "系统的复杂性，同时也使系统处于僵化状态，很难实现拥抱变化的理想。通过增加一个中介" +
                "者，每个同事类的职位、工资、税收都只与中介者通信，中介者封装了各个同事类之间的逻" +
                "辑关系，方便系统的扩展和维护。\n" +
                "门面模式对子系统起封装作用，它可以提供一个统一的对外服务接口。\n\n" +
                "4. 最佳实践\n" +
                "门面模式和中介者模式之间的区别还是比较明显的，门面模式是以封装和隔离为主要任务，" +
                "而中介者模式则是以调和同事类之间的关系为主，因为要调和，所以具有了部分的业务逻辑" +
                "控制。两者的主要区别如下：\n" +
                "- 功能区别\n" +
                "门面模式只是增加了一个门面，它对子系统来说没有增加任何的功能，子系统若脱离门面模" +
                "式是完全可以独立运行的。而中介者模式则增加了业务功能，它把各个同事类中的原有耦合" +
                "关系移植到了中介者，同事类不可能脱离中介者而独立存在，除非是想增加系统的复杂性和" +
                "降低扩展性。\n" +
                "- 知晓状态不同\n" +
                "对门面模式来说，子系统不知道有门面存在，而对中介者来说，每个同事类都知道中介者存" +
                "在，因为要依靠中介者调和和同事之间的关系，它们对中介者非常了解。\n" +
                "- 封装程度不同\n" +
                "门面模式是一中简单的封装，所有的请求处理都委托给子系统完成，而中介者模式则需要有" +
                "一个中心，由中心协调同事类完成，并且中心本身也完成部分业务，它属于更进一步的业务" +
                "功能封装。\n\n" +
                "5. 包装模式群PK\n" +
                "包装模式（wrapping pattern）。注意，包装模式是一组模式而不是一个。包装模式包括：" +
                "装饰模式、适配器模式、门面模式、代理模式、桥梁模式。\n\n" +
                "6. 最佳实践\n" +
                "5个包装模式是大家在系统设计中经常会用到的模式，它们具有相似的特征：都是通过委托" +
                "的方式对一个对象或一系列对象（例如门面模式）施行包装，有了包装，设计的系统才更加" +
                "灵活、稳定，并且极具扩展性。从实现的角度来看，它们都是代理的一种具体表现形式，我" +
                "们来看看它们在使用场景上有什么区别。\n" +
                "代理模式主要用在不希望展示一个对象内部细节的场景中，比如一个远程服务不需要把远程" +
                "连接的所有细节都暴露给外部模块，通过增加一个代理类，可以很轻松地实现被代理类的功" +
                "能封装。此外，代理模式还可以用在一个对象的访问需要限制的场景中，比如AOP。\n" +
                "装饰模式是一种特殊的代理模式，它倡导的是在不改变接口的前提下为对象增强功能，或者" +
                "动态添加额外职责。就扩展性而言，它比子类更加灵活，例如在一个已经运行的项目中，可" +
                "以很轻松地通过增加装饰类来扩展系统的功能。\n" +
                "适配器模式的主要意图是接口转换，把一个对象的接口转换成系统希望的另外一个接口，这" +
                "里的系统指的不仅仅是一个应用，也可能是某个环境，比如通过接口转换可以屏蔽外界接口，" +
                "以免外界接口深入系统内部，从而提高系统的稳定性和可靠性。\n" +
                "桥梁模式是在抽象层产生耦合，解决的是自行扩展的问题，它可以使两个有耦合关系的对象" +
                "互不影响地扩展，比如对于使用笔画图这样的需求，可以采用桥梁模式设计成用什么笔（铅" +
                "笔、毛笔）画什么图（圆形、方形）的方案，至于以后需求的变更，如增加笔的类型，增加" +
                "图形等，对该设计来说是小菜一碟。\n" +
                "门面模式是一个粗粒度的封装，它提供一个方便访问子系统的接口，不具有任何的业务逻辑，" +
                "仅仅是一个访问复杂系统的快速通道，没有它，子系统照样运行，有了它，只是更方便访" +
                "问而已。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 策略模式模拟实现邮件发送
        // 创建一封TEXT格式的邮件
        MailTemplate m = new TextMail("a@a.com","b@b.com","外星人攻击地球了","结局是外星人被中国人熬汤炖着吃了！");
        // 创建一个Mail发送程序
        MailServer mail = new MailServer(m);
        // 发送邮件
        mail.sendMail();

        // 2. 桥梁模式模拟实现邮件发送
        //创建一封TEXT格式的邮件
        com.cheng.zenofdesignpatterns.
                patternpk.crosswarzone.
                strategy_vs_bridge.bridge.
                MailTemplate mailTemplate
                = new com.cheng.zenofdesignpatterns.
                patternpk.crosswarzone.
                strategy_vs_bridge.bridge.
                HtmlMail("a@a.com", "b@b.com", "外星人攻击地球了", "结局是外星人被中国人熬汤炖着吃了！");
        //创建一个Mail发送程序
        com.cheng.zenofdesignpatterns.
                patternpk.crosswarzone.
                strategy_vs_bridge.bridge.
                MailServer postfix
                = new com.cheng.zenofdesignpatterns.
                patternpk.crosswarzone.
                strategy_vs_bridge.bridge.
                Postfix(mailTemplate);
        //发送邮件
        postfix.sendMail();

        // 3. 中介者模式模拟实现工资计算
        // 定义中介者
        Mediator mediator = new Mediator();
        // 定义各个同事类
        IPosition position = new Position(mediator);
        ISalary salary = new Salary(mediator);
        ITax tax = new Tax(mediator);
        // 职位提升了
        System.out.println("===职位提升===");
        position.promote();

        // 4. 门面模式模拟实现工资计算
        // 定义门面
        HRFacade facade = new HRFacade();
        System.out.println("===外系统查询总收入===");
        int querySalary = facade.querySalary("张三",new Date(System.
                currentTimeMillis()));
        System.out.println( "张三 11月 总收入为：" +querySalary);
        //再查询出勤天数
        System.out.println("\n===外系统查询出勤天数===");
        int workDays = facade.queryWorkDays("李四");
        System.out.println("李四 本月出勤：" +workDays);

        // 5. 代理模式模拟明星和代理人
        // 崇拜的明星是谁
        IStarProxy star = new SingerProxy();
        //找到明星的代理人
        IStarProxy agent = new AgentProxy(star);
        System.out.println("追星族：我是你的崇拜者，请签名！");
        // 签字
        agent.sign();

        // 7. 使用装饰模式粉饰明星
        // 定义出这个所谓的明星
        IStarDecorator freakStar = new FreakStar();
        // 看看他是怎么粉饰自己的
        // 开演前吹嘘自己无所不能
        freakStar = new HotAir(freakStar);
        // 演完毕后，死不承认自己演的烂
        freakStar = new Deny(freakStar);
        // 看看他这副嘴脸
        System.out.println("====看看一些虚假明星的嘴脸====");
        freakStar.act();

        // 8. 使用适配器模式模拟替身演员
        System.out.println("\n=======演戏过程模拟==========\n");
        // 定义一个大明星
        IStarAdapter filmStar = new FilmStar();
        filmStar.act("前十五分钟，明星本人演戏");
        // 导演把一个普通演员认为是一个明星演员
        IActor actor = new UnknownActor();
        IStarAdapter standin= new Standin(actor);
        standin.act("中间五分钟，替身在演戏");
        filmStar.act("后十五分钟，明星本人演戏");

        // 9. 使用桥梁模式模拟明星多职业
        // 声明出一个电影明星
        AbstractStar zhangSan = new FilmStarBrdige();
        // 声明出一个歌星
        AbstractStar liSi = new Singer();
        // 展示一下各个明星的主要工作
        zhangSan.doJob();
        liSi.doJob();
        // 当然，也有部分明星不务正业，比如歌星演戏
        liSi = new Singer(new ActFilm());
        liSi.doJob();
    }

}
