package com.prokarma.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.prokarma.exception.NoDataFoundException;
import com.prokarma.model.Technology;

@Repository
public class TechnologyDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void save(Technology technology) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(technology);
	}

	public Technology getTechnologyDetails(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return (Technology) session.get(Technology.class, id);
	}

	public Technology update(Technology technology) {
		Session session = sessionFactory.getCurrentSession();
		session.update(technology);
		return (Technology) session.get(Technology.class, technology.getId());
	}

	public void remove(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Technology technology = (Technology) session.get(Technology.class, id);
		if(Optional.fromNullable(technology).isPresent()){
			session.delete(technology);
		} else {
			throw new NoDataFoundException("No technology found for the id: "+id);
		}
		
	}

	public List<Technology> fetchTechnologies(Integer jobProfileId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Technology> techonologies = session.createQuery(String.format("from Technology where jobprofileid=%d",jobProfileId)).list();
		// in above query, table name TECHNOLOGY should not be passed. java class name Technology should be passed
		return techonologies;
	}

}
