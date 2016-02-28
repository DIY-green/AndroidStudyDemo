package com.cheng.securitystudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cheng.securitystudy.R;

/**
 * APK保护方法之一防止工具反编译之: APK包破坏
 */
public class DestoryAPKPackageActivity extends AppCompatActivity {

    /**
     *
     APK保护方法之一：防止工具反编译之APK包破坏
     APK在PC上面也可以看作一个压缩文件，在Android系统里面
     它是一个手机系统软件文件。Android系统对APK的识别是
     从标志头到标志尾，其他多余数据都会无视。所以说在标志尾
     添加其他数据对把APK看做压缩文件的PC端来说这个文件被破坏了，
     所以你要对其进行解压或者查看都会提示文件已损坏，
     用反编译工具也会提示文件已损坏，但是它却不会影响在Android系统
     里面的正常运行和安装而且也能兼容到所有系统。
     但是这种APK压缩包破坏存在个别市场会不能识别导致不能上传市场。
     使用压缩文件修复工具也能把它修复好让我们做的保护消失。
     示例下载地址：http://dl1.zywa.com.cn/ijiami/apkbus/2.zip
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destoryapkpackage);

        // PackageDestroyUtil 包破坏
    }


}
