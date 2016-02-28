package com.cheng.improve151suggest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.Arrays;
import com.cheng.highqualitycodestudy.R;
import com.cheng.improve151suggest.adapter.I151SuggestListAdapter;

/**
 第11章 开源世界
 建议139： 大胆采用开源工具
 建议140： 推荐使用guava扩展工具包
 建议141： apache扩展包
 建议142： 推荐使用joda日期时间扩展包
 建议143： 可以选择多种collections扩展
 */
public class I151SChapter11Activity extends AppCompatActivity {
    private static final String TAG = "I151SChapter11Activity";

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
        String[] suggests = getResources().getStringArray(R.array.i151schapter11);
        I151SuggestListAdapter adapter = new I151SuggestListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
    }

    /**
     * 建议139： 大胆采用开源工具
     */
    private void suggest139() {
        /*
        在选择开源工具和框架时要遵循一定的原则：
        -普适性原则
        选择一个工具或框架就必须考虑项目成员的整体技术水平，不能有太大的跨度或跳跃性，要确保大部分项目
        成员对工具都比较熟悉
        -唯一性原则
        相同的工具只选择一个或一种，不要让多种相同或相似职能的工具共存。例如集合工具可以使用Apache Commons
        的collections包，当然也可以使用Google Guava的Collections工具包，但是在项目开发前就应该确认
        下来，不能让两者共存
        -“大树纳凉”原则
        在选择工具包时得寻找比较有名的开源组织，比如Apache、Spring、Google等，这些开源组织一则具有固
        定的开发和运作风格，二则具有广阔的使用人群（很多情况下，我们不会是第一个发现Bug的人），在这样
        大树下，我们才有时间和精力纳凉，而不会把大好的时间消耗在排查Bug上
        -精而专原则
        选择的工具包应该是精而专的，而不是广而多的，比如虽然Spring框架提供了Utils工具包，但是一般情况
        下不要使用它，因为它不专，Utils工具包只是Spring框架中的一个附加功能而已，要用就用Apache Commons
        的BeanUtils、Lang等工具包
        -高热度原则
        一个开源项目的热度越高，更新得越频繁，使用的人群就越广，Bug的曝光率就越快，修复效率也就越高，这
        对我们项目的稳定性来说是非常重要的
         */
        /**
         * 注意
         * 对于开源工具，我们应该大胆采用，仔细筛选，如果确实所有的开源工具都无法满足我们的需求，那就自己
         * 开发一个开源项目，为千千万万的Java人服务，也为Java的生态系统贡献自己的力量
         */
    }

    /**
     * 建议140： 推荐使用Guava扩展工具包
     */
    private void suggest140() {
        /*
        2008年Google发布了Google-collections扩展包，主要是对JDK的Collection包进行了扩展，2010年Google
        发布了Guava项目，其中包含了collections、caching、primitives support、concurrency libraries、
        common annotations、I/O等，这些都是项目编码中的基本工具包，大致浏览下它的主要功能：
        1）Collections
        com.google.common.collect包中主要包括四部分：不可变集合（Immutable Collections）、多值Map、
        Table表和集合工具类
        -不可变集合
        不可变集合包括ImmutableList、ImmutableMap、ImmutableSet、ImmutableSortedMap、ImmutableSortedSet
        等，它比不可修改集合（Unmodifiable Collections）更容易使用，效率更高，而且占用的内存更少。示例：
        // 不可变列表
        ImmutableList<String> list = ImmutableList.of("A", "B", "C");
        // 不可变Map
        ImmutableMap<Integer, String> map = ImmutableMap.of(1, "壹", 2, "贰", 3, "叁");
        其中的of方法有多个重载，其目的就是为了便于在初始化的时候直接生成一个不可变集合
        -多值Map
        多值Map比较简单，在JDK中，Map中的一个键对应一个值，在put一个键值对时，如果键重复了，则会覆盖原
        有值，在大多数情况下这比较符合实际应用，但有的时候确实会存在一个键对应多个值的情况，比如我们的通
        讯录，一个人可能会对应两个或三个号码，此时使用JDK的Map就有点麻烦了。在这种情况下，使用Guava的
        Multimap可以很好地解决问题，代码如下：
        // 多值Map
        Multimap<String, String> phoneBook = ArrayListMultimap.create();
        phoneBook.put("张三", "110");
        phoneBook.put("张三", "119");
        System.out.println(phoneBook.get("张三"));
        -Table表
        在GIS（Geographic Information System，地理信息系统）中，我们经常会把一个地点坐标标注在一个坐
        标上，比如把上海人民广场标注在北纬31.23、东经121.48的位置上，也就是说只要给出准确的经度和纬度就
        可以进行精确的定位--两个键决定一个值，这在Guava中是使用Table来表示的，示例代码如下：
        Table<Double, Double, String> g = HashBasedTable.create();
        // 定义人民广场的经纬坐标
        g.put(31.23, 121.48, "人民广场");
        // 输出坐标点的建筑物
        g.get(31.23, 121.48);
        其实Guava的Table类与我们经常接触的DBRMS表非常类似，可以认为它是一个没有Schema限定的数据表，如：
        // Table，完全类似于数据库表
        Table<Integer, Integer, String> user = HashBasedTable.create();
        // 第一行、第一列的值是张三
        user.put(1, 1, "张三");
        // 第一行、第二列的值是李四
        user.put(1, 2, "李四");
        // 第一行第一列是谁
        user.get(1, 1);
        -集合工具类
        Guava的集合工具类分得比较细，比如Lists、Maps、Sets分别对应的是List、Map、Set的工具类，它们的
        使用方法比较简单，比如Map的过滤，如下所示：
        // 姓名、年龄键值对
        Map<String, Integer> user = new HashMap<String, Ingeger>();
        user.put("张三", 20);
        user.put("李四", 22);
        user.put("王五", 25);
        // 所有年龄大于20岁的人员
        Map<String, Integer> filtedMap = Maps.filterValues(user,
            new Predicate<Integer>() {
                public boolean apply(Integer _age) {
                    return _age>20;
                }
            });
        2）字符串操作
        Guava提供了两个非常好用的字符串操作工具：Joiner连接器和Splitter拆分器。当然字符串的连接和拆分
        使用JDK的方法也可以实现，但是使用Guava更简单一些，比如字符串的连接，代码如下：
        // 定义连接符号
        Joiner joiner = Joiner.on(", ");
        // 可以连接多个对象，不局限于String；如果有null，则跳过
        String str = joiner.skipNulls().join("嘿", "Guava很不错的。");
        Map<String, String> map = new HashMap<String, String>();
        map.put("张三", "普通员工");
        map.put("李四", "领导");
        // 键值之间以“是”连接，多个键值以空格分隔
        System.out.println(Joiner.on("\r\n").withKeyValueSeparator(" 是 ").join(map));
        Joiner不仅能够连接字符串，还能够把Map中的键值对串联起来，比直接输出Map优雅了许多。Splitter是
        做字符拆分的，使用方法也比较简单，示例代码如下：
        String str = "你好，Guava";
        // 以“，”中文逗号分隔
        for (String s : Splitter.on("，").split(str)) {
            System.out.println(s);
        }
        // 按照固定长度分隔
        for (String s : Splitter.fixedLength(2).split(str)) {
            System.out.println(s);
        }
        注意fixedLength方法，它是按照给定长度进行拆分的，比如在进行格式化打印的时候，一行最大可以打印
        120个字符，此时使用该方法就非常简单了
        3）基本类型工具
        基本类型工具在primitives包中，是以基本类型名 +s 的方式命名的，比如Ints是int的工具类，Doubles
        是double的工具类，注意这些都是针对基本类型的，而不是针对包装类型的。代码如下：
        int[] ints = {10, 9, 20, 40, 80};
        // 从数组中取出最大值
        System.out.println(Ints.max(ints));
        List<Integer> integers = new ArrayList<Integer>();
        // 把包装类型的集合转为基本类型数组
        ints = Ints.toArray(integers);
         */
        /**
         * 注意
         * Guava还提供了其他操作（如I/O操作），有兴趣自己研究
         */
    }

    /**
     * 建议141： Apache扩展包
     */
    private void suggest141() {
        /*
        Apache Commons通用扩展包，一般情况下lang包用作JDK的基础语言扩展，Collections用作集合扩展，
        DBCP用作数据库连接池等，下面简要介绍：
        1）Lang
        -字符串操作工具类
        JDK提供的String工具不足以满足开发需求，Lang包弥补了这个缺陷，它提供了诸如StringUtils（基本的
        String操作类）、StringEscapeUtils（String的转义工具）、RandomStringUtils（随机字符串工具）
        等非常实用的工具，示例代码如下：
        // 判断一个字符串是否为空，null或“”都返回true
        StringUtils.isEmpty(str);
        // 释放为数字
        StringUtils.isNumeric(str);
        // 最左边两个字符
        StringUtils.left(str, 2);
        // 统计子字符串出现的次数
        StringUtils.countMatches(str, subString);
        // 转义XML标识
        StringEscapeUtils.escapeXml(str);
        // 随机生成，长度为10的仅字母的字符串
        RandomStringUtils.randomAlphabetic(10);
        // 随机生成，长度为10的ASCII字符串
        RandomStringUtils.randomAscii(10);
        // 以一个单词为操作对象，首字母大写，输出结果为：Abc Bcd
        WordUtils.capitalize("abc bcd");
        -Object工具类
        每个类都有equals、hashCode、toString方法，如果自己编写的类需要覆写这些方法，就需要考虑很多
        的因素了，特别是equals方法，如果使用lang包就会简单得多，示例代码如下：
        class Person {
            private String name;
            private int age;
            // ...
            public boolean equals(Object obj) {
                if (obj == null) return false;
                if (obj == this) return true;
                if (obj.getClass() != getClass()) return false;
                Person p = (Person) obj;
                // 只要姓名相同，就认为两个对象相等
                return new EqualsBuilder()
                        .appendSuper(super.equals(obj))
                        .append(name, p.name)
                        .isEquals();
            }
        }
        // 自定义hashCode
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }
        -可变的基本类型
        基本类型都有相应的包装类型，但是包装类型不能参与加、减、乘、除运算，要运算还是得转化为基本类型，
        那如果希望使用包装类进行运算该怎么办呢？使用Lang包的示例如下：
        // 声明一个可变的int类型
        MutableInt mi = new MutableInt(10);
        // mi加10
        mi.add(10);
        // 自加1
        mi.increment();
        -其他Utils工具
        Lang包在日期处理方面主要提供了DateUtils和DateFormatUtils两个工具类
        Lang包还提供了诸如ArrayUtils、LocaleUtils、NumberUtils等多个工具类
        2）BeanUtils
        它是JavaBean的操作工具包，不仅可以实现属性的拷贝、转换等，还可以建立动态的Bean，甚至建立一些
        自由度非常高的Bean
        -属性拷贝
        在分层开发时经常会遇到PO（Persistence Object）和VO（Value Object）之间的转换问题，不过，有
        多种方法可以解决之，但是最好的办法就是使用BeanUtils来操作，代码如下：
        // PO对象
        User user = new User();
        // VO对象
        Person person = new Person();
        // 两个Bean属性拷贝
        PropertyUtils.copyProperties(person, user);
        // 把Map中的键值对拷贝到Bean上
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "张三");
        PropertyUtils.copyProperties(person, map);
        -动态Bean和自由Bean
        要在运行期生成一个动态Bean，或者在需要生成无固定格式的Bean时，使用普通Bean就无法实现了。可以
        使用BeanUtils包解决该问题，示例代码如下：
        // 动态Bean，首先定义Bean类
        DynaProperty[] props = new DynaProperty[]{
                new DynaProperty("name", String.class),
                new DynaProperty("age", int.class)};
        BasicDynaClass dynaClass = new BasicDynaClass("people", null, props);
        // 动态Bean对象
        DynaBean people = dynaClass.newInstance();
        / people的get/set操作 /
        // 自由Bean
        DynaBean user = new LazyDynaBean();
        // 直接定义属性和值
        user.set("name", "张三");
        // 定义属性名，限定属性类型为Map
        user.set("phoneNum", "tel", "021");
        user.set("phoneNum", "mobile", "138");
        // 属性类型为ArrayList
        user.set("address", 0, "上海");
        user.set("address", 1, "北京");
        -转换器
        如果期望把一个Bean的所有String类型属性在输出之前都加上一个i额前缀，该如何做呢？一个一个进行
        属性过滤？或者使用反射来检查属性类型是否为String，然后加上前缀？这样可以解决，但不优雅，看
        BeanUtils如何解决：
        // 一个简单的Bean对象
        User user = new User("张三", 18);
        // 转换工具
        ConvertUtilsBean cub = new ConvertUtilsBean();
        // 注册一个转换器
        cub.register(new Converter() {
            public Object convert(Class type, Object value) {
                // 为每个String类型的属性加上前缀
                return "prefix=" + value;
            }
        }, String.class);
        // 建立一个依赖特定转换工具的Bean工具类
        BeanUtilsBean beanUtils = new BeanUtilsBean(cub);
        // 输出结果为：prefix-张三
        beanUtils.getProperty(user, "name");
        3）Collections
        Collections工具包提供了ListUtils、MapUtils等基本集合操作工具，介绍3个不太常用的结合对象：
        -Bag
        Bag是Collections中的一种，它可以容纳重复元素，与List的最大不同点是它提供了重复元素的统计功
        能，比如一个盒子中有100个球，现在要计算出蓝色球的数量，使用Bag就很容易实现，代码如下：
        // 一个盒子中装了4个球
        Bag box = new HashBag(Arrays.asList("red", "blue", "black", "blue"));
        // 又增加了3个蓝色球
        box.add("blue", 3);
        // 球的数量为7
        box.size();
        // 蓝色球数量为5
        box.getCount("blue");
        -lazy系列
        “在我需要的时候，你再出现”，lazy系列的集合就是起这样的作用的，在集合中的元素被访问时它才会
        生成，这也就涉及一个元素的生成问题了，可通过Factory的实现类来完成，示例代码如下：
        // 把一个List包装成一个lazy类型
        List<String> lazy = LazyList.decorate(new ArrayList(),
                new Factory() {
                    public String create() {
                        return "A";
                    }
                });
         // 访问了第4个元素，此时0、1、2元素为null
         String obj = lazy.get(3);
         // 追加一个元素
         lazy.add("第五个元素");
         // 元素总数为5个
         lazy.size();
         -双向Map
         JDK中的Map要求键必须唯一，而双向Map则要求键、值都必须唯一，也就是键值是一一对应的，此类
         Map的好处就是即可以根据键进行操作，也可以反向根据值进行操作，比如删除、查询等，代码如下：
         // key、value都不允许重复的Map
         BidiMap bidiMap = new TreeBidiMap();
         bidiMap.put(1, "壹");
         // 根据key获取value
         bidiMap.get(1);
         // 根据value获取key
         bidiMap.getKey("壹");
         // 根据value删除键值对
         bidiMap.removeValue("壹");
         */
        /**
         * 注意
         * Apache commons项目有很多非常好用的工具，可以自己去发掘
         */
    }

    /**
     * 建议142： 推荐使用joda日期时间扩展包
     */
    private void suggest142() {
        /*
        在JDK中，日期时间的操作比较麻烦，例如1000小时后是星期几，伦敦时间是几点等，这里介绍一下通过Joda
        开源包来操作时间的方法，非常简单方便
        1）本地格式的日期时间
        依据操作系统或指定的区域输出日期或时间，例如：
        // 当前时间戳
        DateTime dt = new DateTime();
        // 输出英文星期
        dt.dayOfWeek().getAsText(Locale.ENGLISH);
        // 本地日期格式
        dt.toLocalDate();
        // 日期格式化
        dt.toString(DateTimeFormat.forPattern("yyyy 年 M 月 d 日"));
        2）日期计算
        这是Joda最方便的地方，也是JDK最麻烦的地方，比如要计算100天后是星期几，直接使用JDK提供的日期类
        会非常麻烦，使用Joda就简单很多，例如：
        // 当前时间戳
        DateTime dt = new DateTime();
        // 加100小时是星期几
        dt.plusHours(100).dayOfWeek();
        // 100天后的日期
        dt.plusDays(100).toLocalDate();
        // 10年前的今天是星期几
        dt.minusYears(10).dayOfWeek().getAsText();
        这里需要注意的是，DateTime是一个不可变类型，与String非常类似，即使通过plusXXX、minusXX等方法
        进行操作，dt对象仍然不会变，只是新生成一个DateTime对象而已。但是，Joda也提供了一个可变类型的日
        期对象：MutableDateTime类，这样，日期的加减操作就更加方便了，比如列出10年内的黑色星期五，实现
        代码如下（此实现若用JDK的类来计算会异常复杂）：
        // 当前可变时间
        MutableDateTime mdt = new MutableDateTime();
        // 10年后的日期
        DateTime destDateTime = mdt.plusYears(10);
        while (mdt.isBefore(destDateTime)) {
            // 循环一次加1天
            mdt.addDays(1);
            // 是13号，而且是星期五
            if (mdt.getDayOfMonth()==13 && mdt.getDayOfWeek()==5) {
                // 打印出10年内所有的黑色星期五
                System.out.println("黑色星期五：" + mdt);
            }
        }
        3）时区时间
        这个比较简单实用，给定一个时区或地区代码即可计算出该时区的时间，比如在一个全球系统中，数据库中
        全部是按照标准时间来记录的，但是在展示层要按照不同的用户、不同的时区展现，这就涉及时区计算了，
        代码如下：
        // 当前时间戳
        DateTime dt = new DateTime();
        // 当前伦敦市的时间
        dt.withZone(DateTimeZone.forID("Europe/London"));
        // 计算出标准时间
        dt.withZone(DateTimeZone.UTC);
        Joda还有一个优点，它可以与JDK的日期库方便地进行转换，可以从java.util.Date类型转为Joda的
        DateTime类型，也可以从Joda的DateTime转为java.util.Date，代码如下：
        DateTime dt = new DateTime();
        // Joda的DateTime转为JDK的Date
        Date jdkDate = dt.toDate();
        // JDK的Date转为Joda的DateTime
        dt = new DateTime(jdkDate);
        经过这样的转换，Joda可以很好地与现有的日期保持兼容，在需要复杂的日期计算时使用Joda，在需要与
        其他系统通信或写到持久层中则使用JDK的Date
         */
        /**
         * 注意
         * Joda是一个令人惊奇的高效工具，无论是计算日期、打印日期，或是解析日期，Joda都是首选，当然日
         * 期工具类也可以选择date4j，它也是一个不错的开源工具
         */
    }

    /**
     * 建议143： 可以选择多种collections扩展
     */
    private void suggest143() {
        /*
        再介绍三个比较有个性的Collections扩展工具包
        1）fastutil
        fastutil主要提供了两种功能：一种是限定键值类型（Type Specific）的Map、List、Set等，另一种是
        大容量的集合。先来看示例代码：
        // 明确键类型的Map
        Int2ObjectMap<String> map = new Int2ObjectOpenHashMap<String>();
        map.put(100, "A");
        // 超大容量的List，注意调整JVM的Heap内存
        BigList<String> bigList = new ObjectBigArrayBigList<String>(1L + Integer.MAX_VALUE);
        // 基本类型的集合，不再使用Integer包装类型
        IntArrayList arrayList = new IntArrayList();
        这里要特别注意的是大容量集合，什么叫大容量集合呢？我们知道一个Collection的最大容量是Integer的
        最大值（2 147 483 647），不能超过这个容量，一旦我们需要把一组超大的数据放到集合中，就必须要考
        虑对此进行拆分了，这会导致程序的复杂性提高，而fastutil则提供了Big系列的集合，它的最大容量是Long
        的最大值，这已经是一个非常庞大的数字了，超过这个容量基本上是不可能的。但在使用它的时候需要考虑
        内存溢出的问题，注意调节Java的mx参数配置
        2）Trove
        Trove提供了一个快速、高效、低内存消耗的Collection集合，并且还提供了过滤和拦截的功能，同时还提
        供了基本类型的集合，示例代码如下：
        // 基本类型的集合，不使用包装类型
        TIntList intList = new TIntArrayList();
        // 每个元素值乘以2
        intList.transformValues(new TIntFunction() {
            public int execute(int element) {
                return element * 2;
            }
        });
        // 过滤，把大于200的元素组成一个新的列表
        TIntList t2 = intList.grep(new TIntProcedure() {
            public boolean execute(int _element) {
                return _element > 200;
            }
        });
        // 包装为JDK的List
        List<Integer> list = new TIntListDecorator(intList);
        // 键类型确定Map
        TIntObjectMap<String> map = new TIntObjectHashMap<String>();
        Trove的最大优势是在高性能上，在进行一般的增加、修改、删除操作时，Trove的响应时间比JDK的集合少
        一个数量级，比fastutil也会高很多，因此在高性能项目中要考虑使用Trove
        3）lambdaj
        lambdaj是一个纯净的集合操作工具，它不会提供任何的集合扩展，只会提供对集合的操作，比如查询、过滤、
        统一初始化等，特别是它的查询操作，非常类似与DBRMS上的SQL语句，而且也会提供诸如求和、求平均值的
        方法，示例代码如下：
        List<Integer> ints = new ArrayList<Integer>();
        // 计算平均值
        Lambda.avg(ints);
        // 统计每个元素出现的次数，返回一个Map
        Lambda.count(ints);
        // 按照年龄排序
        List<Person> persons = new ArrayList<Person>();
        Lambda.sort(persons, Lambda.on(Person.class).getAge()));
        // 串联所有元素的指定属性，输出为：张三、李四、王五
        Lambda.joinFrom(persons).getName();
        // 过滤出年龄大于20岁的所有元素，输出为一个子列表
        Lambda.select(persons, new BaseMatcher<Person>() {
            @Override
            public boolean matches(Object _person) {
                Person p = (Person) _person;
                return p.getAge() > 20;
            }
            public void describeTo(Description desc) {
            }
        });
        // 查找出最大年龄
        Lambda.maxFrom(persons).getAge();
        // 抽取出所有姓名形成一个数组
        Lambda.extract(persons, Lambda.on(Person.class).getName()));
         */
        /**
         * 注意
         * Collections的扩展很多，按需选择
         */
    }
}