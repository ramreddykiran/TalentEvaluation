package com.prokarma.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prokarma.builders.TechnologyBuilder;
import com.prokarma.exception.InvalidInputException;
import com.prokarma.model.Technology;
import com.prokarma.service.TechnologyService;

@RunWith(MockitoJUnitRunner.class)
public class TechnologyControllerTest {
	
	@InjectMocks
	private TechnologyController technologyController;
	
	@Mock
	private TechnologyService technologyService;
	
	@Test
	public void shouldSaveTechnologyDetails() throws Throwable {
		assertEquals(HttpStatus.OK, technologyController.save(new TechnologyBuilder().build()).getStatusCode());
		verify(technologyService).save(any(Technology.class));
	}
	
	@Test
	public void shouldReturnhttpStatusBadRequestdWhenInvalidThechnologyDetailsPassed() throws Throwable {
		doThrow(new InvalidInputException()).when(technologyService).save(any(Technology.class));
		assertEquals(HttpStatus.BAD_REQUEST, technologyController.save(new Technology()).getStatusCode());
	}
	
	@Test
	public void shouldGetTechnolgyDetailsForId() {
		when(technologyService.getTechnologyDetails(anyInt())).thenReturn(new Technology());
		assertEquals(HttpStatus.OK, technologyController.retrieveTechnology(32132).getStatusCode());
	}
	
	@Test
	public void shouldReturnHttpStatusNotFoundWhenThechnologyIsNotPresent() {
		ResponseEntity<?> response = technologyController.retrieveTechnology(32132);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(technologyService).getTechnologyDetails(anyInt());
	}
	

}
