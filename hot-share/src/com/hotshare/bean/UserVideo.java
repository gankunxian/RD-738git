package com.hotshare.bean;

import java.util.Date;


/**
 * 前端用户上传审核星级视频Bean
 * 
 * @author gkx
 * @version 1.0.0
 * @2016-8-1 上午9:26:37
 */
public class UserVideo {

	private int userVideoId;  //审核视频id

	private String videoUrl;//视频路径

	private User user;  //所属用户

	private Date createTime;//创建时间
	
	private short state;//状态 0正常  1未审核  2无效   -1删除
	
	

	public int getUserVideoId() {
		return userVideoId;
	}

	public void setUserVideoId(int userVideoId) {
		this.userVideoId = userVideoId;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
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
