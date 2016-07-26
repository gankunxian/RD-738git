package com.hotshare.bean;

import java.util.Date;


/**
 * 前端用户Bean
 * 
 * @author gkx
 * @version 1.0.0
 * @2016-8-1 上午9:26:37
 */
public class User {

	private int userId;  //用户id

	private String account;//账号

	private String password;//密码

	private String nickname;//昵称
	
	private String phone;  //联系电话
	
	private String headImgPath;  //头像
	
	private short level = 1;  //星级

	private short state;//状态     0正常,-1禁用
	
	private Date regTime;//注册时间
	
	private String regCode; //注册码
	
	private String shareCode; //分享码
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegTime() {
		return regTime;
	}
	
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}


	public String getHeadImgPath() {
		return headImgPath;
	}

	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}
	
}
