package com.cheng.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.security.auth.x500.X500Principal;

/**
 * App工具类
 */
public class AppUtil {

    private final static X500Principal DEBUG_DN = new X500Principal(
            "CN=Android Debug,O=Android,C=US");

    static {
        Logger.TAG = "AppUtil";
    }

    private AppUtil() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 得到软件版本号
     * @param context 上下文
     * @return 当前版本Code
     */
    public static int getVerCode(Context context) {
        if (context == null) return 1;
        int verCode = -1;
        try {
            String packageName = context.getPackageName();
            verCode = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e);
        }
        return verCode;
    }

    /**
     * 获取应用运行的最大内存
     * @return 最大内存
     */
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory() / 1024;
    }

    /**
     * 得到软件显示版本信息
     * @param context 上下文
     * @return 当前版本信息
     */
    public static String getVerName(Context context) {
        if (context == null) return "";
        String verName = "";
        try {
            String packageName = context.getPackageName();
            verName = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e);
        }
        return verName;
    }

    /**
     * 安装apk
     * @param context 上下文
     * @param file    APK文件
     */
    public static void installApk(Context context, File file) {
        if (context == null) return;
        if (file == null) return;
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 安装apk
     * @param context 上下文
     * @param file    APK文件uri
     */
    public static void installApk(Context context, Uri file) {
        if (context == null) return;
        if (file == null) return;
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(file, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 卸载apk
     * @param context     上下文
     * @param packageName 包名
     */
    public static void uninstallApk(Context context, String packageName) {
        if (context == null) return;
        if (TextUtils.isEmpty(packageName)) return;
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri packageURI = Uri.parse("package:" + packageName);
        intent.setData(packageURI);
        context.startActivity(intent);
    }

    /**
     * 检测服务是否运行
     * @param context   上下文
     * @param className 类名
     * @return 是否运行的状态
     */
    public static boolean isServiceRunning(Context context, String className) {
        if (context == null) return false;
        if (TextUtils.isEmpty(className)) return false;
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> servicesList = activityManager
                .getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo si : servicesList) {
            if (className.equals(si.service.getClassName())) {
                isRunning = true;
            }
        }
        return isRunning;
    }

    /**
     * 停止运行服务
     * @param context   上下文
     * @param className 类名
     * @return 是否执行成功
     */
    public static boolean stopRunningService(Context context, String className) {
        if (context == null) return false;
        if (TextUtils.isEmpty(className)) return false;
        Intent intentService = null;
        boolean ret = false;
        try {
            intentService = new Intent(context, Class.forName(className));
        } catch (Exception e) {
            Logger.e(e);
        }
        if (intentService != null) {
            // TODO 此处的context需要为Activity？
            ret = context.stopService(intentService);
        }
        return ret;
    }

    /**
     * whether this process is named with processName
     * @param context     上下文
     * @param processName 进程名
     * @return <ul>
     * return whether this process is named with processName
     * <li>if context is null, return false</li>
     * <li>if {@link ActivityManager#getRunningAppProcesses()} is null,
     * return false</li>
     * <li>if one process of
     * {@link ActivityManager#getRunningAppProcesses()} is equal to
     * processName, return true, otherwise return false</li>
     * </ul>
     */
    public static boolean isNamedProcess(Context context, String processName) {
        if (context == null || TextUtils.isEmpty(processName)) {
            return false;
        }

        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfoList = manager
                .getRunningAppProcesses();
        if (processInfoList == null) {
            return true;
        }

        for (ActivityManager.RunningAppProcessInfo processInfo : manager
                .getRunningAppProcesses()) {
            if (processInfo.pid == pid
                    && processName.equalsIgnoreCase(processInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * whether application is in background
     * <ul>
     * <li>need use permission android.permission.GET_TASKS in Manifest.xml</li>
     * </ul>
     * @param context 上下文
     * @return if application is in background return true, otherwise return
     * false
     */
    public static boolean isApplicationInBackground(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            if (topActivity != null
                    && !topActivity.getPackageName().equals(
                    context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取应用的签名信息
     * @param context
     * @return
     */
    static public String getSingInfo(Context context) {
        StringBuilder sb = new StringBuilder();
        Signature signature = getApkSignature(context);
        if (signature == null) return "";
        ByteArrayInputStream bais = new ByteArrayInputStream(signature.toByteArray());
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(bais);
            String version = String.valueOf(certificate.getVersion());
            String serialNumber = certificate.getSerialNumber().toString(16);
            String subjectDN = certificate.getSubjectDN().toString();
            String issuerDN = certificate.getIssuerDN().toString();
            String notBefore = certificate.getNotBefore().toString();
            String notAfter = certificate.getNotAfter().toString();
            String sigAlgName = certificate.getSigAlgName();
            byte[] sig = certificate.getSignature();
            String signatureStr = new BigInteger(sig).toString(16);
            PublicKey publicKey = certificate.getPublicKey();
            byte[] pkenc = publicKey.getEncoded();
            StringBuilder pkencSB = new StringBuilder();
            for (int i=0; i<pkenc.length; i++) {
                pkencSB.append(i+",");
            }

            sb.append(version);
            sb.append(serialNumber);
            sb.append(serialNumber);
            sb.append(subjectDN);
            sb.append(issuerDN);
            sb.append(notBefore);
            sb.append(notAfter);
            sb.append(sigAlgName);
            sb.append(signatureStr);
            sb.append(pkencSB);

            Logger.e("版本号 " + version);
            Logger.e("序列号 " + serialNumber);
            Logger.e("全名 " + subjectDN);
            Logger.e("签发者全名n" + issuerDN);
            Logger.e("有效期起始日 " + notBefore);
            Logger.e("有效期截至日 " + notAfter);
            Logger.e("签名算法 " + sigAlgName);
            Logger.e("签名n " + signatureStr);
            Logger.e("公钥 " + pkencSB.toString());
            Logger.e("SingInfo " + sb.toString());
        } catch (CertificateException e) {
            Logger.e(e);
        }

        return sb.toString();
    }

    static public String getApkMD5(Context context) {
        String apkMD5 = "";
        Signature signature = getApkSignature(context);
        if (signature == null) return apkMD5;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            Logger.e(e);
        }
        messageDigest.update(signature.toByteArray());
        byte[] digest = messageDigest.digest();
        apkMD5 = toHexString(digest);
        Logger.e("Apk MD5 : "+apkMD5);
        return apkMD5;
    }

    static public String getApkSHA1(Context context) {
        String apkSHA1 = "";
        Signature signature = getApkSignature(context);
        if (signature == null) return apkSHA1;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            Logger.e(e);
        }
        messageDigest.update(signature.toByteArray());
        byte[] digest = messageDigest.digest();
        apkSHA1 = toHexString(digest);
        Logger.e("Apk SHA1 : " + apkSHA1);
        return apkSHA1;
    }

    /**
     * 检测当前应用是否是Debug版本
     *
     * @param ctx
     * @return
     */
    public static boolean isDebuggable(Context ctx) {
        boolean debuggable = false;
        try {
            PackageInfo pinfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature signatures[] = pinfo.signatures;
            for (int i = 0; i < signatures.length; i++) {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                ByteArrayInputStream stream = new ByteArrayInputStream(signatures[i].toByteArray());
                X509Certificate cert = (X509Certificate) cf
                        .generateCertificate(stream);
                debuggable = cert.getSubjectX500Principal().equals(DEBUG_DN);
                if (debuggable)
                    break;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e);
            return false;
        } catch (CertificateException e) {
            Logger.e(e);
            return false;
        }
        return debuggable;
    }

    static private Signature getApkSignature(Context context) {
        PackageInfo packageInfo = null;
        Signature signature = null;
        try {
            packageInfo = context.
                    getPackageManager().
                    getPackageInfo(
                            context.getPackageName(),
                            PackageManager.GET_SIGNATURES);
            signature = packageInfo.signatures[0];
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e);
        }
        return signature;
    }

    static private void byte2hex(byte b, StringBuffer buf) {
        char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }

    static private String toHexString(byte[] block) {
        StringBuffer buf = new StringBuffer();
        int len = block.length;
        for (int i = 0; i < len; i++) {
            byte2hex(block[i], buf);
            if (i < len - 1) {
                buf.append(":");
            }
        }
        return buf.toString();
    }

}
