/**
 * Program  : DataInfo.java
 * Author   : misery
 * Create   : 2013-4-1 下午6:25:00
 */

package com.hotshare.json.bean;

/**
 * 某条数据详情封装bean
 * 
 * @author misery
 * @version 1.0.0
 * @2013-4-1 下午6:25:00
 */
public class DataInfo<T> {

	private boolean success;

	private T data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
