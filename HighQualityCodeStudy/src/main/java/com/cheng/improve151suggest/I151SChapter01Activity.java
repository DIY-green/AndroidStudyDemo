package com.cheng.improve151suggest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import com.cheng.highqualitycodestudy.R;
import com.cheng.improve151suggest.adapter.I151SuggestListAdapter;
import com.cheng.improve151suggest.model.Client;

/**
 第1章　java开发中通用的方法和准则/1
 建议1： 不要在常量和变量中出现易混淆的字母/2
 建议2： 莫让常量蜕变成变量/2
 建议3： 三元操作符的类型务必一致/3
 建议4： 避免带有变长参数的方法重载/4
 建议5： 别让null值和空值威胁到变长方法/6
 建议6： 覆写变长方法也循规蹈矩/7
 建议7： 警惕自增的陷阱/8
 建议8： 不要让旧语法困扰你/10
 建议9： 少用静态导入/11
 建议10： 不要在本类中覆盖静态导入的变量和方法/13
 建议11： 养成良好习惯，显式声明uid/14
 建议12： 避免用序列化类在构造函数中为不变量赋值/17
 建议13： 避免为final变量复杂赋值/19
 建议14： 使用序列化类的私有方法巧妙解决部分属性持久化问题/20
 建议15： break万万不可忘/23
 建议16： 易变业务使用脚本语言编写/25
 建议17： 慎用动态编译/27
 建议18： 避免instanceof非预期结果/29
 建议19： 断言绝对不是鸡肋/31
 建议20： 不要只替换一个类/33
 */
