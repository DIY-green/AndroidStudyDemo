package com.cheng.improve151suggest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.RandomAccess;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
import com.cheng.highqualitycodestudy.R;
import com.cheng.improve151suggest.adapter.I151SuggestListAdapter;
import com.cheng.improve151suggest.model.City;

/**
 第5章 数组和集合
 建议60： 性能考虑，数组是首选
 建议61： 若有必要，使用变长数组
 建议62： 警惕数组的浅拷贝
 建议63： 在明确的场景下，为集合指定初始容量
 建议64： 多种最值算法，适时选择
 建议65： 避开基本类型数组转换列表陷阱
 建议66： aslist方法产生的list对象不可更改
 建议67： 不同的列表选择不同的遍历方法
 建议68： 频繁插入和删除时使用linkedlist
 建议69： 列表相等只需关心元素数据
 建议70：子列表只是原列表的一个视图
 建议71： 推荐使用sublist处理局部列表
 建议72： 生成子列表后不要再操作原列表
 建议73： 使用comparator进行排序
 建议74： 不推荐使用binarysearch对列表进行检索
 建议75： 集合中的元素必须做到compareto和equals同步
 建议76： 集合运算时使用更优雅的方式
 建议77： 使用shuffle打乱列表
 建议78： 减少hashmap中元素的数量
 建议79： 集合中的哈希码不要重复
 建议80： 多线程使用vector或hashtable
 建议81： 非稳定排序推荐使用list
 建议82： 由点及面，一叶知秋—集合大家族
 */
