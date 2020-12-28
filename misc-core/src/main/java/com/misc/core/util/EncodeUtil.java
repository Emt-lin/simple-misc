package com.misc.core.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * EncodeUtil 算法
 * <p>
 *     机密算法
 * </p>
 */
public class EncodeUtil {

    public final static String EMPTY = "";

    /**
     * 32位
     */
    public final static String MD5 = "MD5";

    /**
     * 64位
     */
    public final static String SHA_256 = "SHA-256";

    /**
     * 92位
     */
    public final static String SHA_384 = "SHA-384";

    /**
     * 128位
     */
    public final static String SHA_512 = "SHA-512";

    public static String getMD5(String source) {
        byte[] bytes = source.getBytes();
        try {
            return getMD5(bytes);
        }catch (NoSuchAlgorithmException e) {
            return EMPTY;
        }finally {
            bytes = null;
        }
    }

    public static String getMD5(byte[] source) throws NoSuchAlgorithmException {
        return encode(source, MD5);
    }

    public static String getSha256(byte[] source) throws NoSuchAlgorithmException {
        return encode(source, SHA_256);
    }

    /**
     * 加密算法
     * @param source source 源
     * @param algorithm 算法
     * @return 字符串
     * @throws NoSuchAlgorithmException 异常
     */
    public static String encode(byte[] source, String algorithm) throws NoSuchAlgorithmException {
        // 获取MD5实例
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(source); // 此处传入要加密的byte类型值
        byte[] digest = md.digest(); // 此处得到的是md5加密后的byte类型值
        StringBuilder sb = new StringBuilder(32);
        try {
            int i;
            for(byte b : digest) {
                i = b;
                if(i < 0) i += 256;
                if(i < 16) sb.append(0);
                sb.append(Integer.toHexString(i)); // 通过Integer.toHexString方法把值转为16进制
            }
            String result = sb.toString();
            //从下标0开始，length目的是截取多少长度的值 ????
            return  result;
        }finally {
            sb = null;
            digest = null;
        }
    }
}
