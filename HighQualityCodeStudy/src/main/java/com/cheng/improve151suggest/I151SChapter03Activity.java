package com.cheng.improve151suggest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.cheng.highqualitycodestudy.R;
import com.cheng.improve151suggest.adapter.I151SuggestListAdapter;

/**
 第3章 类、对象及方法
 建议31： 在接口中不要存在实现代码
 建议32： 静态变量一定要先声明后赋值
 建议33： 不要覆写静态方法
 建议34： 构造函数尽量简化
 建议35： 避免在构造函数中初始化其他类
 建议36： 使用构造代码块精炼程序
 建议37： 构造代码块会想你所想
 建议38： 使用静态内部类提高封装性
 建议39： 使用匿名类的构造函数
 建议40： 匿名类的构造函数很特殊
 建议41： 让多重继承成为现实
 建议42： 让工具类不可实例化
 建议43： 避免对象的浅拷贝
 建议44： 推荐使用序列化实现对象的拷贝
 建议45： 覆写equals方法时不要识别不出自己
 建议46： equals应该考虑null值情景
 建议47： 在equals中使用getclass进行类型判断
 建议48： 覆写equals方法必须覆写hashcode方法
 建议49： 推荐覆写tostring方法
 建议50： 使用package-info类为包服务
 建议51： 不要主动进行垃圾回收
 */
public class I151SChapter03Activity extends AppCompatActivity {

    private static final String TAG = "I151SChapter03Activity";

