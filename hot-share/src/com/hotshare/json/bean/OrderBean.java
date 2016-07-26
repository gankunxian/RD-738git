/**
 * Program  : SortBean.java
 * Author   : lhzh
 * Create   : 2013-1-18 下午11:34:46
 */

package com.hotshare.json.bean;

/**
 * 接收datagrid传递的排序列和排序方法
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 下午11:34:46
 */
public class OrderBean {

	private String sort;

	private String order;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
