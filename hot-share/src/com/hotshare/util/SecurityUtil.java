/**
 * Program  : SecurityUtil.java
 * Author   : chenshilong
 * Create   : 2012-1-10 下午5:33:00
 */

package com.hotshare.util;

import java.security.MessageDigest;

public class SecurityUtil {

	/**
	 * 加密密钥
	 */
	public static final String SecurityKey = "hotshare->Powerful";

	/**
	 * MD5加密
	 * 
	 * @author chenshilong
	 * @create 2012-1-10 下午5:40:01
	 * @since
	 * @param strSrc
	 * @return
	 * @throws Exception
	 */
	public static String MD5(String strSrc) throws Exception {
//		strSrc = strSrc.substring(0, strSrc.length() > 0 ? strSrc.length() - 1
//				: strSrc.length())
//				+ SecurityKey;
		return Encrypt(strSrc, "MD5");
	}

	/**
	 * 加密字符串，支持MD5,SHA-1,SHA-256加密
	 * 
	 * @author chenshilong
	 * @create 2012-1-10 下午6:01:39
	 * @since
	 * @param strSrc
	 * @param encName
	 * @return
	 * @throws Exception
	 */
	public static String Encrypt(String strSrc, String encName)
			throws Exception {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		if (encName == null || encName.equals(""))
			encName = "SHA-256";
		md = MessageDigest.getInstance(encName);
		md.update(bt);
		strDes = bytes2Hex(md.digest());
		return strDes;
	}

	/**
	 * SHA-256加密
	 * 
	 * @author chenshilong
	 * @create 2012-1-10 下午6:01:12
	 * @since
	 * @param strSrc
	 * @return
	 * @throws Exception
	 */
	public static String Encrypt(String strSrc) throws Exception {
		return Encrypt(strSrc, "SHA-256");
	}

	/**
	 * 修正加密结果
	 * 
	 * @author chenshilong
	 * @create 2012-1-10 下午5:44:11
	 * @since
	 * @param bts
	 * @return
	 */
	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(MD5("e10adc3949ba59abbe56e057f20f883e"+"1427700396459"));
		System.out.println(MD5("123456"));
	}
}
