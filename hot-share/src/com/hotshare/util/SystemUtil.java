package com.hotshare.util;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * 系统工具�?
 * 
 * @author lhzh
 * @create 2012-4-4 下午4:45:08
 * @since
 */
public class SystemUtil {

	private static Logger logger = Logger.getLogger(SystemUtil.class);

	/**
	 * 
	 * 回写流信�?
	 * 
	 * @author lhzh
	 * @create 2012-4-4 下午4:45:18
	 * @since
	 * @param response
	 * @param str
	 */
	public static void writeData(HttpServletResponse response, String str) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(str);
		} catch (IOException e) {
			logger.info(e.getMessage());
		} finally {
			if (out != null)
				out.flush();
			out.close();
		}
	}

	/**
	 * 
	 * 回写流信�?
	 * 
	 * @author lhzh
	 * @create 2012-4-4 下午4:46:00
	 * @since
	 * @param data
	 * @param response
	 */
	public static void addDataToResponse(String data,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(data);
		} catch (IOException e) {
			logger.info(e.getMessage());
		} finally {
			if (out != null)
				out.flush();
			out.close();
		}
	}

}
