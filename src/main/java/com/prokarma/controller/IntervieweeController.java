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
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Optional;
import com.prokarma.model.Interviewee;
import com.prokarma.service.IntervieweeService;

@Controller
@RequestMapping("/interviewee")
public class IntervieweeController {
	
	@Autowired
	private IntervieweeService intervieweeService;
	
	private static final Logger logger = LoggerFactory.getLogger(IntervieweeController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> intervieweeDetails(@RequestParam(value="id") Integer id) {
		logger.info("Getting interviewee details for id {}",id);
		Interviewee interviewee = intervieweeService.retrieveIntervieweeDetails(id);
		if (Optional.fromNullable(interviewee).isPresent()) {
			return new ResponseEntity<Interviewee>(interviewee, HttpStatus.OK);
		}
		return new ResponseEntity<String>("No Interviewee details found", HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ResponseEntity<String> submitInterviewee(@RequestBody Interviewee interviewee){
		logger.info("Posting interviewee details");
		try{
			intervieweeService.save(interviewee);
			return new ResponseEntity<String>("Interviewee details saved successfully", HttpStatus.OK);
		} catch(Exception e) {
			logger.error("exception occured while psoting interviewee details {}",e);
			return new ResponseEntity<String>("Unable to save interviewee "+e, HttpStatus.BAD_REQUEST);
		}
		
	}
	

}
