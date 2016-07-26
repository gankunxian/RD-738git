package com.hotshare.exception;

/**
 * 控制器层处理异常
 * 
 * @author lhzh
 * @create 2013-1-28 下午2:04:53
 * @since
 */
public class ControllerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ControllerException(String msg) {
		super(msg);
	}
}
