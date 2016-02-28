package com.cheng.improve151suggest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.Arrays;
import com.cheng.highqualitycodestudy.R;
import com.cheng.improve151suggest.adapter.I151SuggestListAdapter;

/**
 第10章 性能和效率
 建议132： 提升java性能的基本方法
 建议133： 若非必要，不要克隆对象
 建议134： 推荐使用“望闻问切”的方式诊断性能
 建议135： 必须定义性能衡量标准
 建议136： 枪打出头鸟—解决首要系统性能问题
 建议137： 调整jvm参数以提升性能
 建议138： 性能是个大“咕咚”
 */
public class I151SChapter10Activity extends AppCompatActivity {
    private static final String TAG = "I151SChapter10Activity";

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
        String[] suggests = getResources().getStringArray(R.array.i151schapter10);
        I151SuggestListAdapter adapter = new I151SuggestListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
    }

    /**
     * 建议132： 提升java性能的基本方法
     */
    private void suggest132() {
        /*
        1）不要在循环条件中计算
        如果在循环（如for循环、while循环）条件中计算，则每循环一遍就要计算一次，这会降低系统效率
        2）尽可能把变量、方法声明为final static类型
        假设要将阿拉伯数字转换为中文数字，其定义如下：
        public String toChineseNum(int num) {
            // 中文数字
            String[] cns = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
            return cns[num];
        }
        每次调用该方法时都会重新生成一个cns数组，注意该数组不会改变，属于不变数组，在这种情况下，把它声
        明为类变量，并且加上final static修饰会更合适，在类加载后就生成了该数组，每次方法调用则不再重新
        生成数组对象了，这有助于提高系统性能，代码如下：
        // 声明为类变量
        final static String[] cns = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        public String toChineseNum(int num) {
            return cns[num];
        }
        3）缩小变量的作用范围
        关于变量，能定义在方法内的就定义在方法内，能定义在一个循环体内的就定义在循环体内，能放置在一个
        try...catch块内的就放置在该块内，其目的是加快GC的回收
        4）频繁字符串操作使用StringBuilder或StringBuffer
        虽然String的联接操作（“+”号）已经做了很多优化，但在大量的追加操作上StringBuilder或StringBuffer
        还是比“+”号的性能好很多
        5）使用非线性检索
        如果在ArrayList中存储了大量的数据，使用indexOf查找元素会比java.utils.Collections.binarySearch
        的效率低很多，原因是binarySearch是二分搜索法，而indexOf使用的是逐个元素对比的方法。这里要注意：
        使用binarySearch搜索时，元素必须进行排序，否则准确性就不可靠了
        6）覆写Exception的fillInStackTrace方法
        fillInStackTrace方法是用来记录异常时的栈信息的，这是非常耗时的动作，如果在开发时不需要关注栈信
        息，则可以覆盖之，如下覆盖fillInStackTrace的自定义异常会使性能提升10倍以上：
        class MyException extends Exception {
            public Throwable fillInStackTrace() {
                return this;
            }
        }
        7）不要建立冗余对象
        不需要建立的对象就不能建立，说起来很容易，要完全遵循此规则难度就很大了，我们经常就会无意地创建冗
        余对象，例如这样一段代码：
        public void doSomething() {
            // 异常信息
            String exceptionMsg = "我出现异常了，快来救救我";
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                // 转换为自定义运行期异常
                throw new MyException(e, exceptionMsg);
            }
        }
        注意看变量exceptionMsg，这个字符串变量在什么时候会被用到？只有在抛出异常时它才有用武之地，那它
        是什么时候创建的呢？只要该方法被调用就创建，不管会不会抛出异常。我们知道异常不是我们的主逻辑，不
        是我们代码必须或经常要到达的区域，那位了这个不经常出现的场景就每次都多定义一个字符串变量，合适吗？
        而且还要占用更多的内存！所以，在catch块中定义exceptionMsg才是正道：需要的时候才创建对象
        我们知道运行一段程序需要三种资源：CPU、内存、I/O，提升CPU的处理速度可以加快代码的执行速度，直接
        变现就是返回时间缩短了，效率提交了；内存是Java程序必须考虑的问题，在32位的机器上，一个JVM最多只
        能使用2GB的内存，而且程序占用的内存越大，寻址效率也就越低，这也是影响效率的一个因素。I/O是程序展
        示和存储数据的主要通道，如果它很缓慢就会影响正常的显式效果。所以我们在编程时需要从这三个方面入手
         */
        /**
         * 注意
         * Java的基本优化方法非常多，但是随着Java的不断升级，很多看似很正确的优化策略就逐渐过时了（或者
         * 说已经失效了），这一点还需要注意。最基本的优化方法就是自我验证，找出最佳的优化途径，提高系统性
         * 能，不可盲目信任
         */
    }

    /**
     * 建议133： 若非必要，不要克隆对象
     */
    private void suggest133() {
        /*
        通过clone方法生成一个对象时，就会不再执行构造函数了，只是在内存中进行数据块的拷贝，此方法看上去
        似乎比new方法的性能好很多，但是Java的缔造者们也认识到“二八原则”，80%（甚至更多）的对象是通过new
        关键字创建出来的，所以对new在生成对象（分配内存、初始化）时做了充分的性能优化，事实上，一般情况
        下new生成的对象比clone生成的性能方面要好很多，如下示例代码：
        private static class Apple implements Cloneable {
            public Object clone() { // 注：这个实验去掉clone方法的try...catch应该要公平点
                try {
                    return super.clone();
                } catch (CloneNotSupportedException e) {
                    throw new Error();
                }
            }
        }
        public static void main(String[] args) {
            // 循环10万次
            final int maxLoops = 10 * 10000;
            int loops = 0;
            // 开始时间
            long start = System.nanoTime();
            // “母”对象
            Apple apple = new Apple();
            while (++loops < maxLoops) {
                apple.clone;
            }
            long mid = System.nanoTime();
            System.out.println("clone 方法生成对象耗时：" + (mid-start) + " ns");
            // new生成对象
            while (--loops > 0) {
                new Apple();
            }
            long end = System.nanoTime();
            System.out.println("new 生成对象耗时：" + (end-mid) + " ns");
        }
        运行查看输出结果，发现用new生成对象竟然比clone方法快很多！原因是Apple的构造函数非常简单，而且
        JVM对new做了大量的性能优化，而clone方式只是一个冷僻的生成对象方式，并不是主流，它主要用于构造
        函数比较复杂，对象属性比较多，通过new关键字创建一个对象比较耗时的时候
         */
        /**
         * 注意
         * 克隆对象并不比直接生成对象效率高
         */
    }

    /**
     * 建议134： 推荐使用“望闻问切”的方式诊断性能
     */
    private void suggest134() {
        /**
         * 注意
         * 性能诊断遵循“望闻问切”，不可过度急躁
         */
    }

    /**
     * 建议135： 必须定义性能衡量标准
     */
    private void suggest135() {
        /*
        制定性能衡量标准的原因有两个：
        1）性能衡量标准是技术与业务之间的契约
        2）性能衡量标准是技术优化的目标
        性能优化是无底线的，性能优化得越厉害带来的副作用也就越明显，例如代码的可读性差，可扩展性降低等
         */
        /**
         * 注意
         * 一个好的性能衡量标准应该包括以下KPI（Key Performance Indicators）
         * -核心业务的响应时间
         * -重要业务的响应时间
         */
    }

    /**
     * 建议136： 枪打出头鸟—解决首要系统性能问题
     */
    private void suggest136() {
        /*
        在一个系统出现性能问题的时候，很少会出现只有一个功能有性能问题，系统一旦出现性能问题，也就意味
        着一批的功能都出现了问题，在这种情况下，我们要做的就是统计出业务人员认为重要而且缓慢的所有功能，
        然后按照重要优先级和响应时间进行排序，并找出前三名，而这就是我们要找的“准出头鸟”
        “准出头鸟”找到了，然后再对这三个功能进行综合分析，运用“望闻问切”策略，找到问题的可能根源，然后
        只修正第一个功能的性能缺陷，再来测试检查是否解决了这个问题，紧接着是第二个、第三个，循环之。可
        能有疑问：为什么这里只修正第一个缺陷，而不是三个一起全部修正？这是因为第一个性能缺陷才是我们真
        正的出头鸟，经验表明性能优化项目中超过80%的只要修正了第一个缺陷，其他的性能问题就会自行解决或
        非常容易解决，已经不成为问题了
         */
        /**
         * 注意
         * 解决性能优化要“单线程”小步前进，避免关注点过多导致精力分散
         */
    }

    /**
     * 建议137： 调整jvm参数以提升性能
     */
    private void suggest137() {
        /*
        下面提供了四个常用的JVM优化手段，提供参考：
        1）调整堆内存大小
        在JVM中有两种内存：栈内存（Stack）和堆内存（Heap），栈内存的特点是空间比较小，速度快，用来
        存放对象的引用及程序中的基本类型；而堆内存的特点是控件比较大，速度慢，一般对象都会在这里生成、
        使用和消亡
        栈空间是由线程开辟，线程结束，栈空间由JVM回收，因此它的大小一般不会对性能有太大的影响，但是
        它会影响系统的稳定性，在超过栈内存的容量时，系统会报StackOverflowError错误。可以通过“java
        -Xss <size>”设置内存大小来解决此类问题
        堆内存的调整不能太随意，调整得太小，会导致Full GC频繁执行，轻则导致系统性能急速下降，重则导
        致系统根本无法使用：调整得太大，一则是浪费资源（当然，若设置了最小堆内存则可以避免此问题），
        二则是产生系统不稳定的情况，例如在32位的机器上设置超过1.8GB的内存就有可能产生莫名其妙的错误。
        设置初始化堆内存为1GB（也就是最小堆内存），最大堆内存为1.5GB可以用如下的参数：
        java -Xmx1536 -Xms1024m
        2）调整堆内存中各分区的比例
        JVM的堆内存包括三部分：新生区（Young Generation Space）、养老取（Tenure Generation Space）、
        永久存储区（Permanent Space），其中新生成的对象都在新生区，它又分为伊甸区（Eden Space）、
        幸存0区（Survivor 0 Space）和幸存1区（Survivor 1 Space），当程序中使用了new关键字时，首先
        在伊甸区生成该对象，如果伊甸区满了，则用垃圾回收器进行回收，然后把剩余的对象移动到幸存区（0区
        或1区），可如果幸存区也满了呢？垃圾回收器先进行回收，然后把剩余的对象移动到养老区，那要是养老
        区也满了呢？此时就会触发Full GC（这是一个非常危险的动作，JVM会停止所有的执行，所有系统资源都
        会让位给垃圾回收器），会对所有的对象过滤一遍，检查是否有可以回收的对象，如果还是没有的话，就
        抛出OutOfMemoryError错误，系统不干了
        清楚了这个原理，那就可以思考一下如何提升性能了：若扩大新生区，势必会减少养老区，这就可能产生不
        稳定的情况，一般情况下，新生区和养老区的比例为1：3左右，设置命令如下：
        java -XX:NewSize=32 -XX:MaxNewSize=640m -XX:MaxPermSize=1280m -XX:newRatio=5
        该配置指定新生代初始化为32MB（也就是新生区最小内存为32M），最大不超过640MB，养老区最大不超过
        1280MB，新生区和养老区的比例为1：5
        3）变更GC的垃圾回收策略
        Java程序性能的最大障碍就是垃圾回收，我们不知道它何时会发生，也不知道它会执行多长时间，但是我们
        可以想办法改变它对系统的影响，比如启用并行垃圾回收、规定并行回收的线程数量等，命令格式如下：
        java -XX:+UserParallelGC -XX:ParallelGCThreads=20
        这里启用了并行垃圾回收机制，并且定义了20个收集线程（默认的收集线程等于CPU的数量），这对多CPU的
        系统是非常有帮助的，可以大大减少垃圾回收对系统的影响，提高系统性能
        当然，垃圾回收的策略还有很多属性可以修改，比如UseSerialGC（启用串行GC，默认值）、
        ScavengeBeforeFullGC（新生代GC优先于Full GC执行）、UserConcMarkSweepGC（对老生代采用并发标
        记交换算法进行GC）等，这些参数需要在系统中逐步调试
        4）更换JVM

         */
        /**
         * 注意
         * 如果程序已经优化到了极致，但还是觉得性能比较低，那JVM的优化就要提到日程上来了。不过，由于
         * JVM是系统运行的容器，所以稳定性也是必须考虑的，过度的优化可能就会导致系统故障频繁发生，导
         * 致系统质量大幅下降
         */
    }

    /**
     * 建议138： 性能是个大“咕咚”
     */
    private void suggest138() {
        /*
        可以从四个方面分析Java系统的性能问题：
        1）没有慢的系统，只有不满足业务的系统
        如果有使用者告诉你，”这个系统太慢了“，也就是在间接地提醒您：系统没有满足业务需求，尚需努力
        2）没有慢的系统，只有架构不良的系统
        在做系统架构设计时，架构师有没有考虑并行计算？有没有考虑云计算技术？有没有负载均衡？...这些都
        是解决我们性能问题的良方，只要架构设计得当，效率就不是问题
        3）没有慢的系统，只有懒惰的技术人员
        这里的技术人员涉及面很大，可以是开发人员，也可以是维护人员，甚至是应用软件的顾问人员等
        4）没有慢的系统，只有不愿意投入的系统
        这里的投入指的是资源，包括软硬件资源、人员资源及资金资源等，这不是项目组能够单独解决的问题，但
        是它会严重影响系统的性能
         */
        /**
         * 注意
         * 对现代化的系统建设来说，性能就是一个大”咕咚“--看清它的本质吧
         */
    }
}
