package com.hotshare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import com.hotshare.util.Pinyin4jUtil;

public class Data {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
//		showQiHuoProductName("I1610");
//		showQiHuoProductName("RB1610");
//		showQiHuoProductName("CF1601");
//		showQiHuoProductName("RU1601");
//		showQiHuoProductName("SR1601");
//		showQiHuoProductName("JD1509");
//		showQiHuoProductName("AG1512");
		
		showProductName("000002");
//		showProductName("600428");
//		showProductName("000410");
//		showProductName("000060");
//		showProductName("600966");
//		showProductName("600518");
//		showProductName("150130");
//		showProductName("150131");
//		showProductName("601988");
//		showProductName("601628");
//		showProductName("002664");
//		showProductName("603008");
//		showProductName("000848");
//		showProductName("002008");
//		showProductName("000555");
//		showProductName("002197");
//		showProductName("601318");
//		showProductName("600000");
//		showProductName("002415");
//		showProductName("000022");
//		showProductName("000996");
//		showProductName("601218");
//		showProductName("002461");		
//		showProductName("002129");
//		showProductName("601111");
//		showProductName("600887");
//		showProductName("603996");
//		showProductName("002318");
//		
//		showProductName("002331");
//		showProductName("600570");
//		showProductName("000792");
//		showProductName("000878");
//		showProductName("000036");
//		showProductName("600074");
//		showProductName("600435");
//		showProductName("600345");
//		showProductName("000782");
//		
//		showProductName("002702");
//		showProductName("603227");
//		showProductName("000797");
//		showProductName("600894");
//		showProductName("600599");
//		showProductName("300257");
//		showProductName("002029");
//		showProductName("600378");
//		showProductName("002022");
//		showProductName("600037");
//		showProductName("603936");
//		showProductName("600343");
//		showProductName("002468");
//		showProductName("600083");
//		showProductName("000504");
//		showProductName("002073");
//		showProductName("000592");
//		showProductName("600652");
//		showProductName("603686");
//		showProductName("300256");
//		showProductName("600981");
//		showProductName("000838");
//		showProductName("002117");
//		showProductName("002494");
//		showProductName("002027");
//		showProductName("600061");
//		showProductName("600133");
//		showProductName("002667");
//		showProductName("002481");
//		showProductName("600303");
//		showProductName("600052");
//		showProductName("002178");
//		showProductName("601857");
//		showProductName("300242");
//		showProductName("601608");
//		showProductName("002130");
//		showProductName("603729");
//		showProductName("002195");
//		showProductName("000520");
//		showProductName("300329");
//		showProductName("002544");
//		showProductName("601633");
//		showProductName("601155");
//		showProductName("600876");
//		showProductName("300368");
//		showProductName("300122");
//		showProductName("000099");
//		showProductName("300111");
//		showProductName("300013");
//		showProductName("002177");
//		showProductName("600585");
//		showProductName("300059");
//		showProductName("000669");
//		showProductName("002235");
//		showProductName("300266");  
//		showProductName("000570");
//		showProductName("300467");
//		showProductName("002536");
//		showProductName("601336");
		
		showProductName("002528");
		showProductName("002053");
		
