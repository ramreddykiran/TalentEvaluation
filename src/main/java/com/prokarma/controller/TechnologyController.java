package com.prokarma.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Optional;
import com.prokarma.model.Technology;
import com.prokarma.service.TechnologyService;

@Controller
@RequestMapping("/technology")
public class TechnologyController {
	
	@Autowired
	private TechnologyService technologyService;
	
	private static final Logger logger = LoggerFactory.getLogger(JobProfileController.class);

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Technology technology) {
		logger.info("posting technology details");
		try {
			technologyService.save(technology);
			return new ResponseEntity<String>("Technology details are saved successfully", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("unable to post technology details");
			return new ResponseEntity<String>("Unable to save technology details "+e, HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	//@ResponseBody
	public ResponseEntity<?> retrieveTechnology(@PathVariable Integer id) {
		logger.info("Retrieving technology details for id {}",id);
		Technology technology = technologyService.getTechnologyDetails(id);
		if(Optional.fromNullable(technology).isPresent()){
			return new ResponseEntity<Technology>(technology, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No technology found for id :"+id, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody Technology technology) {
		logger.info("updating technology details {}",technology);
		try {
			Technology technologyResponse = technologyService.updateTechnology(technology);
			return new ResponseEntity<Technology>(technologyResponse,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("unable to update technology details");
			return new ResponseEntity<String>("Unable to update technology details "+e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> removeTechnology(@PathVariable Integer id) {
		logger.info("removing technology details for id {}",id);
		try{
			technologyService.removeTechnology(id);
			return new ResponseEntity<String>("Removed technology successfully", HttpStatus.OK);
		}catch(Exception e){
			logger.error("unable to delete technology");
			return new ResponseEntity<String>("Unable to remove technology "+e, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value="jobprofileid/{jobProfileId}", method=RequestMethod.GET)
	public ResponseEntity<?> retrieveTechnologies(@PathVariable Integer jobProfileId) {
		logger.info("Retrieving technologies for job profile id {}",jobProfileId);
		List<Technology> technologies = technologyService.retrieveTechnologies(jobProfileId);
		if(!CollectionUtils.isEmpty(technologies)) {
			return new ResponseEntity<List<Technology>>(technologies, HttpStatus.OK);
		}
		return new ResponseEntity<String>("No technologies found for jobProfileId :"+jobProfileId, HttpStatus.NOT_FOUND);
	}
	
	

}
