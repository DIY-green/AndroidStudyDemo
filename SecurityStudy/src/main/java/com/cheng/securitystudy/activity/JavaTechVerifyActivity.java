package com.cheng.securitystudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cheng.securitystudy.R;

/**
 * APK保护方法之三：运行时验证 – java技术验证
 */
public class JavaTechVerifyActivity extends AppCompatActivity {

    /**
     * 运行时验证 – java技术验证
     同样是获取签名信息进行验证，只是不是通过Android的方法，
     相比Android获取签名的方法用java获取签名更有意义，
     因为关键字搜索很容易就将Android获取签名的代码找到。
     原理：通过Context的getPackageCodePath()和ApplicationInfo的sourceDir
     的方法获取apk本地储存地址，然后通过java获取签名的方法获取签名信息并验证。
     此验证方法一般是通过ndk技术底层验证，此方法在和Android技术验证方式原理
     差别不大，不过效果会更好，安全作用更高。不过依旧有破解的方法，无法保证安全。
     目前市场上使用此方法验证的应用：捕鱼达人2
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javatechverify);
    }

}
