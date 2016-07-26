package com.hotshare.bean;

import java.util.Date;


/**
 * @author  甘坤贤
 * @version 创建时间：2016-7-25 下午02:07:19
 * 类说明   app版本管理
 */
public class AppVersion{
	private int id; //主键id
	private short type; //0是ios  1是安卓
	private float version; //版本号
	private String appUrl; //app下载地址
	private Date createTime;   //创建时间
	private String remark;  //备注
	


	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
