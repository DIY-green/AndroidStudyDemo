package com.cheng.improve151suggest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.Arrays;
import com.cheng.highqualitycodestudy.R;
import com.cheng.improve151suggest.adapter.I151SuggestListAdapter;

/**
 第4章 字符串
 建议52： 推荐使用string直接量赋值
 建议53： 注意方法中传递的参数要求
 建议54： 正确使用string、stringbuffer、stringbuilder
 建议55： 注意字符串的位置
 建议56： 自由选择字符串拼接方法
 建议57： 推荐在复杂字符串操作中使用正则表达式
 建议58： 强烈建议使用utf编码
 建议59： 对字符串排序持一种宽容的心态
 */
public class I151SChapter04Activity extends AppCompatActivity {
    private static final String TAG = "I151SChapter04Activity";

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
        String[] suggests = getResources().getStringArray(R.array.i151schapter4);
        I151SuggestListAdapter adapter = new I151SuggestListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
    }

    /**
     * 建议52： 推荐使用string直接量赋值
     */
    private void suggest52() {
        String str1 = "中国";
        String str2 = "中国";
        String str3 = new String("中国");
        String str4 = str3.intern();
        // 两个直接量是否相等
        boolean b1 = (str1 == str2); // true
        // 直接量和对象是否相等
        boolean b2 = (str1 == str3); // false
        // 经过intern处理后的对象与直接量是否相等
        boolean b3 = (str1 == str4); // true
        /*
        Java为了避免在一个系统中大量产生String对象，于是就设计了一个字符串池（也有叫做字符串常量池，String
        Pool或String Constant Pool或String Literal Pool），在字符串池中所容纳的都是String字符串对象，它
        的创建机制是这样的：创建一个字符串时，首先检查池中是否有字面值相等的字符串，如果有，则不再创建，直接
        返回池中该对象的引用，若没有则创建之，然后放到池中，并返回新建对象的引用。直接声明一个String对象是不
        检查字符串池的，也不会把对象放到池中。那为什么使用intern方法处理后就又相等了呢？因为intern会检查当
        前的对象在对象池中是否有字面值相同的引用对象，如果有则返回池中对象，如果没有则放到对象池中，并返回当
        前对象
        对象放到池中会不会产生线程安全问题呢？Java已经考虑到了，String类是一个不可变（Immutable）对象其实有
        两层意思：一是String类是final类，不可继承，不可能产生一个String的子类；二是在String类提供的所有方法
        中，如果有String返回值，就会新建一个String对象，不对原对象进行修改，这也就保证了原对象是不可改变的
        放到池中，是不是要考虑垃圾回收问题呀？不用考虑了，虽然Java的每个对象都保存在堆内存中，但是字符串池非
        常特殊，它在编译期已经决定了其存在JVM的常量池（Constant Pool），垃圾回收器是不会对它进行回收的
         */
        /**
         * 注意
         * 建议在开发中使用直接量赋值方式，除非确有必要才新建立一个String对象
         */
    }

    /**
     * 建议53： 注意方法中传递的参数要求
     */
    private void suggest53() {
        Log.e(TAG, "好是好".replaceAll("好", "").equals("是") + "");   // true
        Log.e(TAG, "$是$".replaceAll("$", "").equals("是") + "");     // false
        /**
         * 注意
         * replaceAll传递的第一个参数是正则表达式，符合正则表达式的字符串才会被替换
         * 普通的正则表达式需要很熟练
         */
    }

    /**
     * 建议54： 正确使用string、stringbuffer、stringbuilder
     */
    private void suggest54() {
        /**
         * 注意
         * 在不同的场景下使用不同的字符序列
         * 1）使用String类的场景
         * 在字符串不经常变化的场景中可以使用String类，例如常量的声明、少量的变量运算等
         * 2）使用StringBuffer类的场景
         * 在频繁进行字符串的运算（如拼接、替换、删除等），并且运行在多线程的环境中，可以考虑使用
         * StringBuffer，例如XML解析、HTTP参数解析和封装等
         * 3）使用StringBuilder类的场景
         * 在频繁进行字符串的运算（如拼接、替换、删除等），并且运行在单线程的环境中，则可以考虑使用
         * StringBuilder，如SQL语句的拼装、JSON封装等
         */
    }

    /**
     * 建议55： 注意字符串的位置
     */
    private void suggest55() {
        String str1 = 1 + 2 + "apples";
        String str2 = "apples" + 1 + 2;
        Log.e(TAG, str1); // 3 apples
        Log.e(TAG, str2); // apples 12
        /**
         * 注意
         * 在“+”表达式中，String字符串具有最高优先级
         */
    }

    /**
     * 建议56： 自由选择字符串拼接方法
     */
    private void suggest56() {
        int cycles = 50000;
        String str1 = "";
        Log.e(TAG, "Test '+' start:" + System.currentTimeMillis());
        for (int i = 0; i < cycles; i++) {
            str1 += "c";
        }
        Log.e(TAG, "Test '+' end:" + System.currentTimeMillis());

        String str2 = "";
        Log.e(TAG, "Test 'concat' start:" + System.currentTimeMillis());
        for (int i = 0; i < cycles; i++) {
            str2 = str2.concat("c");
        }
        Log.e(TAG, "Test 'concat' end:" + System.currentTimeMillis());

        Log.e(TAG, "Test 'apend' start:" + System.currentTimeMillis());
        StringBuilder str3 = new StringBuilder("");
        for (int i = 0; i < cycles; i++) {
            str3.append("c");
        }
        Log.e(TAG, "Test 'apend' end:" + System.currentTimeMillis());

        /*
        对一个字符串进行拼接有三种方法：加号、concat方法及StringBuilder/StringBuffer的append方法，这
        三种方式有什么区别？
        上面的实验说明在字符串拼接方式中，append方式最快，concat方法次之，加号最慢
        1）“+”方法拼接字符串
        虽然编译器对字符串的加号做了优化，它会使用StringBuilder的append方式进行追加，按道理来说，其执行
        时间应该和使用StringBuilder的append类似，不过它最终是通过toString方法转换成String字符串的，例子
        中“+”拼接的代码与如下代码相同：
        str = new StringBuilder(str).append("c").toString();
        注意看，它与纯粹使用StringBuilder的append方法是不同的：一是每次循环都会创建一个StringBuilder对象，
        二是每次执行完毕都要调用toString方法将其转换为字符串--它的执行时间就是耗费在这里了
        2）concat方法拼接字符串
        其整体看上去就是一个数组拷贝，虽然在内存中的处理都是原子性操作，速度非常快，不过，注意看最后的return
        语句，每次的concat操作都会新创建一个String对象，这就是concat速度慢下来的真正原因
        3）append方法拼接字符串
        整个append方法都在做字符数组处理，加长，然后数组拷贝，这些都是基本数据处理，没有新建任何对象，所以
        速度也就最快了
         */
        /**
         * 注意
         * 适当的场景使用适当的字符串拼接方式
         * 在大多数情况下都可以使用加号操作，只有在系统性能临界的时候才可以考虑使用concat或append方法
         */
    }

    /**
     * 建议57： 推荐在复杂字符串操作中使用正则表达式
     */
    private void suggest57() {
        /**
         * 注意
         * 正则表达式是恶魔，威力巨大，但难以控制
         */
    }

    /**
     * 建议58： 强烈建议使用utf编码
     */
    private void suggest58() {
        /**
         * 注意
         * 一个系统使用统一的编码（都统一为UTF8最好）
         */
    }

    /**
     * 建议59： 对字符串排序持一种宽容的心态
     */
    private void suggest59() {
        /**
         * 注意
         * 如果排序对象是经常使用的汉字，使用Collator类排序完全可以满足要求，毕竟GB2312已经包含了大部分
         * 的汉字，如果需要严格排序，则要使用一些开源项目来实现，比如pinyin4j可以把汉字转换为拼音，然后
         * 自己来实现排序算法
         */
    }

}
