/**
 * Program  : ResultBean.java
 * Author   : lhzh
 * Create   : 2013-3-29 下午3:02:56
 */

package com.hotshare.json.bean;

/**
 * 修改类型操作返回数据JSON MODEL
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-3-29 下午3:02:56
 */
public class ResultBean {

	private String message;

	private int code;

	private boolean success;

	public ResultBean() {
		this.code = -1;
		this.success = false;
	}

	public ResultBean(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
