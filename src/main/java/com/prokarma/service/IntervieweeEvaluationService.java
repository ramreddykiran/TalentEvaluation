package com.prokarma.service;

import java.util.List;

import javax.transaction.Transactional;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.prokarma.controller.IntervieweeEvaluationController;
import com.prokarma.dao.IntervieweeEvaluationDAO;
import com.prokarma.exception.InvalidInputException;
import com.prokarma.model.IntervieweeEvaluation;
import com.prokarma.service.consume.InterviewEvaluationConsumer;
import com.prokarma.service.publish.IntervieweeEvaluationPublisher;
import com.prokarma.service.xml.XmlPreparator;

@Service
public class IntervieweeEvaluationService {

	@Autowired
	private IntervieweeEvaluationPublisher intervieweeEvaluationPublisher;
	
	@Autowired
	private InterviewEvaluationConsumer interviewEvaluationConsumer;

	@Autowired
	private XmlPreparator xmlPreparator;

	private static final Logger logger = LoggerFactory.getLogger(IntervieweeEvaluationController.class);

	@Autowired
	private IntervieweeEvaluationDAO intervieweeEvaluationDAO;

	public void postIntervieweeEvaluationList(List<IntervieweeEvaluation> intervieweeEvaluations)
			throws InvalidInputException {
		if (!CollectionUtils.isEmpty(intervieweeEvaluations)) {
			List<IntervieweeEvaluation> list = getInterviewEvaluationsList(intervieweeEvaluations);
			for (IntervieweeEvaluation intervieweeEvaluation : list) {
				String xml = prepareXmlsForIntervieweeEvaluations(intervieweeEvaluation);
				intervieweeEvaluationPublisher.publish(xml);
			}
		} else {
			throw new InvalidInputException("Interviewee evaluations list is empty ");
		}
	}

	@Transactional
	public void save(List<IntervieweeEvaluation> intervieweeEvaluations) {
		intervieweeEvaluationDAO.save(intervieweeEvaluations);
		logger.info("saved interviewee evaluations successfully");
	}

	private List<IntervieweeEvaluation> getInterviewEvaluationsList(List<IntervieweeEvaluation> intervieweeEvaluations) {
		ObjectMapper mapper = new ObjectMapper();
		List<IntervieweeEvaluation> list = Lists.newArrayList();
		try {
			list = mapper.readValue(mapper.writeValueAsString(intervieweeEvaluations),
					new TypeReference<List<IntervieweeEvaluation>>() {
					});
		} catch (Exception e) {
			logger.error("Unable to convert request to list {}", intervieweeEvaluations);
		}
		return list;
	}

	private String prepareXmlsForIntervieweeEvaluations(IntervieweeEvaluation intervieweeEvaluation) {
		return xmlPreparator.prepareXml(intervieweeEvaluation);
	}

	@Transactional
	public List<IntervieweeEvaluation> fetchIntervieweeEvalutions(Integer intervieweeId) {
		return intervieweeEvaluationDAO.fetchIntervieweeEvaluations(intervieweeId);
	}
	
	
	public void getInterviewEvaluationsFromQueueAndSave() {
		List<IntervieweeEvaluation> intervieweeEvaluations = interviewEvaluationConsumer.consumeXmls();
		if (!CollectionUtils.isEmpty(intervieweeEvaluations)) {
		//	save(intervieweeEvaluations);
		}
	}

}
