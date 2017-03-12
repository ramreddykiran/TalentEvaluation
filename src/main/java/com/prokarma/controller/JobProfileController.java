package com.prokarma.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prokarma.model.JobProfile;
import com.prokarma.service.JobProfileService;

@Controller
@RequestMapping("/job-profile")
public class JobProfileController {
	
	@Autowired
	private JobProfileService jobProfileService;
	
	private static final Logger logger = LoggerFactory.getLogger(JobProfileController.class);
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> jobProfile(@RequestBody JobProfile jobProfile) {
		logger.info("posting job profile details");
		try{
			jobProfileService.saveJobProfile(jobProfile);
			return new ResponseEntity<String>(" job profile posted successfully", HttpStatus.OK);
		}catch(Exception e) {
			logger.error("unable to post job profile details");
			return new ResponseEntity<String>(" Unable to post job profile "+e, HttpStatus.BAD_REQUEST);
		} 
	}

}
