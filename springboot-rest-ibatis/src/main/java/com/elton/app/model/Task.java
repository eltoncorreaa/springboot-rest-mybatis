package com.elton.app.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Task extends AbstractEntity{

	private String name;
	private Date startDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).append(getName())
				.append(getStartDate()).append(getStartDate()).toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		final Task rhs = (Task) obj;
		return new EqualsBuilder().append(getName(), rhs.getName()).append(getStartDate(), rhs.getStartDate()).isEquals();
	}
}