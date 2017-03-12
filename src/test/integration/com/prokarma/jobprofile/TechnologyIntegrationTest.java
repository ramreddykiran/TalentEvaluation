package com.prokarma.jobprofile;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.builders.TechnologyBuilder;
import com.prokarma.controller.TechnologyController;
import com.prokarma.model.Technology;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-beans-test.xml", "classpath:test-db.xml" })
public class TechnologyIntegrationTest {
	
	@Autowired
	private TechnologyController technologyController;
	
	//Test-1
	@Test
	public void shouldDeleteTechnologyForGivenId() {
		ResponseEntity<String> response = technologyController.removeTechnology(14243);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	//test--2
	@Test
	public void shouldSaveTechnologyDetails() {
		Technology technology =buildTechnology(14243, "Core java", "Collections,Exception Handling", 32121);
		
		assertEquals(HttpStatus.OK, technologyController.save(technology).getStatusCode());
	}
	
	//test--3
	@Test
	public void shouldUpdateTechnologyDetails() {
		ResponseEntity<?> response = technologyController
				.update(buildTechnology(14243, "Core Java",null, 32121));
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Technology technology = (Technology) response.getBody();
		assertThat(technology.getId(),is(14243));
		assertThat(technology.getName(),is("Core Java"));
		assertThat(technology.getJobProfileId(),is(32121));
		assertNull(technology.getDescription());
	}
	
	//test--4
	@Test
	public void shouldGetTechnologyDetailsById() {
		ResponseEntity<?> response = technologyController.retrieveTechnology(14243);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Technology technology = (Technology) response.getBody();
		assertThat(technology.getId(),is(14243));
		assertThat(technology.getName(),is("Core Java"));
		assertThat(technology.getJobProfileId(),is(32121));
		//assertNotNull(technology.getDescription());
	}
	
	private Technology buildTechnology(Integer id, String name, String description, Integer jProfileId) {
		return new TechnologyBuilder().withId(id).withName(name)
				.withJobDescription(description).withJobProfileId(jProfileId).build();
	}
	
	@Test
	public void shouldRetrieveTechnologiesForJobProfileId() {
		ResponseEntity<?> response = technologyController.retrieveTechnologies(32121);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		List<Technology> technologies = (List<Technology>) response.getBody();
		assertEquals(2, technologies.size());
	}
	
	

}
