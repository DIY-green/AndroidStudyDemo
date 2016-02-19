package com.cheng.securitystudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cheng.securitystudy.R;

/**
 * APK保护方法之一防止工具反编译之: 素材中的图片故意破坏
 */
public class UiResourceImageVandalism extends AppCompatActivity {

    /**
     * 素材破坏和包破坏的原理其实差不多，
     * 这种破坏也只是针对视apk为压缩文件的pc来说的。
     * 具体的做法是：在开发工具中(例如：eclipse)在打包前
     * 将jpg格式的图片强行修改成png（由于jpg和png格式图片的识别格式不一样
     * 强行修改后 压缩文件在被解压缩的时候会对任何格式的文件进行验证，
     * 在验证到图片格式的时候 会因为文件类型与格式不一样导致无法反编译）。
     * 这种保护措施不能防止查看主要是防止工具反编译(例如:apktool)，
     * 前提是不会影响其apk在手机上的正常运行。
     * 不过这种保护措施，在最新版本的apktool已经修复了。
     * 在老版本的apktool上面这种保护措施是绝对可行的。
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_resourceimagevandalism);
    }

}
