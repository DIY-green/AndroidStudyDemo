所有的工具类简介 (a - z):

AnimationUtils Animation工具类
AppUtils APP相关信息工具类
AssetDatabaseOpenHelper 读取Asset目录中数据库工具类
BitmapUtil Bitmap工具类主要包括获取Bitmap和对Bitmap的操作
CipherUtils 加密与解密的工具类
Colors 常用颜色色值工具类
CommonUtil 一些通用的方法
ChannelUtil 为打包而生的渠道工具类 极速打包传送门
DataCleanManager 应用数据清除类，主要功能有清除内/外缓存，清除数据库，清除sharedPreference，清除files和清除自定义目录
DatabaseExportUtils 导出应用数据库工具类
DateUtils 日期工具类
DeviceStatusUtils 手机状态工具类 主要包括网络、蓝牙、屏幕亮度、飞行模式、音量等
DisplayUtils 系统显示相关工具类（包括键盘操作）
DoubleKeyValueMap 双键值对
DownloadManagerPro 下载管理工具类
FileUtils 文件操作工具类
HanziToPinyin 汉字转拼音工具类
ImsiUtil IMSI工具类
JSONUtils Json解析工具类
LocationUtils 根据经纬度查询地址信息和根据地址信息查询经纬度
LogUtils Log工具类。课参考博文:Android Log工具类。
NetUtil 网络工具类
PackageUtils 应用安装下载相关
PhoneUtil 手机组件调用工具类
PollingUtils 轮询服务工具类
PreferencesCookieStore Cookie存储工具类
RUtils R反射资源ID工具类
RandomUtils 随机工具类
RegUtils 数据校验工具类
ResourceUtils 文件资源读取工具类
SDCardUtils SDcard操作工具类
SettingUtils 应用配置工具类
ShellUtils shell工具类
ShortCutUtils 快捷方式工具类
Singleton 单例模式抽象类
StringUtils 字符串操作工具包。字符串其他操作可以使用TextUtils类。
ViewAnimationUtils 视图动画工具箱，提供简单的控制视图的动画的工具方法
ViewUtils View相关工具类
ViewFinder findViewById替代工具类
WindowUtils 窗口工具类
BaseApplication 应用Application此处主要是为了错误处理。
BaseCrashHandler 在Application中统一捕获异常，保存到文件中下次再打开时上传
RebootThreadExceptionHandler 重启线程异常处理器，当发生未知异常时会提示异常信息并在一秒钟后重新启动应用。
StartAppReceiver 重启应用广播接收器。
需要权限 (Permission)

<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
<uses-permission android:name="android.permission.BLUETOOTH" />
配置 (Configuration)

<application
        android:name="com.ihongqiqu.app.BaseApplication" >
发布正式版本注释Log只需要设置 LogUtils.DEBUG_LEVEL = Log.ASSERT 。

混淆 (Proguard)

代码混淆只需要在Proguard规则文件中添加如下代码即可(Eclipse下为proguard.cfg文件)：

-keep class com.ihongqiqu.** { *; }
-keepclassmembers class com.ihongqiqu.** { *; }
-dontwarn com.ihongqiqu.**