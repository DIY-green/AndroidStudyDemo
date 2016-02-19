package com.cheng.utils.security;

import android.text.TextUtils;

import com.cheng.utils.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密解密工具类
 */
public class EncryptionUtil {

    private static final String kDEFAULT_KEY = "123456";

    static {
        Logger.TAG = "EncryptionUtil";
    }

    /**
     * md5 加密
     */
    static public String md5Encode (String str) {
        MessageDigest algorithm = null;
        try {
            algorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            Logger.e(e);
        }
        if (algorithm != null) {
            algorithm.reset();
            algorithm.update(str.getBytes());
            byte[] bytes = algorithm.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : bytes) {
                hexString.append(Integer.toHexString(0xFF & b));
            }
            return hexString.toString();
        }
        return "";
    }

    /**
     * 将 s 进行 BASE64 编码
     */
    static public String base64Encode(String s) {
        if (s == null) return null;
        return Base64.encode(s.getBytes());
    }

    /**
     * 将 BASE64 编码的字符串 s 进行解码
     */
    static public String base64Decode(String s) {
        byte[] b = null;
        String result = null;
        if (TextUtils.isEmpty(s)) return result;
        try {
            b = Base64.decode(s);
            result = new String(b, "utf-8");
        } catch (Exception e) {
            Logger.e(e);
        }
        return result;
    }

    /**
     * 将字符串str使用key通过aes加密
     * @param key
     * @param str
     * @return
     */
    static public String aesEncode(String key, String str) {
        if (TextUtils.isEmpty(str)) return str;
        if (TextUtils.isEmpty(key)) key = kDEFAULT_KEY;
        String encodeStr = str;
        try {
            encodeStr = AES.encrypt(key, str);
        } catch (Exception e) {
            Logger.e(e);
        }
        return encodeStr;
    }

    /**
     * 将字符串str使用key通过aes解密
     * @param key
     * @param str
     * @return
     */
    static public String aesDecode(String key, String str) {
        if (TextUtils.isEmpty(str)) return str;
        if (TextUtils.isEmpty(key)) key = kDEFAULT_KEY;
        String dncodeStr = str;
        try {
            dncodeStr = AES.decrypt(key, str);
        } catch (Exception e) {
            Logger.e(e);
        }
        return dncodeStr;
    }

    /**
     * 使用3DES算法加密字符串
     * @param str
     * @return
     */
    static public String des3Encode(String str) {
        if (TextUtils.isEmpty(str)) return str;
        String encodeStr = "";
        byte[] secretArr = DES3.encryptMode(str.getBytes());
        encodeStr = new String(secretArr);
        return encodeStr;
    }

    /**
     * 使用3DES算法解密字符串
     * @param str
     * @return
     */
    static public String des3Decode(String str) {
        if (TextUtils.isEmpty(str)) return str;
        String decodeStr = "";
        byte[] originalArr = DES3.decryptMode(str.getBytes());
        decodeStr = new String(originalArr);
        return decodeStr;
    }

    /**
     * 使用DES加密字符串
     * @param key
     * @param str
     * @return
     */
    static public String desEncode(String key, String str) {
        if (TextUtils.isEmpty(str)) return str;
        String encodeStr = DES.encode(key, str);
        return encodeStr;
    }

    /**
     * 使用DES解密字符串
     * @param key
     * @param str
     * @return
     */
    static public String desDecode(String key, String str) {
        if (TextUtils.isEmpty(str)) return str;
        String decodeStr = DES.decode(key, str);
        return decodeStr;
    }

}
