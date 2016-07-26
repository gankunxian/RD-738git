package com.hotshare.json.processer;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;

/**
 * 
 * 
 * @author lhzh
 * @create 2013-3-29 下午3:10:42
 * @since
 */
public class HibernateJsonBeanProcessor implements JsonBeanProcessor {
	public JSONObject processBean(Object obj, JsonConfig jsonConfig) {
		return new JSONObject();
	}
}
