package com.hotshare.bean;

import java.util.Date;


public class Zone {
	private static final long serialVersionUID = 1L;

	private int zoneId;     //地区id
	private String zoneNo;  //地区编号
	private String zoneName;  //地区名称
	private short state;      //状态   0正常   
	private String fatherNo;  //父级地区编号
	private short level;      //级别  从1开始
	private Date createTime;  //创建时间
	private short isHot;      //是否热门  默认0否   1是
	private String initial;   //地区字母
	private short sort;       //排序
	private String remark;    //备注
	
	
	public int getZoneId() {
		return zoneId;
	}
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	public String getZoneNo() {
		return zoneNo;
	}
	public void setZoneNo(String zoneNo) {
		this.zoneNo = zoneNo;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public short getState() {
		return state;
	}
	public void setState(short state) {
		this.state = state;
	}
	public String getFatherNo() {
		return fatherNo;
	}
	public void setFatherNo(String fatherNo) {
		this.fatherNo = fatherNo;
	}
	public short getLevel() {
		return level;
	}
	public void setLevel(short level) {
		this.level = level;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public short getIsHot() {
		return isHot;
	}
	public void setIsHot(short isHot) {
		this.isHot = isHot;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	public short getSort() {
		return sort;
	}
	public void setSort(short sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}