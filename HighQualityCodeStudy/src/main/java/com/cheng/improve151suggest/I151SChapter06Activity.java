package com.cheng.improve151suggest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import com.cheng.highqualitycodestudy.R;
import com.cheng.improve151suggest.adapter.I151SuggestListAdapter;

/**
 第6章 枚举和注解
 建议83： 推荐使用枚举定义常量
 建议84： 使用构造函数协助描述枚举项
 建议85： 小心switch带来的空值异常
 建议86： 在switch的default代码块中增加assertionerror错误
 建议87： 使用valueof前必须进行校验
 建议88： 用枚举实现工厂方法模式更简洁
 建议89： 枚举项的数量限制在64个以内
 建议90： 小心注解继承
 建议91： 枚举和注解结合使用威力更大
 建议92： 注意@override不同版本的区别
 */
public class I151SChapter06Activity extends AppCompatActivity {
    private static final String TAG = "I151SChapter06Activity";

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
        String[] suggests = getResources().getStringArray(R.array.i151schapter6);
        I151SuggestListAdapter adapter = new I151SuggestListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
    }

    /**
     * 建议83： 推荐使用枚举定义常量
     */
    private void suggest83() {
        /*
        在Java1.5之前，只有两种声明常量的方式：类常量和接口常量，1.5新增了一种常量声明方式：枚举声明
        常量，枚举的优点主要表现在以下四个方面
        1）枚举常量更简单
        首先对比定义方式，枚举常量只需要定义每个枚举项，不需要定义枚举值，而接口常量（或类常量）则必须
        定义值，否则编译通不过；其次，虽然两者被引用的方式相同（都是“类名.属性”），但是枚举表示的是一个
        枚举项，而接口常量却是一个值
        2）枚举常量属于稳定型
        枚举在编译期间限定类型，不允许发生越界的情况（在switch中试试）
        3）枚举具有内置方法
        可以通过values方法获取所有的枚举项。每个枚举都是java.lang.Enum的子类，该基类提供了诸如获得排序
        值的ordinal方法、compareTo比较方法等，大大简化了常量的访问
        4）枚举可以自定义方法
        类常量也可以有自己的方法，但关键是枚举常量不仅可以定义静态方法，还可以定义非静态方法，而且还能够
        从根本上杜绝常量类被实例化
        虽然枚举常量在很多方面比接口常量和类常量好用，但是有一点它是比不上接口常量和类常量的，那就是继承，
        枚举类型是不能有继承的，也就是说一个枚举常量定义完毕后，除非修改重构，否则无法做扩展，而接口常量
        和类常量这可以通过继承实现扩展，但是，一般常量在项目构建时就定义完毕了，很少会出现必须通过扩展才
        能实现业务逻辑的场景
         */
        /**
         * 注意
         * 在项目开发中，推荐使用枚举常量代替接口常量或类常量
         */
    }

    /**
     * 建议84： 使用构造函数协助描述枚举项
     */
    private void suggest84() {
        /*
        一般来说，经常使用的枚举项只有一个属性，即排序号，其默认值是从0、1、2...。但是除了排序号外，枚举
        还有一个（或多个）属性：枚举描述，它的含义是通过枚举的构造函数，声明每个枚举项（也就是枚举的实例）
        必须具有的属性和行为，这是对枚举项的描述或补充，目的是使枚举项表述的意义更加清晰准确
        可以通过枚举构造函数声明业务值，定义可选项，添加属性等

        Role
        这是一个角色定义类，描述了两个角色：管理员（Admin）和普通用户（User），同时它还通过构造函数对
        这两个角色进行了描述：
        name：表示的是该角色的中文名称
        lifeTime：表示的是该角色的生命期，也就是多长时间该角色失效
        scope：表示的是该角色的权限范围
         */
        /**
         * 注意
         * 推荐大家在枚举定义中为每个枚举项定义描述，特别是在大规模的项目开发中，大量的常量项定义使用
         * 枚举项描述比在接口常量或类常量中增加注释的方式友好得多，简洁得多
         */
    }

    /**
     * 建议85： 小心switch带来的空值异常
     */
    private void suggest85() {
        doSports(null);
        /*
        doSports方法中输入了一个Season类型的枚举，然后使用switch进行匹配，目的是输出每个季节能进行
        的活动。现在的问题是：这段代码有没有问题？
        上面调用该方法传入一个null值进去看看有没有问题？似乎会打印出“输出错误！”，因为在switch中没有
        匹配到指定值，所以会打印出default的代码块，是这样的吗？不是，运行，报错：NullPointerException
        竟然是空指针异常，怎么会有空指针呢？这就与枚举和switch的特性有关了，此问题也是在开发中经常发生
        的。目前Java中的switch语句只能判断byte、short、char、int类型（JDK7已经允许使用String类型），
        这是Java编译器的限制。问题是为什么枚举类型也可以跟在switch后面呢？
        因为编译时，编译器判断出switch语句后的参数是枚举类型，然后就会根据枚举的排序值继续匹配。Switch
        语句是先计算season变量的排序值，然后与枚举常量的每个排序值进行对比的。例子中season变量是null
        值，无法执行ordinal方法，于是报空指针异常了
         */
        /**
         * 注意
         * 在switch中使用枚举时要先判空
         */
    }

    /**
     * 建议86： 在switch的default代码块中增加AssertionError错误
     */
    private void suggest86() {
        /*
        switch后跟枚举类型，case后列出所有的枚举项，这是一个使用枚举的主流写法，那留着default语句似
        乎没有任何作用，程序已经列举了所有的可能选项，肯定不会执行到default语句，看上去纯属多余嘛！
        错了，这个default还是很有用的。switch代码与枚举之间没有强制约束关系，也就是说两者只是在语义
        上建立了联系，并没有一个强制约束，比如LogLevel枚举发生改变，增加了一个枚举项FATAL，如果此时
        我们对switch语句不做任何修改，编译虽然不会出现问题，但是运行期就会发生非预期的错误：FATAL类
        型的日志没有输出
        为了避免出现这类错误，建议在default后直接抛出一个AssertionError错误，其含义就是“不要跑到这
        里来，一跑到这里就会出问题”，这样一来可以保证在增加一个枚举项的情况下，若其他代码未修改，运行
        期马上就会报错，这样一来就很容易查找到错误，方便立刻排除
         */
        /**
         * 注意
         * 可以修改IDE的错误提示级别，如果不判断所有的枚举项就不能编译通过
         */
    }

    /**
     * 建议87： 使用valueOf前必须进行校验
     */
    private void suggest87() {
        /*
        每个枚举都是java.lang.Enum的子类，都可以访问Enum类提供的方法，比如hashCode、name、valueOf
        等，其中valueOf方法会把一个String类型的名称转变为枚举项，也就四在枚举项中查找出字面值与该参
        数相等的枚举项。
         */
        // 注意summer是小写
        List<String> params = Arrays.asList("Spring", "summer");
        for (String name : params) {
            // 查找字面值与name相同的枚举项
            Season s = Season.valueOf(name);
            if (s != null) {
                // 有该枚举项时
                Log.e(TAG, s.getDesc());
            } else {
                // 没有该枚举项时
                Log.e(TAG, "无相关枚举项");
            }
        }
        /*
        这段程序看似很完美，其中考虑到从String转换为枚举类型可能存在着转换不成功的情况，比如没有匹配
        到指定值，此时valueOf的返回值应该为空，所以后面又紧跟着if...else判断输出。这段程序真的完美
        无缺了吗？运行看看：IllegalArgumentException
        报无效参数异常，也就是说summer无法转换为Season枚举，无法转换就不转换嘛，那也别抛出非受检异常
        啊，一旦抛出这个异常，后续的代码就不会运行了，这才是要命呀！这与我们的习惯用法非常不一致，例如
        从一个List中查找一个元素，即使不存在也不会报错，顶多indexOf方法返回-1
        解决方案
        1）使用try...catch捕捉异常
        2）扩展枚举类
        由于Enum类定义的方法基本上都是final类型的，所以不希望被覆写，那可以通过增加一个contains方法来
        判断是否包含指定的枚举项，然后再继续转换
         */
        /**
         * 注意
         * 在使用枚举的valueOf前判断一下是否包含指定的枚举名称，若包含则可以通过valueOf转换为枚举常量，
         * 若不包含则不转换
         */
    }

    /**
     * 建议88： 用枚举实现工厂方法模式更简洁
     */
    private void suggest88() {
        /*
        工厂方法模式是“创建对象的接口，让子类决定实例化哪一个类，并使一个类的实例化延迟到其子类”。
        可以通过枚举实现工厂方法模式，有如下两种方案：
        1）枚举非静态方法实现工厂方法模式
        每个枚举项都是该枚举的实例对象，那是不是定义一个方法可以生成每个枚举项对应产品来实现此模式呢？
        // 生产汽车
		Car car = CarFactory1.BuickCar.create();
		create是一个非静态方法，也就是只有通过FordCar、BuickCar枚举项才能访问
		2）通过抽象方法生成产品
		枚举类型虽然不能继承，但是可以用abstract修饰其方法，此时就表示该枚举是一个抽象枚举，需要每个
		枚举项自行实现该方法，也就是说枚举项的类型是该枚举的一个子类，见CarFactory2
		首先定义一个抽象制造方法create，然后每个枚举项目自行实现。这种方式编译后会产生两个CarFactory
		的匿名子类，因为每个枚举项都要实现抽象create方法。客户端的调用与上一个方案相同
		Car car = CarFactory2.BuickCar.create();
         */
        /*
        使用枚举类型的工厂方法模式有以下三个优点：
        1）避免错误调用的发生
        一般工厂方法模式的生产方法（也就是createCar方法）可以接收三种类型的参数：类型参数（如上例）、
        String参数（生产方法中判断String参数是需要生产什么产品）、int参数（根据int值判断需要生成什么
        类型的产品），这三种参数都是宽泛的数据类型，很容易产生错误（比如边界问题、null值问题），而且出
        现这类错误编译器还不会报警，例如：
        // 生产车辆
        Car car = CarFactory.createCar(Car.class);
        Car是一个接口，完全合乎createCar方法的要求，所以它在编译时不会报任何错误，但一运行起来就会报
        InstantitationException异常。而使用枚举类型的工厂方法模式就不存在该问题了，不需要传递任何参
        数，只需要选择好生产什么类型的产品即可
        2）性能好，使用便捷
        枚举类型的计算是以int类型的计算为基础的，这是最基础的操作，性能当然会快，至于使用便捷，注意看
        客户端的调用，链式调用，可读性高
        3）降低类间耦合
        不管生产方法接收的是Class、String还是int的参数，都会成为客户端类的负担，这些类并不是客户端需
        要的，而是因为工厂方法的限制必须输入的，例如Class参数，对客户端方法来说，它需要传递一个
        FordCar.class参数才能生产一辆福特汽车，除了在create方法中传递该参数外，业务类不需要改Car的实
        现类。这严重违背了迪米特原则，也就是最少知识原则：一个对象应该对其他对象有最少的了解
        而枚举类型的工厂方法就没有这种问题了，它只需要依赖工厂类就可以生产一辆符合接口的汽车，完全可以
        无视具体汽车类的存在
         */
        /**
         * 注意
         * 尝试使用枚举来实现工厂方法模式
         */
    }

    /**
     * 建议89： 枚举项的数量限制在64个以内
     */
    private void suggest89() {
        /*
         为了更好地使用枚举，Java提供了两个枚举集合：EnumSet和EnmuMap，这两个集合的使用方法都比较简
         单，EnumSet表示其元素必须是某一枚举的枚举项，EnumMap表示Key值必须是某一枚举的枚举项，由于枚
         举类型的实例数量固定并且有限，相对来说EnumSet和EnumMap的效率会比其他Set和Map要高
         虽然EnumSet很好用，但是它有一个隐藏的特点，我们逐步分析。在项目中一般会把枚举用作常量定义，可
         能会定义非常多的枚举项，然后通过EnumSet访问、遍历，但它对不同的枚举数量有不同的处理方式。为了
         进行对比，我们定义两个枚举，一个数量等于64，一个是65（大于64即可，为什么是64而不是128、512呢？
         稍后解释），代码如下：
         见 Const 和 LargeConst
         Const中的枚举项数量是64，LargeConst的数量是65。接下来我们希望把这两个枚举转换为EnumSet，然后
         判断一下它们的class类型是否相同
        */
        // 创建包含所有枚举项的EnumSet
        EnumSet<Const> cs = EnumSet.allOf(Const.class);
        EnumSet<LargeConst> lcs = EnumSet.allOf(LargeConst.class);
        // 打印出枚举项数量
        Log.e(TAG, "Const 枚举项数量：" + cs.size()); // 64
        Log.e(TAG, "LargeConst 枚举项数量：" + lcs.size()); // 65
        // 输出两个EnumSet的class
        Log.e(TAG, "cs :" + cs.getClass()); // RegularEnumSet
        Log.e(TAG, "lcs :" + lcs.getClass()); // JumboEnumSet
        /*
        现在的问题是：cs和lcs的class类型是否相同？应该相同吧，都是EnumSet类的工厂方法allOf生成的EnumSet
        类，而且JDK API也没有提示EnumSet有子类，运行查看输出，发现，两者不相等。就差一个元素，两者就不
        相等了？确实如此，这也是要重点关注枚举数量的原因。来看看Java是如何处理的，首先跟踪allOf方法，查
        看其源代码，allOf通过noneOf方法首先生成一个EnumSet对象，然后把所有的枚举项都加进去，问题可能出
        在EnumSet的生成上，来看noneOf的源码...；Java原来是如此处理的：当枚举项数量小于等于64时，创建一
        个RegularEnumSet实例对象，大于64时则创建一个JumboEnumSet实例对象
        紧接着的问题是：为什么要如此处理呢？这还要看看这两个类之间的差异，首先看RegularEnumSet类（查看源
        码）。我们知道枚举项的排序值ordinal是从0、1、2...依次递增的，没有重号，没有跳号，RegularEnumSet
        就是利用这一点把每个枚举项的ordinal映射到一个long类型的每个位上的，注意看addAll方法的elments元素，
        它使用了无符号右移操作，并且操作数是负数，位移也是负数，这表示是负数（符号位是1）的“无符号左移”：符
        号位为0，并补充低位，简单地说，Java把一个不多于64个枚举项的枚举映射到了一个long类型变量上。这才是
        EnumSet处理的重点，其他的size方法、constains方法等都是根据elements计算出来的。想想看，一个long
        类型的数字包含了所有的枚举项，其效率和性能肯定是非常优秀的
        long类型是64位的，所以RegularEnumSet类型也就只能负责枚举项数量不大于64的枚举（这也是我们以64来举
        例，而不以128或512举例的原因），大于64则由JumboEnumSet处理，我们看它是怎么处理的。JumboEnumSet类
        把枚举项按照64个元素一组拆成了多组，每组都映射到一个long类型的数字上，然后该数组再放置到elemnets数
        组中。简单来说JumboEnumSet类的原理与RegularEnumSet相似，只是JumboEnumSet使用了long数组容纳更多
        的枚举项
         */
        /**
         * 注意
         * 枚举项数量不要超过64，否则建议拆分
         * 可以这样理解，RegularEnumSet是把每个枚举项编码映射到一个long类型数字的每个位上，JumboEnumSet
         * 是先按照64个一组进行拆分，然后每个组再映射到一个long类型数字的每个位上
         */
    }

    /**
     * 建议90： 小心注解继承
     */
    private void suggest90() {
        /**
         * 注意
         * 采用@Inherited元注解有利有弊，利的地方是一个注解只要标注到父类，所有的子类都会自动具有与父
         * 类相同的注解，整齐、统一而且便于管理，弊的地方是单单阅读子类代码，我们无从知道为何逻辑会被改
         * 变，因为子类没有明显标注该注解。总体上来说，使用@Inherited元注解的弊大于利，特别是一个类的
         * 继承层次较深时，如果注解较多，则很难判断出是那个注解对子类产生了逻辑劫持
         */
    }

    /**
     * 建议91： 枚举和注解结合使用威力更大
     */
    private void suggest91() {
        /*
        注解的写法和接口很类似，都采用了关键字interface，而且都不能有实现代码，常量定义默认都是public
        static final类型的等，它们的主要不同点是：注解要在interface前加上@字符，而且不能继承，不能实现
        来分析一个ACL（Access Control List，访问控制列表）设计案例，看看如何避免这些障碍，ACL有三个重
        要元素：
        资源，有哪些信息是要被控制起来的
        权限级别，不同的访问者规划在不同的级别中
        控制器（也叫鉴权人），控制不同的级别访问不同的资源
        鉴权人是整个ACL的设计核心，从最主要的鉴权人开始，代码如下：
        interface Identifier {
            // 无权访问时的礼貌用语
            String REFUSE_WORD = "您无权访问";
            // 鉴权
            public boolean identify();
        }
        这是一个鉴权人接口，定义了一个常量和一个鉴权方法。接下来应该实现该鉴权方法，但问题是我们的权限级
        别和鉴权方法之间是紧耦合，若拆分成两个类显得有点啰嗦，怎么办？可以直接定义一个枚举来实现。如下：
        enum CommonIdentifier implements Identifier {
            // 权限级别
            Reader, Author, Admin;
            // 实现鉴权
            public boolean identify() {
                return false;
            }
        }
        定义了一个通用鉴权者，使用的是枚举类型，并且实现了鉴权者接口。现在就剩下资源定义了，这很容易定义，资
        源就是我们写的类、方法等，之后再通过配置来决定哪些类、方法允许什么级别的访问。这里的问题是：怎么把资
        源和权限级别关联起来呢？使用XML配置文件？是个方法，但对我们的示例程序来说显得太繁重了，如果使用注解
        会更简洁些，不过这需要我们首先定义出现权限级别的注解，代码如下：
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.TYPE)
        @interface Access {
            // 什么级别可以访问，默认是管理员
            CommonIdentifier level() default CommonIdentifier.Admin;
        }
        该注解是标注在类上面的，并且会保留到运行期。我们定义一个资源类，代码如下：
        @Access(level = CommonIdentifier.Author)
        class Foo {}
        Foo类只能是作者级别的人访问。场景都定义完毕了，那看看如何模拟ACL的实现，代码如下：
        public static void main(String[] args) {
            // 初始化商业逻辑
            Foo b = new Foo();
            // 获取注解
            Access access = b.getClass().getAnnotation(Access.class);
            // 没有Access注解或者鉴权失败
            if (access==null || !access.level().identify()) {
                // 没有Access注解或者鉴权失败
                System.out.println(access.level().REFUSE_WORD);
            }
        }
        看看这段代码，简单、易读，而且如果我们是通过ClassLoader类来解释该注解的，那会使我们的开发更加简洁，
        所有的开发人员只要增加注解即可解决访问控制问题。access是一个注解类型，我们想使用Identifier接口的
        identify鉴权方法和REFUSE_WORD常量，但注解是不能继承的，那怎么办？此处，可通过枚举类型CommonIdentifier
        从中间做一个委派动作（Delegate），委派？没看到！你可以让identify返回一个对象，或者在Identifier上
        直接定义一个常量对象，那就是“赤裸裸”的委派了！
         */
    }

    /**
     * 建议92： 注意@override不同版本的区别
     */
    private void suggest92() {
        /*
        Java 1.5 版中@Override是严格遵守覆写的定义：子类方法与父类方法必须具有相同的方法名、输入参数、
        输出参数（允许子类缩小）、访问权限（允许子类扩大），父类必须是一个类，不能是一个接口，否则不能
        算是覆写。而这在Java 1.6 就开放很多，实现接口的方法也可以加上@Override注解
         */
        /**
         * 注意
         * 在多环境部署应用时，需要考虑@Override在不同版本下代表的意义，如果是Java 1.6 版本的程序移植
         * 到1.5版本环境中，就需要删除实现接口方法上的@Override注解
         */
    }

    public void doSports(Season season) {
        switch (season) { // == season.ordinal()
            case Spring: // == Season.Spring.ordinal()
                System.out.println("春天放风筝");
                break;
            case Summer:
                System.out.println("夏天游泳");
                break;
            case Autumn:
                System.out.println("秋天捉知了");
                break;
            case Winter:
                System.out.println("冬天滑冰");
                break;
            default:
                System.out.println("输入错误！");
                break;
        }
    }

}

