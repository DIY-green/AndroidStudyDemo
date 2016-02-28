package com.cheng.zenofdesignpatterns.patterns.visitor;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.visitor.actor.AbsActor;
import com.cheng.zenofdesignpatterns.patterns.visitor.actor.KungFuRole;
import com.cheng.zenofdesignpatterns.patterns.visitor.actor.OldActor;
import com.cheng.zenofdesignpatterns.patterns.visitor.actor.Role;
import com.cheng.zenofdesignpatterns.patterns.visitor.common.Element;
import com.cheng.zenofdesignpatterns.patterns.visitor.common.ObjectStruture;
import com.cheng.zenofdesignpatterns.patterns.visitor.common.Visitor;
import com.cheng.zenofdesignpatterns.patterns.visitor.multivisitor.IShowVisitor;
import com.cheng.zenofdesignpatterns.patterns.visitor.multivisitor.ITotalVisitor;
import com.cheng.zenofdesignpatterns.patterns.visitor.multivisitor.MVCommonEmployee;
import com.cheng.zenofdesignpatterns.patterns.visitor.multivisitor.MVEmployee;
import com.cheng.zenofdesignpatterns.patterns.visitor.multivisitor.MVManager;
import com.cheng.zenofdesignpatterns.patterns.visitor.multivisitor.ShowVisitor;
import com.cheng.zenofdesignpatterns.patterns.visitor.multivisitor.TotalVisitor;
import com.cheng.zenofdesignpatterns.patterns.visitor.report.CommonEmployee;
import com.cheng.zenofdesignpatterns.patterns.visitor.report.Employee;
import com.cheng.zenofdesignpatterns.patterns.visitor.report.Manager;
import com.cheng.zenofdesignpatterns.patterns.visitor.report.ReportVisitor;

import java.util.ArrayList;
import java.util.List;

