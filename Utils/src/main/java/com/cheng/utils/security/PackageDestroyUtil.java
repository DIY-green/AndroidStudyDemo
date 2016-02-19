package com.cheng.utils.security;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * APK包破坏工具
 */
public class PackageDestroyUtil {
    public static void main(String[] args) {
        PackageDestroyUtil.destory("目标apk地址", "破坏后的apk地址");
    }

    /**
     * apk包破坏
     *
     * @param arg1 目标apk地址
     * @param arg2 破坏后的apk地址
     */
    public static void destory(String arg1, String arg2) {
        try {
            File file = new File(arg1);
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream(new File(arg2));
            int read = 0;
            long count = 0;
            long readLen = file.length() - 512;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            while ((read = in.read()) != -1) {
                count++;
                out.write(read);
                if (count >= readLen) {
                    buffer.write(read);
                }
            }
            byte[] b = buffer.toByteArray();
            out.write(b);
            in.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
