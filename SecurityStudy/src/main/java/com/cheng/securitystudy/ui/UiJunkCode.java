package com.cheng.securitystudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cheng.securitystudy.R;

/**
 * APK保护方法之二：代码高级混淆 - 花指令
 */
public class UiJunkCode extends AppCompatActivity {

    /**
     * 花指令是程序中有一些指令，由设计者特别构思，希望使反汇编的时候出错，
     * 让破解者无法清楚正确地反汇编程序的内容，迷失方向。
     * 【花指令】这个词来源于汇编语言，它的思想是非常不错的。
     * 【花指令】另外个目的就是利用反编译工具漏洞，来使工具无法使用。
     * 接下来 我们就在java代码处制造【花指令】，
     * 让反编译工具(jd-gui)无法反编译查询你的java代码。
     * jd-gui的bug其实挺多了。很多特殊代码块或者字段集
     * 都能够让其崩溃无法反编译出源码。
     * 比如：
     private static final char[] wJ = "0123456789abcdef".toCharArray();
     public static String imsi = "204046330839890";
     public static String p = "0";
     public static String keyword = "电话";
     public static String tranlateKeyword = "%E7%94%B5%E8%AF%9D";
     在每个类里面加入 如上字段。。。。 你会发现反编译的类 通过jd-gui查看 后的结果。
     */

    /**
     * 花指令 -- 低版本的jd-gui有效，高版本失效了
     */
    private static final char[] wJ = "0123456789abcdef".toCharArray();
    public static String imsi = "204046330839890";
    public static String p = "0";
    public static String keyword = "电话";
    public static String tranlateKeyword = "%E7%94%B5%E8%AF%9D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_junkcode);
    }

}
