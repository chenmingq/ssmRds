package com.mcin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Mcin on 2017年3月8日14:16:37.
 */
public class SHA1Util {
    /**
     * 用SHA1算法生成安全签名
     * @param str 密文
     * @return 安全签名
     */
    public static String sha1(String str) throws NoSuchAlgorithmException {
        // SHA1签名生成
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(str.getBytes());
        byte[] digest = md.digest();
        StringBuffer hexstr = new StringBuffer();
        String shaHex = "";
        for (int i = 0; i < digest.length; i++) {
            shaHex = Integer.toHexString(digest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }
        return hexstr.toString();
    }
}
