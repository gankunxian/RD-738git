package com.hotshare.bean;

import java.util.Date;


/**
 * 前端广告创意人媒体Bean
 * 
 * @author gkx
 * @version 1.0.0
 * @2016-8-1 上午9:26:37
 */
public class IdeaManMedia {

	private int ideaManMediaId;  //广告创意人媒体id

	private String imgUrl;//图片地址

	private String videoUrl;//视频地址
	
	private short type;  //多媒体类型  0图片  1视频
	
	private short state;	//状态
	
	private Date createTime;//发布时间
	
	private IdeaMan ideaMan; //所属广告创意人

	
	public int getIdeaManMediaId() {
		return ideaManMediaId;
	}

	public void setIdeaManMediaId(int ideaManMediaId) {
		this.ideaManMediaId = ideaManMediaId;
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

	public IdeaMan getIdeaMan() {
		return ideaMan;
	}

	public void setIdeaMan(IdeaMan ideaMan) {
		this.ideaMan = ideaMan;
	}
}
