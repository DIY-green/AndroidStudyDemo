package com.cheng.zenofdesignpatterns.patterns.proxy;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.proxy.common.Proxy;
import com.cheng.zenofdesignpatterns.patterns.proxy.common.RealSubject;
import com.cheng.zenofdesignpatterns.patterns.proxy.common.Subject;
import com.cheng.zenofdesignpatterns.patterns.proxy.dynamic.GamePlayerDynamicHandler;
import com.cheng.zenofdesignpatterns.patterns.proxy.dynamic.GamePlayerDynamic;
import com.cheng.zenofdesignpatterns.patterns.proxy.dynamic.IGamePlayerDynamic;
import com.cheng.zenofdesignpatterns.patterns.proxy.dynamiccommon.DynamicProxyDC;
import com.cheng.zenofdesignpatterns.patterns.proxy.dynamiccommon.MyInvocationHandler;
import com.cheng.zenofdesignpatterns.patterns.proxy.dynamiccommon.RealSubjectDC;
import com.cheng.zenofdesignpatterns.patterns.proxy.dynamiccommon.SubjectDC;
import com.cheng.zenofdesignpatterns.patterns.proxy.dynamiccommon.SubjectDynamicProxy;
import com.cheng.zenofdesignpatterns.patterns.proxy.force.GamePlayerForce;
import com.cheng.zenofdesignpatterns.patterns.proxy.force.GamePlayerProxyForce;
import com.cheng.zenofdesignpatterns.patterns.proxy.force.IGamePlayerForce;
import com.cheng.zenofdesignpatterns.patterns.proxy.normal.GamePlayerProxyNormal;
import com.cheng.zenofdesignpatterns.patterns.proxy.normal.IGamePlayerNormal;
import com.cheng.zenofdesignpatterns.patterns.proxy.section1.GamePlayer1;
import com.cheng.zenofdesignpatterns.patterns.proxy.section1.GamePlayerProxy1;
import com.cheng.zenofdesignpatterns.patterns.proxy.section1.IGamePlayer1;

import java.lang.reflect.InvocationHandler;

