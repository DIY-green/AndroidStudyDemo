package com.cheng.improve151suggest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.Arrays;
import com.cheng.highqualitycodestudy.R;
import com.cheng.improve151suggest.adapter.I151SuggestListAdapter;

/**
 第7章 泛型和反射
 建议93： java的泛型是类型擦除的
 建议94： 不能初始化泛型参数和数组
 建议95： 强制声明泛型的实际类型
 建议96： 不同的场景使用不同的泛型通配符
 建议97： 警惕泛型是不能协变和逆变的
 建议98： 建议采用的顺序是list[t]、list[?]、list[object]
 建议99： 严格限定泛型类型采用多重界限
 建议100： 数组的真实类型必须是泛型类型的子类型
 建议101： 注意class类的特殊性
 建议102： 适时选择getdeclared×××和get×××
 建议103： 反射访问属性或方法时将accessible设置为true
 建议104： 使用forname动态加载类文件
 建议105： 动态加载不适合数组
 建议106： 动态代理可以使代理模式更加灵活
 建议107： 使用反射增加装饰模式的普适性
 建议108： 反射让模板方法模式更强大
 建议109： 不需要太多关注反射效率
 */
public class I151SChapter07Activity extends AppCompatActivity {
    private static final String TAG = "I151SChapter07Activity";

    private ListView mChapterLV;

