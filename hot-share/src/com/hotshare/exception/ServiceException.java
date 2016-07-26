package com.hotshare.exception;

/**
 * 业务层处理异常
 * 
 * @author lhzh
 * @create 2013-1-28 下午2:04:53
 * @since
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException(String msg) {
		super(msg);
	}
}
