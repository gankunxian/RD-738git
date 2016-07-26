package com.hotshare.bean;

/**
 * @author lhzh
 * @version 创建时间：2014-8-20 下午01:44:24
 *  系统消息用户关系表
 */
public class SystemMessageUser {
	private int id;

	private SystemMessage message;
	/** 发送人 **/
	private Manager sender;
	/** 接收人 **/
	private User accepter;
	/** 状态 **/
	private int state;

	public User getAccepter() {
		return accepter;
	}

	public void setAccepter(User accepter) {
		this.accepter = accepter;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SystemMessage getMessage() {
		return message;
	}

	public void setMessage(SystemMessage message) {
		this.message = message;
	}

	public Manager getSender() {
		return sender;
	}

	public void setSender(Manager sender) {
		this.sender = sender;
	}

}