enum Season {
    Spring("春"), Summer("夏"), Autumn("秋"), Winter("冬");

    private String desc;
    Season(String _desc){
        desc = _desc;
    }

    // 获得枚举值
    public String getDesc(){
        return desc;
    }

    // 是否包含指定名称的枚举项
    public static boolean contains(String name) {
        // 所有的枚举值
        Season[] season = values();
        // 遍历查找
        for (Season s : season) {
            if (s.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}

enum Role {

    Admin("管理员",new Lifetime(), new Scope()),
    User("普通用户",new Lifetime(), new Scope());

    // 描述
    private String name;
    // 角色的生命期
    private Lifetime lifeTime;
    // 权限范围
    private Scope scope;

    Role(String _name,Lifetime _lt, Scope _scope) {
        name = _name;
        lifeTime = _lt;
        scope = _scope;
    }
    //角色名称
    public String getName(){
        return name;
    }
    // 获得角色的生命期
    public Lifetime getLifetime() {
        return lifeTime;
    }

    // 获得权限范围
    public Scope getScope() {
        return scope;
    }
}

class Lifetime {
}

class Scope {
}

interface Car {
};

class FordCar implements Car {
};

class BuickCar implements Car {
};

enum CarFactory1 {
    //定义工厂类能生产汽车的类型
    FordCar, BuickCar;
    //生产汽车
    public Car create() {
        switch (this) {
            case FordCar:
                return new FordCar();
            case BuickCar:
                return new BuickCar();
            default:
                throw new AssertionError("无效参数");
        }
    }
}

enum CarFactory2 {
    FordCar {
        public Car create() {
            return new FordCar();
        }
    },
    BuickCar {
        public Car create() {
            return new BuickCar();
        }
    };
    //抽象生产方法
    public abstract Car create();
}

// 普通枚举项，数量小于64
enum Const {
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,
    AA, BA, CA, DA, EA, FA, GA, HA, NA, OA, PA, QA, RA, SA, TA, UA, VA, WA, XA, YA,
    ZA, BC, CC, DC, EC, FC, GC, HC, IC, JC, KC, LC, MC, NC, OC, PC, QC, RC;
}
// 大枚举，数量超过64
enum LargeConst {
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,
    AA, BA, CA, DA, EA, FA, GA, HA, IA, JA, KA, LA, MA, NA, OA, PA, QA, RA, SA, TA,
    UA, VA, WA, XA, YA, ZA, AB, BB, CB, DB, EB, FB, GB, HB, IB, JB, KB, LB, MB;
}