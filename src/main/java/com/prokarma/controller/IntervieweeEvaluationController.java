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

import com.prokarma.model.IntervieweeEvaluation;
import com.prokarma.service.IntervieweeEvaluationService;

@Controller
@RequestMapping("/interviewee-evaluation")
public class IntervieweeEvaluationController {

	@Autowired
	private IntervieweeEvaluationService intervieweeEvaluationService;

	private static final Logger logger = LoggerFactory.getLogger(IntervieweeEvaluationController.class);

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> intervieweeEvaluations(
			@RequestBody List<IntervieweeEvaluation> intervieweeEvaluations) {
		try {
			logger.info("posting interviewee evaluations {}", intervieweeEvaluations);
			//publishing to rabbit mq
			intervieweeEvaluationService.postIntervieweeEvaluationList(intervieweeEvaluations);
			
			return new ResponseEntity<String>("Posted successfully to the queue ", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("exception occured while posting interviewee evaluations {}", e);
			return new ResponseEntity<String>("Unable to post interviewee evaluations to queue: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "interviewee-id/{intervieweeId}", method = RequestMethod.GET)
	public ResponseEntity<?> intervieweeEvaluations(@PathVariable Integer intervieweeId) {
		logger.info("Getting interviewee evaluation details for intervieweeId {}", intervieweeId);
		List<IntervieweeEvaluation> intervieweeEvaluations = intervieweeEvaluationService.fetchIntervieweeEvalutions(intervieweeId);
		if (!CollectionUtils.isEmpty(intervieweeEvaluations)) {
			return new ResponseEntity<List<IntervieweeEvaluation>>(intervieweeEvaluations, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Unable to retrieve interview evaluations", HttpStatus.NOT_FOUND);
	}

}
