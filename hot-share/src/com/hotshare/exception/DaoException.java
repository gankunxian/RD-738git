package com.hotshare.exception;

/**
 * dao层异常
 * @author lhzh
 * @create 2013-4-3 下午4:54:28
 * @since
 */
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException(String msg) {
		super(msg);
	}
}
