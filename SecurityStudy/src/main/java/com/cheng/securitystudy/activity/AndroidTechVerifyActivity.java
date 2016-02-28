package com.cheng.securitystudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cheng.securitystudy.R;
import com.cheng.utils.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * APK保护方法之三：运行时验证 – Android技术验证
 */
public class AndroidTechVerifyActivity extends AppCompatActivity {

    /**
     * 运行时验证 – Android技术验证
     运行时验证，主要是指在代码启动的时候本地获取签名信息然后对签名信息进行检验
     来判断自己的应用是否是正版，如果签名信息不是正版则提示盗版或者直接崩溃。
     原理：APK的唯一识别是根据包名+签名，包名信息是写死在AndroidManifest.xml
     里面的，但是签名则是与APK绑定的，一旦APK被反编译后签名会自动消失。
     APK的签名需要签名文件，签名文件的md5值基本上是无法伪造成一样的。
     签名验证的方法也可以细分为3种：
     1) Java 层验证
     获取签名信息和验证的方法都写在android 的java层。这种保护方法保护的意义并
     不大，因为反编译出源码后通过关键字搜索很快就能够找到验证的代码块，稍微一
     修改这验证保护就完全无效了。
     目前市场上使用此方法验证的应用：神庙逃亡2，qq电池管家，微信，360手机管家等
     2) 服务器验证
     在android 的java层获取签名信息，上传服务器在服务端进行签名然后返回验证结果。
     这种保护还不如在纯java层验证有用，一旦没有网络验证保护就无效了。
     用android方法获取的签名信息用java方法也可以获取，验证存放在服务器上也是
     为了把保护正确的签名信息值，但是保护的意义其实没有任何作用。同样破解后
     全局搜索关键字然后伪造一个正确的签名信息就可以完美破解了。
     目前市场上使用此方法验证的应用：地铁跑酷等
     3) NDK技术底层获取签名和验证
     通过把Context,Activity,PackageManager,PackageInfo四个对象中的一个作为
     参数参入底层，在底层获取签名信息并验证。因为获取和验证的方法都封闭在更安全的so库里面，能够起到一定意义上的保护作用。不过通过java层的hook技术一样可以把这种保护完美破解。 但是相比于前两种，此保护的意义和价值就更大了。
     目前市场上使用此方法验证的应用：植物大战僵尸2等
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidtechverify);

        Logger.TAG = "AndroidTechVerifyActivity";
    }

    /**
     * 用crc32对classes.dex文件的完整性进行校验
     * 具体操作步骤：
     * （1）可以打印出来我们的apk生的classes.dex文件的crc32的值
     * （2）运行程序打印结果，可获得当前apk程序的classes.dex的crc32的值
     * （3）将上面程序的classes.dex文件的crc32的值，保存在资源文件字符串中classesdex_crc中
     * （当然也可以保存在服务器上，然后通过网络获取校验），然后再运行上面的apk程序
     * （4）这时我们在上面的代码中随便加一行或者一个空格，然后重新编译运行会看到我们的程序的crc32的值改变了
     */
    public void testClassesIntegrityVerify(View v) {
        String apkPath = getPackageCodePath();
        // 注意：R.string.classesdex_crc的值现在可以是个随机数
        Long dexCrc = Long.parseLong(getString(R.string.classesdex_crc));
        try {
            ZipFile zipFile = new ZipFile(apkPath);
            ZipEntry dexentry = zipFile.getEntry("classes.dex");
            Logger.i("classes.dexcrc="+dexentry.getCrc());
            if (dexentry.getCrc() != dexCrc) {
                Logger.e("Dexhas been modified!");
            } else {
                Logger.e("Dex hasn't been modified!");
            }
        } catch (IOException e) {
            Logger.e(e);
        }
    }

    /**
     * 用哈希值对整个apk完整性进行校验
     * 由于我们要对整个apk的完整性进行校验，所以我们的算出哈希值就不能存在
     * 资源文件中了因为apk中任何的改动都会引起最终apk生成的哈希值的不同
     * 具体步骤：
     * （1）首先实现apk中计算自身哈希值的代码
     * （2）用linux下的sha1sum命令计算我们的apk的哈希值，命令如下：
     *      sha1sum verification.apk
     * （3）将(2)中生成的哈希值存到服务器上，
     * 然后在我们的代码中从服务器获取进行完整性比较。
     */
    public void testAPKIntegrityVerify(View v) {
        String apkPath = getPackageCodePath();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] bytes = new byte[1024];
            int byteCount;
            FileInputStream fis = new FileInputStream(new File(apkPath));
            while ((byteCount=fis.read(bytes)) > 0) {
                messageDigest.update(bytes, 0, byteCount);
            }
            BigInteger bi = new BigInteger(1, messageDigest.digest());
            String sha = bi.toString(16);
            fis.close();
            // TODO 这里添加从服务器中获取哈希值然后进行对比校验

        } catch (Exception e) {
            Logger.e(e);
        }
    }

}
