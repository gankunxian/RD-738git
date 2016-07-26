package com.hotshare.bean;


/**
 * 模块bean
 * ClassName: Module <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: 2014年8月5日 下午2:29:57 <br/>  
 *  
 * @author lhzh  
 * @version   
 * @since JDK 1.6
 */
public class Module {
	
	/** 模块的id */
	private Integer id ;
	
	/** 模块的父id,根模块的id为 0 */
	private Integer parentID ;
	
	/** 模块的名字 */
	private String name ;
	
	/** 模块的相对地址(添加模块时固定，不可修改) */
	private String url ;
	
	
	/** 模块功能 描述 */
	private String moduleDesc ;
	
	
	/** 模块代号(代码处理用如:ismp-app,ismp-set,app,icv等) */
	private String moduleCode ;
	

	public Module() {
		super();
	}


	public Module(Integer id, Integer parentID, String name, String url,
			String moduleDesc, String moduleCode) {
		super();
		this.id = id;
		this.parentID = parentID;
		this.name = name;
		this.url = url;
		this.moduleDesc = moduleDesc;
		this.moduleCode = moduleCode;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getParentID() {
		return parentID;
	}


	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getModuleDesc() {
		return moduleDesc;
	}


	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}


	public String getModuleCode() {
		return moduleCode;
	}


	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}


	@Override
	public String toString() {
		return "ModuleBean [moduleCode=" + moduleCode + "]";
	}


	@Override
	public boolean equals(Object obj) {
		return obj.toString().equals(this.toString());
	}
}
