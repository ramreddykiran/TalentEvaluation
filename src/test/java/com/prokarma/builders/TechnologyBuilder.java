package com.prokarma.builders;

import org.apache.commons.lang3.builder.Builder;

import com.prokarma.model.Technology;

public class TechnologyBuilder implements Builder<Technology> {

	private Integer id = 11111;
	private String name = "testing tech";
	private String description = "testing desc";
	private Integer jobProfileId = 99999;

	public Technology build() {
		Technology technology = new Technology();
		technology.setId(id);
		technology.setName(name);
		technology.setDescription(description);
		technology.setJobProfileId(jobProfileId);
		return technology;
	}

	public TechnologyBuilder withId(Integer id) {
		this.id = id;
		return this;
	}

	public TechnologyBuilder withJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
		return this;
	}

	public TechnologyBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public TechnologyBuilder withJobDescription(String description) {
		this.description = description;
		return this;
	}

}
