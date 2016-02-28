package com.cheng.zenofdesignpatterns.patterns.strategy;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.strategy.calculator.Add;
import com.cheng.zenofdesignpatterns.patterns.strategy.calculator.CalculatorContext;
import com.cheng.zenofdesignpatterns.patterns.strategy.calculator.Sub;
import com.cheng.zenofdesignpatterns.patterns.strategy.common.ConcreteStrategy1;
import com.cheng.zenofdesignpatterns.patterns.strategy.common.ContextRole;
import com.cheng.zenofdesignpatterns.patterns.strategy.common.IStrategy;
import com.cheng.zenofdesignpatterns.patterns.strategy.smartexpress.BackDoor;
import com.cheng.zenofdesignpatterns.patterns.strategy.smartexpress.BlockEnemy;
import com.cheng.zenofdesignpatterns.patterns.strategy.smartexpress.GivenGreenLight;
import com.cheng.zenofdesignpatterns.patterns.strategy.smartexpress.SmartExpress;
import com.cheng.zenofdesignpatterns.patterns.strategy.strategyenum.CalculatorEnum;

public class ZoDPStrategyActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("策略模式");
        String content = "定义：\n" +
                "Define a family of algorithms, encapsulate each one, and make them " +
                "interchangeable.\n" +
                "定义一组算法，将每个算法都封装起来，并且使它们之间可以互换。\n\n" +
                "策略模式的优点\n" +
                "- 算法可以自由切换\n" +
                "这是策略模式本身定义的，只要实现抽象策略，它就成为策略家族的一个成员，通过" +
                "封装角色对其进行封装，保证对外提供‘可自由切换’的策略。\n" +
                "- 避免使用多重条件判断\n" +
                "使用策略模式后，可以由其他模块决定采用何种策略，策略家族对外提供的访问接口" +
                "就是封装类，简化了操作，同时避免的条件语句判断。\n" +
                "- 扩展性良好\n" +
                "策略模式的缺点\n" +
                "- 策略类数量增多\n" +
                "每一个策略都是一个类，复用的可能性很小，类数量增多。\n" +
                "- 所有的策略类都需要对外暴露\n" +
                "上层模块必须知道有哪些策略，然后才能决定使用哪个策略，这与迪米特法则是相违背" +
                "的。这是原装策略模式的一个缺点，幸运的是，可以使用其他模式来修正这个缺陷，如" +
                "工厂方法模式、代理模式或享元模式。\n\n" +
                "使用场景\n" +
                "- 多个类只有在算法或行为上稍有不同的场景\n" +
                "- 算法需要自由切换的场景\n" +
                "- 需要屏蔽算法规则的场景\n\n" +
                "注意事项\n" +
                "如果系统中的一个策略家族的具体策略数量超过4个，则需要考虑使用混合模式，解决策略" +
                "类膨胀和对外暴露的问题，否则日后的系统维护很麻烦。\n\n" +
                "扩展\n" +
                "* 策略枚举\n" +
                "- 它是一个枚举\n" +
                "- 它是一个浓缩了的策略模式的枚举\n" +
                "注意 策略枚举是一个非常优秀和方便的模式，但是它受枚举类型的限制，每个枚举项都是" +
                "public、final、static的，扩展性受到了一定的约束，因此在系统开发中，策略枚举一" +
                "般担当不经常发生变化的角色。\n\n" +
                "最佳实践\n" +
                "策略模式单独使用的地方比较少，因为它有致命的缺陷：所有的策略都需要暴露出去，这样才" +
                "方便客户端决定使用哪一个策略。在实际项目中，一般通过工厂方法模式来实现策略类的声明，" +
                "可以参考混编模式。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟诸葛亮的锦囊妙计
        SmartExpress smartExpress;
        // 刚刚到吴国的时候拆第一个
        System.out.println("---刚刚到吴国的时候拆第一个---");
        smartExpress = new SmartExpress(new BackDoor()); // 拿到妙计
        smartExpress.operate(); // 拆开执行
        System.out.println("\n\n");
        // 刘备乐不思蜀了，拆第二个了
        System.out.println("---刘备乐不思蜀了，拆第二个了---");
        smartExpress = new SmartExpress(new GivenGreenLight());
        smartExpress.operate(); // 执行了第二个锦囊了
        System.out.println("\n\n");
        // 孙权的小兵追了，咋办？拆第三个
        System.out.println("---孙权的小兵追了，咋办？拆第三个---");
        smartExpress = new SmartExpress(new BlockEnemy());
        smartExpress.operate(); // 孙夫人退兵
        System.out.println("\n\n");
        /*
		 * 问题来了：赵云实际不知道是那个策略呀，他只知道拆第一个锦囊，
		 * 而不知道是BackDoor这个妙计，咋办？  似乎这个策略模式已经把计谋名称写出来了
		 *
		 * 错！BackDoor、GivenGreenLight、BlockEnemy只是一个代码，你写成first、
		 * second、third，没人会说你错!
		 *
		 * 策略模式的好处就是：体现了高内聚低耦合的特性呀
		 */

        // 2. 通用策略模式的演示
        // 声明出一个具体的策略
        IStrategy strategy = new ConcreteStrategy1();
        // 声明出上下文对象
        ContextRole context = new ContextRole(strategy);
        // 执行封装后的方法
        context.doAnythinig();

        // 3. 策略模式实现计算器
        // 加符号
        final String ADD_SYMBOL = "+";
        // 减符号
        final String SUB_SYMBOL = "-";
        // 输入的两个参数是数字
        int a = 50;
        String symbol = "+";  // 符号
        int b = 80;
        System.out.println("输入的参数为：" + a + symbol + b);
        //上下文
        CalculatorContext calculatorContext = null;
        //判断初始化哪一个策略
        if(symbol.equals(ADD_SYMBOL)){
            calculatorContext = new CalculatorContext(new Add());
        }else if(symbol.equals(SUB_SYMBOL)){
            calculatorContext = new CalculatorContext(new Sub());
        }
        System.out.println("运行结果为：" + a + symbol + b + "=" + calculatorContext.exec(a, b, symbol));

        // 4. 策略枚举
        //输入的两个参数是数字
        int a2 = 121;
        String symbol2 = "+";  //符号
        int b2 = 321;
        System.out.println("输入的参数为：" + a2 + symbol2 + b2);
        System.out.println("运行结果为："+a + symbol + b + "=" + CalculatorEnum.ADD.exec(a, b));
        a2 = 131;
        b2 = 333;
        symbol2 = "-";
        System.out.println("输入的参数为：" + a2 + symbol2 + b2);
        System.out.println("运行结果为："+a + symbol + b + "=" + CalculatorEnum.SUB.exec(a, b));
    }
}