public class ZoDPVisitorActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("访问者模式");
        String content = "定义：\n" +
                "Represent an operation to be performed on the elements of an object structure." +
                "ReportVisitor lets you define a new operation without changing the classes of the " +
                "elements on which it operates.\n" +
                "封装一些作用于某种数据结构中的各元素的操作，它可以在不改变数据结构的前提下定义作用于" +
                "这些元素的新的操作。\n\n" +
                "访问者模式的优点\n" +
                "- 符合单一职责原则\n" +
                "具体元素角色也就是Employee抽象类的两个子类负责数据的加载，而Visitor类则负责报表的展" +
                "现，两个不同的职责非常明确地分离开来，各自演绎变化。\n" +
                "- 优秀的扩展性\n" +
                "由于职责分开，继续增加对数据的操作是非常快捷的。\n" +
                "- 灵活性非常高\n" +
                "访问者模式的缺点\n" +
                "- 具体元素对访问者公布细节\n" +
                "访问者要访问一个类就必然要求这个类公布一些方法和数据，也就是说访问者关注了其他类的内部" +
                "细节，这是迪米特法则所不建议的。\n" +
                "- 具体元素变更比较困难\n" +
                "具体元素角色的增加、删除、修改都是比较困难的。\n" +
                "- 违背依赖倒置原则\n" +
                "访问者依赖的是具体元素，而不是抽象元素，这破坏了依赖倒置原则，特别是在面向对象的编程中，" +
                "抛弃了对接口的依赖，而直接依赖实现类，扩展比较难。\n\n" +
                "使用场景\n" +
                "- 一个对象结构包含很多类对象，它们有不同的接口，而你想对这些对象实施一些依赖于其具体类" +
                "的操作，也就是说用迭代器模式已经不能胜任的情景\n" +
                "- 需要对一个对象结构中的对象进行很多不同并且不相关的操作，而你想避免让这些操作‘污染’这" +
                "些对象的类\n" +
                "总结一下，在这种地方一定要考虑使用访问者模式：业务规则要求遍历多个不同的对象。这本身也是" +
                "访问者模式出发点，迭代器模式只能访问同类或同接口的数据（当然了，如果使用instanceof，那" +
                "么能访问所有的数据，这没有争论），而访问者模式是对迭代器模式的扩充，可以遍历不同的对象，" +
                "然后执行不同的操作，也就是针对访问的对象不同，执行不同的操作。访问者模式还有一个用途，就" +
                "是充当拦截器角色。\n\n" +
                "访问者模式的扩展\n" +
                "1. 统计功能\n" +
                "除了那种海量数据处理，不使用存储过程来处理就没有其他办法的，一般的数据统计和报表的批处理" +
                "通过访问者模式来处理会比较简单。\n" +
                "2. 多个访问者\n" +
                "3. 双分派\n" +
                "先来解释一下什么是单分派和多分派，单分派语言处理一个操作是根据请求者的名称和接收到的参数" +
                "决定的，在Java中有静态绑定和动态绑定之说，它的实现是依据重载和覆写实现的。重载在编译器期" +
                "就决定了要调用哪个方法，它是依据表面类型而决定调用哪个方法，这是静态绑定；而如果是有实际" +
                "类型决定的，那就是动态绑定。\n" +
                "双分派意味着得到执行的操作决定于请求的种类和两个接收者的类型，它是多分派的一个特例。从这" +
                "里可以看到Java是一个支持双分派的单分派语言。\n\n" +
                "最佳实践\n" +
                "访问者模式是一种集中规整模式，特别适用于大规模重构的项目，在这一个阶段需求已经非常清晰，原" +
                "系统的功能点也已经明确，通过访问者模式可以很容易把一些功能进行梳理，达到最终目的--功能集中" +
                "化，如一个统一的报表运算、UI展现等，还可以与其他模式混编建议一套自己的过滤器或者拦截器。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟公司员工情况报表
        for (Employee employee : mockEmployee()) {
            employee.accept(new ReportVisitor());
        }

        // 2. 通用访问者模式演示
        for (int i = 0; i < 10; i++) {
            // 获得元素对象
            Element el = ObjectStruture.createElement();
            // 接受访问者访问
            el.accept(new Visitor());
        }

        // 3. 多访问者演示
        // 展示报表访问者
        IShowVisitor showVisitor = new ShowVisitor();
        // 汇总报表的访问者
        ITotalVisitor totalVisitor = new TotalVisitor();
        for (MVEmployee emp : mockMVEmployee()) {
            emp.accept(showVisitor); // 接受展示报表访问者
            emp.accept(totalVisitor); // 接受汇总表访问者
        }

        // 4. 双分派演示
        // 定义一个演员
        AbsActor actor = new OldActor();
        // 定义一个角色
        Role role = new KungFuRole();
        // 开始演戏
        role.accept(actor);
    }

    // 模拟出公司的人员情况，我们可以想象这个数据室通过持久层传递过来的
    private List<Employee> mockEmployee() {
        List<Employee> empList = new ArrayList<Employee>();

        // 产生张三这个员工
        CommonEmployee zhangSan = new CommonEmployee();
        zhangSan.setJob("编写Java程序，绝对的蓝领、苦工加搬运工");
        zhangSan.setName("张三");
        zhangSan.setSalary(1800);
        zhangSan.setSex(Employee.MALE);
        empList.add(zhangSan);

        // 产生李四这个员工
        CommonEmployee liSi = new CommonEmployee();
        liSi.setJob("页面美工，审美素质太不流行了！");
        liSi.setName("李四");
        liSi.setSalary(1900);
        liSi.setSex(Employee.FEMALE);
        empList.add(liSi);

        // 再产生一个经理
        Manager wangWu = new Manager();
        wangWu.setName("王五");
        wangWu.setPerformance("基本上是负值，但是我会拍马屁呀");
        wangWu.setSalary(18750);
        wangWu.setSex(Employee.MALE);
        empList.add(wangWu);

        return empList;
    }

    // 模拟出公司的人员情况，我们可以想象这个数据是通过持久层传递过来的
    private List<MVEmployee> mockMVEmployee() {
        List<MVEmployee> empList = new ArrayList<MVEmployee>();

        // 产生张三这个员工
        MVCommonEmployee zhangSan = new MVCommonEmployee();
        zhangSan.setJob("编写Java程序，绝对的蓝领、苦工加搬运工");
        zhangSan.setName("张三");
        zhangSan.setSalary(1800);
        zhangSan.setSex(Employee.MALE);
        empList.add(zhangSan);

        // 产生李四这个员工
        MVCommonEmployee liSi = new MVCommonEmployee();
        liSi.setJob("页面美工，审美素质太不流行了！");
        liSi.setName("李四");
        liSi.setSalary(1900);
        liSi.setSex(Employee.FEMALE);
        empList.add(liSi);

        // 再产生一个经理
        MVManager wangWu = new MVManager();
        wangWu.setName("王五");
        wangWu.setPerformance("基本上是负值，但是我会拍马屁呀");
        wangWu.setSalary(18750);
        wangWu.setSex(Employee.MALE);
        empList.add(wangWu);

        return empList;
    }
}
