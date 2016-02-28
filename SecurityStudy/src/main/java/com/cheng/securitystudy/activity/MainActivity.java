package com.cheng.securitystudy.activity;

import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseActivity;
import com.cheng.securitystudy.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toPreventSecondarySignaturePack(View v) {
        overlay(PseudoEncryptionActivity.class);
    }

    public void toPreventDecompile(View v) {
        overlay(PreventDecompileActivity.class);
    }

    public void toCodeSeniorConfusion(View v) {
        overlay(CodeSeniorConfusionActivity.class);
    }

    public void toRuntimeVerfication(View v) {
        overlay(RuntimeVerificationActivity.class);
    }

    public void toPreventMemoryChange(View v) {
        overlay(PreventMemoryChangeActivity.class);
    }

    /**
     * 相关专题帖子
     * android apk 防止反编译技术第一篇-加壳技术
     *      http://my.oschina.net/u/2323218/blog/393372
     * android apk 防止反编译技术第二篇-运行时修改Dalvik指令
     *      http://my.oschina.net/u/2323218/blog/396203
     * android apk 防止反编译技术第三篇-加密
     *      http://my.oschina.net/u/2323218/blog/399326
     * android apk 防止反编译技术第四篇-对抗JD-GUI
     *      http://my.oschina.net/u/2323218/blog/403621
     * android apk 防止反编译技术第五篇-完整性校验
     *      http://my.oschina.net/u/2323218/blog/406860
     */

    /**
     * ANDROID应用与系统安全防御
     * http://www.cnblogs.com/chi0591/p/3864747.html
     * Android应用的安全隐患包括三个方面：代码安全、数据安全和组件安全。
     1. 代码安全
     　　代码安全主要是指Android apk有被篡改、盗版等风险，产生代码安全的主要原因是apk很容易被反编译、重打包。我们可以采用以下方法对apk进行保护：
     1.1 代码混淆
     　　代码混淆可以在一定程度上增加apk逆向分析的难度。Android SDK从2.3开始就加入了ProGuard代码混淆功能，开发者只需进行简单的配置就可以实现对代码的混淆。
     1.2 Apk签名校验
     　　每一个软件在发布时都需要开发人员对其进行签名，而签名使用的密钥文件时开发人员所独有的，破解者通常不可能拥有相同的密钥文件，因此可以使用签名校验的方法保护apk。Android SDK中PackageManager类的getPackageInfo()方法就可以进行软件签名检测。
     1.3 Dex文件校验
     　　重编译apk其实就是重编译了classes.dex文件，重编译后，生成的classes.dex文件的hash值就改变了，因此我们可以通过检测安装后classes.dex文件的hash值来判断apk是否被重打包过。
     　　（1）读取应用安装目录下/data/app/xxx.apk中的classes.dex文件并计算其哈希值，将该值与软件发布时的classes.dex哈希值做比较来判断客户端是否被篡改。
     　　（2）读取应用安装目录下/data/app/xxx.apk中的META-INF目录下的MANIFEST.MF文件，该文件详细记录了apk包中所有文件的哈希值，因此可以读取该文件获取到classes.dex文件对应的哈希值，将该值与软件发布时的classes.dex哈希值做比较就可以判断客户端是否被篡改。
     　　为了防止被破解，软件发布时的classes.dex哈希值应该存放在服务器端。
     　　另外由于逆向c/c++代码要比逆向Java代码困难很多，所以关键代码部位应该使用Native C/C++来编写。
     1.4 逆向工具对抗
     　　对apk进行重打包常用的工具是apktool，apktool对于后缀为PNG的文件，会按照PNG格式进行处理，如果我们将一个非PNG格式文件的文件后缀改为PNG，再使用apktool重打包则会报错。
     　　以上是使用比较多的几种保护方法，单独使用其中一种效果不大，应该综合运用。
     1.5 调试器检测
     　　为了防止apk被动态调试，可以检测是否有调试器连接。在Application类中提供了isDebuggerConnected()方法用于检测是否有调试器连接，如果发现有调试器连接，可以直接退出程序。
     1.6 加壳保护
     　　使用加壳程序防止apk逆向是一种非常有效的方式，也是一个趋势。Jack_Jia在《Android APK加壳技术方案》一文中详细阐述了Android apk加壳原理以及几种加壳方案的具体实现。我们可以利用这几种方案对apk进行加壳。
     　　不过这种加壳方式是在Java层实现的，被反编译的风险仍然很大。为了克服这个缺点，今后可以研究采用如下思路来进行保护：
     　　将核心业务逻辑代码放入加密的.jar或者.apk文件中，在需要调用时使用Native C/C++代码进行解密，同时完成对解密后文件的完整性校验。如果需要更加安全的保护方法，可以考虑对so文件（Native C/C++代码编译得到的文件）进行加壳。Android so加壳主要需要解决两个问题：
     　　（1）对ELF文件加壳；
     　　（2）对Android SO的加载、调用机制做特殊处理。
     　　这将是以后Android应用安全研究的一个方向。
     2. 数据安全
     2.1 存储安全问题
     关于数据存储可能出现的问题包括如下几点：
     （1）明文存储敏感数据，导致直接被攻击者复制或篡改。
     将隐私数据明文保存在外部存储
     将系统数据明文保存在外部存储
     将软件运行时依赖的数据保存在外部存储
     将软件安装包或者二进制代码保存在外部存储
     全局可读写的内部文件存储
     （2）不恰当存储登陆凭证，导致攻击者利用此数据窃取网络账户隐私数据。
     解决方案：
     对这些数据进行加密，密码保存在内部存储，由系统托管或者由用户使用时输入。
     对应用配置文件，较安全的方法是保存到内部存储；如果必须存储到SD卡，则应该在每次使用前检验它是否被篡改，与预先保存在内部的文件哈希值进行比较。
     应用如果需要安装或加载位于SD卡的任何文件，应该先对其完整性做验证，判断其与实现保存在内部存储中的（或从服务器下载来的）哈希值是否一致。
     如果要跨应用进行数据共享，有种较好的方法是实现一个Content Provider 组件，提供数据的读写接口并为读写操作分别设置一个自定义的权限。
     对于登录凭证的存储，使用基于凭据而不是密码的协议满足这种资源持久访问的需求，例如OAuth。
     2.2 传输安全问题
     • 不使用加密传输
     • 使用加密传输但忽略证书验证环节
     　　如开发者在代码中不检查服务器证书的有效性，或选择接受所有的证书时，这种做法可能会导致中间人攻击。
     　　我们在对敏感数据进行传输时应该采用基于SSL/TLS的HTTPS进行传输。由于移动软件大多只和固定的服务器通信，我们可以采用“证书锁定”（certificate pinning）方式在代码更精确地直接验证服务器是否拥有某张特定的证书。
     3. 组件安全
     　　android应用内部的Activity、Service、Broadcast Receiver等组件是通过Intent通信的，组件间需要通信就需要在Androidmanifest.xml文件中配置，不恰当的组件配置则会带来风险。
     可能产生的风险：
     （1）恶意调用
     （2）恶意接受数据
     （3）仿冒应用，例如（恶意钓鱼，启动登录界面）
     （4）恶意发送广播、启动应用服务。
     （5）调用组件，接受组件返回的数据
     （6）拦截有序广播
     解决办法：
     （1）最小化组件暴露
     不参与跨应用调用的组件添加android:exported="false"属性，这个属性说明它是私有的，只有同一个应用程序的组件或带有相同用户ID的应用程序才能启动或绑定该服务。
     （2）设置组件访问权限
     对参与跨应用调用的组件或者公开的广播、服务设置权限。只有具有该权限的组件才能调用这个组件。
     （3）暴露组件的代码检查
     Android 提供各种API来在运行时检查、执行、授予和撤销权限。这些 API 是 android.content.Context 类的一部分，这个类提供有关应用程序环境的全局信息。
     另外，Android应用也会存在很多传统web漏洞，比如SQL注入，xss漏洞等，代码级防止出现这些漏洞的方法与web应用防御方法相同。
     ANDROID系统安全防御
     1. 操作系统安全问题
     Android root问题
     系统漏洞，补丁更新不及时
     认证机制问题
     2. 系统安全解决方案
     2.1 权限管理与隔离
     　　对运行在Android系统上的应用程序进行权限的细粒度管理和隔离，防止越权行为的发生和滥用权限获取敏感数据。
     　　可以采用MAC（Mandatory Access Control）强制访问控制模型实现。它是一个针对Linux的安全加强系统SELinux中使用的安全模型，即任何进程想在SELinux系统中干任何事情，都必须先在安全策略配置文件中赋予权限。凡是没有出现在安全策略配置文件中的权限，进程就没有该权限。Google在Android 4.4上正式推出了一套以SELinux为基础的系统安全机制SEAndroid。所以如果我们要定制一个Android系统，可以采用具有SEAndroid安全机制的Android 4.4版本。
     2.2 内核与应用层漏洞防护
     　　增加补丁更新功能，如果发现漏洞，及时提醒用户进行系统补丁更新。
     2.3 恶意程序检测与防护
     　　建立一套恶意代码防护模型，对运行在Android系统上的恶意程序进行检测，抵御恶意代码的入侵。
     2.4 数据安全存储与传输：
     　　对Android系统上的数据存储和数据传输进行加密保护，保证终端上数据能够安全地使用。
     */

}
