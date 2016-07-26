package com.hotshare.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Pinyin4jUtil {
	/**
	 * 声调转字符串的规则，默认设为没有声调
	 */
	public static HanyuPinyinToneType toneType = HanyuPinyinToneType.WITHOUT_TONE;
	/**
	 * 拼音中U字母的规则，默认转化成V
	 */
	public static HanyuPinyinVCharType vType = HanyuPinyinVCharType.WITH_V;
	/**
	 * 大小写，默认是转化成小写
	 */
	public static HanyuPinyinCaseType caseType = HanyuPinyinCaseType.LOWERCASE;
	private static HanyuPinyinOutputFormat format;
	static{
		format = new HanyuPinyinOutputFormat();
		format.setToneType(toneType);
		format.setVCharType(vType);
		format.setCaseType(caseType);
	}
	
	/**
	 * 将中文字符转成拼音字符串，英文不转换，PinYin4J的改良版本
	 * @param chineseChar
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String[] noRepeatHanyuPinyinStringArray(char chineseChar) 
						throws BadHanyuPinyinOutputFormatCombination{
		if(isChinese(chineseChar)){
			String[] strTmp = PinyinHelper.toHanyuPinyinStringArray(chineseChar, format);
			Set<String> setTmp = new HashSet<String>();
			for(String r:strTmp){
				setTmp.add(r);
			}
			String[] result =new String[0];
			for(String s :setTmp){
				result = Arrays.copyOf(result, result.length+1);
				result[result.length-1]=s;
			}
			return result;
		}else{
			return new String[]{String.valueOf(chineseChar)};
		}
	}
	public static boolean isChinese(char chr){
		return chr>='\u4e00' && chr<='\u9fa5';
	}
	
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination{
		String str = "奔驰";
		char i = str.charAt(0);
		String[] s = noRepeatHanyuPinyinStringArray(i);
		System.out.println(s[0]);
		String a = s[0];
		char b = a.charAt(0);
		System.out.println(b);
	}

}
