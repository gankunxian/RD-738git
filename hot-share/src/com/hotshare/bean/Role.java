package com.hotshare.bean;

/**
 * @author lhzh
 * @version 创建时间：2014-8-11 下午01:59:43 类说明
 */
public class Role {
	/** 角色id */
	private Integer id;

	/** 角色名字 */
	private String name;

	/** 角色 描述 */
	private String groupDesc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

}
