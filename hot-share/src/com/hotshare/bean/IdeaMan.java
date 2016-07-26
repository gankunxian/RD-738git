package com.hotshare.bean;

import java.util.Date;


/**
 * 前端广告创意人Bean
 * 
 * @author gkx
 * @version 1.0.0
 * @2016-8-1 上午9:26:37
 */
public class IdeaMan {

	private int ideaManId;  //广告创意人id

	private String name;//姓名

	private String phone;//联系电话

	private String slogan;//广告标语
	
	private String headImgPath;  //头像
	
	private short state;//状态   
	
	private Date createTime;//注册时间
	
	

	public int getIdeaManId() {
		return ideaManId;
	}

	public void setIdeaManId(int ideaManId) {
		this.ideaManId = ideaManId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getHeadImgPath() {
		return headImgPath;
	}

	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
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
}
