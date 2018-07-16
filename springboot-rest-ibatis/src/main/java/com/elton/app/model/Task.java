package com.elton.app.model;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class Task extends AbstractEntity{

	private String name;
	private Date startDate;
}