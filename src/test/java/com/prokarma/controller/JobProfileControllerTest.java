package com.prokarma.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.prokarma.exception.InvalidInputException;
import com.prokarma.model.JobProfile;
import com.prokarma.service.JobProfileService;

@RunWith(MockitoJUnitRunner.class)
public class JobProfileControllerTest {
	
	@InjectMocks
	private JobProfileController jobProfileController;
	
	@Mock
	private JobProfileService jobProfileService;
	
	
	@Test
	public void shouldPostJobProfileSuccessfully() throws Throwable {
		assertEquals(HttpStatus.OK, jobProfileController.jobProfile(new JobProfile()).getStatusCode());
		verify(jobProfileService).saveJobProfile(any(JobProfile.class));
	}
	
	@Test
	public void shouldReturnBadRequestWhenInvalidInputPosted() throws Throwable {
		doThrow(new InvalidInputException()).when(jobProfileService).saveJobProfile(any(JobProfile.class));
		assertEquals(HttpStatus.BAD_REQUEST, jobProfileController.jobProfile(new JobProfile()).getStatusCode());
	}
	
	@Test
	public void shouldGetTechnologiesForJobProfile(){
		
	}
	
}

/*private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(JobProfileController.class).build();
	}*/
/*Gson gson = new Gson();
		String jsonRequest = gson.toJson(new JobProfileBuilder().withId(32121).withName("testprofile").build());
		mockMvc.perform(post("/resources/job-profile").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());*/