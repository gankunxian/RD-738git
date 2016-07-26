/**
 * Program : DateFiend.java Author : lynchoy Create : 2011-7-16 上午12:20:42
 * Copyright 2006 by Embedded Internet Solutions Inc., All rights reserved. This
 * software is the confidential and proprietary information of Embedded Internet
 * Solutions Inc.("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Embedded Internet Solutions
 * Inc.
 */

package com.hotshare.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期和时间操作类
 * 
 * @author lynchoy
 * @version 1.0.0
 * @2011-7-16 上午12:20:42
 */
public class DateFiend {

	private static Calendar cal = Calendar.getInstance();

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	private static SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static SimpleDateFormat reFormat = new SimpleDateFormat("yyyyMMdd");

	public static String getCurrDateTime() {
		return sf.format(new Date());
	}
	
	public static long getLongNowDate() {
		return new Date().getTime();
	}
	
	public static String getSimpleCurrDateTime() {
		return reFormat.format(new Date());
	}

	public static Date getSystemDate() {
		return new Date();
	}

	public static String getSystemTimeStr() {
		return sf.format(new Date());
	}

	public static Timestamp getSystemTime() {
		Date currentDate = new Date();
		return new Timestamp(currentDate.getTime());
	}

	public static String getSystemDateStr() {
		return sdFormat.format(new Date());
	}

	public static String formatter(Date date) {
		return sf.format(date);
	}

	public static String sdFormatter(Date date) {
		return sdFormat.format(date);
	}

