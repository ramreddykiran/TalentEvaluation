package com.prokarma.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;
import com.prokarma.dao.JobProfileDAO;
import com.prokarma.exception.InvalidInputException;
import com.prokarma.model.JobProfile;

@Service
public class JobProfileService {
	
	@Autowired
	private JobProfileDAO jobProfileDAO;

	@Transactional
	public void saveJobProfile(JobProfile jobProfile) throws InvalidInputException {
		if(isValid(jobProfile)) {
			jobProfileDAO.save(jobProfile);
		} else {
			throw new InvalidInputException("invalid input id: "+jobProfile.getId()+" and name: "+jobProfile.getName());
		}
	}

	private boolean isValid(JobProfile jobProfile) {
		if(StringUtils.isNotBlank(jobProfile.getName()) && Optional.fromNullable(jobProfile.getId()).isPresent()) {
			return true;
		}
		return false;
	}

}
