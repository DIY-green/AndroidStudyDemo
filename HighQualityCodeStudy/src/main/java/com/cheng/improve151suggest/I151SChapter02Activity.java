package com.cheng.improve151suggest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import com.cheng.highqualitycodestudy.R;
import com.cheng.improve151suggest.adapter.I151SuggestListAdapter;

/**
 第2章 基本类型
 建议21： 用偶判断，不用奇判断
 建议22： 用整数类型处理货币
 建议23： 不要让类型默默转换
 建议24： 边界，边界，还是边界
 建议25： 不要让四舍五入亏了一方
 建议26： 提防包装类型的null值
 建议27： 谨慎包装类型的大小比较
 建议28： 优先使用整型池
 建议29： 优先选择基本类型
 建议30： 不要随便设置随机种子
 */
public class I151SChapter02Activity extends AppCompatActivity {

    private static final String TAG = "I151SChapter02Activity";

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
        String[] suggests = getResources().getStringArray(R.array.i151schapter2);
        I151SuggestListAdapter adapter = new I151SuggestListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
    }

    /**
     * 建议21： 用偶判断，不用奇判断
     */
    private void suggest21() {
        int [] ints = new int[]{1,2,0,-1,-2};
        for (int i = 0, len = ints.length; i < len; i++) {
            Log.e(TAG, i+"->"+(i%2==1?"奇数":"偶数")); // 输入负数发现判断错误
            // 参考下面的模拟取余计算方法就知道导致该问题的原因
            // 修正很简单，改为判断是否为0即可，如下：
            // i%2 == 0 ? "偶数" : "奇数"
        }
        /**
         * 注意
         * 对于基础知识，应该“知其然，并知其所以然”
         */
    }
    // 模拟取余计算，dividend被除数，devisor除数
    private int remainder(int dividend, int divisor) {
        return dividend - dividend / divisor * divisor;
    }


    /**
     * 建议22： 用整数类型处理货币
     */
    private void suggest22() {
        Log.e(TAG, 10.00-9.60 + ""); // 期望输出0.4，查看输出结果
        // 在计算机中浮点数有可能（注意是可能）是不准确的，它只能无限接近准确值，
        // 而不能完全精确。这是由浮点数的存储规则所决定的，十进制小数使用“乘2取整，顺序
        // 排列”法转换成二进制小数
        /**
         * 注意
         * 在要求准确的系统中如何避免浮点数导致的不准确，有如下两种方法：
         * 1）使用BigDecimal
         * BigDecimal是专门为弥补浮点数无法精确计算的缺陷而设计的类，并且它本身也提供了加减乘除
         * 的常用数学算法。特别是与数据库Decimal类型的字段映射时，BigDecimal是最优的解决方案
         * 2）使用整型
         * 把参与运算的值扩大100倍，并转变为整型，然后在展现时再缩小100倍，这样处理的好处是技术简单、
         * 准确，一般非金融行业（如零售行业）应用较多
         */
    }

    /**
     * 建议23： 不要让类型默默转换
     */
    private static final int LIGHT_SPEED = 30 * 10000 * 1000;
    private void suggest23() {
        Log.e(TAG, "题目1：月亮光想照射到地球需要1秒，计算月亮和地球的距离");
        long dis1 = LIGHT_SPEED * 1;
        Log.e(TAG, "月亮与地球的距离是：" + dis1 + " 米");
        Log.e(TAG, "--------------------------------------------");
        Log.e(TAG, "题目2：太阳光照射到地球上需要8分钟，计算太阳到地球的距离。");
        //可能要超出整数范围，使用long型
        long dis2 = LIGHT_SPEED * 60 * 8;
        Log.e(TAG, "太阳与地球的距离是：" + dis2 + " 米"); // 输出 -202888064
        /*
        诡异，dis2不是已经考虑到int类型可能越界的问题，并且使用了long类型，为什么还会出现负值？
        那是因为Java是先运算然后再进行类型转换的，具体地说就是因为dis2的三个运算参数都是int类型，
        三者相乘的结果虽然也是int类型，但是已经超过了int的最大值，所以其值就是负值了，再转换成long
        类型，结果还是负值
        解决方案：
        加上一个“L”即可， 如 long dis2 = LIGHT_SPEED * 60L * 8;
        60L是一个长整型，乘出来的结果也是一个长整型。
        在实际开发中，更通用的做法是主动声明式类型转化（注意不是强制类型转换），代码如下：
        long dis2 = 1L * LIGHT_SPEED * 60 * 8;
         */
        /**
         * 注意
         * 基本类型转换时，使用主动声明方式减少不必要的Bug
         */
    }

    /**
     * 建议24： 边界，边界，还是边界
     */
    private static final int LIMIT = 2000; // 一个会员拥有产品的最多数量
    private void suggest24() {
        // 会员当前拥有的产品数量
        int cur = 1000;
        int [] inputInts = new int[]{800, 2147483647};
        for (int i=0, len=inputInts.length; i < len; i++) {
            int order = inputInts[i];
            Log.e(TAG, "请输入需要预订的数量：" + order);
            // 当前拥有的与准备订购的产品数量之和
            if (order>0 && order+cur<=LIMIT) {
                Log.e(TAG, "你已经成功预订的 " + order + " 个产品");
            } else {
                Log.e(TAG, "超过限额，预订失败！");
            }
        }
        // 竟然会输出：你已经成功预订的 2147483647 个产品
        // Why？来看程序，order的值是2147483647，那再加上1000就超出int的范围了，其结果是
        // -2147482649，是个负数，当然小于2000.一句话可归结其原因：数字越界使检验条件失效
        /**
         * 注意
         * 边界测试，如果一个方法接收的是int类型的参数，那以下三个值是必测的：0、正最大值、
         * 负最小值，其中正最大和负最小是边界值，如果这三个值都没有问题，方法才是比较安全靠谱的
         */
    }

    /**
     * 建议25： 不要让四舍五入亏了一方
     */
    private void suggest25() {
        Log.e(TAG, "10.5的近似值：" + Math.round(10.5)); // 11
        Log.e(TAG, "-10.5的近似值：" + Math.round(-10.5)); // 10
        // 这是四舍五入的经典案例，绝对值相同的两个数字，近似值为什么就不同了呢？这是由Math.round
        // 采用的舍入规则所决定的（采用的是正无穷方向舍入规则）。我们知道四舍五入是有误差的：其误差
        // 值是舍入位的一半。下面以银行利息计算为例来阐述该问题
        // 银行账户数量，5千万
        int accountNum = 5000 * 10000;
        // 按照人行的规定，每个季度月末的20日为银行结息日
        double cost = 0.0005 * accountNum * 4;
        Log.e(TAG, "银行每年损失的金额：" + cost);
        /*
         对于这个算法的误差，美国银行家提出了一个修正算法，叫做银行家舍入（Banker's Round）的近似算法，
         其规则如下：
         舍去位的数值小于5时，直接舍去
         舍去位的数值大于6时，进位后舍去
         当舍去位的数值等于5时，分两种情况：5后面还有其他数字（非0），则进位后舍去；若5后面是0（即5是
         最后一个数字），则根据5前一位数的奇偶性来判断是否需要进位，奇数进位，偶数舍去
         以上规则汇总成一句话：四舍六入五考虑，五后非零就进一，五后为零看奇偶，五前为偶应舍去，五前为奇
         要进一。举例说明，取2位精度：
         round(10.5551) = 10.56
         round(10.555) = 10.56
         round(10.545) = 10.54
         要在Java5以上版本中使用银行家的舍入法则非常简单，直接使用RoundingMode类提供的Round模式即可，
         示例如下：
         */
        // 存款
        BigDecimal d = new BigDecimal(888888);
        // 月利率，乘3计算季利率
        BigDecimal r = new BigDecimal(0.001875 * 3);
        // 计算利息
        BigDecimal i = d.multiply(r).setScale(2, RoundingMode.HALF_EVEN); // 表示使用银行家舍入法则进行近似计算
        Log.e(TAG, "季利息是：" + i);
        /*
        目前Java支持以下七种舍入方式：
        ROUND_UP：远离零方向舍入（向绝对值最大的方向舍入，只要舍弃位非0即进位）
        ROUND_DOWN：趋向零方向舍入（向绝对值最小的方向舍入，所有的位都舍弃，不存在进位）
        ROUND_CEILING：向正无穷方向舍入（Math.round方法使用的即为此模式）
        ROUND_FLOOR：向负无穷方向舍入
        HALF_UP：最近数字舍入（5进）（最最经典的四舍五入模式）
        HALF_DOWN：最近数字舍入（5舍）
        HALF_EVEN：银行家算法
         */
        /**
         * 注意
         * 根据不同的场景，慎重选择不同的舍入模式，以提高项目的精确度，减少算法损失
         */
    }

    /**
     * 建议26： 提防包装类型的null值
     */
    private void suggest26() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(null); // 将空值放入List中，是否会自动转换为0？会不会报错？
        Log.e(TAG, "f(list)=" + f(list)); // 运行失败，报空指针异常
        /*
        在程序的for循环中，隐含了一个拆箱过程，在此过程中包装类型转换为了基本类型。我们知道
        拆箱过程是通过调用包装对象的intValue方法来实现的，由于包装对象为null，访问其intValue
        方法报空指针异常也就在所难免了。
        解决方案：
        加入null值检查即可，如下：
        count += (i!=null)?i:0;
         */
        /**
         * 注意
         * 包装类型参与运算时，要做null值校验
         */
    }
    // 接收一个元素是整数的List参数，计算所有元素之和
    private int f(List<Integer> list) {
        int count = 0;
        for (int i : list) {
            count += i;
        }
        return count;
    }

    /**
     * 建议27： 谨慎包装类型的大小比较
     */
    private void suggest27() {
        Integer i = new Integer(100);
        Integer j = new Integer(100);
        // 比较两个包装对象大小
        Log.e(TAG, "i == j : " + (i == j)); // false
        Log.e(TAG, "i > j : " + (i > j)); // false
        Log.e(TAG, "i < j : " + (i < j)); // false
        /**
         * 注意
         * 在Java中“==”是用来判断两个操作数是否有相等关系的，如果是基本类型则判断值是否相等，如果
         * 是对象则判断是否是一个对象的两个引用，也就是地址是否相等
         * 在Java中“>”和“<”用来判断两个数字类型的大小关系，注意只能是数字型的判断，对于Integer包装
         * 类型，是根据其intValue()方法的返回值（也就是其相应的基本类型）进行比较的
         * 对于两个对象之间的比较就应该采用相应的方法，而不是通过Java的默认机制来处理
         */
    }

    /**
     * 建议28： 优先使用整型池
     */
    private void suggest28() {
        int[] ints = new int[]{127, 128, 555};
        for (int i=0,len=ints.length; i < len; i++) {
            int ii = ints[i];
            Log.e(TAG, "\n====" + ii + " 的相等判断======");
            // 两个通过new产生的Integer对象
            Integer iInteger = new Integer(ii);
            Integer jInteger = new Integer(ii);
            Log.e(TAG, "new 产生的对象：" + (iInteger==jInteger));

            // 基本类型转为包装类型后比较
            iInteger = ii;
            jInteger = ii;
            Log.e(TAG, "基本类型转换的对象" + (iInteger==jInteger));

            // 通过静态方法生成的对象
            iInteger = Integer.valueOf(ii);
            jInteger = Integer.valueOf(ii);
            Log.e(TAG, "valueOf产生的对象" + (iInteger==jInteger));
        }
        /*
        结果：
        ====127=====
        false
        true
        true
        ====128====
        false
        false
        false
        ====555====
        false
        false
        false
         */
        /*
         很不可思议，数字127的比较结果竟然与其他两个数字不同，它的装箱动作所产生的对象竟然是
         同一个对象，valueOf产生的也是同一个对象，但是大于127的数字128和555在比较过程中所产
         生的却不是同一个对象，这是为什么？
         1）new产生的Integer对象
         new声明的就是要生成一个新对象，这是两个对象，地址肯定不同
         2）装箱生成的对象
         首先要说明的是装箱动作是通过valueOf方法实现的，也就是说后两个算法是相同的，那结果肯定
         也是一样的，现在的问题是：valueOf是如何生成对象的呢？来看下Integer.valueOf的源码：
         public static Integer valueOf(int i) {
            final int offset = 128;
            if (i >= -128 && i <= 127) { // must cache
                return IntegerCache.cache[i + offset];
            }
            return new Integer(i);
         }
         cache是IntegerCache内部类的一个静态数组，容纳的是-128到127之间的Integer对象。通过
         valueOf产生包装对象时，如果int参数在-128到127之间，则直接从整型池中获得对象，不在该
         范围的int类型则通过new生成包装对象
         整型池的存在不仅仅提高了系统性能，同时也节约了内存空间，这也是使用整型池的原因，也就是
         在声明包装对象的时候使用valueOf生成，而不是通过构造函数来生成的原因。顺便提醒大家，在
         判断对象是否相等的时候，最好是用equals方法，避免用“==”产生非预期结果
         */
        /**
         * 注意
         * 通过包装类的valueOf生成包装实例可以显著提高空间和时间性能
         */
    }

    /**
     * 建议29： 优先选择基本类型
     */
    private void suggest29() {
        int i = 140;
        // 分别传递int类型和Integer类型
        f29(i);                  // 输出：基本类型的方法被调用
        f29(Integer.valueOf(i)); // 输出：基本类型的方法被调用
        /*
        整个f29(Integer.valueOf(i))的执行过程是这样的：
        i通过valueOf方法包装成一个Integer对象
        由于没有f(Integer i)方法，编译器“聪明”地把Integer对象转换成int
        int自动拓宽为long，编译结束（使用的是f29(long a)方法）
         */
        /**
         * 注意
         * 重申，基本类型优先考虑
         */
    }
    private void f29(long a) {
        Log.e(TAG, "基本类型的方法被调用");
    }
    private void f29(Long a) {
        Log.e(TAG, "包装类型的方法被调用");
    }

    /**
     * 建议30： 不要随便设置随机种子
     */
    private void suggest30() {
        Random r1 = new Random();
        for (int i = 0; i < 4; i++) {
            Log.e(TAG, "第" + i + "次" + r1.nextInt());
        }
        Random r2 = new Random(1000);
        for (int i = 0; i < 4; i++) {
            Log.e(TAG, "第" + i + "次" + r2.nextInt());
        }
        /*
        多次打印后会发现r1产生的随机数都是是不同的，而r2打印的随机数，似乎不随机了，几次
        打印出来的几个数是相同的，问题何在？
        这是因为产生随机数的种子被固定了，在Java中，随机数的产生取决于种子，随机数和种子
        之间的关系遵从以下两个规则：
        种子不同，产生不同的随机数
        种子相同，即使实例不同也产生相同的随机数
        顺便提一下，在Java中有两种方法获得不同的随机数：通过java.util.Random类获得随机
        数的原理和Math.random方法相同，Math.random()方法也是通过生成一个Random类的实例，
        然后委托nextDouble()方法的，两者是殊途同归，没有差别
         */
        /**
         * 注意
         * 若非必要，不要设置随机数种子
         */
    }
}