public class ZoDPProxyActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("代理模式");
        String content = "定义：\n" +
                "Provide a surrogate or placeholder for another object to control " +
                "access to it.\n" +
                "为其他对象提供一种代理以控制对这个对象的访问。\n\n" +
                "代理模式的优点\n" +
                "- 职责清晰\n" +
                "真实的角色就是实现实际的业务逻辑，不用关心其他非本职责的事务，通过后期的代理" +
                "完成一件事务，附带的结果就是编程简洁清晰。\n" +
                "- 高扩展性\n" +
                "具体主题角色是随时都会发生变化的，只要它实现了接口，甭管它如何变化，都逃不脱" +
                "如来佛的手掌（接口），那我们的代理类完全可以在不做任何修改的情况下使用。\n" +
                "- 智能化\n" +
                "可以看看Struts是如何把表单元素映射到对象上的。\n\n" +
                "使用场景\n" +
                "想想现实世界中，打官司为什么要找个律师？因为你不想参与中间过程的是是非非，只要" +
                "完成自己的答辩就成，其他的比如事前调查、事后追查都由律师来搞定，这就是为了减轻" +
                "你的负担。代理模式的使用场景非常多，可以看看Spring AOP，这是是一个非常典型的" +
                "动态代理。\n\n" +
                "代理模式的扩展\n" +
                "1. 普通代理\n" +
                "就是需要知道代理的存在，然后才能访问。它的要求是客户端只能访问代理角色，而不能" +
                "访问真实角色。\n" +
                "注意 普通代理模式的约束问题，尽量通过团队内的编程规范来约束，因为每一个主题类" +
                "是可被重用的和可维护的，使用技术约束的方式对系统维护是一种非常不利的因素。\n" +
                "2. 强制代理\n" +
                "一般的思维都是通过代理找到真实的角色，但是强制代理却是要‘强制’，你必须通过真实" +
                "角色查找到代理角色，否则你不能访问。甭管你是通过代理类还是通过直接new一个主题" +
                "角色类，都不能访问，只有通过真实角色指定的代理类才可以访问，也就是说由真实角色" +
                "管理代理角色（明星和经纪人）。\n" +
                "3. 动态代理\n" +
                "动态代理是在实现阶段不用关心代理谁，而在运行阶段才指定代理哪一个对象。相对来说，" +
                "自己写代理类的方式就是静态代理。\n" +
                "InvocationHandler是JDK提供的动态代理接口，对被代理类的方法进行代理。其中invoke" +
                "方法是接口InvocationHandler定义必须实现的，它完成对真实方法的调用，即所有被" +
                "代理的方法都由InvocationHandler接管实际的处理任务。\n\n" +
                "最佳实践\n" +
                "代理模式应用得非常广泛，大到一个系统框架、企业平台，小到代码片段、事务处理，稍不" +
                "留意就用到代理模式。\n" +
                "学习AOP时，弄清楚几个名词就成：切面（Aspect）、切入点（JoinPoint）、通知（Advice）、" +
                "织入（Weave）就足够了。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. Section1
        // 定义一个痴迷的玩家
        IGamePlayer1 player = new GamePlayer1("张三");
        // 然后再定义一个代练者
        IGamePlayer1 proxy = new GamePlayerProxy1(player);
        // 开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-25 10:45");
        proxy.login("zhangSan", "password");
        // 开始杀怪
        proxy.killBoss();
        // 升级
        proxy.upgrade();
        // 记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");

        // 2. 通用代理模式演示
        Subject realSubject = new RealSubject();
        Proxy proxy1 = new Proxy(realSubject);
        proxy1.request();

        // 3. 普通代理演示
        // 定义一个代练者
        IGamePlayerNormal proxy2 = new GamePlayerProxyNormal("张三");
        // 开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-25 10:45");
        proxy2.login("zhangsan", "password");
        // 开始杀怪
        proxy2.killBoss();
        // 升级
        proxy2.upgrade();
        // 记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");

        // 4. 强制代理演示
        // 4.1 直接访问真实角色
        // 定义个游戏的角色
        IGamePlayerForce player41 = new GamePlayerForce("张三");
        // 开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-25 10:45");
        player41.login("zhangSan", "password"); // 请使用指定的代理访问
        // 开始杀怪
        player41.killBoss(); // 请使用指定的代理访问
        // 升级
        player41.upgrade(); // 请使用指定的代理访问
        // 记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");

        // 4.2 直接访问代理类
        IGamePlayerForce player42 = new GamePlayerForce("张三");
        // 定义一个代练者
        IGamePlayerForce proxy42 = new GamePlayerProxyForce(player42);
        // 开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-25 10:45");
        proxy42.login("zhangSan", "password"); // 请使用指定的代理访问
        // 开始杀怪
        proxy42.killBoss(); // 请使用指定的代理访问
        // 升级
        proxy42.upgrade(); // 请使用指定的代理访问
        // 记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");

        // 4.3 强制代理演示
        IGamePlayerForce player43 = new GamePlayerForce("张三");
        // 获得指定的代理
        IGamePlayerForce proxy43 = player43.getProxy();
        // 开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-25 10:45");
        proxy43.login("zhangSan", "password");
        // 开始杀怪
        proxy43.killBoss();
        // 升级
        proxy43.upgrade();
        // 记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");
        /**
         * 强制代理的概念就是要从真实角色查找到代理角色，不允许直接访问真实角色。
         * 高层模块只要调用getProxy就可以访问真实角色就可以访问真实角色的所有方法，
         * 它根本就不需要产生一个代理出来，代理的管理已经由真实角色自己完成
         */

        // 5. 动态代理
        // 定义一个痴迷的玩家
        IGamePlayerDynamic player5 = new GamePlayerDynamic("张三");
        // 然后定义一个代练者
        InvocationHandler handler5 = new GamePlayerDynamicHandler(player5);
        // 开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-25 10:45");
        //===============================================//
        // 获得类的 ClassLoader
        ClassLoader cl = player5.getClass().getClassLoader();
        // 动态产生一个代理者
        IGamePlayerDynamic proxy5 = (IGamePlayerDynamic) java.lang.reflect.Proxy.newProxyInstance(cl, new Class[] {IGamePlayerDynamic.class}, handler5);
        // 登录
        proxy5.login("zhangSan", "password");
        // 开始杀怪
        proxy5.killBoss();
        // 升级
        proxy5.upgrade();
        //===============================================//
        String str[] = {"zhangSan", "password"};
        Class type[] = {String.class, String.class};
        try {
            handler5.invoke(null, player5.getClass().getMethod("login", type), str);
            handler5.invoke(null, player5.getClass().getMethod("killBoss", String.class), null);
            handler5.invoke(null, player5.getClass().getMethod("upgrade", String.class), null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        // 记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");

        // 6. 动态代理通用演示
        // 6.1 普通动态代理调用
        // 定义一个主题
        SubjectDC subjectDC61 = new RealSubjectDC();
        // 定义一个Handler
        InvocationHandler handler61 = new MyInvocationHandler(subjectDC61);
        // 定义主题的代理
        SubjectDC proxy61 = DynamicProxyDC.newProxyInstance(subjectDC61.getClass().getClassLoader(),
                subjectDC61.getClass().getInterfaces(), handler61);
        // 代理的行为
        proxy61.doSomething("Finish");

        // 6.2 具体业务的动态代理调用
        // 定义一个主题
        SubjectDC subjectDC62 = new RealSubjectDC();
        // 定义主题的代理
        SubjectDC proxy62 = SubjectDynamicProxy.newProxyInstance(subjectDC62);
        // 代理的行为
        proxy62.doSomething("Finish");
        /**
         * 以上的动态代理是一个通用代理框架。如果想设计自己的AOP框架，完全可以在此基础上
         * 扩展，这里设计的是一个通用代理，只要有一个接口，一个实现类，就可以使用该代理，
         * 完成代理的所有功效。
         */
    }
}