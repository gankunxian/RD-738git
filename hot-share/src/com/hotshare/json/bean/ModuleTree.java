package com.hotshare.json.bean;

import java.util.List;

import com.google.gson.annotations.Expose;


/**
 * @author lhzh
 * @version 创建时间：2014-8-12 上午09:43:25 类说明
 */
public class ModuleTree {
	/** 模块ID **/
	@Expose
	private String businessID;
	/** 模块名称 **/
	@Expose
	private String businessName;
	/** 父模块ID **/
	@Expose
	private String parentID;
	/** 子模块列表 **/
	@Expose
	private List<ModuleTree> children;
	/** 功能项列表 **/
	@Expose
	private List<FunctionJsonBean> operation;

	public String getBusinessID() {
		return businessID;
	}

	public void setBusinessID(String businessID) {
		this.businessID = businessID;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public List<ModuleTree> getChildren() {
		return children;
	}

	public void setChildren(List<ModuleTree> children) {
		this.children = children;
	}

	public List<FunctionJsonBean> getOperation() {
		return operation;
	}

	public void setOperation(List<FunctionJsonBean> operation) {
		this.operation = operation;
	}

}
