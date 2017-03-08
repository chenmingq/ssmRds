package com.mcin.util;

import java.security.MessageDigest;
import java.util.Random;

/**
 * MD5摘要工具类
 * @author Mcin
 *
 */
public class MD5Uitl {
	
	private static final String ENCODE = "UTF-8";
	private static final String ALGOR = "MD5";
	private static final String base = "qwertyuiopasdfghjklzxcvbnm";

	public final static String MD5LowerCase( String src ) {
		StringBuffer buf = new StringBuffer("");
		try {
			// 获取MD5摘要算法对象
			MessageDigest digest = MessageDigest.getInstance(ALGOR);
			// 使用指定的字节更新摘要
			digest.update(src.getBytes(ENCODE));
			// 获取密文
			byte[] b = digest.digest();
			//! 将密文转换成16进制的字符串形式
			int i = 0;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) i += 256;
				if (i < 16) buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return buf.toString();
	}

	public final static String getRandomStr(){
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(MD5LowerCase("1234563246243643ffffdsaa"));
	}
}
