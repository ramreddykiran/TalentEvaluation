package com.prokarma.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prokarma.model.JobProfile;

@Repository
public class JobProfileDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void save(JobProfile jobProfile) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(jobProfile);
	}

}
