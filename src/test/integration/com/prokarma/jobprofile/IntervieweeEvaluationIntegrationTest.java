package com.prokarma.jobprofile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.prokarma.builders.IntervieweeEvaluationBuilder;
import com.prokarma.builders.IntervieweeEvaluationIdBuilder;
import com.prokarma.controller.IntervieweeEvaluationController;
import com.prokarma.model.IntervieweeEvaluation;
import com.prokarma.service.IntervieweeEvaluationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-beans-test.xml", "classpath:test-db.xml" })
public class IntervieweeEvaluationIntegrationTest {

	@Autowired
	private IntervieweeEvaluationController controller;
	
	@Autowired
	private IntervieweeEvaluationService intervieweeEvaluationService;

	@Test
	public void shouldPostInterviewEvaluationsDetails() {
		IntervieweeEvaluation obj1 = new IntervieweeEvaluationBuilder().withEvaluatorName("EvaluatorName1")
				.withIntervieweeEvaluationId(new IntervieweeEvaluationIdBuilder().withEvaluatorId(1000)
						.withIntervieweeId(9000).withInterviewRound(1).build()).build();
		IntervieweeEvaluation obj2 = new IntervieweeEvaluationBuilder().withEvaluatorName("EvaluatorName2")
				.withIntervieweeEvaluationId(new IntervieweeEvaluationIdBuilder().withEvaluatorId(1001)
						.withIntervieweeId(9000).withInterviewRound(2).build()).build();
		List<IntervieweeEvaluation> intervieweeEvaluations = Lists.newArrayList(obj1, obj2);
		ResponseEntity<String> response = controller.intervieweeEvaluations(intervieweeEvaluations);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void shouldRetrieveIntervieweeEvaluationDetailsForIntervieweeId() {
		ResponseEntity<?> response = controller.intervieweeEvaluations(9000);
		List<IntervieweeEvaluation> evaluationDetails = (List<IntervieweeEvaluation>) response.getBody();
		assertEquals(2, evaluationDetails.size());
		for (IntervieweeEvaluation entry : evaluationDetails) {
			assertNotNull(entry.getIntervieweeEvaluationId());
			assertNotNull(entry.getIntervieweeEvaluationId().getEvaluatorId());
			assertNotNull(entry.getIntervieweeEvaluationId().getIntervieweeId());
			assertNotNull(entry.getIntervieweeEvaluationId().getInterviewRound());
		}
	}
	
	@Test
	public void shouldGetInteviewEvaluationObjectsFromQueue() {
		intervieweeEvaluationService.getInterviewEvaluationsFromQueueAndSave();
		
	}

}
