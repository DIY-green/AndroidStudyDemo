package com.cheng.zenofdesignpatterns.patterns.state;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.state.common.ConcreteState1;
import com.cheng.zenofdesignpatterns.patterns.state.common.StateContext;
import com.cheng.zenofdesignpatterns.patterns.state.liftstate.ClosingState;
import com.cheng.zenofdesignpatterns.patterns.state.liftstate.LiftContext;

public class ZoDPStateActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("状态模式");
        String content = "定义：\n" +
                "Allow an object to alert its behavior when its internal state changes. The " +
                "object will appear to changes its class.\n" +
                "当一个对象内在状态改变时允许其改变行为，这个对象看起来像改变了其类。\n\n" +
                "状态模式的优点\n" +
                "- 结构清晰\n" +
                "避免过多的switch...case或者if...else语句的使用，避免了程序的复杂性，提高系统的可维护性。\n" +
                "- 遵循设计原则\n" +
                "很好地体现了开闭原则和单一职责原则，每个状态都是一个子类，要增加状态就要增加子类，要" +
                "修改状态，只修改一个子类就可以了。\n" +
                "- 封装性非常好\n" +
                "这也是状态模式的基本要求，状态变换放置到类的内部来实现，外部的调用不用知道类内部如何实现" +
                "状态和行为的变换。\n" +
                "状态模式的缺点\n" +
                "有一个缺点，子类会太多，也就是类膨胀。如果一个事物有很多个状态也不稀奇，如果完全使用状态" +
                "模式就会有太多的子类，不好管理，这个需要在项目中自己衡量。其实有很多方式可以解决这个状态" +
                "问题，如在数据库中建立一个状态表，然后根据状态执行相应的操作。\n\n" +
                "使用场景\n" +
                "- 行为随状态改变而改变的场景\n" +
                "这也是状态模式的根本出发点，例如权限设计，人员的状态不同即使执行相同的行为结构也会不同，" +
                "在这种情况下需要考虑使用状态模式。\n" +
                "- 条件、分支判断语句的替代者\n" +
                "在程序中大量使用switch语句或者if判断语句导致结构不清晰，逻辑混乱，使用状态模式可以很好地" +
                "避免这一问题，它通过扩展子类实现了条件的判断处理。\n\n" +
                "注意事项\n" +
                "状态模式适用于当某个对象在它的状态发生改变时，它的行为也随着发生比较大的变化，也就是说在行" +
                "为受状态约束的情况下可以使用状态模式，而且使用时的状态最好不要超过5个。\n\n" +
                "最佳实践\n" +
                "建造者模式+状态模式会起到非常好的封装作用。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟电梯运行
        LiftContext liftContext = new LiftContext();
        liftContext.setLiftState(new ClosingState());
        liftContext.open();
        liftContext.close();
        liftContext.run();
        liftContext.stop();
        liftContext.open();

        // 2. 通用状态模式演示
        // 定义环境角色
        StateContext stateContext = new StateContext();
        // 初始化状态
        stateContext.setCurrentState(new ConcreteState1());
        // 行为执行
        stateContext.handle1();
        stateContext.handle2();
    }
}
