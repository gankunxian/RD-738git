/**
 * Program  : BeanAttrFilter.java
 * Author   : lhzh
 * Create   : 2013-1-18 上午11:26:34
 *
 */

package com.hotshare.json;

import java.util.Date;

import com.hotshare.json.processer.DateJsonValueProcessor;
import com.hotshare.json.processer.HibernateJsonBeanProcessor;
import com.hotshare.json.processer.HibernateJsonBeanProcessorMatcher;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * json转换配置类
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 上午11:26:34
 */
public class BeanAttrFilter {

	/**
	 * 指定bean类中不输出的json属性
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:59:40
	 * @since
	 * @param attributes
	 * @param datePattern
	 * @return
	 */
	public static JsonConfig getConfig(String[] attributes, String datePattern) {
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor(datePattern));
		config.registerJsonBeanProcessor(
				org.hibernate.proxy.HibernateProxy.class,
				new HibernateJsonBeanProcessor());
		config.setJsonBeanProcessorMatcher(new HibernateJsonBeanProcessorMatcher());
		config.setExcludes(attributes);
		return config;
	}

	/**
	 * 指定输出时间格式
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午3:00:13
	 * @since
	 * @param datePattern
	 * @return
	 */
	public static JsonConfig getConfig(String datePattern) {
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor(datePattern));
		config.registerJsonBeanProcessor(
				org.hibernate.proxy.HibernateProxy.class,
				new HibernateJsonBeanProcessor());
		config.setJsonBeanProcessorMatcher(new HibernateJsonBeanProcessorMatcher());
		return config;
	}

	/**
	 * 输出默认时间格式
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午3:00:43
	 * @since
	 * @return
	 */
	public static JsonConfig getConfig() {
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor());
		config.registerJsonBeanProcessor(
				org.hibernate.proxy.HibernateProxy.class,
				new HibernateJsonBeanProcessor());
		config.setJsonBeanProcessorMatcher(new HibernateJsonBeanProcessorMatcher());
		return config;
	}
}