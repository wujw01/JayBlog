package com.jay.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5摘要
 * Created by xiang.wei on 2018/10/15
 *
 * @author xiang.wei
 */
public class MD5 {
    /**
     * 对字符串进行MD5加密
     * @param input
     * @return
     */
    public static String md5(String input) {
        byte[] code = null;
        try {
            code = MessageDigest.getInstance(input).digest();
        } catch (NoSuchAlgorithmException e) {
            code = input.getBytes();
        }
        BigInteger bi = new BigInteger(code);
        return bi.abs().toString(32).toUpperCase();
    }


}
