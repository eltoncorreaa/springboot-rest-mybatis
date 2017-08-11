package com.elton.app.model;

import java.util.Date;

public class AbstractEntity {

	private Long id;
	private Integer version;
	private Date creationTime;
	private Date lastUpdateTime;

	public Long getId() {
		return id;
	}
	public void setId(final Long id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(final Integer version) {
		this.version = version;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(final Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(final Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
