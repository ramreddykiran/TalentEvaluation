package com.prokarma.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.prokarma.builders.TechnologyBuilder;
import com.prokarma.dao.TechnologyDAO;
import com.prokarma.exception.InvalidInputException;
import com.prokarma.model.Technology;

@RunWith(MockitoJUnitRunner.class)
public class TechnologyServiceTest {
	
	@InjectMocks
	private TechnologyService technologyService;
	
	@Mock
	private TechnologyDAO technologyDAO;
	
	@Test
	public void shouldSaveTechnologyDetailsForValidInput() throws Exception {
		technologyService.save(buildTechnology(499, "Core java", "Collections,Exception Handling", 43543));
		verify(technologyDAO).save(any(Technology.class));
	}
	
	@Test
	public void shouldSaveTechnologyDetailsWithoutDescription() throws Exception {
		technologyService.save(buildTechnology(499, "Core java", null, 43543));
		verify(technologyDAO).save(any(Technology.class));
	}

	private Technology buildTechnology(Integer id, String name, String description, Integer jProfileId) {
		return new TechnologyBuilder().withId(id).withName(name)
				.withJobDescription(description).withJobProfileId(jProfileId).build();
	}
	
	@Test(expected = InvalidInputException.class)
	public void shouldThrowExceptionWhenJobProfileIdIsNotPresent() throws Exception{
		technologyService.save(buildTechnology(499, "Core java", "Collections,Exception Handling", null));
	}
	
	@Test(expected = InvalidInputException.class)
	public void shouldThrowExceptionWhenTechnologyIdIsNotPresent() throws Exception{
		technologyService.save(buildTechnology(null, "Core java",  "Collections,Exception Handling" , 8888));
	}
	
	@Test(expected = InvalidInputException.class)
	public void shouldThrowExceptionWhenTechnologyNameIsNotPresent() throws Exception{
		technologyService.save(buildTechnology(499, null, "Collections,Exception Handling", null));
	}
	
	@Test
	public void shouldGetTechnologyDetailsForGivenId() {
		when(technologyDAO.getTechnologyDetails(anyInt())).thenReturn(new Technology());
		assertNotNull(technologyService.getTechnologyDetails(2121));
	}

}
