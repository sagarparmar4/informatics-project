package com.projects.informatics.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "color")
public class Color extends AbstractEntity {

	@Column
	@NotNull
	private String name;

	@Column
	@NotNull
	private String label;

	public Color() {
		super();
	}

	public Color(String name, String label) {
		super();
		this.name = name;
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
