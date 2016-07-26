/**
 * Program  : DateJsonValueProcessor.java
 * Author   : lhzh
 * Create   : 2013-3-29 下午3:04:31
 */

package com.hotshare.json.processer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * JSON时间格式工具�?
 * 
 * @author lhzh
 * @create 2013-3-29 下午3:05:50
 * @since
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private DateFormat dateFormat;

	public DateJsonValueProcessor() {
		dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
	}

	public DateJsonValueProcessor(String datePattern) {
		dateFormat = new SimpleDateFormat(
				datePattern == null ? DEFAULT_DATE_PATTERN : datePattern);
	}

	public Object processArrayValue(Object value, JsonConfig arg1) {
		return value == null ? "" : value.toString();
	}

	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		if (value == null)
			return "";
		if (value instanceof Date) {
			return dateFormat.format((Date) value);
		}
		return value.toString();
	}

}