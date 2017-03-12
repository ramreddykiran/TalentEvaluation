package com.prokarma.builders;

import org.apache.commons.lang3.builder.Builder;

import com.prokarma.model.JobProfile;

public class JobProfileBuilder implements Builder<JobProfile>{
	
	private Integer id = 42314;
	private String name = "some name";

	public JobProfile build() {
		JobProfile jobProfile = new JobProfile();
		jobProfile.setId(id);
		jobProfile.setName(name);
		return jobProfile;
	}
	
	public JobProfileBuilder withId(Integer id) {
		this.id = id;
		return this;
	}
	
	public JobProfileBuilder withName(String name) {
		this.name = name;
		return this;
	}

}
