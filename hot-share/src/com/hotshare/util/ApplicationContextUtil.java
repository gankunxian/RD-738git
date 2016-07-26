/**
 * Program  : ApplicationContextUtil.java
 * Author   : lhzh
 * Create   : 2013-1-18 上午10:05:13
 */

package com.hotshare.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 实现ApplicationContextAware接口，自动注入ApplicationContext
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 上午10:05:13
 */

public class ApplicationContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextUtil.context = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static Object getBean(String key) {
		return context.getBean(key);
	}
}
