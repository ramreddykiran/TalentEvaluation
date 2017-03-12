package com.prokarma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TECHNOLOGIES")
public class Technology {
	@Id
	@Column(name="id")
	private Integer id;
	private String name;
	private String description;
	private Integer jobProfileId;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getJobProfileId() {
		return jobProfileId;
	}
	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}

	@Override
	public String toString() {
		return "Technology [id=" + id + ", name=" + name + ", description=" + description + ", jobProfileId="
				+ jobProfileId + "]";
	}

}
