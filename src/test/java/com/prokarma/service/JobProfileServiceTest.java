package com.prokarma.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.prokarma.builders.JobProfileBuilder;
import com.prokarma.dao.JobProfileDAO;
import com.prokarma.exception.InvalidInputException;
import com.prokarma.model.JobProfile;

@RunWith(MockitoJUnitRunner.class)
public class JobProfileServiceTest {

	@InjectMocks
	private JobProfileService jobProfileService;

	@Mock
	private JobProfileDAO jobProfileDAO;

	@Test(expected = InvalidInputException.class)
	public void shouldThrowExceptionWhenJobProfileNameIsNull() throws Throwable{
		jobProfileService.saveJobProfile(new JobProfileBuilder().withName(null).build());
	}
	
	@Test(expected = InvalidInputException.class)
	public void shouldThrowExceptionWhenJobProfileIdIsNull() throws Throwable{
		jobProfileService.saveJobProfile(new JobProfileBuilder().withId(null).build());
	}
	
	
	@Test
	public void shouldSaveJobProfileWhenJobProfileHasValidData() throws Throwable {
		jobProfileService.saveJobProfile(new JobProfileBuilder().withId(23412).withName("java").build());
		verify(jobProfileDAO).save(any(JobProfile.class));;
	}

}
