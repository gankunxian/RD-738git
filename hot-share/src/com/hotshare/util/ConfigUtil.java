/**
 * Program : ConfigUtil.java Author : lynchoy Create : 2011-7-14 上午01:49:25
 * Copyright 2006 by Embedded Internet Solutions Inc., All rights reserved. This
 * software is the confidential and proprietary information of Embedded Internet
 * Solutions Inc.("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Embedded Internet Solutions
 * Inc.
 */

package com.hotshare.util;

/**
 * 负责提取配置文件信息，并监测配置文件的改�?
 * 
 * @author lynchoy
 * @version 1.0.0
 * @2011-7-14 上午01:49:25
 */
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class ConfigUtil {
	public final static String CONFIG_FILE = "config.properties";

	private static long lastModified = 0L;

	private static File configFile = null;

	private static Logger log = Logger.getLogger(ConfigUtil.class);

	private static Properties props = new Properties();

	static {
		loadProperty();
	}

	/**
	 * 从配置文件中读取�?��的属�?
	 * 
	 * @author lynchoy
	 * @create 2011-7-14 上午01:50:54
	 * @since
	 */
	private static void loadProperty() {
		try {
			String path = ConfigUtil.class.getResource("/config.properties").getFile();
			if (System.getProperty("os.name").startsWith("Windows")) {
				path = path.substring(1);
				log.info(path);
				path = path.replaceAll("%20", " ");
				log.info(path);
			}
			File f = new File(path);

			lastModified = f.lastModified();
			configFile = f;
			log.info("load config from: " + f.getAbsolutePath());
			props.load(new FileInputStream(f));
			(new ReloadThread()).start();
		} catch (Exception e) {
			log.error("load config falied!", e);
			System.exit(-1);
		}
	}

	/**
	 * �?��config文件是否被改动，改动后立即更�?
	 * 
	 * @author lynchoy
	 * @create 2011-7-14 上午01:51:03
	 * @since
	 */
	private static void checkUpdate() {
		if (configFile != null) {
			long m = configFile.lastModified();
			if (m != lastModified) {
				lastModified = m;
				try {
					Properties prop = new Properties();
					prop.load(new FileInputStream(configFile));
					props = prop;
					log.info("reload config file:" + configFile.getAbsolutePath());
				} catch (Exception e) {
					log.error("failed to reload config file: " + configFile.getAbsolutePath(), e);
				}
			}
		}
	}

	/**
	 * 根据属�?名获得对应�?，如果得不到值返回defaultValue
	 * 
	 * @author lynchoy
	 * @create 2011-7-14 上午01:51:11
	 * @since
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getConfig(String name, String defaultValue) {
		checkUpdate();
		String ret = props.getProperty(name, defaultValue);
		if (ret == null) {
			return defaultValue;
		} else {
			return ret.trim();
		}
	}

	public static boolean getConfigBoolean(String name) {
		boolean flag = false;
		name = getConfig(name, null);
		if (name != null)
			flag = Boolean.parseBoolean(name);
		return flag;
	}

	public static String getConfig(String name) {
		return getConfig(name, null);
	}

	/**
	 * �?��config文件是否被改动的线程，每5秒检测一�?
	 * 
	 * @author lynchoy
	 * @create 2011-7-14 上午01:51:20
	 * @since
	 */
	static class ReloadThread extends Thread {
		public void run() {
			log.info("update checking for config file: " + configFile.getAbsolutePath());
			while (true) {
				if (configFile != null) {
					long m = configFile.lastModified();
					if (m != lastModified) {
						lastModified = m;
						try {
							Properties prop = new Properties();
							prop.load(new FileInputStream(configFile));
							props = prop;

							log.info("config file changed, reload: " + configFile.getAbsolutePath());

						} catch (Exception e) {
							log.error("failed to reload config file: " + configFile.getAbsolutePath(), e);
						}
					}

					try {
						Thread.sleep(5000);
					} catch (Exception e) {
						log.error("", e);
					}

				} else
					break;
			}
		}
	}

}
