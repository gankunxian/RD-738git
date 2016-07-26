package com.hotshare.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotshare.bean.Manager;
import com.hotshare.util.ManagerInfoUtil;


/**
 * 
 * 管理员会话过滤器
 * 
 * @author lhzh
 * @version 1.0.0
 * @2012-12-20 上午9:49:04
 */
public class SessionFilter implements Filter {

	protected FilterConfig filterConfig;

	private List<String> notCheckURLList = new ArrayList<String>();

	private Pattern egnoreResourcePattern;

	/**
	 * 
	 * 上下文初始化
	 * 
	 * @author lhzh
	 * @create 2012-12-20 上午9:52:05
	 * @since
	 * @param arg0
	 * @throws ServletException
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

		String egnoreResource = filterConfig.getInitParameter("egnoreResourceRegex");
		if (egnoreResource != null) {
			egnoreResourcePattern = Pattern.compile(egnoreResource);
		}

		String notCheckURLListStr = filterConfig.getInitParameter("notCheckURLList");

		if (notCheckURLListStr != null) {
			StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");
			notCheckURLList.clear();
			while (st.hasMoreTokens()) {
				notCheckURLList.add("/" + st.nextToken());
			}
		}

	}

	/**
	 * 
	 * 执行过滤
	 * 
	 * @author lhzh
	 * @create 2012-12-20 上午9:50:40
	 * @since
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		request.setAttribute("basePath", basePath);
		Manager manager = ManagerInfoUtil.getSessionManagerInfo(session);
		if ((!checkRequestURIIsNotFilterList(request)) && manager == null) {
			response.sendRedirect(request.getContextPath() + "/jsp/login/login.jsp");
			return;
		}
		filterChain.doFilter(request, response);
	}

	/**
	 * 
	 * 过滤器销毁
	 * 
	 * @author lhzh
	 * @create 2012-12-20 上午9:52:30
	 * @since
	 */
	public void destroy() {
		notCheckURLList.clear();
	}

	/**
	 * 判断终端访问URL
	 * 
	 * @author lhzh
	 * @create 2011-12-27 上午12:43:52
	 * @since
	 * @param request
	 * @return
	 */
	private boolean checkRequestURIIsNotFilterList(HttpServletRequest request) {
		String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
		boolean flag = true;
		if (egnoreResourcePattern != null) {
			Matcher matcher = egnoreResourcePattern.matcher(uri.trim());
			flag = matcher.matches();
		}
		if (!flag && notCheckURLList != null) {
			flag = notCheckURLList.contains(uri);
		}
		return flag;
	}
}
