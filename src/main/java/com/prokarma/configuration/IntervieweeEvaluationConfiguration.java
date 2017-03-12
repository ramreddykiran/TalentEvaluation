package com.prokarma.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.MethodInvokingRunnable;

import com.prokarma.service.IntervieweeEvaluationService;

@Configuration
public class IntervieweeEvaluationConfiguration {
	
	@Autowired
	private IntervieweeEvaluationService intervieweeEvaluationService;
	
	@Bean
	public TaskScheduler intervieweeEvaluationResendScheduler() {
		return new ThreadPoolTaskScheduler();
	}
	
	@Bean
	public MethodInvokingRunnable intervieweeEvaluationResendTask() {
		MethodInvokingRunnable methodInvokingRunnable = new MethodInvokingRunnable();
		methodInvokingRunnable.setTargetObject(intervieweeEvaluationService);
		methodInvokingRunnable.setTargetMethod("getInterviewEvaluationsFromQueueAndSave");
		return methodInvokingRunnable;
		
	}
	

}