    private ListView mChapterLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i151suggestchapter);
        initView();
        initData();
    }

    private void initView() {
        this.mChapterLV = (ListView) findViewById(R.id.isi_chapter_lv);
    }

    private void initData() {
        String[] suggests = getResources().getStringArray(R.array.i151schapter3);
        I151SuggestListAdapter adapter = new I151SuggestListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
    }

    /**
     * 建议31： 在接口中不要存在实现代码
     */
    private void suggest31() {
        // 调用接口的实现
        B.s.doSomething();
        /*
         在一般的项目中，此类代码是严禁出现的，原因很简单：这是一种不好的编程习惯，接口是用来
         干什么的？接口是一个契约，不仅仅约束着实现者，同时也是一个保证，保证提供的服务（常量，
         方法）是稳定、可靠的，如果把实现代码写到接口中，那接口就绑定了可能变化的因素，这就会
         导致实现不再稳定和可靠，是随时都可能被抛弃、被更改、被重构的。所以，接口中虽然可以有
          实现，但应该避免使用
         */
        /**
         * 注意
         * 接口中不能存在实现代码
         */
    }
    // 在接口中存在实现代码
    interface B {
        static final S s = new S() {
            @Override
            public void doSomething() {
                Log.e(TAG, "我在接口中实现了");
            }
        };
    }
    // 被实现的接口
    interface S {
        void doSomething();
    }

    /**
     * 建议32： 静态变量一定要先声明后赋值
     */
    private static int i1 = 1;
    static {
        i1 = 100;
        i2 = 200;
    }
    private static int i2 = 2;
    private void suggest32() {
        Log.e(TAG, "i1 = " + i1); // 100
        Log.e(TAG, "i2 = " + i2); // 2
        /*
        i2输出的是2，而不是200。仅仅调换了一下位置，输出就变了，而且变量i2还真是先使用后声明的
        Why？这要从静态变量的诞生说起，静态变量是类加载时被分配到数据区（Data Area）的，它在内
        存中只有一个拷贝，不会被分配多次，其后的所有赋值操作都是值改变，地址则保持不变。我们知道
        JVM初始化变量是先声明空间，然后再赋值的，也就是说：
        int i = 100;
        在JVM中是分开执行，等价于：
        int i; // 分配地址空间
        i = 100; // 赋值
        静态变量是在类初始化时首先被加载的，JVM会去查找类中所有的静态声明，然后分配空间，注意这
        时候只是完成了地址空间的分配，还没有赋值，之后JVM会根据类中静态赋值（包括静态类赋值和静
        态块赋值）的先后顺序来执行。
        有些程序员喜欢把变量定义放到类的底部，如果这是实例变量还好说，没有任何问题，但如果是静态
        变量，而且还在静态块中进行了赋值，那这结果可就和你期望的不一样了，所以遵循Java通用的开发
        规范“变量先声明后使用”是一个良好的编码风格
         */
        /**
         * 注意
         * 再次重申变量要先声明后使用，这不是一句废话
         */
    }

    /**
     * 建议33： 不要覆写静态方法
     */
    private void suggest33() {
        Base base = new Sub();
        //调用非静态方法
        base.doAnything(); // 我是子类非静态方法
        //调用静态方法
        base.doSomething(); // 我是父类静态方法
        /*
        一个实例对象有两个类型：表面类型（Apparent Type）和实际类型（Actual Type），表面类型是
        声明时的类型，实际类型是对象产生时的类型。对于非静态方法，它是根据对象的实际类型来执行的，
        而对于静态方法就比较特殊了，首先静态方法不依赖实例对象，它是通过类名访问的；其次，可以通过
        对象访问静态方法，如果通过对象调用静态方法，JVM则会通过对象的表面类型查找到静态方法的入口，
        继而执行之。
         */
        /**
         * 注意
         * 在子类中构建与父类相同的方法名、输入参数、输出参数、访问权限（权限可以扩大），并且父类、
         * 子类都是静态方法，此种行为叫做隐藏（Hide），她与覆写有两点不同：
         * 表现形式不同。隐藏用于静态方法，覆写用于非静态方法。在代码的表现是：@Override注解可以
         * 用于覆写，不能用于隐藏
         * 职责不同。隐藏的目的是为了遮盖父类的方法，也就是期望父类的静态方法不要破坏子类的业务行
         * 为；而覆写则是将父类的行为增强或减弱，延续父类的职责
         * -- 通过实例对象访问静态方法或静态属性不是好习惯，建议使用类名访问静态方法或静态属性
         */
    }

    /**
     * 建议34： 构造函数尽量简化
     */
    private void suggest34() {
        Server s = new SimpleServer(1000);
        /*
        该代码是一个服务类的简单模拟程序，Server类实现了服务器的创建逻辑，子类只要在生成实例对象时
        传递一个端口号即可创建一个监听该端口的服务，该代码的意图如下：
        通过SimpleServer的构造函数接收接口参数
        子类的构造函数默认调用父类的构造函数
        父类构造函数调用子类的getPort方法获得端口号
        父类构造函数建立端口监听机制
        对象创建完毕，服务监听启动，正常运行
        貌似很合理，尝试多次运行看看，输出结果要么是“端口号：40000”，要么是“端口号：0”，永远不会出现
        “端口号：100”或是“端口号：1000”，这就奇怪了，40000还好说，但那个0是怎么冒出来的呢？
         */
        /*
        子类是如何实例化的？
        子类实例化时，会首先初始化父类（注意这里是初始化，可不是生成父类对象），也就是初始化父类的变量，
        调用父类的构造函数，然后才会初始化子类的变量，调用子类自己的构造函数，最后生成一个实例对象
         */
        /*
        问题在于，在类初始化时getPort方法返回的port值还没有赋值，port只是获得了默认初始值（int类的实
        例变量默认初始化值是0）。这问题的产生从浅处说是类元素初始化顺序导致的，从深处说是因为构造函数
        太复杂而引起的。构造函数作为初始化变量，声明示例的上下文，这都是简单的实现，没有任何问题，但我
        们的例子却实现了一个复杂的逻辑，而这放在构造函数里就不合适了
        问题知道了，修改也很简单，把父类的无参构造函数中的所有实现都移动到一个叫做start的方法中，将
        SimpleServer类初始化完毕，再调用其start方法即可实现服务器的启动工作，简洁又直观
         */
        /**
         * 注意
         * 构造函数简化，在简化，应该达到“一眼洞穿”的境界
         */
    }

    /**
     * 建议35： 避免在构造函数中初始化其他类
     */
    private void suggest35() {
        Son1 s = new Son1();
        s.doSomething();
        /*
        输出什么？
        答案是这段代码不能运行，报StackOverflowError异常，栈（Stack）内存溢出。这是因为声明s变量时，
        调用了Son的无参构造函数，JVM又默认调用了父类Father的午餐构造函数，接着Father类又初始化了Other
        类，而Other类又调用了Son类，于是一个死循环就诞生了，直到栈内存被消耗完毕为止
        可能会觉得这样的场景不可能在开发中出现，那我们来思考这样的场景：Father是由框架提供的，Son类是
        我们自己编写的扩展代码，而Other类则是框架要求的拦截类（Interceptor类或者Handler类或者Hook方
        法），再来看看该问题，这种场景不可能出现吗？
         */
        /**
         * 注意
         * 不要在构造函数中声明初始化其他类，养成良好的习惯
         */
    }

    /**
     * 建议36： 使用构造代码块精炼程序
     */
    private void suggest36() {
        /*
        什么叫代码块？用大括号把多行代码封装在一起，形成一个独立的数据体，实现特定算法的代码集合即为代码
        块，一般来说代码块是不能单独运行的，必须要有运行主体。在Java中一共有四种类型的代码块：
        1）普通代码块
        就是在方法后面使用“{}”括起来的代码片段，它不能单独执行，必须通过方法名调用执行
        2）静态代码块
        在类中使用static修饰，并使用“{}”括起来的代码片段，用于静态变量的初始化或对象创建前的环境初始化
        3）同步代码块
        使用synchronized关键字修饰，并使用“{}”括起来的代码片段，它表示同一时间只能有一个线程进入到该方法
        块中，是一种多线程保护机制
        4）构造代码块
        在类中没有任何的前缀或后缀，并使用“{}”括起来的代码片段

        构造函数和构造代码块是什么关系？构造代码块是在什么时候执行的？
        先来看看编译器是如何处理构造代码块的，很简单，编译器会把构造代码块插入到每个构造函数的最前端。很
        显然，在通过new关键字生成一个实例时会先执行构造代码块，然后再执行其他代码，也就是说：构造代码块
        会在每个构造函数内首先执行（需要注意的是：构造代码块不是在构造函数之前运行的，它依托于构造函数的执
        行），明白了这一点，就可以把构造代码块应用到如下场景中：
        1）初始化实例变量（Instance Variable）
        如果每个构造函数都要初始化变量，可以通过构造代码块来实现。当然也可以通过定义一个方法，然后在每个构
        造函数中调用该方法来实现，没错，可以解决，但是要在每个构造函数中都调用该方法，而这就是其缺点，若采
        用构造代码块的方式则不用定义和调用，会直接由编译器写入到每个构造函数中，这才是解决此类问题的绝佳方式
        2）初始化实例环境
        一个对象必须在适当的场景下才能存在，如果没有适当的场景，则就需要在创建对象时创建此场景，例如在JEE开
        发中，要产生HTTP Request必须首先建立HTTP Session，在创建HTTP Request时就可以通过构造代码块来检查
        HTTP Session是否已经存在，不存在则创建之
         */
        /**
         * 注意
         * 构造代码块有两个特性：
         * 在每个构造函数中都运行
         * 在构造函数中它会首先运行
         * 很好地利用构造代码块的这两个特性不仅可以减少代码量，还可以让程序更容易阅读，特别是当所有的构造函
         * 数都要实现逻辑，而且这部分逻辑又很复杂时，这时就可以通过编写多个构造代码块来实现。每个代码块完成
         * 不同的业务逻辑，按照业务顺序依次存放，这样在创建实例对象时JVM也就会按照顺序依次执行，实现复杂对象
         * 的模块化创建
         */
    }

    /**
     * 建议37： 构造代码块会想你所想
     */
    private void suggest37() {
        /*
        上一个建议说编译器会把构造代码块插入到每一个构造函数中，但是有一个例外的情况没有说明：如果遇到this
        关键字（也就是构造函数调用自身其他的构造函数时）则不插入构造代码块
        构造代码块是为了提取构造函数的共同量，减少各个构造函数的代码而产生的，因此，Java就很聪明地认为把代
        码块插入到没有this方法的构造函数中即可，而调用其他构造函数的则不插入，确保每个构造函数只执行一次构
        造代码块
        还有一点需要说明，千万不要以为this是特殊情况，那super也会类是处理了。其实不会，在构造代码块的处理
        上，super方法没有任何特殊的地方，编译器只是把构造代码块插入到super方法之后执行而已，仅此不同
         */
        /**
         * 注意
         * 放心地使用构造代码块吧，Java已经想你所想了
         */
    }

    /**
     * 建议38： 使用静态内部类提高封装性
     */
    private void suggest38() {
        /*
        只有在是静态内部类的情况下才能把static修饰符放在类前，其他任何时候static都是不能修饰类的
        为什么需要静态内部类呢？因为静态内部类有两个优点：加强了类的封装性和提高了代码的可读性
        可能会觉得外部类和静态内部类之间是组合关系（Composition），这是错误的，外部类和静态内部类
        之间有强关联关系，这仅仅表现在“字面”上，而深层次的抽象意义则依赖与类的设计
         */
        /**
         * 注意
         * 静态内部类与普通内部类有什么区别？
         * 1）静态内部类不持有外部类的引用
         * 在普通内部类中，可以直接访问外部类的属性、方法，即使是private类型也可以访问，这是因为内部
         * 类持有一个外部类的引用，可以自由访问。而静态内部类，则只可以访问外部类的静态方法和静态属性
         * （如果是private权限也能访问，这是由其代码位置所决定的），其他则不能访问
         * 2）静态内部类不依赖外部类
         * 普通内部类与外部类之间是相互依赖的关系，内部类实例不能脱离外部实例，也就是说它们会同生同死，
         * 一起声明，一起被垃圾回收器回收。而静态内部类是可以独立存在的，即使外部类消亡，静态内部类还
         * 是可以存在的
         * 3）普通内部类不能声明static的方法和变量
         * 普通内部类不能声明static的方法和变量，注意这里说的是变量，常量（也就是final static修饰的
         * 属性）还是可以的，而静态内部类形似外部类，没有任何限制
         */
    }

    /**
     * 建议39： 使用匿名类的构造函数
     */
    private void suggest39() {
        List l1 = new ArrayList();
        List l2 = new ArrayList(){};
        List l3 = new ArrayList(){{}};
        Log.e(TAG, "l1.getClass()==l2.getClass() :" + (l1.getClass()==l2.getClass())); // false
        Log.e(TAG, "l2.getClass()==l3.getClass() :" + (l2.getClass()==l3.getClass())); // false
        Log.e(TAG, "l1.getClass()==l3.getClass() :" + (l1.getClass()==l3.getClass())); // false
        // 虽然父类相同，但是类还是不同的
        /*
        l1很容易解释，就是声明了ArrayList的实例对象，那l2和l3代表的是什么呢？
        1）l2 = new ArrayList(){}
        l2代表的是一个匿名类的声明和赋值，它定义了一个继承与ArrayList的匿名类，只是没有任何的覆写方法而已，
        其代码类似于：
        // 定义一个继承ArrayList的内部类
        class Sub extends ArrayList{
        }
        List l2 = new Sub();
        2）l3 = new ArrayList(){{}}
        这个语句就有点怪了，还带有两对大括号，分开来解释就会明白，这也是一个匿名类的定义，它的代码类似于：
        // 定义一个继承ArrayList的内部类
        class Sub extends ArrayList{
            {
                // 初始化块
            }
        }
        // 声明和赋值
        List l3 = new Sub();
        就是多了一个初始化块而已，起到构造函数的功能。一个类肯定有一个构造函数，且构造函数的名称和类名相同，
        那问题来了：匿名类的构造函数是什么呢？它没有名字呀！很显然，初始化块就是它的构造函数。当然，一个类中
        的构造函数块可以是多个，也就是可以出现如下代码：
        List l3 = new ArrayList(){{}{}{}{}{}};
         */
        /**
         * 注意
         * 匿名类虽然没有名字，但也是可以有构造函数的，它用构造代码块来代替
         */
    }

    /**
     * 建议40： 匿名类的构造函数很特殊
     */
    private void suggest40() {
        Calculator c1 = new Calculator(1,2) {
            {
                setOperator(Ops.ADD);
            }
        };
        Calculator c2 = new Add(1,2);
        Log.e(TAG, c1.getResult() + "");
        Log.e(TAG, c2.getResult() + "");
        /*
        一般类（也就是具有显式名字的类）的所有构造函数默认都是调用父类的无参构造的，而匿名类因为没有名字，只
        能由构造代码块代替，也就不所谓的有参和无参构造函数了，它在初始化时直接调用了父类的同参数构造，然后再
        调用自己的构造代码块
        上面的匿名内部类与 Add 类其实是等价的，它首先会调用父类有两个参数的构造方法，而不是无参构造，这里匿
        名类的构造函数与普通类的差别，但是这一点也确实鲜有人细细琢磨，因为它的处理机制符合习惯呀，我传递两个
        参数，就是希望先调用父类有两个参数的构造，然后再执行我自己的构造函数，而Java的处理机制正是如此
         */
        /**
         * 注意
         * 匿名类在初始化的时候直接调用父类的同参数构造，然后调用自己的构造代码块
         */
    }

    /**
     * 建议41： 让多重继承成为现实
     */
    private void suggest41() {
        /*
        在Java中一个类可以多重实现，但不能多重继承，也就是说一个类能够同时实现多个接口，但不能同时继承多个
        类。但是有时候确实需要继承多个类，比如希望拥有两个类的行为功能，就很难使用单继承来解决问题了。幸运
        的是Java中提供的内部类可以曲折地解决此问题。
        来看一个案例，定义一个父亲、母亲接口，描述父亲强壮、母亲温柔的理想情况（见下面的定义）
        儿子继承自父亲，变得比父亲更强壮了（覆写父类strong方法），同时儿子也具有母亲的优点，只是温柔指数降
        低了。注意看，这里构造了MotherSpecial类继承母亲类，也就是获得了母亲类的行为，这也是内部类的一个重
        要特性：内部类可以继承一个与外部类无关的类，保证了内部类的独立性，正是基于这一点，多重继承才会称为
        可能。MotherSpecial的这种内部类叫做成员内部类（也叫做实例内部类，Instance Inner Class）
         */
    }

    /**
     * 建议42： 让工具类不可实例化
     */
    private void suggest42() {
        // 1. 构造函数设置为私有
        // 2. 在构造函数中抛异常
        /*
        public class UtilsClass {
            private UtilsClass(){
                throw new Error("不要实例化我！");
            }
        }
         */
        /**
         * 注意
         * 如果一个类不允许实例化，就要保证“平常”渠道都不能实例化它
         */
    }

    /**
     * 建议43： 避免对象的浅拷贝
     */
    private void suggest43() {
        /*
        一个类实现了Cloneable接口就表示它具备了被拷贝的能力，如果再覆写clone()方法就会完全具备拷贝能力，
        拷贝是在内存中进行的，所以在性能方面比直接通过new生成要快很多，特别是在大对象的生成上，这会使性能
        的提升非常显著。但是对象拷贝也有一个比较容易忽略的问题：浅拷贝（Shadow Clone，也叫做影子拷贝）存
        在对象属性拷贝不彻底的问题
        所有的类都继承自Object，Object提供了一个对象拷贝的默认方法，但是该方法是有缺陷的，它提供的是一种
        浅拷贝方式，也就是说它并不会把对象的所有属性全部拷贝一份，而是有选择性的拷贝，它的拷贝规则如下：
        1）基本类型
        如果变量是基本类型，则拷贝其值，比如int、float等
        2）对象
        如果变量是一个实例对象，则拷贝地址引用，也就是说此时新拷贝出的对象与原有对象共享该实例变量，不受访
        问权限的限制。这在Java中是很疯狂的，因为它突破了访问权限的定义：一个private修饰的变量，竟然可以被
        两个不同的实例对象访问，这让Java的访问权限体系情何以堪
        3）String字符串
        这个比较特殊，拷贝的也是一个地址，是个引用，但是在修改时，它会从字符串池（String Pool）中重新生成
        新的字符串，原有的字符串对象保持不变，在此处可以认为String是一个基本类型
         */
        /**
         * 注意
         * 浅拷贝只是Java提供的一种简单拷贝机制，不便于直接使用
         */
    }

    /**
     * 建议44： 推荐使用序列化实现对象的拷贝
     */
    private void suggest44() {
        /*
        如果一个项目中有大量的对象是通过拷贝生成的，那该如何处理？每个类都写一个clone方法，并且还要深拷贝？
        想想看这是何等巨大的工作量呀，是否有更好的方法呢？
        其实，可以通过序列化方式来处理，在内存中通过字节流的拷贝来实现，也就是把母对象写到一个字节流中，再
        从字节流中将其读出来，这样就可以重建一个新对象了，该新对象与母对象之间不存在引用共享的问题，也就相
        当于深拷贝了一个新对象

        自定义工具类CloneUtils，该工具类要求被拷贝的对象必须实现Serializable接口，否则是没办法拷贝的（当
        然，使用反射那是另外一种技巧）。用此方法进行对象拷贝时需要注意两点：
        1）对象的内部属性都是可序列化的
        如果内部属性不可序列化，则会抛出序列化异常，这会让调用者呢纳闷：生成一个对象怎么会出现序列化异常呢？
        从这一点来考虑，也需要把CloneUtils工具的异常进行细化处理
        2）注意方法和属性的特殊修饰符
        比如final、static变量的序列化问题会被引入到对象拷贝中来，这点需要特别注意，同时transient变量（瞬
        态变量，不进行序列化的变量）也会影响到拷贝的效果
         */
        /**
         * 注意
         * 可以采用序列化方式实现对象的拷贝，使用Apache下的commons工具包中的SerializationUtils工具类，直
         * 接使用更加简洁方便
         */
    }

    /**
     * 建议45： 覆写equals方法时不要识别不出自己
     */
    private void suggest45() {
        /**
         * 注意
         * equals方法的自反性原则：对于任何非空引用x，x.equals(s)应该返回true
         */
    }

    /**
     * 建议46： equals应该考虑null值情景
     */
    private void suggest46() {
        /**
         * 注意
         * equals方法的对称性原则：对于任何引用x和y的情形，如果x.equals(y)返回true，那么y.equals(x)
         * 也应该返回true
         */
    }

    /**
     * 建议47： 在equals中使用getclass进行类型判断
     */
    private void suggest47() {
        /**
         * 注意
         * instanceof关键字是用来判断是否是一个类的实例对象的，这很容易让子类“钻空子”。在覆写equals方法
         * 时建议使用getClass进行类型判断，而不要使用instanceof
         */
    }

    /**
     * 建议48： 覆写equals方法必须覆写hashcode方法
     */
    private void suggest48() {
        /**
         * 注意
         * 覆写equals方法必须覆写hashcode方法，在Apache的commons包下有一个HashCodeBuilder类，是一个
         * 哈希码生成工具，使用起来非常方便
         */
    }

    /**
     * 建议49： 推荐覆写tostring方法
     */
    private void suggest49() {
        /**
         * 注意
         * Java提供的默认toString方法不友好，打印出来看不懂，toString可以做为一个类的说明
         * 使用Apache的commons工具包中的ToStringBuilder类，简洁、实用又方便
         */
    }

    /**
     * 建议50： 使用package-info类为包服务
     */
    private void suggest50() {
        /*
        Java中有一个特殊的类：package-info类，它是专门为本包服务的，它的特殊体现在3个方面：
        1）它不能随便被创建
        在一般的IDE中，Eclipse、package-info等文件是不能随便被创建的，会报“Type name is
        notvalid”错误，类名无效（中划线不在Java变量定义规范中）。那怎么创建这个文件呢？很
        简单，用记事本创建一个，然后拷贝进去再改一下就成了，直接从别的项目拷贝也行
        2）它服务的对象很特殊
        它是描述和记录本包信息的
        3）package-info类不能有实现代码
        在package-info.java文件里不能声明package-info类
        package-info类还有几个特殊的地方，比如不可以继承，没有接口，没有类间关系（关联、组合、
        聚合等）等。package-info的作用主要表现在以下三个方面：
        1）声明友好类和包内访问常量
        这个比较简单，而且很实用，比如一个包中有很多内部访问的类或常量，就可以统一放到package-info
        类中，这样很方便，而且便于集中管理，可以减少友好类到处游走的情况
        2）为在包上标注注解提供便利
        3）提供包的整体注解说明
         */
        /**
         * 注意
         * 在需要用到包的地方，就可以考虑一下package-info这个特殊类，也许能起到事半功倍的作用
         */
    }

    /**
     * 建议51： 不要主动进行垃圾回收
     */
    private void suggest51() {
        /**
         * 注意
         * 主动进行垃圾回收是一个非常危险的动作，因为System.gc要停止所有的响应（Stop the World），
         * 才能检查内存中是否有可回收的对象
         * 不要调用System.gc，即使经常出现内存溢出也不要调用，内存溢出是可分析的，是可以查找出原
         * 因的，GC可不是一个好招数
         */
    }

}
class Base{
    //父类静态方法
    public static void doSomething(){
        System.out.println("我是父类静态方法");
    }
    //父类非静态方法
    public void doAnything(){
        System.out.println("我是父类非静态方法");
    }
}

