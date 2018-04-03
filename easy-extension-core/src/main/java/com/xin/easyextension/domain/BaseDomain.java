package com.xin.easyextension.domain;

import java.util.Date;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-03-27
* @version 1.0
* @Description
*/
public class BaseDomain {
	private Long id;
	private Date created;
	private Date lastUpdated;
	private String userId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
