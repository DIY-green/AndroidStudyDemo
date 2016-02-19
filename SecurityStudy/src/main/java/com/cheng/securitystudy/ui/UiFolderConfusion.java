package com.cheng.securitystudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cheng.securitystudy.R;

/**
 * APK保护方法之二：代码高级混淆 – 文件夹混淆
 */
public class UiFolderConfusion extends AppCompatActivity {

    /**
     * 文件夹混淆之一：文件夹后缀追加.2
     文件夹混淆主要指的是 利用windows,linux,android
     三个系统环境下的文件夹名的特殊性来对源码文件夹进行混淆，
     让混淆后的文件夹在window看起来失去原有的逻辑性，
     但是完全不影响其在android系统上的运行。
     原理：在windows和linux下文件夹的名字是不区分大小写的，
     但是在android环境下它却要区分大小写。.2在linux算一个特殊符号，
     所以文件夹名字里面添加的.2会被忽略。
     但是windows下 .2却是一个很普通的字符串。
     方法：反编译开发完成的apk，找到包目录下的最后一层文件夹
     (例如：包名是com.example.test2222,找到test2222所在的文件夹)，
     修改test2222文件夹名字为test2222.2并创建文件夹Test2222
     并随意存放一个有效的smali文件在Test2222里面，然后重新重写打包成apk,签名。

     * 文件夹混淆之二:文件名字混淆
     大家一定知道proguard混淆，会对java的类名进行混淆，
     修改为a,b,c,d等等名字来混淆反编译的阅读，
     但是其混淆的类只能是开发者自己创建的类，对android原始类不能进行混淆。
     原理：proguard混淆为基础，在开发完毕后统一修改自己程序的类名
     (包括主配文件也进行修改)
     这两类保护，不但能够使自己的apk在破解后很难被破解者阅读，
     还能防止一键反编译工具和检测工具，因为自己文件夹.2的方法已经破坏了
     windows下包路径的规律让其不能通过代码去根据主配文件里面的包名去寻找到类。
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_folderconfusion);
    }

}
