package com.cheng.utils.exception;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;

import com.cheng.utils.C;
import com.cheng.utils.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

/**
 * 全局异常处理类
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";
    public static final String kEXCEPTION = C.Intent.kEXCEPTION;
    private static final String kVERSION_NAME = "versionName";
    private static final String kVERSION_CODE = "versionCode";
    private static final String kSTACK_TRACE = "STACK_TRACE";
    // 错误报告文件的扩展名
    private static final String CRASH_REPORTER_EXTENSION = ".cr";

    /** 是否开启日志输出, 在Debug状态下开启, 在Release状态下关闭以提升程序性能 */
    public static final boolean DEBUG = true;

    // 程序的Context对象
    private Context mContext;
    // 用于显示异常信息的Activity
    private Class<Activity> mUiCrashClazz;
    // 指定重启App时跳转到的Activity
    private Class<Activity> mUiMainClazz = null;
    // CrashHandler实例
    private static CrashHandler sInstance = new CrashHandler();
    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    // 用来存储设备信息和异常信息
    private Map<String, String> mInfos = new HashMap<>();
    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    // 使用Properties来保存设备的信息和错误堆栈信息
    private Properties mDeviceCrashInfo = new Properties();

    /** 保证只有一个CrashHandler实例 */
    private CrashHandler() {
        Logger.TAG = TAG;
    }

    /** 获取CrashHandler实例 ,单例模式 */
    public static CrashHandler getInstance() {
        return sInstance;
    }

    /**
     * 初始化
     * @param context 用于跳转UiCrash或者使用对话框显示异常信息
     */
    public void init(Context context) {
        this.mContext = context;
        // 获取系统默认的UncaughtException处理器
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 初始化
     * 如果调用该初始化方法将会跳转到指定Activity
     * @param context
     * @param crashTargetActivity  用于显示崩溃信息的目标Activity
     */
    public void init(Context context, Class<Activity> crashTargetActivity) {
        this.mContext = context;
        this.mUiCrashClazz = crashTargetActivity;
        // 获取系统默认的UncaughtException处理器
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void setMainActivity(Class<Activity> mainActivity) {
        this.mUiMainClazz = mainActivity;
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (mDefaultHandler == null) return;
        if (!handleException(ex)) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Logger.e("error : ", e);
            }
            if (mUiMainClazz == null) return;
            Intent intent = new Intent(mContext.getApplicationContext(), mUiMainClazz);
            PendingIntent restartIntent = PendingIntent.getActivity(
                    mContext.getApplicationContext(),
                    0,
                    intent,
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            // 退出程序
            AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
            // 1秒后重启应用
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis()+1000, restartIntent);
            // TODO 调用自定义Application中的关闭所有Activity的方法
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理
     * 会根据调用者调用的情况使用
     * 1. 跳转到Activity显示异常信息
     * 2. 使用AlertDialog显示异常信息
     * 3. 收集错误信息 发送错误报告
     * @param ex
     * @return
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) return false;
        if (mContext == null) return false;
        ex.printStackTrace();
        // 拼接异常堆栈信息
        StringBuilder exceptionSB = new StringBuilder();
        exceptionSB.append(
                "API Level: " +
                        android.os.Build.VERSION.SDK_INT +
                        "\n");
        exceptionSB.append(
                "Android: " +
                        android.os.Build.VERSION.RELEASE +
                        " (" +
                        android.os.Build.MODEL +
                        ")\n\n\n");
        exceptionSB.append("异常信息: \n");
        exceptionSB.append("Exception: " + ex.getMessage() + "\n\n\n");
        exceptionSB.append("堆栈信息: \n");
        StackTraceElement[] elements = ex.getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            exceptionSB.append(elements[i].toString() + "\n");
        }
        // TODO 处理异常信息
        if (mUiCrashClazz != null)
        // 1. 跳转到统一的Activity，显示异常堆栈信息
        showExceptionInActivity(exceptionSB.toString());
        // 2. 使用对话框显示异常堆栈信息
        showExceptionByDialog(exceptionSB.toString());
        // 3. 保存为本地文件，按照一定策略上报异常
        uploadCrashException(ex);
        return true;
    }

    /**
     * 在用户自定义Activity中显示异常信息
     * @param exceptionStr
     */
    private void showExceptionInActivity(String exceptionStr) {
        try {
            // TODO 使用自定义的展示异常堆栈信息的Activity
            // 具体Activity调用者自己实现
            Intent intent = new Intent(mContext, mUiCrashClazz);
            intent.putExtra(kEXCEPTION, exceptionStr);
            if (!(mContext instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            mContext.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            Logger.e(e);
        }
    }

    /**
     * 使用Dialog显示异常信息
     * @param exceptionStr
     */
    private void showExceptionByDialog(final String exceptionStr) {
        if (!(mContext instanceof Activity)) return;
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setMessage(exceptionStr)
                        .setCancelable(true)
                        .setNeutralButton("我知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                                // 杀死线程，退出应用。
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        }).create().show();
                Looper.loop();
            }
        }.start();
    }

    /**
     * 上传异常信息
     * @param ex
     */
    private void uploadCrashException(Throwable ex) {
        // 收集设备参数信息
        collectCrashDeviceInfo(mContext);
        // 保存日志文件
        saveCrashInfoToFile(ex);
        // 发送错误报告到服务器
        sendCrashReportsToServer(mContext);
    }

    /**
     * 收集设备参数信息
     * @param context
     */
    private void collectCrashDeviceInfo(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            if (pi != null) {
                String versionName = pi.versionName==null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                // 1.使用Map保存
                mInfos.put(kVERSION_NAME, versionName);
                mInfos.put(kVERSION_CODE, versionCode);
                // 2.使用Property保存
                mDeviceCrashInfo.put(kVERSION_NAME, versionName);
                mDeviceCrashInfo.put(kVERSION_CODE, versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                // 1.使用Map保存
                mInfos.put(field.getName(), field.get(null).toString());
                // 2.使用Property保存
                mDeviceCrashInfo.put(field.getName(), field.get(null).toString());
                if (DEBUG) {
                    Logger.d(field.getName() + " : " + field.get(null));
                }
            } catch (Exception e) {
                Logger.e(e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     * @param ex
     * @return  返回文件名称,便于将文件传送到服务器
     */
    private String saveCrashInfoToFile(Throwable ex) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : mInfos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        // toString() 以字符串的形式返回该缓冲区的当前值
        String result = writer.toString();
        printWriter.close();
        sb.append(result);
        mDeviceCrashInfo.put(kSTACK_TRACE, result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = mDateFormat.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory()
                        +"/crash/"; // TODO 保存到SD卡的crash目录下，这里需要优化
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            } else {
                // 保存文件
                FileOutputStream trace = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
                mDeviceCrashInfo.store(trace, "");
                trace.flush();;
                trace.close();
            }
            return fileName;
        } catch (Exception e) {
            Logger.e(e);
        }
        return null;
    }

    /**
     * 把错误报告发送给服务器,包含新产生的和以前没发送的.
     * @param context
     */
    private void sendCrashReportsToServer(Context context) {
        String [] crashFiles = getCrashReportFiles(context);
        if (crashFiles!=null && crashFiles.length>0) {
            TreeSet<String> sortedFiles = new TreeSet<>();
            sortedFiles.addAll(Arrays.asList(crashFiles));
            for (String fileName : sortedFiles) {
                // TODO 注意文件的保存路径可能在SD卡和data目录下，这里未处理SD卡
                File crashFile = new File(context.getFilesDir(), fileName);
                postReport(crashFile);
                crashFile.delete();// 删除已发送的报告
            }
        }
    }

    /**
     * 获取错误报告文件名
     * @param context
     * @return
     */
    private String[] getCrashReportFiles(Context context) {
        File filesDir = context.getFilesDir();
        // 实现FilenameFilter接口的类实例可用于过滤器文件名
        FilenameFilter filter = new FilenameFilter() {
            // accept(File dir, String name)
            // 测试指定文件是否应该包含在某一文件列表中。
            public boolean accept(File dir, String name) {
                return name.endsWith(CRASH_REPORTER_EXTENSION);
            }
        };
        // list(FilenameFilter filter)
        // 返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中满足指定过滤器的文件和目录
        return filesDir.list(filter);
    }

    private void postReport(File file) {
        // TODO 使用HTTP Post 发送错误报告到服务器
    }

    /**
     * 在程序启动时候, 可以调用该函数来发送以前没有发送的报告
     */
    public void sendPreviousReportsToServer() {
        sendCrashReportsToServer(mContext);
    }

    /**
     * 调用方式
     * 1. 添加权限
     * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     * 2. 在自定义Application中（非必须）
     *    在AndroidManifest.xml中注册自定义的Application
     *    在自定义Application的onCreate方法中初始化CrashHandler
     *    CrashHandler.getInstance().init(getApplicationContext());
     * 3. 在入口Activity中（非必须）
     *    CrashHandler.getInstance().init(this, UiCrash.class);
     * 注意：
     * 2、3可以任选其一
     */


    /**
     * 说明
     * Thread.UncaughtExceptonHandler：线程未捕获异常处理器，用来处理未捕获异常。
     * 如果程序出现了未捕获异常，默认会弹出系统中强制关闭对话框。我们需要实现此接口，
     * 并注册为程序中默认未捕获异常处理。这样当未捕获异常发生时，就可以做一些个性化
     * 的异常处理操作
     */

}
