/**
 * Program  : BaseController.java
 * Author   : lhzh
 * Create   : 2013-3-29 下午2:41:51
 */

package com.hotshare.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 基础控制器类
 * 
 * @author lhzh
 * @create 2013-3-29 下午2:42:35
 * @since
 */
public class BaseController {

	private static final Logger logger = Logger.getLogger(BaseController.class);

	/**
	 * 回写终端数据�?
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:43:47
	 * @since
	 * @param response
	 * @param data
	 */
	public static void addResponseData(HttpServletResponse response, String data) {
		response.setContentType("text/html;");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(data);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (out != null)
				out.flush();
			out.close();
		}
	}
}
