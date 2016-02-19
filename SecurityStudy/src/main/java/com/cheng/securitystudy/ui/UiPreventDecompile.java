package com.cheng.securitystudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cheng.base.BaseUi;
import com.cheng.securitystudy.R;

/**
 * APK保护方法之一防止反编译
 */
public class UiPreventDecompile extends BaseUi {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_preventdecompile);
    }

    public void toPseudoEncryption(View v) {
        overlay(UiPseudoEncryption.class);
    }

    public void toDestoryAPKPackage(View v) {
        overlay(UiDestoryAPKPackage.class);
    }

    public void toResourceImageVandalism(View v) {
        overlay(UiResourceImageVandalism.class);
    }

    /**
     * 参考：
     * http://blog.csdn.net/tiandyoin/article/details/24346167
        方法一：
        将核心数据通过服务器存储，客户端每次使用时将核心数据读下来才能用，否则会出错。
        这个核心数据每一定周期（比如一个月）改变一次加密解密算法，客户端当无法处理算法时，进行apk更新，也就是每月更新一次apk。
        方法二：
        关于图片处理的问题，例如Logo这类的小图不要以文件方式存储，可以存储到数据库中，或者将Logo以编码方式存于静态对像中；
        方法三：
        将核心代码存于服务器上，每次运行时读取服务器上的代码加密字符串，解密后，通过eval方式转为可执行代码。
        通过以上多种方法处理再proguard后，即使反编译回去，也不会完整，不可能随便改Logo处理。
     */

}
