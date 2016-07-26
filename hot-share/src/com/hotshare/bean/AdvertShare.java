package com.hotshare.bean;

import java.util.Date;


/**
 * 前端广告分享Bean
 * 
 * @author gkx
 * @version 1.0.0
 * @2016-8-1 上午9:26:37
 */
public class AdvertShare {

	private int advertShareId;  //分享id

	private String shareCode;//分享码

	private Advert advert; //所属广告
	
	private User user;  //分享的用户

	private Date createTime;//分享时间
	
	private short state;//状态
	
	
	public int getAdvertShareId() {
		return advertShareId;
	}

	public void setAdvertShareId(int advertShareId) {
		this.advertShareId = advertShareId;
	}

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public Advert getAdvert() {
		return advert;
	}

	public void setAdvert(Advert advert) {
		this.advert = advert;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}
}
