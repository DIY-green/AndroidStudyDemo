package com.cheng.improve151suggest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.Arrays;
import com.cheng.highqualitycodestudy.R;
import com.cheng.improve151suggest.adapter.I151SuggestListAdapter;

/**
 第8章 异常
 建议110： 提倡异常封装
 建议111： 采用异常链传递异常
 建议112： 受检异常尽可能转化为非受检异常
 建议113： 不要在finally块中处理返回值
 建议114： 不要在构造函数中抛出异常
 建议115： 使用throwable获得栈信息
 建议116： 异常只为异常服务
 建议117： 多使用异常，把性能问题放一边
 */
public class I151SChapter08Activity extends AppCompatActivity {
    private static final String TAG = "I151SChapter08Activity";

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
        String[] suggests = getResources().getStringArray(R.array.i151schapter8);
        I151SuggestListAdapter adapter = new I151SuggestListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
    }

    /**
     * 建议110： 提倡异常封装
     */
    private void suggest110() {
        /*
        Java语音的异常处理机制可以确保程序的健壮性，提高系统的可用率，但是Java API提供的异常都是比较
        低级的（这里的低级别是指“低级别”的异常），只有开发人员才能看得懂，才明白发生了什么问题。而对于
        用户来说，这些异常是没意义的，与业务无关，是纯计算机语言的描述，那该怎么办？这就需要我们对异常
        进行封装了。异常封装有三方面的优点：
        1）提高系统的友好性
        可以把异常的阅读者分为两类：开发人员和用户。开发人员查找问题，需要打印出堆栈信息，而用户则需要
        了解具体的业务原因，比如文件太长、不能同时编写文件等，代码如下：
        public void doStuff() throws MyBusinessException {
            try {
                InputStream is = new FileInputStream("无效文件.txt");
            } catch (FileNotFoundException e) {
                // 为方便开发和维护人员而设置的异常信息
                e.printStackTrace();
                // 抛出业务异常
                throw new MyBusinessException();
            }
        }
        2）提高系统的可维护性
        写一个catch块来处理所有的异常，这种方式不可取，虽然JVM会打印出栈中的出错信息，但是该信息只有开
        发人员自己才看得懂，维护人员看到这段异常时基本上无法处理，因为需要深入到代码逻辑中去分析问题。正
        确的做法是对异常进行分类处理，并进行封装输出，代码如下：
        public void doStuff() {
            try {
                // do something
            } catch (FileNotFoundException e) {
                log.info("文件未找到，使用默认配置文件...");
            } catch (SecurityException e) {
                log.error("无权访问，可能原因是...");
                e.printStackTrace();
            }
        }
        如此包装后，维护人员看到这样的异常就有了初步的判断，或者检查配置，或者初始化环境，不需要直接到
        代码层级去分析了
        3）解决Java异常机制自身的缺陷
        Java中的异常一次只能抛出一个，比如doStuff方法有两个逻辑代码片段，如果在第一个逻辑片段中抛出异常，
        则第二个逻辑片段就不再执行了，也就无法抛出第二个异常了，现在的问题是：如何才能一次抛出两个（或多
        个）异常呢？
        其实，使用自行封装的异常可以解决该问题，代码如下：
        class MyException extends Exception {
            // 容纳所有的异常
            private List<Throwable> causes = new ArrayList<Throwable>();
            // 构造函数，传递一个异常列表
            public MyException(List<? extends Throwable> _cause) {
                causes.addAll(_causes);
            }
            // 读取所有的异常
            public List<Throwable> getExceptions() {
                return causes;
            }
        }
        MyException异常只是一个异常容器，可以容纳多个异常，但它本身并不代表任何异常含义，它所解决的是一次
        抛出多个异常的问题，具体调用如下：
        public void doStuff() throws MyException {
            List<Throwable> list = new ArrayList<Throwable>();
            // 第一个逻辑片段
            try {
                // do something
            } catch (Exception e) {
                list.add(e);
            }
            // 第二个逻辑片段
            try {
                // do something
            } catch (Exception e) {
                list.add(e);
            }
            // 检查是否有必要抛出异常
            if (list.size() > 0) {
                throw new MyException(list);
            }
        }
        这样一来，doStuff方法的调用者就可以一次获得多个异常了，也能够为用户提供完整的例外情况说明。可能有
        疑问：这种情况可能出现吗？怎么会要求一个方法抛出多个异常呢？
        绝对可能出现，例如Web界面注册时，展现层依次把User对象传递到逻辑层，Register方法需要对各个Field进
        行校验并注册，例如用户名不能重复，密码必须符合密码策略等，不要出现用户第一次提交时系统提示“用户名重
        复”，在用户修改用户名再次提交后，系统又提示“密码长度少于6位”的情况，这种操作模式下用户体验非常糟糕，
        最好的解决办法就是封装异常，建立异常容器，一次性对User对象进行校验，然后返回所有的异常
         */
        /**
         * 注意
         * 异常封装很重要，作用很大
         */
    }

    /**
     * 建议111： 采用异常链传递异常
     */
    private void suggest111() {
        /*
        设计模式中有一个模式叫做责任链模式（Chain of Responsibility），它的目的是将多个对象连成一条链，
        并沿着这条链传递该请求，直到有对象处理它为止，异常的传递处理也应该采用责任链模式
        异常需要封装，但仅仅封装还是不够的，还需要传递异常。比如，JEE项目一般都有三层结构：持久层、逻辑
        层、展现层，持久层负责与数据库交互，逻辑层负责业务逻辑的实现，展现层负责UI数据的处理。有这样一个
        模块：用户第一次访问的时候，需要持久层从user.xml中读取信息，如果该文件不存在则提示用户创建之，那
        问题来了：如果直接把持久层异常FileNotFoundException抛弃掉，逻辑层根本无从得知发生了何事，也就不
        能为展现层提供一个友好的处理结果了，最终倒霉的就是展现层：没有办法提供异常信息，只能告诉用户说“出
        错了，我也不知道出什么错了”--毫无友好性可言
        正确的做法是先封装，然后传递，过程如下：
        1）把FileNotFoundException封装为MyException
        2）抛出到逻辑层，逻辑层根据异常代码（或者自定义的异常类型）确定后续处理逻辑，然后抛出到展现层
        3）展现层自行决定要展现什么，如果是管理员则可以展现低层级的异常，如果是普通用户则展示封装后的异常
        那异常如何传递了？很简单，使用异常链进行异常的传递，以IOException为例来看看如何传递的，代码如下：
        public class IOException extends Exception {
            // 定义异常原因
            public IOException(String message) {
                super(message);
            }
            // 定义异常原因，并携带原始异常
            public IOException(String message, Throwable cause) {
                super(message, cause);
            }
            // 保留原始异常信息
            public IOException(Throwable cause) {
                super(cause);
            }
        }
        在IOException的构造函数中，上一个层级的异常可以通过异常链进行传递，链中传递异常的代码如下所示：
        try {
            // do something
        } catch (Exception e) {
            throw new IOException(e);
        }
        捕捉到Exception异常，然后把它转化为IOException异常并抛出（此种方式也叫作异常转译），调用者获得
        该异常后再调用getCause方法即可获得Exception的异常信息，如此即可方便地查找到产生异常的根本信息，
        便于解决问题
         */
        /**
         * 注意
         * 异常需要封装和传递，我们在进行系统开发时不要“吞噬”异常，也不要“赤裸裸”地抛出异常，封装后再抛
         * 出，或者通过异常链传递，可以达到系统更健壮、友好的目的
         */
    }

    /**
     * 建议112： 受检异常尽可能转化为非受检异常
     */
    private void suggest112() {
        /*
        受检异常是正常逻辑的一种补偿处理手段，特别是对可靠性要求比较高的系统来说，在某些条件下必须抛出受
        检异常以便程序进行补偿处理，也就是说受检异常有合理的存在理由，那为什么要把受检异常转化为非受检异
        常呢？难道说受检异常有什么缺陷或不足吗？是的，受检异常确实有不足的地方：
        1）受检异常使接口声明脆弱
        OOP要求我们尽量多地面向接口编程，可以提高代码的扩展性、稳定性等，但是一旦涉及异常问题就不一样了，
        例如系统初期是这样设计一个接口的：
        interface User {
            // 修改用户名密码，抛出安全异常
            void changePassword() throws MySecurityException;
        }
        随着系统的开发，User接口有了多个实现者，比如普通的用户UserImpl、 模拟用户MockUserImpl（用作测
        试或系统管理）。非实体用户NonUserImpl（如自动执行机、逻辑处理器等），此时如果发现changePassword
        方法可能还需要抛出RejectChangeException（拒绝修改异常，如自动执行机正在处理任务时不能修改密码），
        那就需要抛出User接口了：changePassword方法增加抛出RejectChangeException异常，这会导致所有的User
        调用者都要追加对RejectChangeException异常问题的处理
        这里产生了两个问题：一是异常是主逻辑的补充逻辑，修改一个补充逻辑，就会导致主逻辑也被修改，也就是出
        现了实现类“逆影响”接口的情景，我们知道实现类是不稳定的，而接口是稳定的，一旦定义了异常，则增加了接
        口的不稳定性，这是对面向对象设计的严重亵渎；二是实现的类变更最终会影响到调用者，破坏了封装性，这也
        是迪米特法则所不能容忍的
        2）受检异常使代码的可读性降低
        一个方法增加了受检异常，则必须有一个调用者对异常进行处理，try catch降低了可读性
        3）受检异常增加了开发工作量
        我们知道，异常需要封装和传递，只有封装才能让异常更容易理解，上层模块才能更好的处理，可这也会导致低
        层级的异常没完没了的封装，无端加重了开发的工作量。比如FileNotFoundException在持久层抛出，就需要
        定义一个MyException进行封装，并抛出到上一个层级，于是增加了开发工作量
        受检异常有这么多的缺点，那有没有什么办法可以避免或减少这些缺点呢？有，很简单的一个规则：将受检异常
        转化为非受检异常即可，但是我们也不能把所有的受检异常转化为非受检异常，原因是在编码期上层模块不知道
        下层模块会抛出何种非受检异常，只有通过规则或文档来约束，可以这样说：
        受检异常提出的是“法律下的自由”，必须遵守异常的约定才能自由编写代码
        非受检异常则是“协约性质的自由”，你必须告诉我你要抛出什么异常，否则不会处理
        以User接口为例，我们在声明接口时不再声明异常，而是在具体实现时根据不同的情况产生不同的非受检异常，
        这样持久层和逻辑层抛出的异常将会由展现层自行决定如何展示，不再受异常的规则约束了，大大简化开发工作，
        提供了代码的可读性
        那问题又来了：在开发和设计时什么样的受检异常有必要转化为非受检异常呢？“尽可能”是以什么作为判断依据
        呢？受检异常转换为非受检异常是需要根据项目的场景来决定的，例如同样是刷卡，员工拿着自己的工卡到考勤
        机上打考勤，此时如果附近有磁性物质干扰，则考勤机可以把这种受检异常转化为非受检异常，黄灯闪烁后不做
        任何记录登记，因为考勤失败这种情况不是“致命”的业务逻辑，出错了，重新刷新一下即可。但是到银行网点取
        钱就不一样了，拿着银行卡到银行取钱，同样有磁性物质干扰，刷不出来，那这种异常就必须登记处理，否则会
        称为威胁银行卡安全的事件。汇总成一句话：当受检异常威胁到了系统的安全性、稳定性、正确性时，则必须处
        理，不能转化为非受检异常，其他情况则可以转换为非受检异常
         */
        /**
         * 注意
         * 受检异常威胁到了系统的安全性、稳定性、正确性时，则必须处理，不能转化为非受检异常
         */
    }

    /**
     * 建议113： 不要在finally块中处理返回值
     */
    private void suggest113() {
        /*
        在项目中绝对不能在finally代码块中出现return语句，这是因为这种处理方式非常容易产生“误解”，会严重
        误导开发者。例如如下代码：
        public static void main(String[] args) {
            try {
                doStuff(-1);
                doStuff(100);
            } catch (Exception e) {
                Log.e(TAG, "这里是永远都不会到达的");
            }
        }
        // 该方法抛出受检异常
        public static int doStuff(int _p) throws Exception {
            try {
                if (_p < 0) {
                    throw new DataFormatException("数据格式错误");
                } else {
                    return _p;
                }
            } catch (Exception e) {
                // 异常处理
                throw e;
            } finally {
                return -1;
            }
        }
        对于这段代码，有两个问题：main方法中的doStuff方法的返回值是什么？doStuff方法永远都不会抛出异常？
        答案是：doStuff(-1)的值是-1，doStuff(100)的值也是-1，调用doStuff方法永远都不会抛出异常，有这么
        神奇？原因就是我们在finally代码块中加入了return语句，而这会导致出现以下两个问题：
        1）覆盖了try代码块中的return返回值
        当执行doStuff(-1)时，doStuff方法产生了DataFormatException异常，catch块在捕捉此异常后直接抛出，
        之后代码执行到finally代码块，就会重置返回值，结果就是-1了，也就是出现了先返回，再执行finally，再
        重置返回值的情况
        思考下：是不是可以定义一个变量，在finally中修改后再return呢？代码如下：
        public static int doStuff() {
            int a = 1;
            try {
                return a;
            } catch (Exception e) {
            } finally {
                // 重新修改一下返回值
                a = -1;
            }
            return 0;
        }
        该方法的返回值永远是1，而不会是-1或0（为什么不会执行到“return 0”呢？原因是finally执行完毕后该方法
        已经有返回值了，后续代码就不会再执行了），这都是源于异常代码块的处理方式，在代码中加上try代码块就标
        志着运行时会有一个Throwable线程监视着该方法的运行，若出现异常，则交由异常逻辑处理
        我们知道方法是在栈内存中运行的，并且会按照“先进后出”的原则执行，main方法调用了doStuff方法，则main
        方法在下层，doStuff在上层，当doStuff方法执行完“return a”时，此方法的返回值已经确定是int类型1（a
        变量的值，注意基本类型都是值拷贝，而不是引用），此后finally块再修改a的值已经与doStuff返回值没有任
        何关系了，因此该方法永远都会返回1
        问：那是不是可以在finally代码块中修改引用类型的属性以达到修改返回值的效果呢？代码如下：
        public static Person doStuff() {
            Person person = new Person();
            person.setName("张三");
            try {
                return person;
            } catch (Exception e) {
            } finally {
                // 重新修改一下返回值
                person.setName("李四");
            }
            person.setName("王五");
            return person;
        }
        class Person {
            private String name;
            / name的getter/setter方法省略 /
        }
        此方法的返回值永远都是name为李四的Person对象，原因是Person是一个引用对象，try代码块中的返回值是
        Person对象的地址，finally中再修改那当然会是李四了
        2）屏蔽异常
        为什么明明把异常throw出去了，但main方法却捕捉不到呢？这是因为异常线程在监视到有异常发生时，就会登
        记当前的异常类型为DataFormatException，但是当执行器执行finally代码块时，则会重新为doStuff方法赋
        值，也就是告诉调用者”该方法执行正确，没有产生异常，返回值是1“，于是乎，异常神奇的消失了，简化如下：
        public static void doSomething() {
            try {
                // 正常抛出异常
                throw new RuntimeException();
            } finally {
                // 告诉JVM:该方法正常返回
                return;
            }
        }
        public static void main(String[] args) {
            try {
                doSomething();
            } catch (RuntimeException e) {
                Log.e(TAG, "这里永远都不会到达！");
            }
        }
        上面finally代码块中的return已经告诉JVM：doSomething方法正常执行结束，没有异常，所以main方法就不
        可能获得任何异常信息了。这样的代码会使可读性大大降低，读者很难理解作者的意图，增加了修改难度
        在finally中处理return返回值，代码看上去很完美，都符合逻辑，但是执行起来就会产生逻辑错误，最重要的
        一点是finally是用来做异常的收尾处理的，一旦加上了return语句就会让程序的复杂度陡然提升，而且会产生
        一些隐蔽性非常高的错误
        与return语句相似，System.exit(0)或Runtime.getRuntime().exit(0)出现在异常代码块中会产生非常多的
        错误假象，增加代码的复杂性
         */
        /**
         * 注意
         * 不要在finally代码块中出现return语句
         */
    }

    /**
     * 建议114： 不要在构造函数中抛出异常
     */
    private void suggest114() {
        /*
        Java的异常机制有三种：
        Error类及其子类表示的是错误，它是不需要程序员处理也不能处理的异常，比如VirtualMachineError虚拟
        机错误，ThreadDeath线程僵死等
        RuntimeException类及其子类表示的是非受检异常，是系统可能会抛出的异常，程序员可以去处理，也可以
        不处理，最经典的就是NullPointerException空指针异常和IndexOutOfBoundsException越界异常
        Exception类及其子类（不包含非受检异常）表示的是受检异常，这是程序员必须处理的异常，不处理则程序
        不能通过编译，比如IOException表示I/O异常，SQLException表示数据库访问异常
        我们知道，一个对象的创建要进过内存分配、静态代码初始化、构造函数执行等过程，对象生成的关键步骤是
        构造函数，那是不是也允许在构造函数中抛出异常呢？从Java语法上来说，完全可以在构造函数中抛出异常，
        三类异常都可以，但是从系统设计和开发的角度来分析，则尽量不要在构造函数中抛出异常，下面以三种不同
        类型的异常来说明之。
        1）构造函数抛出错误是程序员无法处理的
        2）构造函数不应该抛出非受检异常
            在构造函数中抛出非受检异常，加重了上层代码编写者的负担
            如果抛出非受检异常，而没有对其进行捕捉，异常最终会抛到JVM，这会导致整个线程执行结束后，后面
        所有的代码都不会继续执行了，这就对业务逻辑产生了致命的影响
        3）构造函数尽可能不要抛出受检异常
            -导致子类代码膨胀
            -违背了里氏替换原则（子类抛出比父类抛出的异常范围要宽，如果在使用父类的地方替换为子类，就需要
            增加新的catch块才能解决）
            为什么Java的构造函数允许子类的构造函数抛出更广泛的异常类呢？这正好与类的异常机制相反，类方法
            的异常是这样要求的：
            子类的方法可以抛出多个异常，但都必须是被覆写方法的子类型，这是Java覆写的要求。构造函数之所以
            与此相反，是因为构造函数没有覆写的概念，只是构造函数间的引用调用而已，所以在构造函数中抛出受
            检异常会违背里氏替换原则，使程序缺乏灵活性
            -子类构造函数扩展受限
            子类存在的原因就是期望实现并扩展父类的逻辑，但是父类构造函数抛出异常却会让子类构造函数的灵活
            性大大降低，例如我们期望这样的构造函数
            class Sub extends Base {
                public Sub() throws Exception {
                    try {
                        super();
                    } catch (IOException e) {
                        // 异常处理后再抛出
                        throw e;
                    } finally {
                        // 收尾处理
                    }
                }
            }
            很不幸，这段代码编译通不过，原因是构造函数Sub中没有把super()放在第一句话中，想把父类的异常
            重新包装后再抛出是不可行的（当然，这里有很多种”曲线“的实现手段，比如重新定义一个方法，然后父
            子类的构造函数都调用该方法，那么子类构造函数就可以自由处理异常了），这是Java语法限制
         */
        /**
         * 注意
         * 在构造函数中不要抛出异常，尽量曲线救国
         */
    }

    /**
     * 建议115： 使用throwable获得栈信息
     */
    private void suggest115() {
        /*
        AOP编程可以很轻松地控制一个方法调用哪些类，也能够控制哪些方法允许被调用，一般来说切面（比如AspectJ）
        只能控制到方法级别，不能实现代码级别的植入（Weave），比如一个方法被类A的m1方法调用时返回1，在
        类B的m2方法调用时返回0（同参数情况下），这就要求被调用者具有识别调用者的能力。这种情况下，可以
        使用Throwable获得栈信息，然后鉴别调用者并分别输出，代码如下：
        class Foo {
            public static boolean m() {
                // 取得当前栈信息
                StackTraceElement[] sts = new Throwable().getStackTrace();
                // 检查是否是m1方法调用
                for (StackTraceElement st : sts) {
                    if (st.getMethodName().equals("m1")) {
                        return true;
                    }
                }
                return false;
            }
        }
        // 调用者
        class Invoker {
            // 调用方法打印出true
            public static void m1() {
                Log.e(TAG, Foo.m() + "");
            }
            // 该方法打印出false
            public static void m2() {
                Log.e(TAG, Foo.m() + "");
            }
        }
        注意看Invoker类，两个方法m1和m2都调用了Foo的m方法，都是无参调用，返回值却不同，这是我们的
        Throwable类发挥效能了。JVM在创建一个Throwable类及其子类时会把当前线程的栈信息记录下来，以
        便在输出异常时准确定位异常原因，来看Throwable源代码：
        public class Throwable implements Serializable {
            // 出现异常的栈记录
            private StackTraceElement[] stackTrace;
            // 默认构造函数
            public Throwable() {
                // 记录栈帧
                fillInStackTrace();
            }
            // 本地方法，抓取执行时的栈信息
            public synchronized native Throwable fillInStackTrace();
        }
        在出现异常时（或主动声明一个Throwable对象时），JVM会通过fillInStackTrace方法记录下栈帧信息，
        然后生成一个Throwable对象，这样就可以知道类间的调用顺序、方法名称及当前行号等了
        获得栈信息可以对调用者进行判断，然后决定不同的输出，比如我们的m1和m2方法，同样的输入参数，同样
        的调用方法，但是输出却不同，这看起来很像是一个Bug：方法m1调用m方法是正常显示，而方法m2调用却会
        返回”错误“数据。因此我们虽然可以依据调用者不同产生不同的逻辑，但这仅局限在对此方法的广泛认知上。
        更多的时候我们使用m方法的变形体，代码如下：
        class Foo {
            public static boolean m() {
                // 取得当前栈信息
                StackTraceElement[] sts = new Throwable().getStackTrace();
                // 检查是否是m1方法调用
                for (StackTraceElement st : sts) {
                    if (st.getMethodName().equals("m1")) {
                        return true;
                    }
                }
                throw new RuntimeException("除m1方法外，该方法不允许其他方法调用");
            }
        }
        只是把”return false“替换成一个运行期异常，除了m1方法外，其他方法调用都会产生异常，该方法常用
        作离线注册码校验，当破解者试图暴力破解时，由于主执行者不是期望的值，因此会返回一个包装和混淆的
        异常信息，大大增加了破解的难度
         */
        /**
         * 注意
         * Throwable需要认真研究研究
         */
    }

    /**
     * 建议116： 异常只为异常服务
     */
    private void suggest116() {
        /*
        异常只为异常服务，这是何解？难道异常还能为其他服务不成？确实能，异常原本是正常逻辑的一个补充，
        但是有时候会被当作主逻辑使用，看如下代码：
        // 判断一个枚举是否包含String枚举项
        public static <T extends Enum<T>> boolean Contain(Class<T> c, String name) {
            boolean result = false;
            try {
                Enum.valueOf(c, name);
                result = true;
            } catch (RuntimeException e) {
                // 只要抛出异常，则认为是不包含
            }
            return result;
        }
        判断一个枚举是否包含指定的枚举项，这里会根据valueOf方法是否抛出异常来进行判断，如果抛出异常（一
        般是IllegalArgumentException异常），则认为是不包含，若不抛出异常则可以认为包含该枚举项，看上去
        这段代码很正常，但是其中却有三个错误：
        异常判断降低了系统性能
        降低了代码的可读性，只是详细了解valueOf方法的人才能读懂这样的代码，因为valueOf抛出的是非受检异常
        隐藏了运行期可能产生的错误，catch到异常，但没有做任何处理
        这段代码是用一段异常实现了一个正常的业务逻辑，这导致代码产生了坏味道。要解决此问题也很容易，即不在
        主逻辑中使用异常，代码如下：
        // 判断一个枚举是否包含String枚举项
        public static <T extends Enum<T>> boolean Contain(Class<T> c, String name) {
            // 遍历枚举项
            for (T t : c.getEnumConstants()) {
                // 枚举项名称是否相等
                if (t.name().equals(name)) {
                    return true;
                }
            }
            return false;
        }
        异常只能用在非正常的情况下，不能成为正常情况的主逻辑，也就是说，异常只是主场景中的辅助场景，不能喧
        宾夺主。而且，异常虽然是描述例外事件的，但能避免则避免之，除非是确实无法避免的异常，例如：
        public static void main(String[] args) {
            File file = new File("文件.txt");
            try {
                FileInputStream fis = new FileInputStream(file);
                / 其他业务逻辑处理 /
            } catch (FileNotFoundException e) {
                // 异常处理
            }
        }
        这样一段代码经常会出现在我们的项目中，但经常写并不代表不可优化，这里的异常类FileNotFoundException
        完全可以在它诞生前就消除掉：先判断文件是否存在，然后再生成FileInputStream对象，代码如下：
        public static void main(String[] args) {
            File file = new File("文件.txt");
            // 经常出现的异常情况，可以先做判断
            if (file.exists() && !file.isDirectory()) {
                try {

                } catch () {

                }
            }
        }
        虽然增加了if判断语句，增加了代码量，但是却会减少FileNotFoundException异常出现的几率，提高了程序
        的性能和稳定性
         */
        /**
         * 注意
         * 异常只为确实异常的事件服务
         */
    }

    /**
     * 建议117： 多使用异常，把性能问题放一边
     */
    private void suggest117() {
        /*
        我们在编写用例文档时，其中有一项叫作”例外事件“，是用来描述主场景外的例外场景的，例如用户登录的用例，
        就会在”例外事件“中说明”连续3次登录失败即锁定用户帐号“，这就是登录事件的一个异常处理，代码如下：
        public void login() {
            try {
                // 正常登录
            } catch (InvalidLoginException lie) {
                // 用户名无效
            } catch (InvalidPasswordException pe) {
                // 密码错误的异常
            } catch (TooMuchLoginException tmle) {
                // 多次登录失败的异常
            }
        }
        如此设计可以让我们的login方法更符合实际的处理逻辑，同时使主逻辑（正常登录，try代码块）更加清晰。当
        然了，使用异常还有很多优点，比如可以让正常代码和异常代码分离、能快速查找问题（栈信息快照）等，但是
        异常有一个缺点：性能比较慢
        Java的异常处理机制确实比较慢，这个”比较慢“是相对于诸如String、Integer等对象来说，单单从对象的创建
        来说，new一个IOException会比String慢5倍，这从异常的处理机制也可以解释：因为它要执行fillInStackTrace
        方法，要记录当前栈的快照，而String类则是直接申请一个内存创建对象，异常类慢一筹也就在所难免了
        而且，异常类是不能缓存的，期望预先建立大量的异常对象以提高异常性能也是不现实的
        难道异常的性能问题就没有任何可提高的办法了？确实不没有，但是我们不能因为性能问题而放弃使用异常，而且
        经过测试，在 JDK 1.6 下，一个异常对象创建的时间只需要1.4毫秒左右（注意是毫秒，通常一个交易处理是在
        100毫秒左右），难道如此微小的性能消耗都不允许吗？
         */
        /**
         * 注意
         * 性能问题不是拒绝异常的借口
         */
    }
}
