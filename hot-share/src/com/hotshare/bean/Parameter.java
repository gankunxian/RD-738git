package com.hotshare.bean;

import java.util.Date;


/**
 * @author Enjoy gkx
 * @version 创建时间：2014-8-12 上午10:18:48
 * 类说明     系统基础参数实体类
 */
public class Parameter{
	private static final long serialVersionUID = 1L;
	public Parameter() {
		super();
	}
	private int parameterId; //主键
	private String parameterName;
	private short state;
	private Date createTime;
	private String createPerson;
	private Date modifyTime;
	private String modifyPerson;
	private String remark;
	
	
	
	public int getParameterId() {
		return parameterId;
	}
	public void setParameterId(int parameterId) {
		this.parameterId = parameterId;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public short getState() {
		return state;
	}
	public void setState(short state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyPerson() {
		return modifyPerson;
	}
	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
