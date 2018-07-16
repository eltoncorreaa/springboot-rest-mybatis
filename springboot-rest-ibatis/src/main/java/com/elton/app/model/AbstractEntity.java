package com.elton.app.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractEntity {

	private Long id;
	private Integer version;
	private Date creationTime;
	private Date lastUpdateTime;
}
