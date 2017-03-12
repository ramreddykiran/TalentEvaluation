package com.prokarma.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prokarma.model.Interviewee;

@Repository
public class IntervieweeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Interviewee fetchIntervieweeDetails(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return (Interviewee) session.get(Interviewee.class, id);
	}

	public void save(Interviewee interviewee) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(interviewee);
	}

}
