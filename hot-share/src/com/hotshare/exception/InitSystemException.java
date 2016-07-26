/**
 * Program  : InitSystemException.java
 * Author   : lhzh
 * Create   : 2012-5-15 上午10:33:43
 */

package com.hotshare.exception;

/**
 * 
 * 系统初始化异常
 * 
 * @author   lhzh
 * @version  1.0.0
 * @2012-5-15 上午10:33:43
 */
public class InitSystemException extends RuntimeException{
	
	private static final long serialVersionUID = 4610578347962149204L;
	
	public InitSystemException(String msg){
		super(msg);
	}
}

