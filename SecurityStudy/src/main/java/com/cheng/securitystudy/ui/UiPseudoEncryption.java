package com.cheng.securitystudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cheng.securitystudy.R;

/**
 APK保护方法之一防止工具反编译之：防止工具反编译之伪加密
 */
public class UiPseudoEncryption extends AppCompatActivity {

    /**
     伪加密是Android4.2.x系统发布前最流行的加密方式之一，
     通过java代码对APK(压缩文件)进行伪加密，其修改原理是
     修改连续4位字节标记为”P K 01 02”的后第5位字节，奇数
     表示不加密偶数表示加密。伪加密后的APK不但可以防止PC端
     对它的解压和查看也同样能防止反编译工具编译。
     但是伪加密对其APK加密后市场也无法对其进行安全检测，
     部分市场会拒绝这类APK上传市场。伪加密的加密方式和
     解密方式也早已公布导致它的安全程度也大大降低。
     Android4.2+系统由于修改了签名验证的方式导致无法安装伪加密的APK。
     示例下载地址：http://dl1.zywa.com.cn/ijiami/apkbus/1.zip
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_pseudoencryption);

        // PseudoEncryptionUtil 伪加密
    }

    /**
     * 参考
     * http://blog.csdn.net/tiandyoin/article/details/24537921
     * http://bbs.csdn.net/topics/390625266
     * http://www.apkbus.com/android-145245-3-1.html
     */

}
