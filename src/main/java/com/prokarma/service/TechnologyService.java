package com.prokarma.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;
import com.prokarma.dao.TechnologyDAO;
import com.prokarma.exception.InvalidInputException;
import com.prokarma.model.Technology;

@Service
public class TechnologyService {
	
	@Autowired
	private TechnologyDAO technologyDAO;

	@Transactional
	public void save(Technology technology) throws InvalidInputException {
		if(isValid(technology)) {
			technologyDAO.save(technology);
		} else {
			throw new InvalidInputException("Invalid techonology details.mandatory fields id: "+technology.getId()+ " name: "+technology.getName()
			+" jobProfileId: "+technology.getJobProfileId());
		}
	}

	private boolean isValid(Technology technology) {
		if(Optional.fromNullable(technology.getId()).isPresent() && StringUtils.isNotBlank(technology.getName())
				&&Optional.fromNullable(technology.getJobProfileId()).isPresent())  {
			return true;
		}
		return false;
	}

	@Transactional
	public Technology getTechnologyDetails(Integer id) {
		return technologyDAO.getTechnologyDetails(id);
	}

	@Transactional
	public Technology updateTechnology(Technology technology) throws InvalidInputException {
		if(isValid(technology)){
			return technologyDAO.update(technology);
		} else {
			throw new InvalidInputException("Invalid techonology details.mandatory fields id: "+technology.getId()+ " name: "+technology.getName()
			+" jobProfileId: "+technology.getJobProfileId());
		}
	}

	@Transactional
	public void removeTechnology(Integer id) {
		technologyDAO.remove(id);
		
	}

	@Transactional
	public List<Technology> retrieveTechnologies(Integer jobProfileId) {
		return technologyDAO.fetchTechnologies(jobProfileId);
	}

}
