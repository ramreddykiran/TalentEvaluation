package com.prokarma.jobprofile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.builders.JobProfileBuilder;
import com.prokarma.controller.JobProfileController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-beans-test.xml", "classpath:test-db.xml" })
public class JobProfileIntegrationTest {

	@Autowired
	private JobProfileController jobProfileController;

	@Test
	public void shouldPostJobProfileSuccessfully() throws Throwable {
		assertEquals(HttpStatus.OK, jobProfileController
				.jobProfile(new JobProfileBuilder().withId(32121).withName("testprofile").build()).getStatusCode());
	}
	
}

/*
 * private MockMvc mockMvc;
 * 
 * @Before public void setUp() { mockMvc =
 * MockMvcBuilders.standaloneSetup(JobProfileController.class).build(); }
 */

/*
 * Gson gson = new Gson(); String json = gson.toJson(new
 * JobProfileBuilder().withId(32121).withName("testprofile").build());
 * mockMvc.perform(post("/resources/job-profile").contentType(MediaType.
 * APPLICATION_JSON).content(json)) .andExpect(status().isOk());
 */