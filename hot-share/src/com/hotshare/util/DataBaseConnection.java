/**
 * Program  : DataBaseConnection.java
 * Author   : lhzh
 * Create   : 2013-1-18 上午10:11:13
 */

package com.hotshare.util;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

/**
 * 数据源类 设置解密密码
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 上午10:11:13
 */
public class DataBaseConnection extends BasicDataSource {

	private Logger logger = Logger.getLogger(DataBaseConnection.class);

	/**
	 * 覆写密码设置方法
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午3:11:56
	 * @since
	 * @param password
	 */
	public synchronized void setPassword(String password) {
		try {
			super.setPassword(new DesUtils().decrypt(password));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
