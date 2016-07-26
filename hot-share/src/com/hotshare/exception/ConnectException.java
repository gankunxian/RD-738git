package com.hotshare.exception;

/**
 * 自定义网络连接异常
 * 
 * @author lhzh
 * @create 2013-3-29 下午2:57:48
 * @since
 */
public class ConnectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConnectException(String msg) {
		super(msg);
	}
}
