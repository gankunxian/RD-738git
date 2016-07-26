package com.hotshare.filter;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class ApplicationServlet extends HttpServlet {

	private final Log log = LogFactory.getLog(ApplicationServlet.class);
	private java.util.Timer timer = null; 

	/**
	 * Constructor of the object.
	 */
	public ApplicationServlet() {
		super();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		try{
			ServletContext sc = getServletContext();
			
			//------------------------------------读取所有路径配置文件开始---------------------------------
			ResourceBundle resource = PropertyResourceBundle.getBundle("config");
			String aLiAccount = resource.getString("aLiAccount");
			sc.setAttribute("aLiAccount", aLiAccount);
			String aLiPassword = resource.getString("aLiPassword");
			sc.setAttribute("aLiPassword", aLiPassword);
			
			System.out.println("启动的时候加载我一次...ApplicationServlet");
			log.info("Application context load success!");
		}catch(Exception e){
	       log.error("Application context load error!"+e.toString());
	       e.printStackTrace();
	    }


	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}
}