public class I151SChapter01Activity extends AppCompatActivity {
    private static final String TAG = "I151SChapter01Activity";

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
        String[] suggests = getResources().getStringArray(R.array.i151schapter1);
        I151SuggestListAdapter adapter = new I151SuggestListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
    }

    /**
     * 建议1： 不要在常量和变量中出现易混淆的字母
     */
    private void suggest1() {
        long i = 1l;
        Log.e(TAG, "i的两倍是：" + (i+i)); //2
        // 查看输出结果：i的两倍是：2（不是22）
        /**
         * 注意
         * 字母“l”作为长整型标志时务必大写
         * 字母“O”与数字混合使用的时候请添加注释
         */
    }

    /**
     * 建议2： 莫让常量蜕变成变量
     */
    private void suggest2() {
        Log.e(TAG, "常量会变哦：" + Const.RAND_CONST);
        /**
         * 注意
         * 务必让常量的值在运行期保持不变
         */
    }
    /*接口常量*/
    interface Const {
        // 这还是常量吗？
        static final int RAND_CONST = new Random().nextInt();
    }

    /**
     * 建议3： 三元操作符的类型务必一致
     */
    private void suggest3() {
        int i = 80;
        String s = String.valueOf(i<100?90:100);
        String s1 = String.valueOf(i<100?90:100.0);
        Log.e(TAG, "两者是否相等" + s.equals(s1)); // false
        // 这里涉及三元操作符类型的转换规则：
        /*
        若两个操作数不可转换，则不做转换，返回值为Object类型
        若两个操作数是明确类型的表达式（比如变量），则按照正常的二进制数字来转换，
        int类型转换为long，long转化为float
        若两个操作数中有一个数字S，另一个是表达式，且其类型标示为T，那么，若数字
        S在T的范围内，则转换为T类型；若S超出了T类型的范围，则T转换为S类型
        若两个操作数都是直接量数字，则返回值类型为范围较大者
         */
        /**
         * 注意
         * 保证三元操作符中的两个操作数类型一致，可减少可能错误的发生
         */
    }

    /**
     * 建议4： 避免带有变长参数的方法重载
     */
    private void suggest4() {
        Client client = new Client();
        // 499 元的货物，打75折
        client.calPrice(49900, 75); // 调用 calPrice(int price,int discount)
        /**
         * 注意
         * 慎重考虑变长参数的方法重载
         */
    }

    /**
     * 建议5： 别让null值和空值威胁到变长方法
     */
    private void suggest5() {
        Client client = new Client();
        client.methodA("China", 0);
        client.methodA("China", "People");
        // client.methodA("China");
        // client.methodA("China", null);
        // 上面这两处编译通不过，提示是相同的：方法模糊不清，编译器不知道调用哪一个方法，
        // 但这两次代码反映的代码味道可是不同的
        // 对于methodA("China")方法，根据实参"China"（Stirng类型），
        // 两个方法都符合形参格式，编译器不知道该调用哪个方法，于是报错
        // 这是Client类的设计者违反了KISS原则（Keep It Simple, Stupid，即懒人原则），
        // 按照此规则设计的方法应该很容易调用
        // 对于client.methodA("China", null)方法，直接量null是没有类型的，虽然两个methodA
        // 方法都符合调用请求，但不知道调用哪一个，于是报错了
        // 该方法除了不符合上面的懒人原则外，这里还有一个非常不好的编码习惯，即调用者隐藏了实参
        // 类型，这是非常危险的，不仅仅调用者需要“猜测”该调用哪个方法，而且被调用者也可能产生内
        // 部逻辑混乱的情况
        // 对于本例来说应该做如下修改：
        String[] strs = null;
        client.methodA("China", strs);
        // 也就是说让编译器知道这个null值是String[]类型的，编译即可通过，也就减少了错误的发生
        /**
         * 注意
         * 类的设计应该按照KISS(Keep It Simple, Stupid，即懒人原则)
         */
    }

    /**
     * 建议6： 覆写变长方法也循规蹈矩
     */
    private void suggest6() {
        // 向上转型
        Base  base = new Sub();
        base.fun(100, 50);
        // 不转型
        Sub sub = new Sub();
        // sub.fun(100, 50);
        // 上面这行编译通不过，问题出在什么地方呢？
        // @Override注解吗？非也，覆写是正确的，因为父类的calPrice编译成字节码后的形参是一个
        // int类型的形参加上一个int数组类型的形参，子类的参数列表也与此相同，那覆写是理所当然
        // 了，所以加上@Override注解没有问题。错误提示是上面这句找不到fun(int, int)方法
        // 这太奇怪了：子类继承了父类的所有属性和方法，甭管是私有的还是公有的访问权限，同样的
        // 参数、同样的方法名，通过父类调用没有任何问题，通过子类调用却编译通不过，为啥？难道
        // 是没有继承下来？或者子类缩小了父类方法的前置条件？
        // 事实上，base对象是把子类对象Sub做了向上转型，形参列表是由父类决定的，由于是变长参数，
        // 在编译时，“base.fun(100, 50)”中的“50”这个实参会被编译器“猜测”而编译成“{50}”数组，
        // 再由子类Sub执行。再来看看直接调用子类的情况，这时编译器并不会把“50”做类型转换，因为
        // 数组本身也是一个对象，编译器还没有聪明到要在两个没有继承关系的类之间做转换，要知道
        // Java是要求严格类型匹配的，类型不匹配编译器自然就会拒绝执行，并给予错误提示。
        /**
         * 覆写必须满足的条件：
         * 重写方法不能缩小访问权限
         * 参数列表必须与被重写方法相同
         * 返回类型必须与被重写方法的相同或是其子类
         * 重写方法不能抛出新的异常，或者超出父类范围的异常，但是可以抛出更少、更有限的异常，或者不抛出异常
         */
        /**
         * 注意
         * 覆写的方法参数与父类相同，不仅仅是类型、数量，还包括显示形式
         */
    }

    // 基类
    class Base{
        void fun(int price,int... discounts){
            System.out.println("Base……fun");
        }
    }

    // 子类，覆写父类方法
    class Sub extends Base{
        @Override
        void fun(int price,int[] discounts){
            System.out.println("Sub……fun");
        }
    }

    /**
     * 建议7： 警惕自增的陷阱
     */
    private void suggest7() {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count = count++;
        }
        Log.e(TAG, "count=" + count); // 0
        // 这个程序输出的count等于几？答案等于10？错了，运行结果是count等于0，为什么呢？
        /*
        count++ 是一个表达式，是有返回值的，它的返回值就是count自加前的值，Java对自加是这样处理的：
        首先把count的值（注意是值，不是引用）拷贝到一个临时变量区，然后对count变量加1，最后返回临时
        变量区的值。程序第一次循环时的详细处理步骤如下：
        步骤1 JVM把count值（其值是0）拷贝到临时变量区
        步骤2 count值加1，这时候count的值是1
        步骤3 返回临时变量区的值，注意这个值是0，没有修改过
        步骤4 返回值给count，此时count值被重置成0
        “count = count++;”这条语句可以按照如下代码来理解：
            // 先保存初始值
            int temp = count;
            // 做自增操作
            count = count + 1;
            // 返回原始值
            return temp;
         解决方法很简单，修改为“count++”即可。
         该问题在不同的语音环境有不同的实现：C++中“count=count++”与“count++”是等效的，而在PHP中则
         保持着与Java相同的处理方式。每种语言对自增的实现方式各不同。
         */
        /**
         * 注意
         * i++表达式的返回值是i自加之前的值
         */
    }

    /**
     * 建议8： 不要让旧语法困扰你
     */
    private void suggest8() {
        // 数据定义及初始化
        int fee = 200;
        // 其他业务处理
        saveDefault:save(fee);
        /*
         “saveDefault:save(fee)” 此句代码很神奇，编译没有错，运行也很正常。Java中竟然有冒号操作符？
         原来是goto语句。Java中抛弃了goto语法，但还是保留了该关键字，只是不进行语义处理而已。Java中
         虽然没有了goto关键字，但是扩展了break和continue关键字，它们的后面都可以加上标号做跳转，完全
         实现了goto功能，同时也把goto的诟病带了进来，很少看到这样使用。
         */
        /**
         * 注意
         * Java没有goto
         * 为了可读性少用break、continue，就算要使用也不能加上标号做跳转
         */
    }
    void saveDefault() {

    }
    void save(int fee) {

    }

    /**
     * 建议9： 少用静态导入
     */
    private void sugget9() {
        /**
         * 注意
         * 对于静态导入，一定要遵循两个规则：
         * 不使用*（星号）通配符，除非是导入静态常量类（只包含常量的类或接口）
         * 方法名是具有明确、清晰表象意义的工具类
         */
    }

    /**
     * 建议10： 不要在本类中覆盖静态导入的变量和方法
     */
    private void suggest10() {
        /**
         * 注意
         * 编译器有一个“最短路径”原则：如果能够在本类中查找到的变量、常量、方法，就不会到其他包或
         * 父类、接口中查找，以确保本类中的属性、方法优先
         * 如果要变更一个被静态导入的方法，最好的办法是在原始类中重构，而不是在本类中覆盖
         */
    }

    /**
     * 建议11： 养成良好习惯，显式声明uid
     */
    private void suggest11() {
        /*
        在序列化和反序列化的类不一致的情形下，反序列化时会报一个InvalidClassException异常，原因
        是序列化和反序列化所对应的类版本发生了变化，JVM不能把数据流转换为实例对象。那JVM是根据什
        么来判断一个类版本的呢？
        好问题，通过SerialVersionUID，也叫做流标识符（Stream Unique Identifier），即类的版本
        定义的，它可以显式声明也可以隐式声明。显式声明格式如下：
        private static final long serialVersionUID = xxxxL;
        而隐式声明则是我不声明，由编译器在编译的时候帮我生成。生成的依据是通过包名、类名、继承关系、
        非私有的方法和属性，以及参数、返回值等诸多因子计算得出的，极度复杂，基本上计算出来的这个值
        是唯一的。
        再来看看serialVersionUID的作用。JVM在反序列化时，会比较数据流中的serialVersionUID与类的
        serialVersionUID是否相同，如果相同，则认为类没有发生改变，可以把数据流load为实例对象；如果
        不相同，抛个异常InvalidClassException。这是一个非常好的校验机制，可以保证一个对象即使在网络
        或磁盘中“滚过”一次，仍能做到“出淤泥而不染”，完美地实现类的一致性
         */
        /**
         * 注意
         * 显示声明serialVersionUID可以避免对象不一致，提高代码的健壮性，可在关键时候发挥作用，
         * 但是尽量不要以这种方式向JVM“撒谎”
         */
    }

    /**
     * 建议12： 避免用序列化类在构造函数中为不变量赋值
     */
    private void suggest12() {
        /*
        我们知道带有final标识的属性是不变量，也就是说只能赋值一次，不能重复赋值，但是在序列化类中就
        有点复杂了。
        如果final属性是一个直接量，在反序列化时就会重新计算，这是基本规则。final变量另一种赋值方式：
        通过构造函数赋值，这里触及了反序列化的另一个规则：反序列化时构造函数不会执行。
        反序列化的执行过程是这样的：JVM从数据流中获取一个Object对象，然后根据数据流中的类文件描述信息
        （在序列化时，保存到磁盘的对象文件中包含了类描述信息，注意是类描述信息，不是类）查看，发现是
        final变量，需要重新计算，于是引用Person类中的name值，而此时JVM又发现name竟然没有赋值（在构造
        函数中赋的值），不能引用，于是它就不再初始化，保持原值状态。
        这很容易出现反序列化生成的final变量值与新产生的实例值不相同的情况，于是业务异常就产生了。
         */
        /**
         * 注意
         * 在序列化类中，不要使用构造函数为final变量赋值
         */
    }

    /**
     * 建议13： 避免为final变量复杂赋值
     */
    private void suggest13() {
        /*
        为final变量赋值还有一种方式：通过方法赋值，即直接在声明时通过方法返回值赋值，这也会有final变量
        没有重新赋值的问题。
        上个建议所说final会被重新赋值，其中的“值”值的是简单对象。简单对象包括：8个基本类型，以及数组、
        字符串（字符串情况很复杂，不通过new关键字生成Sting对象的情况下，final变量的赋值与基本类型相同），
        但不能方法赋值。
        其中的原理是这样的，保存到磁盘上（或者网络传输）的对象文件包括两部分：
        （1）类描述信息
        包括包路径、继承关系、访问权限、变量描述、变量访问权限、方法签名、返回值，以及变量的关联类信息。要
        注意的一点是，它并不是class文件的翻版，它不记录方法、构造函数、static变量等的具体实现。之所以类
        描述会被保存，很简单，是因为能去也能回嘛，这保证反序列化的健壮运行
        （2）非瞬态（transient关键字）和非静态（static关键字）的实例变量值
         */
        /**
         * 注意
         * 反序列化时final变量在以下情况下不会被重新赋值：
         * 通过构造函数为final变量赋值
         * 通过方法返回值为final变量赋值
         * final修饰的属性不是基本类型
         */
    }

    /**
     * 建议14： 使用序列化类的私有方法巧妙解决部分属性持久化问题
     */
    private void suggest14() {
        /*
         在Person类中增加了writeObject和readObject两个方法，并且访问权限都是私有级别，这可以改变Person
         序列化的行为，为什么呢？其实这里使用了序列化独有的机制：序列化回调。Java调用ObjectOutputStream
         类把一个对象转换成流数据时，会通过反射检查被序列化的类是否有writeObject方法，并且检查其是否符合
         私有、无返回值的特性。若有，则会委托该方法进行对象序列化，若没有，则由ObjectOutputStream按照默认
         规则继续序列化。同样，在从流数据恢复成实例对象时，也会检查是否有一个私有的readObject方法，如果有，
         则会通过该方法读取属性值。此处有几个关键点要说明：
         （1）out.defaultWriteObject()
         告知JVM按照默认的规则写入对象，惯例的写法是写在第一句话里
         （2）in.defaultReadObject()
         告知JVM按照默认的规则读入对象，惯例的写法也是写在第一句话里
         （3）out.writeXX和in.readXX
         分别是写入和读取相应的值，类似一个队列，先进先出，如果此处有复杂的数据逻辑，建议按封装Collection
         对象处理
         */
        /**
         * 注意
         * 实现了Serializable接口的类可以实现两个私有方法：writeObject和readObject，以影响和控制序列化
         * 和反序列化的过程
         */
    }

    /**
     * 建议15： break万万不可忘
     */
    private void suggest15() {
        Log.e(TAG, "2=" + toChineseNumberCase(2));
        /**
         * 注意
         * 忘记写break会导致很隐蔽的bug
         * 对于此类问题，有一个简单的解决办法：修改IDE的警告级别
         */
    }
    //把阿拉伯数字翻译成中文大写数字
    private String toChineseNumberCase(int n) {
        String chineseNumber = "";
        switch (n) {
            case 0:chineseNumber = "零";
            case 1:chineseNumber = "壹";
            case 2:chineseNumber = "贰";
            case 3:chineseNumber = "叁";
            case 4:chineseNumber = "肆";
            case 5:chineseNumber = "伍";
            case 6:chineseNumber = "陆";
            case 7:chineseNumber = "柒";
            case 8:chineseNumber = "捌";
            case 9:chineseNumber = "玖";
        }
        return chineseNumber;
    }

    /**
     * 建议16： 易变业务使用脚本语言编写
     */
    private void suggest16() {
        /*
        //获得一个javascript的执行引擎
        ScriptEngine engine=new ScriptEngineManager().getEngineByName("javascript");
        //建立上下文变量
        Bindings bind=engine.createBindings();
        bind.put("factor", 1);
        //绑定上下文，作用域是当前引擎范围
        engine.setBindings(bind,ScriptContext.ENGINE_SCOPE);
        Scanner input = new Scanner(System.in);
        while(input.hasNextInt()){
            int first = input.nextInt();
            int sec = input.nextInt();
            System.out.println("输入参数是："+first+","+sec);
            //执行js代码
            engine.eval(new FileReader("c:/model.js"));
            //是否可调用方法
            if(engine instanceof Invocable){
                Invocable in=(Invocable)engine;
                //执行js中的函数
                Double result = (Double)in.invokeFunction("formula",first,sec);
                System.out.println("运算结果："+result.intValue());
            }
        }
        */
        /**
         * 注意
         * 可以在Java中使用脚本语言来实现经常变动的需求
         */
    }

    /**
     * 建议17： 慎用动态编译
     */
    private void suggest17() {
        /*
        动态编译虽然是很好的工具，但是使用的还是很少，因为静态编译已经能够解决大部分问题，甚至全部
        问题，即使真的需要动态编译，也有很好的替代方案，如JRuby、Groovy等无缝的脚本语言
         */
        /**
         * 注意
         * （1）在框架中谨慎使用
         * （2）不要在要求高性能的项目使用
         * （3）动态编译要考虑安全问题
         * （4）记录动态编译过程
         */
    }

    /**
     * 建议18： 避免instanceof非预期结果
     */
    private void suggest18() {
        //String对象是否是Object的实例
        boolean b1 = "Sting" instanceof Object;
        //String对象是否是String的实例
        boolean b2 = new String() instanceof String;
        //Object对象是否是String的实例
        boolean b3 = new Object() instanceof String;
        //拆箱类型是否是装箱类型的实例
        //	boolean b4 = 'A' instanceof Character;
        //空对象是否是String的实例
        boolean b5 = null instanceof String;
        //类型转换后的空对象是否是String的实例
        /*
         flase 不要看这里有个强制类型转换就认为结果是true，不是的，null是一个万用类型，
         也可以说它没有类型，即使类型转换还是个null
          */
        boolean b6 = (String)null instanceof String;
        //Date对象是否是String的实例
        //	boolean b7 = new Date() instanceof String;
        //在泛型类中判断String对象是否是Date的实例
        boolean b8 = new GenericClass<String>().isDateInstance(""); // false

        /**
         * 注意
         * instanceof 只能用于对象的判断，不能用于基本类型的判断
         * instanceof 特有的规则：若左操作数是null，结果就直接返回false，不再运算右操作数是什么类
         */
    }

    class GenericClass<T>{
        //判断是否是Date类型
        public boolean isDateInstance(T t){
            return t instanceof Date;
        }
    }

    /**
     * 建议19： 断言绝对不是鸡肋
     */
    private void suggest19() {
        /*
        在防御式编程中经常会用断言（Assertion）对参数和环境做出判断，避免程序因不当的输入或错误的
        环境而产生逻辑异常。在Java中断言使用的是assert关键字，其基本的用法如下：
        assert <布尔表达式>
        assert <布尔表达式> : <错误信息>
        在布尔表达式为假时，抛出AssertionError错误，并附带了错误信息。assert的语法较简单，有以下
        两个特性：
        （1）assert默认是不启用的
        （2）assert抛出的异常AssertionError是继承自Error的

        assert虽然是做断言的，但不能将其等价于if...else...这样的条件判断，以下情况不可使用：
        （1）在对外公开的方法中
        （2）在执行逻辑代码的情况下
        assert的支持是可选的，在开发时可以让它运行，但在生产系统中则不需要其运行了，因此在assert的
        布尔表达式中不能执行逻辑代码，否则会因为环境不同而产生不同的逻辑

        在什么情况下能够使用assert呢？一句话：按照正常执行逻辑不可能到达的代码区域可以放置assert。
        具体分为三种情况：
        （1）在私有方法中放置asser作为参数的校验
        （2）流程控制中不可能达到的区域
        （3）建立程序探针
         */
    }

    /**
     * 建议20： 不要只替换一个类
     */
    private void suggest20() {
        /**
         * 注意
         * 发布应用系统时禁止使用类文件替换方式，整体war包发布才是万全之策
         */
    }
}