class Sub extends Base{
    //子类同名、同参数的静态方法
    public static void doSomething(){
        System.out.println("我是子类静态方法");
    }

    //重写父类的非静态方法
    @Override
    public void doAnything(){
        System.out.println("我是子类非静态方法");
    }
}

//定义一个服务
abstract class Server{
    public final static int DEFAULT_PORT = 40000;
    public Server(){
        //获得子类提供的端口号
        int port = getPort();
        System.out.println("端口号：" + port);
		/*进行监听动作*/
    }
    //由子类提供端口号，并做可用性检查
    protected abstract int getPort();
}

class SimpleServer extends Server{
    private int port=100;
    //初始化传递一个端口号
    public SimpleServer(int _port){
        port = _port;
    }
    //检查端口号是否有效，无效则使用默认端口，这里使用随机数模拟
    @Override
    protected int getPort() {
        return Math.random() > 0.5?port:DEFAULT_PORT;
    }
}

//父类
class Father1{
    Father1(){
        new Other();
    }
}
//子类
class Son1 extends Father1{
    public void doSomething(){
        System.out.println("Hi,show me something");
    }
}
//相关类
class Other{
    public Other(){
        new Son();
    }
}

//定义一个枚举，限定操作符
enum Ops {ADD, SUB}

class Calculator {
    private int i, j, result;
    //无参构造
    public Calculator() {}
    //有参构造
    public Calculator(int _i, int _j) {
        i = _i;
        j = _j;
    }
    //设置符号，是加法运算还是减法运算
    protected void setOperator(Ops _op) {
        result = _op.equals(Ops.ADD)?i+j:i-j;
    }
    //取得运算结果
    public int getResult(){
        return result;
    }
}

//加法计算
class Add extends Calculator {
    {
        setOperator(Ops.ADD);
    }
    //覆写父类的构造方法
    public Add(int _i, int _j) {
        super(_i,_j);
    }
}

//父亲
interface Father{
    public int strong();
}

//母亲
interface Mother{
    public int kind();
}
class FatherImpl implements Father{
    //父亲的强壮指数是8
    public int strong(){
        return 8;
    }
}

class MotherImpl implements Mother{
    //母亲的温柔指数是8
    public int kind(){
        return 8;
    }
}

class Son extends FatherImpl implements Mother{
    @Override
    public int strong(){
        //儿子比父亲强壮
        return super.strong() + 1;
    }

    @Override
    public int kind(){
        return new MotherSpecial().kind();
    }

    private class MotherSpecial extends MotherImpl{
        public int kind(){
            //儿子温柔指数降低了
            return super.kind() - 1;
        }
    }
}

class Daughter extends MotherImpl implements Father{

    @Override
    public int strong() {
        return new FatherImpl(){
            @Override
            public int strong() {
                //女儿的强壮指数降低了
                return super.strong() - 2 ;
            }
        }.strong();
    }
}