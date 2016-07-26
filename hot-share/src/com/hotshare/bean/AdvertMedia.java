package com.hotshare.bean;

import java.util.Date;


/**
 * 前端广告媒体Bean
 * 
 * @author gkx
 * @version 1.0.0
 * @2016-8-1 上午9:26:37
 */
public class AdvertMedia {

	private int advertMediaId;  //广告媒体id

	private String imgUrl;//图片地址

	private String videoUrl;//视频地址
	
	private short type;  //多媒体类型  0图片  1视频
	
	private short isTop;  //0不置顶,1置顶
	
	private short state;	//状态
	
	private Date createTime;//发布时间
	
	private Advert advert; //所属广告
	

	public int getAdvertMediaId() {
		return advertMediaId;
	}

	public void setAdvertMediaId(int advertMediaId) {
		this.advertMediaId = advertMediaId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public short getIsTop() {
		return isTop;
	}

	public void setIsTop(short isTop) {
		this.isTop = isTop;
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

	public Advert getAdvert() {
		return advert;
	}

	public void setAdvert(Advert advert) {
		this.advert = advert;
	}
}
