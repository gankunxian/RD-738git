package com.hotshare.json.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author lhzh
 * @version 创建时间：2014-8-12 下午03:07:09 类说明
 */
public class FunctionJsonBean {
	@Expose
	private int id;
	@Expose
	@SerializedName("operationName")
	private String functionName;
	@Expose
	@SerializedName("operationCode")
	private String functionCode;
	@Expose
	@SerializedName("operationFlag")
	private String functionFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getFunctionFlag() {
		return functionFlag;
	}

	public void setFunctionFlag(String functionFlag) {
		this.functionFlag = functionFlag;
	}

}
