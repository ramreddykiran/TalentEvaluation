package com.prokarma.controller;

import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.MethodInvokingRunnable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interviewee-evaluation-xml")
public class IntervieweeEvaluationSchedulerController {
	private static final String SCHEDULER_STOPPED_SUCCESSFULLY = "interviewee-evaluation-xml scheduler  stopped successfully";
	private static final String SCHEDULER_STARTED_SUCCESSFULLY = "interviewee-evaluation-xml scheduler  started successfully";
	private static final String SCHEDULER_ALREADY_STARTED = "interviewee-evaluation-xml scheduler  already started";
	private static final String SCHEDULER_ALREADY_STOPPED = "interviewee-evaluation-xml scheduler  already stopped";

	@Autowired
	private TaskScheduler intervieweeEvaluationResendScheduler;

	@Autowired
	private MethodInvokingRunnable intervieweeEvaluationResendTask;

	private static final Logger log = LoggerFactory.getLogger(IntervieweeEvaluationSchedulerController.class);

	private ScheduledFuture<?> scheduledFuture;

	@Value("${interviewee.evaluation.retry.poller.auto.startup}")
	private boolean pollerAutoStartUp;

	@Value("${interviewee.evaluation.retry.poller.cron.interval}")
	private String pollerCronInterval;

	@PostConstruct
	public void init() {
		if (pollerAutoStartUp) {
			startScheduler();
		}
	}

	@RequestMapping("/start")
	public ResponseEntity<String> startScheduler() {
		if (isSchedulerUp()) {
			log.info(SCHEDULER_ALREADY_STARTED);
			return new ResponseEntity<String>(SCHEDULER_ALREADY_STARTED, HttpStatus.OK);
		}
		scheduledFuture = intervieweeEvaluationResendScheduler.schedule(intervieweeEvaluationResendTask,
				new CronTrigger(pollerCronInterval));
		log.info(SCHEDULER_STARTED_SUCCESSFULLY);
		return new ResponseEntity<String>(SCHEDULER_STARTED_SUCCESSFULLY, HttpStatus.OK);
	}

	@RequestMapping("/stop")
	public ResponseEntity<String> stopScheduler() {
		if (!isSchedulerUp()) {
			log.info(SCHEDULER_ALREADY_STOPPED);
			return new ResponseEntity<String>(SCHEDULER_ALREADY_STOPPED, HttpStatus.OK);
		}
		if (isSchedulerUp()) {
			scheduledFuture.cancel(true);
			scheduledFuture = null;
			log.info(SCHEDULER_STOPPED_SUCCESSFULLY);
		}
		return new ResponseEntity<String>(SCHEDULER_STOPPED_SUCCESSFULLY, HttpStatus.OK);
	}

	public boolean isSchedulerUp() {
		return scheduledFuture != null && !scheduledFuture.isCancelled();
	}

}
