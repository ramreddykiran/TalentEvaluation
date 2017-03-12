package com.prokarma.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;
import com.prokarma.dao.IntervieweeDAO;
import com.prokarma.exception.InvalidInputException;
import com.prokarma.model.Interviewee;

@Service
public class IntervieweeService {
	
	@Autowired
	private IntervieweeDAO intervieweeDAO;

	@Transactional
	public Interviewee retrieveIntervieweeDetails(Integer id) {
		return intervieweeDAO.fetchIntervieweeDetails(id);
	}

	@Transactional
	public void save(Interviewee interviewee) throws InvalidInputException {
		if (StringUtils.isNotBlank(interviewee.getName()) && Optional.fromNullable(interviewee.getId()).isPresent()
				&& Optional.fromNullable(interviewee.getJobProfileId()).isPresent()) {
			intervieweeDAO.save(interviewee);
		} else {
			throw new InvalidInputException("Please pass valid interviewee information");
		}
	}

}
