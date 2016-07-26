package com.hotshare.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hotshare.exception.ControllerException;
import com.hotshare.exception.DaoException;
import com.hotshare.exception.ServiceException;

/**
 * spring-mvc异常处理
 * 
 * @author lhzh
 * @create 2012-9-12 上午10:50:25
 * @since
 */
public class CSSException implements HandlerExceptionResolver {

	private Logger logger = Logger.getLogger(CSSException.class);

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception ex) {
		logger.error(ex);
		Throwable e = ex.getCause();
		if(e == null){
			e = ex;
		}
		if (e instanceof DaoException) {
			printLogInfo(request,object,ex,"数据层异常");
		} else if(e instanceof ServiceException){
			printLogInfo(request,object,ex,"服务层异常");
		} else if(e instanceof ControllerException){
			printLogInfo(request,object,ex,"控制层异常");
		} else {
			printLogInfo(request,object,ex,"未知异常");
		}
		return null;
	}
	
	
	public void printLogInfo(HttpServletRequest request, Object object, Exception e,String msg){
		logger.error("[admin]-" + "["+msg+"]-"+"["+object.getClass().getSimpleName() +"]-[" + e.getMessage() + "]");
	}

}
