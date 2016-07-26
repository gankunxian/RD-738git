package com.hotshare.bean;

import java.util.Date;

/**
 * @author lhzh
 * @version 创建时间：2014-8-19 下午07:37:25 系统消息
 */
public class SystemMessage {

	private int id;
	 /**标题**/
	private String title;
	 /**内容**/
	private String content;
	 /**创建时间**/
	private Date createTime;
	 /**备注**/
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