	/**
	 * 格式化日期字符串
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:33:42
	 * @since
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		String dateStr = null;
		try {
			SimpleDateFormat sfTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateStr = sfTemp.format(date);
			return dateStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;

	}

	/**
	 * 格式化日期字符串
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:33:42
	 * @since
	 * @param date
	 * @return
	 */
	public static String formatStr(Date date) {
		String dateStr = null;
		try {
			SimpleDateFormat sfTemp = new SimpleDateFormat("yyyy/MM/dd");
			dateStr = sfTemp.format(date);
			return dateStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;

	}

	/**
	 * 格式化日�?
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:34:17
	 * @since
	 * @param dateStr
	 *            日期字符�?0000-00-00 00:00:00
	 * @return
	 */
	public static Date format(String dateStr) {
		java.util.Date dd = null;
		try {
			dd = sf.parse(dateStr);
			// logger.info(dd.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dd;
	}

	/**
	 * 2011/10/19字符串转化为date
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date speformat(String dateStr) {
		java.util.Date dd = null;
		try {
			dd = sdf.parse(dateStr);
			// logger.info(dd.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dd;
	}

	/**
	 * 格式化日�?
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:34:52
	 * @since
	 * @param dateStr
	 *            日期字符�?0000-00-00
	 * @return
	 */
	public static Date sdFormat(String dateStr) {
		java.util.Date dd = null;
		try {
			dd = sdFormat.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dd;
	}
	
	
	/**
	 * 格式化日�?
	 * @author misery
	 * @create 2013-4-12 下午6:11:43
	 * @since 
	 * @param dateStr 20130414182300
	 * @return
	 */
	public static Date dfFormat(String dateStr) {
		java.util.Date dd = null;
		try {
			dd = df.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dd;
	}

	/**
	 * 计算两个日期相差的天�?
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:37:43
	 * @since
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDays(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return 0;
		}
		int days = 0;

		cal = Calendar.getInstance();

		cal = Calendar.getInstance();
		cal.setTime(date2);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		date2 = cal.getTime();

		cal.setTime(date1);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);

		if (date1.before(date2)) {
			while (date2.after(cal.getTime())) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
				days++;
			}
		} else {
			while (date2.before(cal.getTime())) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
				days--;
			}
		}
		return days;
	}

	/**
	 * 计算2个日期之间的间隔天数,startDate和endDate的时间格式是yyyy-MM-dd
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:38:44
	 * @since
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getIntervalDays(String startDate, String endDate) {
		int intervalDays = 0;
		// startDateTemp和endDateTemp的时间格式是yyyyMMdd
		String startDateTemp = startDate.substring(0, 4) + startDate.substring(5, 7) + startDate.substring(8);

		String endDateTemp = endDate.substring(0, 4) + endDate.substring(5, 7) + endDate.substring(8);

		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");

		Date date1 = new Date();
		Date date2 = new Date();

		try {
			date1 = formatDate.parse(startDateTemp);
			date2 = formatDate.parse(endDateTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			long len = date2.getTime() - date1.getTime();
			intervalDays = (int) (len / (24 * 60 * 60 * 1000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intervalDays;
	}

	/**
	 * 计算2个日期之间的间隔天数,startDate和endDate的时间格式是yyyy-MM-dd
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:38:35
	 * @since
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getIntervalDays(Date startDate, Date endDate) {
		int intervalDays = 0;
		try {
			if (startDate.after(endDate)) {
				Date temp = endDate;
				endDate = startDate;
				startDate = temp;
			}
			long len = endDate.getTime() - startDate.getTime();
			intervalDays = (int) (len / (24 * 60 * 60 * 1000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intervalDays;
	}

	/**
	 * 计算�?��日期N天之前的日期
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:39:17
	 * @since
	 * @param intervalDays
	 * @return
	 */
	public static int getTodayBefore(String intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, Integer.parseInt("-" + intervalDays));
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dayBefore = format.format(date);
		return Integer.parseInt(dayBefore);
	}

	/**
	 * 计算�?��日期N天之后的日期
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:39:24
	 * @since
	 * @param intervalDays
	 * @return
	 */
	public static int getTodayAfter(String intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, Integer.parseInt(intervalDays));
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dayAfter = format.format(date);
		return Integer.parseInt(dayAfter);
	}

	/**
	 * 计算�?��日期1天之后的日期
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:39:24
	 * @since
	 * @param intervalDays
	 * @return
	 */
	public static Date getTodayAfter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss 返回时间�?yyyy-MM-dd
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:39:30
	 * @since
	 * @param date
	 * @return
	 */
	public static String getDayString(Date date) {
		try {
			String dateStr = formatter(date);
			dateStr = dateStr.substring(0, 10);
			return dateStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss 返回时间�?HH:mm:ss
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:39:38
	 * @since
	 * @param date
	 * @return
	 */
	public static String getTimeString(Date date) {
		try {
			String dateStr = formatter(date);
			dateStr = dateStr.substring(11, 19);
			return dateStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 计算�?��日期N天之前的日期
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:39:46
	 * @since
	 * @param intervalDays
	 * @return
	 */
	public static String getTodayBefore(int intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, Integer.parseInt("-" + intervalDays));
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * 计算�?��日期N天之后的日期
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:39:46
	 * @since
	 * @param intervalDays
	 * @return
	 */
	public static String getTodayAfter(int intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, intervalDays);
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dayAfter = format.format(date);
		return dayAfter;
	}

	/**
	 * 计算�?��日期N天之后的日期
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:39:46
	 * @since
	 * @param intervalDays
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getOnedayAfter(String datestr, int intervalDays) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(datestr);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, intervalDays);
		date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dayAfter = format.format(date);
		return dayAfter;
	}

	/**
	 * 根据某日期，获取某天后的日期
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午12:22:20
	 * @since
	 * @param dateForm
	 * @param days
	 * @return
	 */
	public static String getDateAfterSomeDays(String dateFormat, String dateForm, long days) {
		Date dt = null;
		Date date = null;
		try {
			dt = new SimpleDateFormat(dateFormat).parse(dateForm);
			long times = dt.getTime();
			long dateMillSec = days * 24 * 60 * 60 * 1000;
			times = times + dateMillSec;
			date = new Date(times);
		} catch (Exception e) {
			return null;
		}
		return DateFormat.getDateInstance(DateFormat.DEFAULT).format(date);

	}

	/**
	 * 格式化日�?
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:41:08
	 * @since
	 * @param time
	 * @return
	 */
	public static String getDayString(long time) {
		String timePattren = "yyyyMMdd";
		Date date = new Date(time);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattren);
		return simpleDateFormat.format(date);
	}

	/**
	 * 格式化日�?
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:41:20
	 * @since
	 * @param time
	 * @return
	 */
	public static String getTimeString(long time) {
		String timePattren = "yyyyMMddHHmmss";
		Date date = new Date(time);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattren);
		return simpleDateFormat.format(date);
	}

	/**
	 * 获取时间的long�?精确到秒
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:41:34
	 * @since
	 * @param date
	 * @return
	 */
	public static long getDateLong(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis() / 1000;
	}
	
	/**
	 * 根据起始时间和时间间隔获得结束时�?
	 * @author misery
	 * @create 2013-4-12 下午5:56:05
	 * @since 
	 * @param date （yyyyMMddHHmmss�?
	 * @param duration（HHmmss�?
	 * @return
	 */
	public static String getDateByDuration(String date,String duration){
		java.util.Date dd = null;
		String timeEndStr = "";
		try {
			dd = df.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dd);
			long timeStart = calendar.getTimeInMillis() / 1000;
			long timeAdd = Integer.parseInt(duration.substring(0,2))*3600+Integer.parseInt(duration.substring(2,4))*60;
			long timeEnd = timeStart + timeAdd;
		    timeEndStr = getTimeString(timeEnd*1000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeEndStr;
	}

	
	public static int getDurationValue(String duration){
		return Integer.parseInt(duration.substring(0,2))*3600+Integer.parseInt(duration.substring(2,4))*60;
		
	}
	/**
	 * 获得固定格式的当前时�?
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:41:44
	 * @since
	 * @param timePattern
	 * @return
	 */
	public static final String getCurrentTime(String timePattern) {
		// String timePattren = "yyyyMMddHHmms";
		SimpleDateFormat dfmt = new SimpleDateFormat(timePattern);
		return dfmt.format(new Date());
	}

	/**
	 * 获取某一时刻的下�?��时刻
	 * 
	 * @author lynchoy
	 * @create 2011-7-16 上午01:41:51
	 * @since
	 * @param date
	 * @return
	 */
	public static Date getNextDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long dateLong = calendar.getTimeInMillis();
		Date nextDate = new Date(dateLong + (1000 * 60 * 60 * 24));
		return nextDate;
	}

	// /**
	// * Calendar转换为XMLGregorianCalendar
	// *
	// * @author lynchoy
	// * @create 2011-7-16 上午01:42:00
	// * @since
	// * @param calendar
	// * @return
	// */
	// public static XMLGregorianCalendar convert(Calendar calendar) {
	// XMLGregorianCalendar xmlcalendar = new XMLGregorianCalendarImpl();
	// xmlcalendar.setYear(calendar.get(Calendar.YEAR));
	// xmlcalendar.setMonth(calendar.get(Calendar.MONTH) + 1);
	// xmlcalendar.setDay(calendar.get(Calendar.DAY_OF_MONTH));
	// xmlcalendar.setHour(calendar.get(Calendar.HOUR_OF_DAY));
	// xmlcalendar.setMinute(calendar.get(Calendar.MINUTE));
	// xmlcalendar.setSecond(calendar.get(Calendar.SECOND));
	// xmlcalendar.setMillisecond(calendar.get(Calendar.MILLISECOND));
	// xmlcalendar.setTimezone(calendar.get(Calendar.ZONE_OFFSET) / 60000);
	// return xmlcalendar;
	// }

	/**
	 * 转换格式
	 * 
	 * @author lynchoy
	 * @create 2011-7-30 上午04:11:45
	 * @since
	 * @param timeStr
	 * @param myFormatter
	 * @return
	 */
	public static String parserPubTime(String timeStr, SimpleDateFormat myFormatter) {
		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone zone = TimeZone.getTimeZone("GMT+8");
		sdf.setTimeZone(zone);
		String time;
		try {
			Date d = myFormatter.parse(timeStr, pos);
			time = (d != null) ? sdf.format(d) : sdf.format(new Date());
		} catch (Exception e) {
			time = getCurrDatetime("yyyy-MM-dd hh:mm:ss");
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 按格式获取到当前时间
	 * 
	 * @author lynchoy
	 * @create 2011-7-30 上午04:11:33
	 * @since
	 * @return"yyyy-MM-dd hh:mm:ss"
	 */
	public static String getCurrDatetime(String formatStr) {
		String str = "";
		DateFormat df = new SimpleDateFormat(formatStr);
		Date d = new Date();
		str = df.format(d);
		return str;
	}

	/**
	 * 转换格式
	 * 
	 * @author lynchoy
	 * @create 2011-7-30 上午04:12:07
	 * @since
	 * @param timeStr
	 * @return
	 */
	public static String parserPubtime(String timeStr) {
		SimpleDateFormat formatter;
		// 判断是中文格式的时间还是英文格式的时�?
		if (timeStr.toLowerCase().matches("^[fmtws].*")) {
			formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		} else {
			formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.CHINA);
		}
		return parserPubTime(timeStr, formatter);
	}

	/**
	 * 判断当天是星期几
	 * 
	 * @author lynchoy
	 * @create 2011-12-25 下午5:19:04
	 * @since
	 * @return
	 */
	public static int judgeWeek(Date date) {
		int dayNames[] = { 1, 2, 3, 4, 5, 6, 7 };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) == 1 ? 8 : calendar.get(Calendar.DAY_OF_WEEK);
		return dayNames[dayOfWeek - 2];
	}

	/**
	 * 判断当天是几�?
	 * 
	 * @author lynchoy
	 * @create 2011-12-25 下午5:19:04
	 * @since
	 * @return
	 */
	public static int judgeMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 判断当天是几�?
	 * 
	 * @author lynchoy
	 * @create 2011-12-25 下午5:19:04
	 * @since
	 * @return
	 */
	public static int judgeDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 判断某天是否是每周的第一天�?每月的第�?��、每年的第一�?
	 * 
	 * @author lynchoy
	 * @create 2011-12-25 下午5:08:38
	 * @since
	 * @return 0：每周的第一天； 1：每月的第一天； 2：每年的第一�?每月的第�?��、每年的第一天； 3：每周的第一天�?每月的第�?���?
	 *         4：每周的第一天�?每年的第�?��/每周的第�?��、每月的第一天�?每年的第�?��
	 */
	public static int judgeDateDwmy(Date date) {
		int retn = -1;
		int week = judgeWeek(date);
		int month = judgeMonth(date);
		int day = judgeDay(date);
		if (week == 1 && month != 1 && day != 1)
			retn = 0;
		else if (week != 1 && month != 1 && day == 1)
			retn = 1;
		else if (week != 1 && month == 1 && day == 1)
			retn = 2;
		else if (week == 1 && month != 1 && day == 1)
			retn = 3;
		else if (week == 1 && month == 1 && day == 1)
			retn = 4;
		return retn;
	}

	public static void main(String args[]) throws Exception {
		// String date_str = "Thu, 28 Jul 2011 17:13:05 +0800";
		// System.out.println(parserPubtime(date_str));
		// System.out.println(getIntervalDays("2011-07-08", "2011-08-09"));
//		String date_str = "2001-01-01";
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = sd.parse(date_str);
//		System.out.println(judgeWeek(date));
//		System.out.println(judgeMonth(date));
//		System.out.println(judgeDay(date));
//		System.out.println(judgeDateDwmy(date));
		System.out.println(getDurationValue("010200"));
	}

}
