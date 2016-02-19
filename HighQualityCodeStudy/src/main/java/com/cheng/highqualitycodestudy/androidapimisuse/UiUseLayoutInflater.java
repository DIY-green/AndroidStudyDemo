package com.cheng.highqualitycodestudy.androidapimisuse;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cheng.highqualitycodestudy.R;

public class UiUseLayoutInflater extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_uselayoutinflater);
    }

    /**
     * 参考：
     * http://blog.jobbole.com/72156/
     */
    /**
     * LayoutInflater常见使用误区
     */
    private void badCodeDemo() {
        // ListView getView(int position, View convertView, ViewGroup parent)
        // Fragment onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        LayoutInflater inflater = null;
        View tempView = inflater.inflate(R.layout.z_temp, null);
    }

    /**
     * 获得 LayoutInflater 实例的三种方式:
     * 1.LayoutInflater inflater = getLayoutInflater();  //调用Activity的getLayoutInflater()
     * 2.LayoutInflater localinflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     * 3.LayoutInflater inflater = LayoutInflater.from(context);
     * 其实，这三种方式本质是相同的：
     * 这三种方式最终本质是都是调用的Context.getSystemService()
     *
     * 认识LayoutInflater
     * 首先看一下LayoutInflater的工作原理，有两个重载的版本可以使用：
     *      inflate(int resource, ViewGroup root)
     *      inflate(int resource, ViewGroup root, boolean attachToRoot)
     * 第一个参数指出要载入的布局文件资源，第二个参数指出视图结构中载入的布局将要放入的根视图。
     * 如果有第三个参数，那么它用来决定是否把载入后的视图绑定到给出的根视图中。
     * 最后两个参数可能会导致一些问题。如果使用两个参数的版本，Layoutinflater会自动尝试
     * 把载入的视图绑定到给定的根视图对象中。但是，如果你传递null，系统就不会尝试绑定操作了，
     * 否则应用程序就崩溃了。
     * 很多开发者会这样做，认为传递null作为根视图就可以禁用绑定操作了。很多时候很多开发者
     * 甚至不知道还有三个参数的Layoutinflater版本的存在，如果这么做的话，也会同时禁用了
     * 根视图的一个很重要的函数……。
     *
     * =====================================================================================
     * 参考自Hongyang的Blog
     * http://blog.csdn.net/lmj623565791/article/details/38171465
     * Inflate(resId , null ) 只创建temp ,返回temp
     * Inflate(resId , parent, false )创建temp，然后执行temp.setLayoutParams(params);返回temp
     * Inflate(resId , parent, true ) 创建temp，然后执行root.addView(temp, params);最后返回root
     * =====================================================================================
     *
     * 框架中的示例
     * 来仔细看看Android框架关于动态载入布局的场景
     * Adapter是最常用的场景，我们经常需要使用LayoutInflater来自定义ListView（通过重写getView()方法），
     * 具体的方法签名是这样的：
     * getView(int position, View convertView, ViewGroup parent)
     * Fragment也会用到inflation操作，通过onCreateView()方法创建view的时候会用到。这个方法的签名是这样的：
     * onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
     * 不知你有没有注意到这一点，每次Framework需要你去载入一个布局文件时，都会传入一个ViewGroup参数
     * （最后需要绑定到的根视图），如果Layoutinflater设为自动绑定到根视图的话，会抛出一个异常。
     * 所以你想想看，如果我做绑定操作的话，为什么要给你一个ViewGroup参数呢？事实证明父视图在这个inflation
     * 操作过程中是很重要的，它会计算被载入的XML在根元素中的LayoutParams，如果传入null话，就等于是告诉框架
     * “我不知道载入的View要放到哪个父视图中”。
     * 问题在于，android:layout_xxx属性会在父视图对象中被重新计算，结果就是所有你定义的LayoutParams都会被
     * 忽略掉（因为没有已知的父视图对象）。然后你就纳闷“为什么框架会忽略掉我自己定义的布局属性呢？还是去
     * StackOverFlow上看看，提一个bug吧”。
     * 如果没有设置LayoutParams，那么最终ViewGroup也会给你生成一个默认的属性，幸运的话（很多时候），
     * 这些默认的设置正好和你在XML文件中定义的一样……所以你就察觉不到其实已经出现问题了。
     *
     * 应用案例
     * 你敢说你没有在应用中碰到过这样的场景吗？看看下面的代码，为Listview简单地载入一个布局文件：
     * R.layout.item_row:
     * <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
         android:layout_width="match_parent"
         android:layout_height="?android:attr/listPreferredItemHeight"
         android:gravity="center_vertical"
         android:orientation="horizontal">
         <TextView
             android:id="@+id/text1"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:paddingRight="15dp"
             android:text="Text1" />
         <TextView
             android:id="@+id/text2"
             android:layout_width="0dp"
             android:layout_height="wrap_content"
             android:layout_weight="1"
             android:text="Text2" />
       </LinearLayout>
     * 这里我们想把高度设置为固定高度，上面把它设为当前主题下的推荐高度……看似很合理。
     * public View getView(int position, View convertView, ViewGroup parent) {
           if (convertView == null) {
                convertView = inflate(R.layout.item_row, null);
           }
           return convertView;
       }
     * 你会发现为什么设定的固定高度不起作用？这是因为你没有把所有子View的高都设为固定高度，
     * 只需要把根视图的高设置成wrap_content就可以了。
     * 而如果这样载入布局的话就没有问题：
     * public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflate(R.layout.item_row, parent, false);
            }
            return convertView;
       }
     * 这样我们就得到了想要的结果：Item的高度为设置的固定高度
     *
     * 任何规则都有例外
     * 当然，也有需要在载入布局的时候指定null作为父布局对象，但这种情况非常少。一个典型的例子
     * 就是为AlertDialog中载入一个自定义布局。看看下面的例子，使用和上面一样的XML布局文件来
     * 作为对话框的布局：
     * AlertDialog.Builder builder = new AlertDialog.Builder(context);
       View content = LayoutInflater.from(context).inflate(R.layout.item_row, null);
       builder.setTitle("My Dialog");
       builder.setView(content);
       builder.setPositiveButton("OK", null);
       builder.show();
     * 这里的问题就是，AlertDialog.Builder支持自定义布局，但是setView()方法不提供带有布局文件
     * 作为参数的版本，所以只能先手动载入XML布局文件。由于最终会进入到对话框里面，不会接触到根布局
     * （事实上这时候还没有根布局），所以我们也操作不了布局文件的最终父视图对象，当然也就不能用于
     * 载入使用了。事实证明，这些都是无关紧要的，因为AlertDialog会擦除布局上的所有Layoutparams
     * 然后替换为match_parent。
     * 所以，下次使用inflate()函数时，如果还想输入null应该停下来想一想“我真的不知道它该放到哪里吗？”
     * 最后，你应该想想两个参数的inflate()版本作为一个便捷的使用方式，可以忽略第三个参数（默认为true），
     * 但是不要想着为了方便而传递一个null却忽略了第三个参数会默认是false。
     */

    private void goodCodeDemo() {
        // ListView getView(int position, View convertView, ViewGroup parent)
        // Fragment onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        LayoutInflater inflater = null;
        ViewGroup viewGroup = null;
        // 最后一个参数false必须
        View tempView = inflater.inflate(R.layout.z_temp, viewGroup, false);

        // 在AlertDialog中使用
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View content = LayoutInflater.from(this).inflate(R.layout.z_temp, null);
        builder.setTitle("My Dialog");
        builder.setView(content);
        builder.setPositiveButton("OK", null);
        builder.show();
    }
    // 自定义View
    private class CustomView extends LinearLayout {

        public CustomView(Context context) {
            super(context);
            // 注意，最后一个参数千万不能写null
            LinearLayout rootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.z_temp, this);
        }
    }

}