		showProductName("002517");
		showProductName("603936");
		showProductName("600415");
		showProductName("600745");
		showProductName("300359");
		showProductName("399006");
		showProductName("600485");
		showProductName("002178");
		showProductName("000917");
		showProductName("600383");
		showProductName("002536");
		
	}
	
	public static String showGPData(String no){
		String newPrice = "";
		double zuixin = 0.0D;
		double shoupan = 0.0D;
		DecimalFormat df1 = new DecimalFormat("0.00%");
		if ((no.equals("")) || (no.equals("-99")))
			return newPrice;
		String con = "";
		String url = "http://hq.sinajs.cn/list=sh";
		if(no.startsWith("0")||no.startsWith("3")||no.startsWith("1")){
			url = "http://hq.sinajs.cn/list=sz";
		}
		url = url + no;
		try {
			con = GetWebContent(url, "gbk", 10000);
			if ((con != null) && (con.length() != 0) && (con.length() > 25)) {
				String[] ary = con.split(",");
				
				shoupan = Double.parseDouble(ary[2]);
				zuixin = Double.parseDouble(ary[3]);
				newPrice = df1.format((zuixin - shoupan) / shoupan).replace("%", "kb/s");
				String str = "down";	 
				if(zuixin-shoupan>=0){
					str = "rise";
				}
				newPrice = zuixin+" "+str+" "+newPrice;
			}else{
				newPrice = no;
			}
		} catch (Exception e) {
			e.printStackTrace();
			newPrice = "系统异常";
			return newPrice;
		}
//		System.out.println(no + ":" + newPrice);
		return newPrice;
	}
	/** 
	 * */
	public static String showProductName(String no) throws Exception {
		String productName = "";
		
		if ((no.equals("")) || (no.equals("-99")))
			return productName;
		String con = "";
		String url = "http://hq.sinajs.cn/list=sh";
		if(no.startsWith("0")||no.startsWith("3")||no.startsWith("1")){
			url = "http://hq.sinajs.cn/list=sz";
		}
		url = url + no;
		try {
			con = GetWebContent(url, "gbk",10000);
			if ((con != null) && (con.length() != 0) && (con.length() > 25)) {
				String[] ary = con.split(",");
				String str = ary[0];
				String[] ary2 = str.split("\"");
				String name = ary2[1];
				for (int i = 0; i < name.length(); ++i) {
					char chineseChar = name.charAt(i);
					String[] s = Pinyin4jUtil.noRepeatHanyuPinyinStringArray(chineseChar);
					productName = productName + s[0] + " ";
				}
			}else{
				productName = "查询的编号不存在,请核实";
			}
		} catch (Exception e) {
			e.printStackTrace();
			productName = "系统异常";
			return productName;
		}
		System.out.println(no + ":" + productName+":"+showGPData(no));
		return productName;
	}
	
	public static String showQHData(String no){
		String newPrice = "";
		if ((no.equals("")) || (no.equals("-99")))
			return newPrice;
		String con = "";
		String url = "http://hq.sinajs.cn/list=" + no;
		try {
			con = GetWebContent(url, "gbk", 10000);
			if ((con != null) && (con.length() != 0) && (con.length() > 30)) {
				String[] ary = con.split(",0");
				newPrice = ary[8];
			}else{
				newPrice = no;	
			}
		} catch (Exception e) {
			e.printStackTrace();
			newPrice = "系统异常";
			return newPrice;
		}
//		System.out.println(no + ":" + newPrice);
		return newPrice;
	}
	
	
	public static String showQiHuoProductName(String no) throws Exception {
		String productName = "";
		if ((no.equals("")) || (no.equals("-99")))
			return productName;

		String con = "";
		String url = "http://hq.sinajs.cn/list=" + no;
		try {
			con = GetWebContent(url, "gbk", 10000);
			if ((con != null) && (con.length() != 0) && (con.length() > 30)) {
				String[] ary = con.split(",");
				String str = new String("A");
				
				String[] ary2 = str.split("\"");
				String name = ary2[1];
				for (int i = 0; i < name.length(); ++i) {
					char chineseChar = name.charAt(i);
					String[] s = Pinyin4jUtil
							.noRepeatHanyuPinyinStringArray(chineseChar);
					productName = productName + s[0] + " ";
				}
			}else{
				productName = "查询的编号不存在,请核实";
			}
		} catch (Exception e) {
			e.printStackTrace();
			productName = "系统异常";
			return productName;
		}
		System.out.println(no + ":" + productName+":"+showQHData(no));
		return productName;
	}
	
	public static String GetWebContent(String urlString, final String charset,
			int timeout) throws IOException {
		if (urlString == null || urlString.length() == 0) {
			return null;
		}
		urlString = (urlString.startsWith("http://") || urlString
				.startsWith("https://")) ? urlString : ("http://" + urlString)
				.intern();
		URL url = new URL(urlString);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
		conn.setRequestProperty("Accept", "text/html");

		conn.setConnectTimeout(timeout);
		try {
			if (conn.getResponseCode()!= HttpURLConnection.HTTP_OK) {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		InputStream input = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input,
				charset));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\r\n");
			
			sb.append(true).append("String");
		}
			if (reader != null){
		 		reader.read();
				reader.close();
		}
		if (conn != null) {
			conn.disconnect();
		}
		return sb.toString();
	}
	/**
	 *
while ((line = reader.readLine()) != null) {
			sb.append(line).append("\r\n");
		}
		if (reader != null) {
			reader.close();
		}
		if (conn != null) {
			conn.disconnect();
		}
		return sb.toString();
    

	 * */

}