    /**
     * 泛型可以减少强制类型的转换，可以规范集合的元素类型，还可以提高代码的安全性和可读性，正是因为这
     * 些优点，自从Java引入泛型后，项目的编码规则上便多了一条：优先使用泛型
     * 反射可以“看透”程序的运行情况，可以让我们在运行期知晓一个类或实例的运行状况，可以动态地加载和调
     * 用，虽然有一定的性能忧患，但它带给我们的便利远远大于其性能缺陷
     */

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
        String[] suggests = getResources().getStringArray(R.array.i151schapter7);
        I151SuggestListAdapter adapter = new I151SuggestListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
    }

    /**
     * 建议93： java的泛型是类型擦除的
     */
    private void suggest93() {
        /*
        Java泛型（Generic）的引入加强了参数类型的安全性，减少了类型的转换，它与C++中的模版（Templates）
        比较类似，但是有一点不同的是：Java的泛型在编译期有效，在运行期被删除，也就是说所有的泛型参数类型
        在编译后都会被清除掉，来看一个例子，代码如下：
        public class Foo {
            // arrayMethod接收数组参数，并进行重载
            public void arrayMethod(String[] strArray) {}
            public void arrayMethod(Integer[] intArray) {}
            // listMethod接收泛型List参数，并进行重载
            public void listMethod(List<String>) {}
            public void listMethod(List<Integer>) {}
        }
        程序很简单，编写了4个方法，arrayMethod方法接收String数组和Integer数组，这是一个典型的重载，
        listMethod接收元素类型为String和Integer的list变量。现在的问题是，这段程序是否能编译？
        事实上，这段程序是无法编译的，编译时报错，说listMethod(List<Integer>)方法在编译时擦除类型后
        的方法是listMethod(List<E>)，它与另外一个方法重复，通俗地说就是方法签名重复。这就是Java泛型
        擦除引起的问题：在编译后所有的泛型类型都会做相应的转化。转换规则如下：
        List<String>、List<Integer>、List<T>擦除后的类型为List
        List<String>[]擦除后的类型为List[]
        List<? extends E>、List<? super E>擦除后的类型为List<E>
        List<T extends Serializable & Cloneable>擦除后为List<Serializable>
        Java编译后的字节码中已经没有泛型的任何信息了，也就是说一个泛型类和一个普通类经过编译后都指向了
        同一字节码，比如Foo<T>类，经过编译后将只有一份Foo.class类，不管是Foo<String>还是Foo<Integer>
        引用的都是同一字节码。Java之所以如此处理，有两个原因：
        避免JVM的大换血。C++的泛型生命期延续到了运行期，而Java是在编译器擦除掉的，我们想想如果JVM也把泛
        型类型延续到运行期，那么JVM就需要进行大量的重构工作了
        版本兼容。在编译期擦除可以更好地支持原生类型（RawType），在Java 1.5 或 1.6 平台上，即使声明一
        个List这样的原生类型也是可以正常编译通过的，只是会产生警告信息而已
         */
        /**
         * 注意
         * 1）泛型的class对象是相同的
         * 每个类都有一个class属性，泛型化不会改变class属性的返回值
         * 2）泛型数组初始化时不能声明泛型类型
         * List<String>[] listArray = new List<String>[]; // 编译通不过
         * 原因很简单，可以声明一个带有泛型参数的数组，但是不能初始化该数组，因为执行了类型擦除操作，
         * List<Object>[] 与 List<String>[]就是同一回事了，编译器拒绝如此声明
         * 3）instanceof不允许存在泛型参数
         * List<String> list = new ArrayList<String>();
         * Log.e(TAG, (list instanceof List<String>) + ""); // 编译通不过
         * 原因一样，泛型类型被擦除了
         */
    }

    /**
     * 建议94： 不能初始化泛型参数和数组
     */
    private void suggest94() {
        /*
        泛型类型在编译期被擦除，我们在类初始化时将无法获得泛型的具体参数，比如这样的代码：
        class Foo<T> {
            private T t = new T();
            private T[] tArray = new T[5];
            private List<T> list = new ArrayList<T>();
        }
        这段代码有什么问题呢？t、tArray、list都是类变量，都是通过new声明一个类型，看起来非常相似啊！
        但这段代码是编译通不过的，因为编译器在编译时需要获得T类型，但泛型在编译期类型已经被擦除了，所
        以new T()和new T[5]都会报错（可能有疑惑：泛型类型可以擦除为顶级类Object，那T类型擦除成
        Object不就可以编译了吗？这样也不行，泛型这是Java语言的一部分，Java毕竟是一个强类型、编译型的
        安全语言，要确保运行期的稳定性和安全性就必须要求在编译器上严格检查）。可为什么new ArrayList<T>()
        却不会报错呢？
        这是因为ArrayList表面是泛型，其实已经在编译期转型为Object了，查看ArrayList的源码...（元素加
        入时向上转型为Object类型（E类型转为Object），取出时向下转型为E类型（Object转为E类型））
        在某些情况下，确实需要泛型数组，那该如何处理呢？代码如下：
        class Foo<T> {
            // 不再初始化，由构造函数初始化
            private T t;
            private T[] tArray;
            private List<T> list = new ArrayList<T>();
            // 构造函数初始化
            public Foo() {
                try {
                    Class<?> tType = Class.forName("");
                    t = (T)tType.newInstance();
                    tArray = (T[])Array.newInstance(tType, 5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        此时，运行就没有任何问题了。剩下的问题就是怎么在运行期获得T的类型，也就是tType参数，一般情况下泛
        型类型是无法获取的，不过，在客户端调用时多传输一个T类型的class就会解决问题
         */
        /**
         * 注意
         * 类的成员变量是在类初始化前初始化的，所以要求在初始化前它必须具有明确的类型，否则就只能声明，不
         * 能初始化
         */
    }

    /**
     * 建议95： 强制声明泛型的实际类型
     */
    private void suggest95() {
        /**
         * 注意
         * 无法从代码中推断出泛型类型的情况下，即可强制声明泛型类型
         */
    }

    /**
     * 建议96： 不同的场景使用不同的泛型通配符
     */
    private void suggest96() {
        /*
        Java泛型支持通配符（Wildcard），可以单独使用一个“？”表示任意类，也可以使用extends关键字表示某
        一个类（接口）的子类型，还可以使用super关键字表示某一个类（接口）的父类型，但问题是什么时候该用
        extends，什么时候该用super呢？
        1）泛型结构只参与“读”操作则限定上界（extends关键字）
        如果期望从List集合中读取数据就需要使用extends关键字，也就是要界定泛型的上界
        2）泛型结构只参与“写”操作则限定下界（使用super关键字）
        对于是要限定上界还是限定下界，JDK的Collections.copy方法是一个非常好的例子，它实现了了把源列表
        中的所有元素拷贝到目标列表中对应的索引位置上，代码如下：
        public static <T> void copy(List<? super T> dest, List<? extends T> src) {
            for (int i=0; i<srcSize; i++)
                dest.set(i, src.get(i));
        }
        源列表是用来提供数据的，所以src变量需要限定上界，带有extends关键字。目标列表是用来写入数据的，所
        以dest变量需要界定上界，带有super关键字
         */
        /**
         * 如果一个泛型结构即用作“读”操作又用作“写”操作，那该如何进行限定呢？不限定，使用确定的泛型类型即
         * 可，如List<E>
         */
    }

    /**
     * 建议97： 警惕泛型是不能协变和逆变的
     */
    private void suggest97() {
        /*
        编程语言的类型框架中，协变和逆变是指宽类型和窄类型在某种情况下（如参数、泛型、返回值）替换或交
        换的特性，简单地说，协变是用一个窄类型替换宽类型，而逆变则是用宽类型覆盖窄类型。其次，在Java中
        协变和逆变我们已经用了很久了，只是我们没有发觉而已，看如下代码：
        class Base {
            public Number doStuff() {
                return 0;
            }
        }
        class Sub extends Base {
            @Override
            public Integer doStuff() {
                return 0;
            }
        }
        子类的doStuff方法返回值的类型比父类方法要窄，此时doStuff方法就是一个协变方法，同时根据Java的
        覆写定义来看，这又属于覆写。那逆变是怎么回事呢？代码如下：
        class Base {
            public void doStuff(Integer i){}
        }
        class Sub extends Base {
            public void doStuff(Number n){}
        }
        子类的doStuff方法的参数类型比父类要宽，此时就是一个逆变方法，子类扩大了父类方法的输入参数，但
        根据覆写定义来看，doStuff不属于覆写，只是重载而已。由于此时的doStuff方法已经与父类没有任何关
        系了，只是子类独立扩展的一个行为，所以是否声明为doStuff方法名意义不大，逆变已经不具有特别的意
        义了，来重点关注一下协变，先看如下代码是否是协变：
        Base base = new Sub();
        base变量是否发生了协变？是的，发生了协变，base变量是Base类型，它是父类，而其赋值却是子类实例，
        也就是用窄类型覆盖了宽类型。这也叫多态（Polymorphism），两者同含义
        下面来想想泛型是否也支持协变和逆变，答案是：泛型即不支持协变，也不支持逆变
        1）泛型不支持协变
        数组和泛型很相似，一个是中括号，一个是尖括号，那就以数组为参照对象，代码如下：
        // 数组支持协变
        Number[] n = new Integer[10];
        // 编译不通过，泛型不支持协变
        List<Number> ln = new ArrayList<Integer>();
        ArrayList是List的子类型，Integer是Number的子类型，里氏替换原则在此处行不通了，原因就是Java
        为了保证运行期的安全性，必须保证泛型参数类型是固定的，所以它不允许一个泛型参数可以同时包含两种
        类型，即使是父子类关系也不行
        泛型不支持协变，但可以使用通配符模拟协变，代码如下：
        // Number的子类型（包括Number类型）都可以是泛型参数类型
        List<? extends Number> ln = new ArrayList<Integer>();
        “? extends Number”表示的意思是，允许Number所有的子类（包括自身）作为泛型参数类型，但在运行期
        只能是一个具体类型，或者是Integer类型，或者是Double类型，或者是Number类型，也就是说通配符只是
        在编码期有效，运行期则必须是一个确定类型
        2）泛型不支持逆变
        Java虽然可以允许逆变存在，但在对类型赋值上是不允许逆变的，你不能把一个父类实例对象赋值给一个子
        类类型变量，泛型自然也不允许此种情况发生了，但是它可以使用super关键字来模拟实现，代码如下：
        // Integer的父类型（包括Integer）都可以是泛型参数类型
        List<? super Integer> li = new ArrayList<Number>();
        “? super Integer”的意思是可以把所有Integer父类型（自身、父类或接口）做为泛型参数，这里看着就
        像是把一个Number类型的ArrayList赋值给了Integer类型的List，其外观类似于使用一个宽类型覆盖一个
        窄类型，它模拟了逆变的实现
        带有泛型参数的子类型定义与经常使用的类类型也不相同，其基本的类型关系如下
        Integer是Number的子类型？                                             √
        ArrayList<Integer>是List<Integer>的子类型？                           √
        Integer[]是Number[]的子类型？                                         √
        List<Integer>是List<Number>的子类型？                                 ×
        List<Integer>是List<? extends Integer>的子类型？                      ×
        List<Integer>是List<? super Integer>的子类型？                        ×
         */
        /**
         * 注意
         * Java的泛型是不支持协变和逆变的，只是能够实现协变和逆变
         */
    }

    /**
     * 建议98： 建议采用的顺序是list[t]、list[?]、list[object]
     */
    private void suggest98() {
        /*
        List<T>、List<?>、List<Object>这三者都可以容纳所有的对象，但使用的顺序应该是首选List<T>，
        次之List<?>，最后选择List<Object>，原因如下：
        1）List<T>是确定的某一个类型
        List<T>表示的是List集合中的元素都为T类型，具体类型在运行期决定；List<?>表示的是任意类型，与
        List<T>类似，而List<Object>则表示List集合中的所有元素为Object类型，因为List<Object>也可以
        容纳所有的类类型，从这一字面意义上分析，List<T>更符合习惯：编码者知道它是某一个类型，只是在运
        行期才确定而已
        2）List<T>可以进行读写操作
        List<T>可以进行诸如add、remove等操作，因为它的类型是固定的T类型，在编码期不需要进行任何转型
        List<?>是只读类型的，不能进行增加、修改操作，因为编译器不知道List中容纳的是什么类型的元素，也
        就无法校验类型是否安全了，而且List<?>读取出的元素都是Object类型，需要主动转型，所以它经常用于
        泛型方法的返回值。注意，List<?>虽然无法增加、修改元素，但是却可以删除元素，比如执行remove、
        clear等方法，那是因为它的删除动作与泛型类型无关
        List<Object>也可以读写操作，但是它执行写入操作时需要向上转型，在读取数据后需要向下转型，而此时
        已经失去了泛型存在的意义了
         */
        /**
         * 注意
         * Dao<T>应该比Dao<?>、Dao<Object>优先采用，Desc<Person>则比Desc<?>、Dest<Object>优先采用
         */
    }

    /**
     * 建议99： 严格限定泛型类型采用多重界限
     */
    private void suggest99() {
        /*
        在Java的泛型中，可以使用“&”符号关联多个上界并实现多个边界限定，而且只有上界才有此限定，下界没有
        多重限定的情况。想想就会明白：多个下界，编码者可以自行推断出具体的类型，比如“? super Integer”
        和“? extends Double”，可以更细化为Number类型了，或者Object类型了，无须编译器推断了
        为什么要说明多重边界？是因为编码者太少使用它了，比如一个判断用户权限的方法，使用的是策略模式，（
        Strategy Pattern），示意代码如下：
        public class UserHandler<T extends User> {
            // 判断用户是否有权限执行操作
            public boolean permit(T user, List<Job> jobs) {
                List<Class<?>> iList = Arrays.asList(user.getClass().getInterfaces());
                // 判断是否是管理员
                if (iList.indexOf(Admin.class) > -1) {
                    Admin admin = (Admin) user;
                    / 判断管理员是否有此权限 /
                } else {
                    / 判断普通用户是否有此权限 /
                }
                return false;
            }
        }
        此处进行了一次泛型参数类别判断，这里不仅仅违背了单一职责原则，而且让“泛型”很汗颜：已经使用泛型限
        定参数的边界了，还要进行泛型判断。事实上，使用多重边界可以很方便地解决问题，而且非常优雅
         */
        /**
         * 注意
         * 使用“&”符号设定多重边界
         */
    }

    /**
     * 建议100： 数组的真实类型必须是泛型类型的子类型
     */
    private void suggest100() {
        /*
        List接口的toArray方法可以把一个集合转化为数组，但是使用不方便，toArray()方法返回的是一个Object
        数组，所以需要自行转变；toArray(T[] a)虽然返回的是T类型的数组，但是还需要传入一个T类型的数组，
        这也挺麻烦的，我们期望输入的是一个泛型化的List，这样就能转化为泛型数组了，看看能否实现，代码如下：
        public static <T> T[] toArray(List<T> list) {
            T[] t = (T[]) new Object[list.size()];
            for (int i=0,n=list.size(); i<n; i++) {
                t[i] = list.get(i);
            }
            return t;
        }
        上面把要输出的参数类型定义为Object数组，然后转型为T类型数组，之后遍历List赋值给数组的每个元素，
        这与ArrayList的toArray方法很类似（注意只是类似），客户端的调用如下：
        public static void main(String[] args) {
            List<String> list = Arrays.asList("A", "B");
            for (String str : toArray(list)) {
                Log.e(TAG, str);
            }
        }
        编译没有任何问题，运行后出现异常：ClassCastException
        类型转换异常，也就是说不能把一个Object数组转换为String数组，这段异常包含了两个问题：
        为什么Object数组不能向下转型为String数组？
        数组是一个容器，只有确保容器内的所有元素类型与期望的类型有父子关系时才能转换，Object数组只能保
        证数组内的元素是Object类型，却不能确保它们都是String的父类型或子类，所以类型转换失败
        为什么是main方法抛出异常，而不是toArray方法？
        其实，是在toArray方法中进行的类型向下转换，而不是main方法中。那为什么异常会在main方法中抛出，应
        该在toArray方法的“T[] t = (T[]) new Object[list.size()]”这段代码才对呀？那是因为泛型是类型擦
        除的，toArray方法经过编译后与如下代码相同：
        public static Object[] toArray(List list) {
            // 此处的强制类型没必要存在，只是为了与源代码对比
            Object[] t = (Object[])new Object[list.size()];
            for (int i=0, n=list.size(); i<n; i++) {
                t[i] = list.get(i);
            }
            return t;
        }
        public static void main(String[] args) {
            List<String> list = Arrays.asList("A", "B");
            for (String str : (String[])toArray(list)) {
                Log.e(TAG, str);
            }
        }
        阅读完此段代码就很清楚了：toArray方法返回后会进行一次类型转换，Object数组转换成了String数组，于
        是就报ClassCastException异常了
        Object数组不能转为String数组，T类型又无法在运行期获得，那该如何解决这个问题呢？其实，要想把一个
        Object数组转换为String数组，只要Object数组的实际类型也是String就可以了，例如：
        // objArray的实际类型是Object数组，表面类型是String数组
        Object[] objArray = {"A", "B"};
        // 抛出ClassCastException
        String[] strArray = (String[])objArray();

        String[] ss = {"A", "B"};
        // objs的真实类型是String数组，显示类型为Object数组
        Object[] objs = ss;
        // 顺利转换为String数组
        String[] strs = (String[])objs;
        明白了这个问题，我们就把泛型数组声明为泛型类的子类型吧！代码如下：
        public static <T> T[] toArray(List<T> list, Class<T> tClass) {
            // 声明并初始化一个T类型的数组
            T[] t = (T[]) Array.newInstances(tClass, list.size());
            for (int i=0,n=list.size(); i<n; i++) {
                t[i] = list.get(i);
            }
            return t;
        }
        通过反射类Array声明一个T类型的数组，由于无法在运行期获得泛型类型的参数，因此就需要调用者主动传入一
        个T参数类型。此时，客户端再调用就不会出现任何异常了
         */
        /**
         * 注意
         * 当一个泛型类（特别是泛型集合）转变为泛型数组时，泛型数组的真实类型不能是泛型类型的父类型（比如
         * 顶层类Object），只能是泛型类型的子类型（当然包括自身类型），否则就会出现类型转换异常
         */
    }

    /**
     * 建议101： 注意class类的特殊性
     */
    private void suggest101() {
        /*
        Java语言是先把Java源文件编译成后缀为class的字节码文件，然后再通过ClassLoader机制把这些类文件加
        载到内存中，最后生成实例执行的，这是Java处理的基本机制，但是加载到内存中的数据是如何描述一个类的
        呢？比如在Dog.class文件中定义的是一个Dog类，那它在内存中是如何展现的呢？
        Java使用一个元类（MetaClass）来描述加载到内存中的类数据，这就是Class类，它是一个描述类的类对象，
        比如Dog.class文件加载到内存中后就会一个Class的实例对象描述之。因为Class类是“类中类”，也就有预示
        着它有很多特殊的地方：
        无构造函数。Java中的类一般都有构造函数，用于创建实例对象，但是Class类却没有构造函数，不能实例化，
        Class对象是在加载类时由Java虚拟机通过调用类加载器中的defineClass方法自动构建的
        可以描述基本类型。虽然8个基本类型在JVM中并不是一个对象，它们一般存在于栈内存中，但是Class类仍然可
        以描述它们，例如可以使用int.class表示int类型的类对象
        其对象都是单例模式。一个Class的实例对象描述一个类，并且只描述一个类，反过来也成立，一个类只有一个
        Class实例对象，如：String.class.equals(new String().getClass()); // true
         */
        /**
         * 注意
         * Class类是Java的反射入口，只有在获得了一个类的描述对象后才能动态地加载、调用，一般获得一个Class
         * 对象有三种途径：
         * 类属性方式，如String.class
         * 对象的getClass方法，如new String().getClass()
         * forName方法加载，如Class.forName("java.lang.String");
         * 获得了Class对象后，就可以通过getAnnotation()获得注解，通过getMethod()获得方法，通过
         * getConstructors()获得构造函数等，这为后续的反射代码铺平了道路
         */
    }

    /**
     * 建议102： 适时选择getDeclared×××和get×××
     */
    private void suggest102() {
        /*
        Java的Class类提供了很多的getDeclaredXXX方法和getXXX方法，例如getDeclaredMethod和getMethod成对
        出现，getDeclaredConstructors和getConstructors也是成对出现，那这两者之间有什么差别呢？
        getMethod方法获得的是所有public访问级别的方法，包括从父类继承的方法，而getDeclaredMehtod获得的是
        自身类的所有方法，包括公有方法、私有方法等，而且不受限制于访问权限
        其他的getDeclaredConstructors和getConstructors、getDeclaredFields和getFields等与此相似。Java
        之所以如此处理，是因为反射本意只是正常代码逻辑的一种补充，而不是让正常代码逻辑产生翻天覆地的变动，所
        以public属性和方法最容易获取，私有属性和方法也可以获取，但要限定本类
         */
        /**
         * 注意
         * 如果需要列出所有继承自父类的方法，该如何实现呢？简单，先获得父类，然后使用getDeclaredMethods，之
         * 后持续递归即可
         */
    }

    /**
     * 建议103： 反射访问属性或方法时将Accessible设置为true
     */
    private void suggest103() {
        /*
        Java中通过反射执行一个方法的过程如下：获取一个方法对象，然后根据isAccessible返回值确定是否能够
        执行，如果返回值为false则需要调用setAccessible(true)，最后再调用invoke执行方法。
        这已经成为了习惯用法：通过反射方式执行方法时，必须在invoke之前检查Accessible属性。这是一个好习
        惯，也确实应该如此，但方法对象的Accessible属性并不是用来决定是否可访问的。
        Accessible的属性并不是我们语法层级理解的访问权限，而是指是否更容易获得，是否进行安全检查。
        我们知道，动态修改一个类或方法或执行方法时都会受Java安全体系的制约，而安全的处理是非常消耗资源的
        （性能非常低），因此对于运行期要执行的方法或要修改的属性就提供了Accessible可选项：由开发者决定是
        否要逃避安全体系的检查
        AccessibleObject是Field、Method、Constructor的父类，决定其是否可以快速访问而不进行访问控制检
        查，在AccessibleObject类中是以override变量保存该值的，但是具体是否快速执行是在Method类的invoke
        方法中决定的
        Accessible属性只是用来判断是否需要进行安全检查的，如果不需要则直接执行，这就可以大幅度地提升系统
        性能（当然了，由于取消了安全检查，也可以运行private方法、访问private私有属性了）。经过测试，在大
        量的反射情况下，设置Accessible为true可以提升性能20倍以上
        AccessibleObject的其他两个子类Field和Constructor与Method的情形相似：Accessible属性决定Field
        和Constructor是否受访问控制检查。我们在设置Field或执行Constructor时，务必要设置Accessible为
        true，这并不仅仅是因为操作习惯的问题，还是在为我们系统的性能考虑
         */
        /**
         * 注意
         * 对于我们已经“习惯”了的代码，多思考一下为什么
         */
    }

    /**
     * 建议104： 使用forname动态加载类文件
     */
    private void suggest104() {
        /*
        动态加载是指在程序运行时加载需要的类库文件，对Java程序来说，一般情况下，一个类文件在启动时或首次
        初始化时会被加载到内存中，而反射则可以在运行时再决定是否要加载一个类，比如从Web上接收一个String
        参数作为类名，然后在JVM中加载并初始化，这就是动态加载，此动态加载通常是通过Class.forName(String)
        实现的，只是这个forName方法到底是什么意思呢？
        我们知道一个类文件只有在被加载到内存中后才可能生成实例对象，也就是说一个对象的生成必然会经过以下
        两个步骤：
        加载到内存中生成Class的实例对象
        通过new关键字生成实例对象
        如果我们使用的是import关键字产生的依赖包，JVM在启动时会自动加载所有依赖包下的类文件，这没有什么
        问题，如果要动态加载类文件，就要使用forName方法了，但问题是我们为什么要使用forName方法动态加载
        一个类文件呢？那是因为我们不知道生成的实例对象是什么类型（如果知道就不用动态加载），而且方法和属
        性都不可访问呀。问题又来了：动态加载的意义在什么地方呢？
        意义在于：加载一个类即表示要初始化该类的static变量，特别是static代码块，在这里我们可以做大量的
        工作，比如注册自己，初始化环境等，这才是我们要重点关注的逻辑（对于此种动态加载，最经典的应用就是
        数据库驱动程序的加载片段，如：Class.forName("com.mysql.jdbc.Driver");）
        需要说明的是，forName只是把一个类加载到内存中，并不保证由此产生一个实例对象，也不会执行任何方法，
        之所以会初始化static代码，那是由类加载机制所决定的，而不是forName方法决定的。也就是说，如果没有
        static属性或static代码块，forName就只是加载类，，没有任何的执行行为
         */
        /**
         * 注意
         * forName只是加载类，并不执行任何代码
         */
    }

    /**
     * 建议105： 动态加载不适合数组
     */
    private void suggest105() {
        /*
        如果forName要加载一个类，那它首先必须是一个类--8个基本类型排除在外，它们不是一个具体的类；其次，
        它必须具有可追索的类路径，否则就会报ClassNotFoundException
        在Java中，数组是一个非常特殊的类，虽然它是一个类，但没有定义类路径
        在声明时可以定义为String[]，但编译器编译后会为不同的数组类型生成不同的类，具体如下所示
        元素类型                                    编译后的类型
        byte[]                                          [B
        char[]                                          [C
        Double[]                                        [D
        Float[]                                         [F
        Int[]                                           [I
        Long[]                                          [J
        Short[]                                         [S
        Boolean[]                                       [Z
        引用类型(如String[])              [L 引用类型（如：[Ljava.lang.String:）
        在编码期，我们可以声明一个变量为String[]，但是经过编译器编译后就成了[Ljava.lang.String。明白
        了这一点，再根据以上的表格可知，动态加载一个对象数组只要加载编译后的数组对象就可以了，代码如下：
        // 加载一个String数组
        Class.forName("[Ljava.lang.String;");
        // 加载一个long数组
        Class.forName("[J");
        虽然以上代码可以动态加载一个数组类，但是这没有任何意义，因为它不能生成一个数组对象，也就是说以上
        代码只是把一个String类型的数组类和long类型的数组类加载到了内存中（如果内存中没有该类的话），并
        不能通过newInstance方法生成一个实例对象，因为它没有定义数组的长度，在Java中数组是定长的，没有
        长度的数组是不允许存在的
        既然反射不能定义一个数组，那问题就来了：如何动态加载一个数组呢？比如依据输入动态产生一个数组。其
        实可以使用Array数组反射类来动态加载，代码如下：
        // 动态创建数组
        String[] strs = (String[]) Array.newInstance(String.class, 8);
        // 创建一个i额多维数组
        int[][] ints = (int[][]) Array.newInstance(int.class, 2, 3);
        因为数组比较特殊，要想动态创建和访问数组，基本的反射是无法实现的，“上帝对你关闭一扇门，同时会为你
        打开另外一扇窗”，于是Java就专门定义了一个Array数组反射工具类来实现动态探知数组的功能
         */
        /**
         * 注意
         * 通过反射操作数组使用Array类，不要采用通用的反射处理API
         */
    }

    /**
     * 建议106： 动态代理可以使代理模式更加灵活
     */
    private void suggest106() {
        /*
        Java的反射框架提供了动态代理（Dynamic Proxy）机制，允许在运行期对目标类生成代理，避免重复开发。
        我们知道一个静态代理是通过代理主题角色（Proxy）和具体主题角色（Real Subject）共同实现抽象主题
        角色（Subject）的逻辑的，只是代理主题角色把相关的执行逻辑委托给了具体主题角色而已，一个简单的静
        态代理如下所示：
        // 抽象主题角色
        interface Subject {
            // 定义一个方法
            public void request();
        }
        // 具体主题角色
        class RealSubject implements Subject {
            // 实现方法
            public void request() {
                // 业务逻辑处理
            }
        }
        // 代理主题角色
        class Proxy implements Subject {
            // 要代理哪个实现类
            private Subject subject = null;
            // 默认被代理者
            public Proxy () {
                subject = new RealSubject();
            }
            // 实现接口中定义的方法
            public void request() {
                before();
                subject.request();
                after();
            }
            // 预处理
            private void before() {
                // do something
            }
            // 善后处理
            private void after() {
                // do something
            }
        }
        这是一个简单的静态代理。Java还提供了java.lang.reflect.Proxy用于实现动态代理：只要提供一个
        抽象主题角色和具体主题角色，就可以动态实现其逻辑的，其实例代码如下：
        // 抽象主题角色
        interface Subject {
            // 定义一个方法
            public void request();
        }
        // 具体主题角色
        class RealSubject implements Subject {
            // 实现方法
            public void request() {
                // 业务逻辑处理
            }
        }
        class SubjectHandler implements InvocationHandler {
            // 被代理的对象
            private Subject subject;
            public SubjectHandler(Subject _subject) {
                subject = _subject;
            }
            // 委托处理方法
            public Object invoke(Object proxy, Method method, Object[] args)
                          throws Throwable {
                // 预处理
                Log.e(TAG, "预处理");
                // 直接调用被代理类的方法
                Object obj = method.invoke(subject, args);
                Log.e(TAG, "后处理");
                return obj;
            }
        }
        注意看，这里没有了代理主题角色，取而代之的是SubjectHandler作为主要的逻辑委托处理，其中invoke
        方法是接口InvocationHandler定义必须实现的，它完成了对真实方法的调用
        来详细了解下InvocationHandler接口，动态代理是根据被代理的接口生成所有方法的，也就是说给定一个
        （或多个）接口，动态代理会宣称“我已经实现该接口下的所有方法了”，那想想看，动态代理怎么才能实现
        代理接口中的方法呢？在默认情况下所有方法的返回值都是空的，是的，虽然代理已经实现了它，但是没有
        任何的逻辑含义，那怎么办？好办，通过InvocationHandler接口的实现，所有方法都是由该Handler进行
        处理的，即所有被代理的方法都由InvocationHandler接管实际的处理任务
        动态代理的场景类，代码如下：
        public static void main(String[] args) {
            // 具体主题角色，也就是被代理类
            Subject subject = new RealSubject();
            // 代理实例的处理Handler
            InvacationHandler handler = new SubjectHandler(subject);
            // 当前加载器
            ClassLoader cl = subject.getClass().getClassLoader();
            // 动态代理
            Subject proxy = (Subject) Proxy.newProxyInstance(cl,
                        subject.getClass().getInterface(), handler);
            // 执行具体主题角色方法
            proxy.request();
        }
        此时就实现了不用显式创建代理类即实现代理的功能，例如可以在被代理角色执行前进行权限判断，或者执行
        后进行数据校验
         */
        /**
         * 注意
         * 动态代理很容易实现通用的代理类，只要在InvocationHandler的invoke方法中读取持久化数据即可实现，
         * 而且还能实现动态切入的效果，这也是AOP编程理念
         */
    }

    /**
     * 建议107： 使用反射增加装饰模式的普适性
     */
    private void suggest107() {
        /*
        装饰模式的定义是“动态地给一个对象添加一些额外的职责。就增加功能来说，装饰模式相比于生成子类更为灵
        活”，不过，使用Java的动态代理也可以实现装饰模式的效果，而且其灵活性、适应性都会更强
        以《Tom and Jerry》为例，看看如何包装小Jerry让它更强大。首先定义Jerry的类：老鼠（Rat类），如下：
        interface Animal {
            void doStuff();
        }
        // 老鼠是一种动物
        class Rat implements Animal {
            public void doStuff() {
                Log.e(TAG, "Jerry will play with Tom.");
            }
        }
        接下来要给Jerry增加一些能力，比如飞行、钻地等能力，当然使用类继承也很容易实现，但我们这里只是临时
        地为Rat类增加这些能力，使用装饰模式更符合此处的场景。首先定义装饰类，代码如下：
        // 定义某种能力
        interface Featrue {
            // 加载特性
            void load();
        }
        // 飞行能力
        class FlyFeature implements Feature {
            public void load() {
                Log.e(TAG, "增加一双翅膀...");
            }
        }
        // 钻地能力
        class DigFeature implements Feature {
            public void load() {
                Log.e(TAG, "增加钻地能力...");
            }
        }
        此处定义了两种能力：一种是飞行，另一种是钻地，如要把这两种属性赋予到Jerry身上，那就需要一个包装
        动作类了，代码如下：
        class DecorateAnimal implements Animal {
            // 被包装的动物
            private Animal animal;
            // 使用哪一个包装器
            private Class<? extends Feature> clz;
            public DecorateAnimal(Animal _animal, Class<? extends Feature> _clz) {
                animal = _animal;
                clz = _clz;
            }
            @Override
            public void doStuff() {
                InvocationHandler handler = new InvocationHandler() {
                    // 具体包装行为
                    public Object invoke(Object p, Method m, Object[] args) throws Throwable {
                        Object obj = null;
                        // 设置包装条件
                        if (Modifier.isPublic(m.getModifiers())) {
                            obj = m.invoke(clz.newInstance(), args);
                        }
                        animal.doStuff();
                        return obj;
                    }
                };
                // 当前加载器
                ClassLoader cl = getClass().getClassLoader();
                // 动态代理，由Handler决定如何包装
                Feature proxy = (Featrue) Proxy.newProxyInstance(cl, clz.getInterfaces(), handler);
                proxy.load();
            }
        }
        注意看doStuff方法，一个装饰类型必然是抽象构建（Component）的子类型，它必须实现doStuff，此处的
        doStuff仿委托给了动态代理执行，并且在动态代理的控制器Handler中还设置了决定装饰方式和行为的条件
        （即代码中InvocationHandler匿名类中的if判断语句），当然，此处也可以通过读取持久化数据的方式进
        行判断，这样就更加灵活了
        抽象构件有了，装饰类也有了，装饰动作类也完成了，客户端调用代码如下：
        public static void main(String[] args) throws Exception {
            // 定义Jerry
            Animal Jerry = new Rat();
            // 为Jerry增加飞行能力
            Jerry = new DecorateAnimal(Jerry, FlyFeature.class);
            // 为Jerry增加钻地能力
            Jerry = new DecorateAnimal(Jerry, DigFeature.class);
            // Jerry开始耍猫了
            Jerry.doStuff();
        }
        此类代码是一个比较通用的装饰模式，只需要定义被装饰的类及装饰类即可，装饰行为由动态代理实现，实
        现了对装饰类和被装饰类的完全解耦，提供了系统的扩展性
         */
        /**
         * 注意
         * 使用动态代理实现装饰模式
         */
    }

    /**
     * 建议108： 反射让模板方法模式更强大
     */
    private void suggest108() {
        /*
        模版方法模式的定义是：定义一个操作中的算法骨架，将一些步骤延迟到子类中，使子类不改变一个算法的
        结构即可重用该算法的某些特定步骤。简单地说，就是父类定义抽象模版作为骨架，其中包括基本方法（是
        由子类实现的方法，并且在模版方法被调用）和模版方法（实现对基本方法的调度，完成固定的逻辑），它
        使用了简单的继承和覆写机制
        我们经常会开发一些测试或演示程序，期望系统在启动时自行初始化，以方便测试或讲解，一般的做法是写
        一个SQL文件，在系统启动前手动导入，不过，这样不仅麻烦而且还容易出现错误，于是我们就自己动手写了
        一个初始化数据的框架：在系统（或容器）启动时自行初始化数据。但问题是每个应用程序要初始化的内容
        我们并不知道，只能由实现者自行编写。那就必须预留接口，此时就得考虑使用模板方法模式了，代码：
        public abstract class AbsPopulator {
            // 模板方法
            public final void dataInitialing() throws Exception {
                // 调用基本方法
                doInit();
            }
            // 基本方法
            protected abstract void doInit();
        }
        这里定义了一个抽象模板类AbsPopulator，它负责数据初始化，但是具体要初始化哪些数据则是由doInit
        方法决定的，这是一个抽象方法，子类必须实现，来看一个用户表数据的加载：
        public class UserPopulator extends AbsPopulator {
            protected void doInit() {
                / 初始化用户表，如创建、加载数据等 /
            }
        }
        现在的问题是：初始化一张User表需要非常多的操作，比如先建表，然后筛选数据，之后插入，最后校验，如
        果把这些都放到一个doInit方法里会非常庞大（即使提炼出多个方法承担不同的职责，代码的可读性依然很差），
        那该如何做呢？又或者doInit是没有任何的业务意义的，是否可以起一个优雅而又动听的名字呢？
        答案是可以使用反射增强模板方法模式，使模板方法实现对一批固定规则的基本方法的调用。代码如下：
        public abstract class AbsPopulator {
            // 模板方法
            public final void dataInitialing() throws Exception {
                // 获得所有的public方法
                Method[] methods = getClass().getMethods();
                for (Method m : methods) {
                    // 判断是否是数据初始化方法
                    if (isInitDataMethod(m)) {
                        m.invoke(this);
                    }
                }
            }
            // 判断是否是数据初始化方法，基本方法鉴别器
             private boolean isInitDataMethod(Method m) {
                return m.getName().startsWith("init") // init开始
                && Modifier.isPublic(m.getModifiers()) // 公开方法
                && m.getReturnType().equals(Void.TYPE) // 返回值是void
                && !m.isVarArgs() // 输入参数为空
                && !Modifier.isAbstract(m.getModifiers()); // 不能是抽象方法
             }
        }
        在一般的模板方法模式中，抽象模板（这里是AbsPopulator类）需要定义一系列的基本方法，一般都是
        protected访问级别的，并且是抽象方法，这标志着子类必须实现这些基本方法，这对子类来说既是一个
        约束也是一个负担。但是使用了反射后，不需要定义任何抽象方法，只需定义一个基本方法鉴别器（例子
        中isInitDataMethod）即可加载符合规则的基本方法。鉴别器在此处的作用是鉴别子类方法中哪些是基
        本方法，模板方法（例子中的dataInitialing）则根据基本方法鉴别器放回的结果通过反射执行相应的
        方法。此时，如果需要进行大量的数据初始化工作，子类的实现就非常简单了，代码如下：
        public class UserPopulator extends AbsPopulator {
            public void initUser() {
                / 初始化用户表，如创建、加载数据等 /
            }
            public void initPassword() {
                / 初始化密码 /
            }
            public void initJobs() {
                / 初始化工作任务 /
            }
        }
        UserPopulator类中的方法只要符合基本鉴别器条件即会被模板方法调用，方法的数据量也不再受父类的
        约束，实现了子类灵活定义基本方法、父类批量调用的功能，并且缩减了子类的代码量（可参考JUnit）
         */
        /**
         * 注意
         * 决定使用模板方法模式时，请尝试使用反射方式实现，它会让你的程序更灵活、更强大
         */
    }

    /**
     * 建议109： 不需要太多关注反射效率
     */
    private void suggest109() {
        /*
        反射的效率相对于正常的代码执行确实低很多（经过测试，相差15倍左右），但是它是一个非常有效的运
        行期工具类，只要代码结构清晰、可读性好那就先开发起来，等到进行性能测试时证明确实有问题时再修
        改也不迟（一般情况下反射并不是性能的终极杀手，而代码结构混乱、可读性差则很可能会埋下性能隐患）
        看一个例子：在运行期获得泛型类的泛型类型，代码如下：
        class Utils {
            // 获得一个泛型类的实际泛型类型
            public static <T> Class<T> getGenricClassType(Class clz) {
                Type type = clz.getGenericSuperclass();
                if (type instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType)type;
                    Type[] types = pt.getActualTypeArguments();
                    if (types.length>0 && types[0] instanceof Class) {
                        // 若有多个泛型参数，依据位置索引返回
                        return (Class) types[0];
                    }
                }
                return (Class) Object.class;
            }
        }
        前面讲过，Java的泛型类型只存在于编译期，那为什么这个工具类可以取得运行期的泛型类型呢？那是因
        为该工具只支持继承的泛型类，如果是在Java编译时已经确定了泛型类的类型参数，那当然可以通过泛型
        获得了。例如有这样一个泛型类：
        abstract class BaseDao<T> {
            // 获得T的运行期类型
            private Class<T> clz = Utils.getGenricClassType(getClass());
            // 根据主键获得一条记录
            public void get(long id) {
                session.get(clz, id);
            }
        }
        // 操作user表
        class UserDao extends BaseDao<String> {
        }
        对于UserDao类，编译器编译时已经明确了其参数类型是String，因此可以通过反射的方式获取器类型，
        这也是getGenricClassType方法使用的场景
         */
        /**
         * 注意
         * 反射效率低是个真命题，但因为这一点而不使用它就是个假命题
         */
    }
}
