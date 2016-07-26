/**
 * Program  : HibernateJsonBeanProcessorMatcher.java
 * Author   : lhzh
 * Create   : 2013-3-29 下午3:08:13
 *
 */

package com.hotshare.json.processer;

import java.util.Set;

import net.sf.json.processors.JsonBeanProcessorMatcher;

import org.hibernate.proxy.HibernateProxy;

/**
 * hibernate延迟加载
 * 
 * @author lhzh
 * @create 2013-3-29 下午3:10:05
 * @since
 */
public class HibernateJsonBeanProcessorMatcher extends JsonBeanProcessorMatcher {

	@SuppressWarnings("rawtypes")
	@Override
	public Object getMatch(Class target, Set set) {
		if (target.getName().contains("_$$_javassist_")) {
			return HibernateProxy.class;
		}
		return DEFAULT.getMatch(target, set);
	}
}