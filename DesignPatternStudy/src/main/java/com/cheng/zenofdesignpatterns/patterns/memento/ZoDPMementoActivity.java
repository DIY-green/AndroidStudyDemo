package com.cheng.zenofdesignpatterns.patterns.memento;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.memento.clone.CloneOriginator;
import com.cheng.zenofdesignpatterns.patterns.memento.common.Caretaker;
import com.cheng.zenofdesignpatterns.patterns.memento.common.Originator;
import com.cheng.zenofdesignpatterns.patterns.memento.multistate.MSCaretaker;
import com.cheng.zenofdesignpatterns.patterns.memento.multistate.MSOriginator;
import com.cheng.zenofdesignpatterns.patterns.memento.pursue.Boy;
import com.cheng.zenofdesignpatterns.patterns.memento.pursue.BoyCaretaker;

public class ZoDPMementoActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("备忘录模式");
        String content = "定义：\n" +
                "Without violating encapsulation, capture and externalize an object's internal " +
                "state so that the object can be restored to this state later.\n" +
                "在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样以后" +
                "就可将该对象恢复到原先保存的状态。\n\n" +
                "使用场景\n" +
                "- 需要保存和恢复数据的相关状态场景\n" +
                "- 提供一个可回滚（rollback）的操作\n" +
                "- 需要监控的副本场景中\n" +
                "- 数据库连接的事物管理就是用的备忘录模式\n\n" +
                "注意事项\n" +
                "- 备忘录的生命周期\n" +
                "备忘录创建出来就要在‘最近’的代码中使用，要主动管理它的生命周期，建立就要使用，不使用" +
                "就要立刻删除其引用，等待垃圾回收器对它的回收处理。\n" +
                "- 备忘录的性能\n" +
                "不要在频繁建立备份的场景中使用备忘录模式（比如一个for循环中），原因有二：一是控制不了" +
                "备忘录建立的对象数量；二是大对象的建立是要消耗资源的，系统的性能需要考虑。\n\n" +
                "备忘录模式的扩展\n" +
                "1. clone方式的备忘录\n" +
                "注意 使用clone方式的备忘录模式，可以使用在比较简单的场景或者比较单一的场景中，尽量不" +
                "要与其他的对象产生严重的耦合关系。\n" +
                "2. 多状态的备忘录模式\n" +
                "Spring、Apache工具集commons等都提供了把Bean的所有属性及数值放入到HashMap和把HashMap" +
                "的值返回到Bean中的工具类，可用来操作多属性的Bean。\n" +
                "注意 如果要设计一个在运行期决定备份状态的框架，则建议采用AOP框架来实现，避免采用动态代" +
                "理无谓地增加程序逻辑复杂性。\n" +
                "3. 多备份的备忘录\n" +
                "检查点（CheckPoint），也就是在备份的时候做的戳记，系统级的备份一般是时间戳，那程序的检" +
                "查点该怎样设计呢？一般是一个有意义的字符串。\n" +
                "注意 使用Map保存多备份要小心内存溢出问题，该备份一旦生产就装入内存，没有任何销毁的意向，" +
                "这是非常危险的。因此，在系统设计时，要严格限定备忘录的创建，建议增加Map的上限，否则系统" +
                "很容易产生内存溢出情况。\n" +
                "4. 封装得更好一点\n" +
                "在系统管理上，一个备份的数据是完全、绝对不能修改的，它保证数据的洁净，避免数据污染而使备" +
                "份失去意义。在设计领域中，也存在着同样的问题，备份是不能被篡改的，也就是说需要缩小备份出" +
                "的备忘录的阅读权限，保证只能是发起人可读就成了，那怎么才能做到这一点呢？使用内置类。\n" +
                "双接口设计：在系统设计时，如果考虑对象的安全问题，可以提供两个接口，一个是业务的正常接口，" +
                "实现必要的业务逻辑，叫做宽接口；另外一个接口是空接口，什么方法都没有，其目的是提供给子系" +
                "统外的模块访问，比如容器对象，这个叫窄接口，由于窄接口中没有提供任何操作数据的方法，因此" +
                "相对来说比较安全。\n\n" +
                "最佳实践\n" +
                "在设计的时候不要使用数据库的临时表作为缓存备份数据了，虽然是一个简单的办法，但是它加大了" +
                "数据库操作的频繁度，把压力放到数据库了，最好的解决办法就是使用备忘录模式。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 模拟电影《Next》男主角追求女主角过程
        // 声明出男主角
        Boy boy = new Boy();
        // 声明出备忘录的管理者
        BoyCaretaker caretaker = new BoyCaretaker();
        // 初始化当前状态
        boy.setState("心情很棒！");
        System.out.println("=====男孩现在的状态======");
        System.out.println(boy.getState());
        // 需要记录下当前状态呀
        caretaker.setMemento(boy.createMemento());
        // 男孩去追女孩，改变状态
        boy.changeState();
        System.out.println("\n=====男孩追女孩子后的状态======");
        System.out.println(boy.getState());
        // 追女孩失败，恢复原状
        boy.restoreMemento(caretaker.getMemento());
        System.out.println("\n=====男孩恢复后的状态======");
        System.out.println(boy.getState());

        // 2. 通用备忘录模式演示
        // 定义出发起人
        Originator originator = new Originator();
        // 定义出备忘录管理员
        Caretaker caretaker1 = new Caretaker();
        // 创建一个备忘录
        caretaker1.setMemento(originator.createMemento());
        // 恢复一个备忘录
        originator.restoreMemento(caretaker1.getMemento());

        // 3. clone方式的备忘录
        // 定义发起人
        CloneOriginator originatorClone = new CloneOriginator();
        // 建立初始状态
        originatorClone.setState("初始状态...");
        System.out.println("初始状态是：" + originatorClone.getState());
        // 建立备份
        originatorClone.createMemento();
        // 修改状态
        originatorClone.setState("修改后的状态");
        System.out.println("修改后的状态是：" + originatorClone.getState());
        // 恢复原有状态
        originatorClone.restoreMemento();
        System.out.println("恢复后的状态是：" + originatorClone.getState());

        // 4. 多状态备忘录
        // 定义出发起人
        MSOriginator msOriginator = new MSOriginator();
        // 定义出备忘录管理员
        MSCaretaker msCaretaker = new MSCaretaker();
        // 初始化
        msOriginator.setState1("中国");
        msOriginator.setState1("强盛");
        msOriginator.setState1("繁荣");
        System.out.println("===初始化状态===\n" + msOriginator);
        // 创建一个备忘录
        msCaretaker.setMemento(msOriginator.createMemento());
        // 修改状态值
        msOriginator.setState1("软件");
        msOriginator.setState1("架构");
        msOriginator.setState1("优秀");
        System.out.println("===修改后状态===\n" + msOriginator);
        // 恢复一个备忘录
        msOriginator.restoreMemento(msCaretaker.getMemento());
        System.out.println("===恢复后状态===\n" + msOriginator);

        // 5. 多备份使用内置类封装得更好一点

    }
}
