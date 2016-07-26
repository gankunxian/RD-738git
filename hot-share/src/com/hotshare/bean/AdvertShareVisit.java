package com.hotshare.bean;

import java.util.Date;


/**
 * 前端访问广告分享Bean
 * 
 * @author gkx
 * @version 1.0.0
 * @2016-8-1 上午9:26:37
 */
public class AdvertShareVisit {

	private int advertShareVisitId;  //访问分享id

	private String visitMac;//访问分享的mac地址

	private AdvertShare advertShare;  //所属的分享

	private Date visitTime;//访问时间
	
	private short state;//状态
	
	

	public int getAdvertShareVisitId() {
		return advertShareVisitId;
	}

	public void setAdvertShareVisitId(int advertShareVisitId) {
		this.advertShareVisitId = advertShareVisitId;
	}

	public String getVisitMac() {
		return visitMac;
	}

	public void setVisitMac(String visitMac) {
		this.visitMac = visitMac;
	}

	public AdvertShare getAdvertShare() {
		return advertShare;
	}

	public void setAdvertShare(AdvertShare advertShare) {
		this.advertShare = advertShare;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}
}