public class I151SChapter05Activity extends AppCompatActivity {
    private static final String TAG = "I151SChapter05Activity";

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
        String[] suggests = getResources().getStringArray(R.array.i151schapter5);
        I151SuggestListAdapter adapter = new I151SuggestListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
    }

    /**
     * 建议60： 性能考虑，数组是首选
     */
    private void suggest60() {
        /**
         * 注意
         * 性能要求较高的场景使用数组替代集合
         */
    }

    /**
     * 建议61： 若有必要，使用变长数组
     */
    private void suggest61() {
        // 一个班级最多容纳60个学生
        Integer[] classes = new Integer[60];
        /*classes初始化...*/
        // 偶尔一个班级可以容纳80人，数组加长
        classes = expandCapacity(classes, 80);
        /*重新初始化超过限额的20人...*/
        /**
         * 注意
         * 通过这样的处理方式，曲折地解决了数组的变长问题。其实，集合的长度自动维护功能的原理与此类似。
         * 在实际开发中，如果确实需要变长的数据集，数组也是在考虑范围之内的，不能因固定长度而将其否定
         */
    }
    // 采用Arrays数组工具类的copyOf方法，产生一个newLen长度的新数组，并把原有的值拷贝了进去，之后就可以
    // 对超长的元素进行赋值了
    private <T> T[] expandCapacity(T[] datas, int newLen) {
        // 不能是负值
        newLen = newLen<0 ? 0: newLen;
        return Arrays.copyOf(datas, newLen);
    }

    /**
     * 建议62： 警惕数组的浅拷贝
     */
    private void suggest62() {
        /**
         * 注意
         * 通过copyOf方法产生的一个数组是一个浅拷贝，这与序列化的浅拷贝完全相同：基本类型是直接拷贝值，
         * 其他都是拷贝引用地址。需要说明的是，数组的clone方法也是与此相同的，同样是浅拷贝，而且集合的
         * clone方法也都是浅拷贝，在拷贝时需要多留心
         */
    }

    /**
     * 建议63： 在明确的场景下，为集合指定初始容量
     */
    private void suggest63() {
        /*
        查看ArrayList源码，可以看出，如果不设置初始容量，系统就按照1.5倍的规则扩容，每次扩容都是一次数组
        的拷贝，如果数据量很大，这样的拷贝会非常耗费资源，而且效率非常低下。如果已经知道一个ArrayList的可
        能长度，然后对ArrayList设置一个初始容量则可以显著提高系统性能。比如一个班级的学生，通常也就是50人
        左右，就声明ArrayList的默认容量为50的1.5倍（元素数量小，直接计算，避免数组拷贝），即new ArrayList
        <Student>(75);这样在使用add方法增加元素时，只要在75以内都不用做数组拷贝，超过了75才会按照默认规则
        扩容。如此处理，对开发逻辑并不会有任何影响，而且可以提高运行效率
         */
        /**
         * 注意
         * 非常有必要在集合初始化时声明容量
         */
    }

    /**
     * 建议64： 多种最值算法，适时选择
     */
    private void suggest64() {
        Integer[] datas = new Integer[]{};
        // 转换为列表
        List<Integer> dataList = Arrays.asList(datas.clone());
        // 转换为TreeSet，删除重复元素并升序排列
        TreeSet<Integer> treeSet = new TreeSet<>(dataList);
        // 取得比最大值小的最大值，也就是老二了
        Integer second = treeSet.lower(treeSet.last());
        /*
        剔除重复元素并升序排列，这都由TreeSet类实现的，然后可再使用lower方法寻找小于最大值的值
         */
        /**
         * 注意
         * 最值计算时使用集合最简单，使用数组性能最优
         */
    }

    /**
     * 建议65： 避开基本类型数组转换列表陷阱
     */
    private void suggest65() {
        int[] data = {1,2,3,4,5};
        List list = Arrays.asList(data);
        Log.e(TAG, "列表中的元素数量是：" + list.size()); // 1
        Log.e(TAG, "元素类型：" + list.get(0).getClass()); // class [I
        Log.e(TAG, "转换前后是否相等：" + data.equals(list.get(0)));
        // 代码修正如下
        Integer[] data2 = {1,2,3,4,5};
        List list2 = Arrays.asList(data2);
        Log.e(TAG, "列表中的元素数量是：" + list2.size());
        /*
        仔细看一下Arrays.asList的方法说明：输入一个变长参数，返回一个固定长度的列表。注意这里是一个变
        长参数，看源代码：
        public static <T> List<T> asList(T... a) {
            return new ArrayList<T<(a);
        }
        asList方法输入的是一个泛型变长参数，我们知道基本类型是不能泛型化的，也就是说8个基本类型不能作为
        泛型参数，要想作为泛型参数必须使用其所对应的包装类型。那上面的例子传递了一个int类型的数组，为什么
        程序没有报编译错呢？
        在Java中，数组是一个对象，它是可以泛型化的，也就是说上面的例子是把一个int类型的数组做为了T的类型，
        所以转换后在List中就只有一个类型为int数组的元素了
        可看到上面打印输出：“元素类型：class [I”？我们并没有指明是数组（Array）类型呀！这是因为JVM不可能
        输出Array类型，因为Array是属于java.lang.reflect包的，它是通过反射访问数组元素的工具类。在Java
        中任何一个数组的类型都是“[I”，究其原因就是Java并没有定义数组这一个类，它是在编译器编译的时候生成
        的，是一个特殊的类，在JDK的帮助中也没有任何数组类的信息
         */
        /**
         * 注意
         * 原始类型数组不能作为asList的输入参数，否则会引起程序逻辑混乱
         */
    }

    /**
     * 建议66： aslist方法产生的list对象不可更改
     */
    enum Week {Sun, Mon, Tue, Wed, Thu, Fir, Sat}
    private void suggest66() {
        // 五天工作制
        Week[] workDays = {Week.Mon, Week.Tue, Week.Wed, Week.Thu, Week.Fir};
        // 转换为列表
        List<Week> list = Arrays.asList(workDays);
        // 增加周六也为工作日
        list.add(Week.Sat); // UnsuppostedOperationException
        /* 工作日开始干活了 */
        /*
        asList()返回的List居然不支持add方法，这真是奇怪了！来看看asList方法的源代码：
        public static <T> List<T> asList(T... a) {
            return new ArrayList<T<(a);
        }
        直接new了一个ArrayList对象返回，难道ArrayList不支持add方法？不可能呀！可能，问题就出在这个
        ArrayList类上，此ArrayList非java.util.ArrayList，而是Arrays工具类的一个内置类（私有静态内部类），
        除了Arrays能访问外，其他类都不能访问。再深入地看看这个ArrayList静态内部类，它仅仅实现了5个方法：
        size：元素数量
        toArray：转化为数组，实现了数组的浅拷贝
        get：获取指定元素
        set：重置某一元素
        contains：是否包含某元素
        对于常用的add和remove方法它都没有实现，也就是说asList返回的是一个长度不可变的列表
         */
        /**
         * 注意
         * 有些开发者喜欢通过如下方式定义和初始化列表：
         * List<Stirng> names = Arrays.asList("Java", "Android", "OC");
         * 一句话完成了列表的定义和初始化，看似很便捷，却深藏着重大隐患--列表长度无法修改
         * 如果有这种习惯，请慎之戒之，除非非常自信该List只用于读操作
         */
    }

    /**
     * 建议67： 不同的列表选择不同的遍历方法
     */
    private void suggest67() {
        /*
        ArrayList数组实现了RandomAccess接口（随机存取接口），这也就标志着ArrayList是一个可以随机存取的
        列表。在Java中，RandomAccess和Cloneable、Serializable一样，都是标志性接口，不需要任何实现，只
        是用来表明其实现类具有某种特质的，实现了Cloneable表明可以被拷贝，实现了Serializable接口表明被序
        列化了，实现了RandomAccess则表明这个类可以随机存取，对ArrayList来说也就是标志着其数据元素之间没
        有关联，即两个位置相邻的元素之间没有相互依赖和索引关系，可以随机访问和存储
        Java中的foreach语法是Iterator(迭代器)的变形用法，如下代码是等价的：
        int sum = 0;
        for(int i : list) {
            sum += i;
        }
        等价于
        for(Iterator<Integer> i=list.iterator; i.hasNext(); ) {
            sum += i;
        }
        那想想什么是迭代器，迭代器是23种设计模式中的一种，“提供一种方法访问一个容器对象中的各个元素，同时
        又无须暴露该对象的内部细节”，也就是对于ArrayList，需要先创建一个迭代器容器，然后屏蔽内部遍历细节，
        对外提供hasNext、next等方法。问题是ArrayList实现了RandomAccess接口，已表明元素之间本来没有关系，
        可以，为了使用迭代器就需要强制建立一种互相“知晓”的关系，比如上一个元素可以判断是否有下一个元素，以
        及下一个元素是什么等关系，这也是通过foreach遍历耗时的原因
         */
        // 优化的遍历求和
        int sum = 0;
        List<Integer> list = null;
        if (list instanceof RandomAccess) {
            // 可以随机存取，则使用下标遍历
            for (int i=0,size=list.size(); i < size; i++) {
                sum += list.get(i);
            }
        } else {
            // 有序存放，使用foreach方式
            for (int i : list) {
                sum += i;
            }
        }
        /**
         * 注意
         * 列表遍历不是那么简单的，其中很有“学问”，适时选择最优的遍历方式，不要固化为一种
         */
    }

    /**
     * 建议68： 频繁插入和删除时使用linkedlist
     */
    private void suggest68() {
        /**
         * 注意
         * LindedList在插入和删除元素时表现好
         * ArrayList在读取和修改元素时效率高
         */
    }

    /**
     * 建议69： 列表相等只需关心元素数据
     */
    private void suggest69() {
        ArrayList<String> strs = new ArrayList<>();
        strs.add("A");

        Vector<String> strs2 = new Vector<>();
        strs2.add("A");

        Log.e(TAG, "strs.equals(strs2)" + strs.equals(strs2)); // true
        /**
         * 注意
         * 判读集合是否相等时只须关注元素是否相等即可
         */
    }

    /**
     * 建议70：子列表只是原列表的一个视图
     */
    private void suggest70() {
        // 定义一个包含两个字符串的列表
        List<String> c = new ArrayList<>();
        c.add("A");
        c.add("B");
        // 构造一个包含c列表的字符串列表
        List<String> c1 = new ArrayList<>(c);
        // subList生成与c相同的列表
        List<String> c2 = c.subList(0, c.size());
        // c2增加一个元素
        c2.add("C");
        Log.e(TAG, "c == c1 ? " + c.equals(c1)); // false
        Log.e(TAG, "c == c2 ? " + c.equals(c2)); // true

        // String也有个substring方法，看看它是如何工作的
        String str = "AB";
        String str1 = new String(str);
        String str2 = str.substring(0) + "C";
        Log.e(TAG, "str == str1 ? " + str.equals(str1));
        Log.e(TAG, "str == str2 ? " + str.equals(str2));

        /*
        看上面的例子，与String类刚好相反，同样是一个sub类型的操作，为什么会相反呢？
        查看subList的源代码，它的实现原理是这样的：它返回的SubList类也是AbstractList的子类，其所有的方
        法如get、set、add、remove等都是在原始列表上的操作，它自身并没有生成一个数组或是链表，也就是子列
        表这是原列表的一个视图（View），所有的修改动作都反映在原列表上。解释了c==c2，再回过头来看看为什么
        变量c与c1不相等。很简单，因为通过ArrayList构造函数创建的List对象c1实际上是新列表，它是通过数组的
        copyOf动作生成的，所生成的列表c1与原列表c之间没有任何关系（虽然是浅拷贝，但元素类型是String，也就
        是说元素是深拷贝的），然后c又增加了元素，因为c1与c之间已经没有一毛钱关系了，那自然是不相等了
         */
        /**
         * 注意
         * subList产生的列表只是一个视图，所有的修改动作直接作用于原列表
         */
    }

    /**
     * 建议71： 推荐使用sublist处理局部列表
     */
    private void suggest71() {
        /*
        来看这样一个简单的需求：一个列表有100个元素，现在要删除索引位置为20~30的元素
         */
        // 方案1 一个遍历很快就可以完成
        // 初始化一个固定长度，不可变列表
        List<Integer> initData = Collections.nCopies(100, 0);
        // 转换为可变列表
        List<Integer> list = new ArrayList<>(initData);
        // 遍历，删除符合条件的元素
        for (int i=0, size=list.size(); i < size; i++) {
            if (i>=20 && i<30) {
                list.remove(i);
            }
        }
        // 或者
        for (int i = 20; i < 30; i++) {
            if (i < list.size()) {
                list.remove(i);
            }
        }
        // 遍历一遍，符合条件的就删除，简单而又实用。不过，还有没有其他方式呢？有没有“one-lining”一行代码
        // 就解决问题的方式呢？
        // 有，直接使用ArrayList的removeRange方法不就可以了吗？等等，这个方法有protected关键字修饰着，不
        // 能直接使用，那怎么办？看如下代码：
        // 方案2
        // 初始化一个固定长度，不可变列表
        List<Integer> initData2 = Collections.nCopies(100, 0);
        // 转换为可变列表
        List<Integer> list2 = new ArrayList<>(initData2);
        // 删除指定范围的元素
        list.subList(20, 30).clear();
        /**
         * 注意
         * 因为subList返回的List是原始列表的一个视图，删除这个视图中的所有元素，最终就会反映到原始字符串上，
         * 那么一行代码即解决问题了
         */
    }

    /**
     * 建议72： 生成子列表后不要再操作原列表
     */
    private void suggest72() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        List<String> subList = list.subList(0, 2);
        // 原字符串增加一个元素
        list.add("D");
        Log.e(TAG, "原列表长度：" + list.size());
        Log.e(TAG, "子列表长度：" + subList.size()); // 抛出异常 ConcurrentModificationException
        /*
        这里根本就没有多线程操作，何来并发修改呢？这个问题很容易回答，那是因为subList取出的列表是原列表
        的一个视图，原数据集（代码中的list变量）修改了，但是subList取出的子列表不会重新生成一个新列表（
        这点与数据库视图是不同的），后面再对子列表继续操作时，就会检测到修改计数器与预期的不相同，于是就
        抛出了并发修改异常。出现这个问题的最终原因还是在子列表提供的size方法的检查上（可看size方法源码）
        subList的其他方法也会检测修改计数器，例如set、get、add等方法，若生成子列表后，再修改原列表，这
        些方法也会抛出ConcurrentModificationException异常
        对于子列表操作，因为视图是动态生成的，生成子列表后再操作原列表，必然会导致“视图”的不稳定，最有效
        的办法就是通过Collections.unmodifiableList方法设置列表为只读状态，代码如下：
         */
        List<String> list2 = new ArrayList<>();
        List<String> subList2 = list.subList(0, 2);
        // 设置列表为只读状态
        list2 = Collections.unmodifiableList(list2);
        // 对list2进行只读操作
        // doReadSomething(list2);
        // 对subList2进行读写操作
        // doReadAndWriteSomething(subList2);
        /*
        还有一个问题，数据库的一张表可以有很多视图，我们的List也可以有多个视图，也就是可以有多个子列表，
        但问题是只要生成的子列表多于一个，则任何一个子列表就都不能修改了，否则就会抛出并发修改异常
         */
        /**
         * 注意
         * subList生成子列表后，保持原列表的只读状态
         */
    }

    /**
     * 建议73： 使用Comparator进行排序
     */
    private void suggest73() {
        /*
        在Java中，要想给数据排序，有两种实现方式，一种是实现Comparable接口，一种是实现Comparator接口，
        这两者有什么区别呢？
        实现了Comparable接口的类表明自身是可比较的，有了比较才能进行排序；而Comparator接口是一个工具类接
        口，它的名字（比较器）也已经表明了它的作用：用作比较，它与原有类的逻辑没有关系，只是实现两个类的比
        较逻辑，从这方面来说，一个类可以有很多的比较器，只要有业务需求就可以产生比较器，有比较器就可以产生
        N多种排序，而Comparable接口的排序只能说是实现类的默认排序算法，一个类稳定、成熟后其compareTo方法
        基本不会改变，也就是说一个类只能有一个固定的、由compareTo方法提供的默认排序算法
         */
        /**
         * 注意
         * Comparable接口可以作为实现类的默认排序法，Comparator接口则是一个类的扩展排序工具
         */
    }

    /**
     * 建议74： 不推荐使用binarysearch对列表进行检索
     */
    private void suggest74() {
        List<String> cities = new ArrayList<>();
        cities.add("上海");
        cities.add("广州");
        cities.add("广州");
        cities.add("北京");
        cities.add("天津");
        // indexOf方法取得索引值
        int index1 = cities.indexOf("广州");
        // binarySearch查找到索引值
        int index2 = Collections.binarySearch(cities, "广州");
        Log.e(TAG, "索引值（indexOf）：" + index1);   // 1
        Log.e(TAG, "索引值（binarySearch）：" + index1); // 2
        /*
        结果不一样，查看源码，两者的算法都没有问题，难道是用错了？这还真是使用错了，因为二分法查询的一个首
        要前提是：数据集已经实现升序排列，否则二分法查找的值是不准确的。不排序怎么确定是在小区（比中间值小
        的区域）中查找还是在大区（比中间值大的区域）中查找呢？二分法必须要先排序，这是二分法查找的首要条件
         */
        /**
         * 使用binarySearch首先要考虑排序问题，从性能方面考虑，binarySearch是最好的选择
         */
    }

    /**
     * 建议75： 集合中的元素必须做到compareto和equals同步
     */
    private void suggest75() {
        List<City> cities = new ArrayList<City>();
        cities.add(new City("021", "上海"));
        cities.add(new City("021", "沪"));

        City city = new City("021","沪");
        //排序
        Collections.sort(cities);
        // indexOf方法取得索引值
        int index1 = cities.indexOf(city);
        // binarySearch查找到索引值
        int index2 = Collections.binarySearch(cities, city);
        Log.e(TAG, "索引值(indexOf)：" + index1);           // 0
        Log.e(TAG, "索引值（binarySearch)：" + index2);     // 1
        /*
        indexOf返回的是第一个元素，而binarySearch返回的是第二个元素（索引值是1），这是怎么回事呢？
        这是因为indexOf是通过equals方法判断的，equals等于true就认为找到符合条件的元素了，而binarySearch
        查找的依据是compareTo方法的返回值，返回0即认为找到符合条件的元素
        仔细审查一下代码，City类覆写了compareTo和equals方法，但是两者并不一致
        从这个例子中，可以理解两点：
        indexOf依赖equals方法查找，binarySearch则依赖compareTo方法查找
        equals是判断元素是否相等，compareTo是判断元素在排序中的位置是否相同
        既然一个是决定排序位置，一个是决定相等，那就应该保证当排序位置相同时，其equals也相同，否则就会产生
        逻辑混乱
         */
        /**
         * 注意
         * 实现了compareTo方法，就应该覆写equals方法，确保两者同步
         */
    }

    /**
     * 建议76： 集合运算时使用更优雅的方式
     */
    private void suggest76() {
        // 遍历可以实现并集、交集、差集等运算，但这不是最优雅的处理方式，看看如何进行更优雅的集合操作
        // 1)并集 也叫做合集，把两个集合加起来即可
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        List<String> list2 = new ArrayList<>();
        list2.add("C");
        list2.add("B");
        // 并集
        list1.addAll(list2);

        // 2)交集 计算两个集合的共有元素，也就是你有我也有的元素集合
        list1.retainAll(list2);
        // 注意retainAll方法会删除list1中没有出现在list2中的元素

        // 3)差集 由所有属于A但不属于B的元素组成的集合，叫做A与B的差集
        list1.removeAll(list2);
        // 从list1中删除出现在list2的元素，即可得出list1与list2的差集部分

        // 4)无重复的并集 并集是集合A加集合B，那如果集合A和集合B有交集，就需要确保并集的结果中只有一份交集，
        //此为无重复的并集
        // 删除在list1中出现的元素
        list2.removeAll(list1);
        // 把剩余的list2元素加到list1中
        list1.addAll(list2);

        /**
         * 注意
         * 集合的这些操作在持久层中使用得非常频繁，从数据库中取出的就是多个数据集合，之后我们就可以使用集合
         * 的各种方法构建我们需要的数据了，需要两个集合的and结果，那是交集，需要两个集合的or结果，那是并集，
         * 需要两个集合的not结果，那是差集
         */
    }

    /**
     * 建议77： 使用shuffle打乱列表
     */
    private void suggest77() {
        /**
         * 注意
         * 一般很少用到shuffle这个方法，那它可以用在什么地方呢？
         * 可以用在程序的“伪装”上
         *  比如标签云，或者是游戏中的打怪、修行、群殴时宝物的分配策略
         * 可以用在抽奖程序中
         * 可以用在安全传输方面
         *  比如发送端发送一组数据，先随机打乱顺序，然后加密发送，接收端解密，然后自行排序，即可实现即使是
         *  相同的数据源，也会产生不同密文的效果，加强了数据的安全性
         */
    }

    /**
     * 建议78： 减少HashMap中元素的数量
     */
    private void suggest78() {
        Map<String, String> map = new HashMap<>();
        final Runtime rt = Runtime.getRuntime();
        // JVM终止前记录内存信息
        rt.addShutdownHook(new Thread() {
            @Override
            public void run() {
                StringBuffer sb = new StringBuffer();
                long heapMaxSize = rt.maxMemory() >> 20;
                sb.append("最大可用内存：" + heapMaxSize + "M\n");
                long total = rt.totalMemory() >> 20;
                sb.append("堆内存大小：" + total + "M\n");
                long free = rt.freeMemory() >> 20;
                sb.append("空闲内存：" + free + "M");
                Log.e(TAG, sb.toString());
            }
        });
        // 放入近40万键值对
        for (int i = 0; i < 393217; i++) {
            map.put("key" + i, "value" + i);
        }
        /*
        该程序只是向Map中放入了近40万个键值对（不是整40碗个，而是393217个，为什么呢？），只是增加，没有
        任何其他操作。想想看，会出现什么问题？内存溢出？
        运行发现，内存溢出了！难道是String字符串太多了？不对呀，字符串对象加起来撑死也就10MB。或者是put
        方法有缺陷，产生了内存泄漏？这里还有可用内存，应该要用尽了才会出现内存泄漏啊
        为了更清晰地理解该问题，与ArrayList做一个对比，把相同数据插入到ArrayList中看看会怎样，代码如下：
         */
        List<String> list = new ArrayList<>();
        /*Runtime增加的钩子函数相同*/
        // 放入40万相同字符串
        for (int i = 0; i < 400000; i++) {
            list.add("key" + i);
            list.add("value" + i);
        }
        /*
        ArrayList运行很正常，没有出现内存溢出情况。两个容器，容纳的元素相同，数量相同，ArrayList没有溢出，
        但HashMap却溢出了。很明显，这与HashMap内部的处理机制有极大的关系
        HashMap在底层也是以数组方式保存元素的，其中每一个键值对就是一个元素，也就是说HashMap把键值对封装
        成一个Entry对象，然后再把Entry放到数组中
        回过头来想想，对上面的例子来说，HashMap比ArrayList多了一次封装，把Stirng类型的键值对转换成Entry
        对象后再放入数组，这就多了40万个对象，这应该是问题产生的第一个原因
        HashMap的长度也是可以动态增加的，它的扩容机制与ArrayList稍有不同。在Map的size为393216时，符合了
        扩容条件，于是393216个元素准备开始大搬家，要扩容嘛，那首先要申请一个长度为1048576（当前长度的两倍
        嘛，2的19次方再乘以2，即2的20次方）的数组，但是问题是此时剩余的内存不足以支撑此运算，于是就报内存
        溢出了！这是第二个原因，也是最根本的原因
        综合来说，HashMap比ArrayList多了一个层Entry的底层对象封装，多占用了内存，并且它的扩容策略是2倍长
        度的递增，同时还会依据阈值判断规则进行判断，因此相对于ArrayList来说，它就会先出现内存溢出
         */
        /**
         * 注意
         * 尽量让HashMap中的元素少量并简单
         */
    }

    /**
     * 建议79： 集合中的哈希码不要重复
     */
    private void suggest79() {
        int size = 10000;
        List<String> list = new ArrayList<>(size);
        // 初始化
        for (int i = 0; i < size; i++) {
            list.add("value" + i);
        }
        // 记录开始时间，单位纳秒
        long start = System.nanoTime();
        // 开始查找
        list.contains("value" + (size - 1));
        // 记录结束时间，单位纳秒
        long end = System.nanoTime();
        Log.e(TAG, "list执行时间" + (end - start) + "ns");
        // Map的查找时间
        Map<String, String> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            map.put("key" + i, "value" + i);
        }
        start = System.nanoTime();
        map.containsKey("key" + (size - 1));
        end = System.nanoTime();
        Log.e(TAG, "map执行时间" + (end - start) + "ns");
        /*
        HashMap的效率比ArrayList高很多
        HashMap每次增加元素时都会先计算其哈希吗，然后使用hash方法再次对hashCode进行抽取和统计，同时兼顾哈
        希码的高位和低位信息产生一个唯一值，也就是说hashCode不同，hash方法返回的值也不同。之后再通过indexFor
        方法与数组长度做一次与运算，即可计算出其在数组中的位置，简单地说，hash方法和indexFor方法就是把哈希
        码转变成数组的下标
        顺便说明一下，null值也是可以做为key值的，它的位置永远在Entry数组中的第一位
        关于哈希冲突（两个不同的Entry，可能产生相同的哈希码），HashMap是如何处理这种冲突问题的呢？答案是通
        过链表，每个键值对都是一个Entry，其中每个Entry都有一个next变量，也就是说它会指向下一个键值对。如果
        新加入的键值对的hashCode是唯一的，那直接插入的数组中，它Entry的next值则为null；如果新加入的键值对
        的hashCode与其他元素冲突，则替换掉数组中的当前值，并把新加入的Entry的next变量指向被替换掉的元素--
        于是，一个链表就生成了
        HashMap的存储主线还是数组，遇到哈希冲突的时候则使用链表解决。了解了HashMap是如何存储的，查找也就一
        目了然了：使用hashCode定位元素，若有哈希冲突，则遍历对比，换句话说在没有哈希冲突的情况下，HashMap的
        查找则是依赖hashCode定位的，因为是直接定位，那效率当然就高了
         */
        /**
         * 注意
         * HashMap中的hashCode应该避免冲突
         */
    }

    /**
     * 建议80： 多线程使用vector或hashtable
     */
    private void suggest80() {
        // suggest80 Code1
        testSuggest80Code1(); // 运行，抛出ConcurrentModificationException
        /*
        把ArrayList替换成Vector试试，结果照旧，抛出相同的异常，Vector已经是线程安全的，为什么还报这个错误？
        因为这里混淆了线程安全和同步修改异常，基本上所有的集合类都有一个叫做快速失败（Fail-Fast）的校验机制，
        当一个集合在被多个线程修改并访问时，就可能会出现ConcurrentModificationException异常，这是为了确保
        集合方法一致而设置的保护措施，它的实现原理是我们经常提到的modCount修改计数器：如果在读列表时，
        modCount发生变化（也就是有其他线程修改）则会抛出ConcurrentModificationException异常。这与线程同步
        是两码事，线程同步是为了保护集合中的数据不被脏读、脏写而设置的，来看看线程安全用在什么地方，看Code2
         */
        testSuggest80Code2(); // 运行，会发现有多个线程在卖同一张票
        /*
        注意，上面有两个线程在卖同一张火车票，这才是线程不同步的问题，此时把ArrayList修改为Verctor即
        可解决问题，因为Verctor的每个方法前都加上了synchronized关键字，同时只会允许一个线程进入该方法，
        确保了程序的可靠性
         */
        /**
         * 注意
         * 多线程环境下考虑使用Vector或HashTable
         * 这里说的是真正的多线程，不是并发修改的问题，比如一个线程增加，一个线程删除，这不属于多线程的范畴
         */
    }

    /**
     * 建议81： 非稳定排序推荐使用list
     */
    private void suggest81() {
        //==========1==========//
        SortedSet<Person> set1 = new TreeSet<>();
        // 身高180CM
        set1.add(new Person(180));
        // 身高175CM
        set1.add(new Person(175));

        for(Person p:set1){
            Log.e(TAG, "身高:"+p.getHeight());
        }

        //==========2==========//
        SortedSet<Person> set2 = new TreeSet<Person>();
        // 身高180CM
        set2.add(new Person(180));
        // 身高175CM
        set2.add(new Person(175));
        // 身高最矮的人大变身
        set2.first().setHeight(185);
        for (Person p : set2) {
            Log.e(TAG, "身高:" + p.getHeight());
        }
        /*
        运行发现，竟然没有重新排序
        SortedSet接口（TreeSet实现了该接口）只是定义了在该集合加入元素时将其进行排序，并不能保证元素
        修改后的排序结果，因此TreeSet适用于不变量的集合数据排序，比如String、Integer等类型，但不适合
        用于可变量的排序，特别是不确定何时元素会发生变化的数据集合
         */

        //==========3==========//
        SortedSet<Person> set3 = new TreeSet<Person>();
        // 身高180CM
        set3.add(new Person(180));
        // 身高175CM
        set3.add(new Person(175));
        // 身高最矮的人大变身
        set3.first().setHeight(185);
        //set重排序
        set3 = new TreeSet<Person>(new ArrayList<Person>(set3));
        for (Person p : set3) {
            Log.e(TAG, "身高:" + p.getHeight());
        }
        /*
        上的代码使用Set集合重排序，使数据变化后重新变得有序
         */

        //==========4==========//
        List<Person> list4 = new ArrayList<Person>();
        // 身高180CM
        list4.add(new Person(180));
        // 身高175CM
        list4.add(new Person(175));
        // 身高最矮的人大变身
        list4.get(1).setHeight(185);

        //排序
        Collections.sort(list4);
        for (Person p : list4) {
            Log.e(TAG, "身高:" + p.getHeight());
        }
        /*
        上面代码彻底重构掉TreeSet，使用List，解决数据变化后的排序问题
         */
        /**
         * 注意
         * SortedSet中的元素被修改后可能会影响其排序位置
         */
    }

    /**
     * 建议82： 由点及面，一叶知秋——集合大家族
     */
    private void suggest82() {
        /*
        1）List
        实现List接口的集合主要有：ArrayList、LinkedList、Vector、Stack，其中ArrayList是一个动态
        数组，LinkedList是一个双向链表，Vector是一个线程安全的的动态数组，Stack是一个对象栈（FILO）
        2）Set
        Set是不包含重复元素的集合，其主要的实现类有：EnumSet、HashSet、TreeSet，其中EnumSet是枚举
        类型的专用Set，所有元素都是枚举类型；HashSet是以哈希码决定其元素位置的Set，其原理与HashMap
        相似，它提供快速的插入和查找方法；TreeSet是一个自动排序的Set，它实现了SortedSet接口
        3）Map
        Map是一个大家族，它可以分为排序Map和非排序Map、排序Map主要是TreeMap类，它根据Key值进行自动
        排序；非排序Map主要包括：HashMap、HashTable、Properties、EnumMap等，其中Properties是
        HashTable的子类，它的主要用途是从Property文件中加载数据，并提供方便的读写操作，EnumMap则是
        要求其Key必须是某一个枚举类型
        Map中还有一个WeakHashMap类需要说明，它是一个采用弱键方式实现的Map类，它的特点是：WeakHashMap
        对象的存在并不会阻止垃圾回收器对键值对的回收，也就是说使用WeakHashMap装载数据不用担心内存溢
        出的问题，GC会自动删除不用的键值对，这是好事。但也存在一个严重问题：GC是静悄悄回收的
        4）Queue
        队列，它分为两类，一类是阻塞式队列，队列满了以后再插入元素则会抛出异常，主要包括：
        ArrayBlockingQueue、PriorityBlockingQueue、LinkedBlockingQueue，其中ArrayBlockingQueue
        是一个以数组方式实现的有界阻塞队列，PriorityBlockingQueue是依照优先级组建的队列，
        LinkedBlockingQueue是通过链表实现的阻塞队列；另一类是非阻塞队列，无边界的，只要内存允许，都可
        以持续追加元素
        还有一种队列，是双端队列，支持在头、尾两端插入和移除元素，它的主要实现类是：ArrayDeque、
        LinkedBlickingDeque、LinkedList
        5）数组
        数组与集合的最大区别就是数组能够容纳基本类型，而集合就不行，更重要的一点就是所有的集合底层存储的
        都是数组
        6）工具类
        数组的工具类是java.util.Arrays和java.lang.reflect.Array，集合的工具类是java.util.Collections，
        有了这两个工具类，操作数组和集合会易如反掌，得心应手
        7）扩展类
        可以使用Apache的commons-collections扩展包，也可以使用Google的google-collections扩展包
         */
        /**
         * 注意
         * commons-collections、google-collections是JDK之外的优秀数据集合工具包
         */
    }

    private void testSuggest80Code1() {
        // 火车票列表
        final List<String> tickets = new ArrayList<>();
        // 初始化票据池
        for (int i = 0; i < 100000; i++) {
            tickets.add("火车票" + i);
        }
        // 退票
        Thread returnThread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    tickets.add("车票" + new Random().nextInt());
                }
            }
        };
        // 售票
        Thread saleThread = new Thread() {
            @Override
            public void run() {
                for (String ticket : tickets) {
                    tickets.remove(ticket);
                }
            }
        };
        // 启动退票线程
        returnThread.start();
        // 启动售票线程
        saleThread.start();
    }

    private void testSuggest80Code2() {
        // 火车票列表
        final List<String> tickets = new ArrayList<>();
        // 初始化票据池
        for (int i = 0; i < 100000; i++) {
            tickets.add("火车票" + i);
        }
        // 10个窗口售票
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    while (true) {
                        Log.e(TAG, Thread.currentThread().getName() +
                                ":" + Thread.currentThread().getId() +
                                "----" + tickets.remove(0));
                    }
                }
            }.start();
        }
    }

    static class Person implements Comparable<Person>{
        //身高
        private int height;

        public Person(int _age){
            height = _age;
        }


        public int getHeight() {
            return height;
        }


        public void setHeight(int height) {
            this.height = height;
        }

        //按照身高排序
        public int compareTo(Person o) {
            return height - o.height;
        }

    }
}
